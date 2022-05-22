package vn.mvpthinh.services;

import vn.mvpthinh.model.*;
import vn.mvpthinh.utils.CSVUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class OrderService implements IOrderService {
    private final static String PATH = "data/orders.csv";
    IUserService userService;
    IItemService itemService;
    IOrderItemService orderItemService;
    private static OrderService instance;

    private OrderService() {
        userService = UserService.getInstance();
        itemService = ItemService.getInstance();
        orderItemService = OrderItemService.getInstance();
    }

    public static OrderService getInstance() {
        if (instance == null) instance = new OrderService();
        return instance;
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        List<String> records = CSVUtils.read(PATH);
        for (String record : records) {
            orders.add(Order.parse(record));
        }
        return orders;
    }

    @Override
    public void add(Order newOrder) {
        newOrder.setCreatedAt(Instant.now());
        List<Order> orders = findAll();
        orders.add(newOrder);
        CSVUtils.write(PATH, orders);
    }


    @Override
    public void purchaseStock(Order newOrder) {
        newOrder.setUpdateAt(Instant.now());
        newOrder.setType(OrderType.IN);
        List<Order> orders = findAll();
        for (Order order : orders) {
            if (order.getId().equals(newOrder.getId())) {
                order.setStatus(OrderStatus.DELIVERED);

                double grandTotal = 0;
                List<OrderItem> orderItems = newOrder.getOrderItems();
                for (OrderItem orderItem : orderItems) {
                    int quantity = orderItem.getQuantity();

                    grandTotal += (quantity * orderItem.getPrice());

                    Long itemId = orderItem.getItemId();
                    if (itemId == null) {
                        Item item = orderItem.getItem();
                        item.setId(itemId = System.currentTimeMillis());
                        orderItem.setItemId(itemId);
                        item.setOrderId(newOrder.getId());
                        item.setProductId(orderItem.getProductId());
                        itemService.add(item);

                    }
                    itemService.increaseItemAvailable(itemId, quantity);
                    orderItemService.add(orderItem);
                }
                order.setGrandTotal(grandTotal);

                String content = newOrder.getContent();
                if (content != null && !content.isEmpty()) order.setContent(content);

                CSVUtils.write(PATH, orders);
                break;
            }
        }

    }

    @Override
    public Order createNewOrder(OrderType type) {
        Long id = System.currentTimeMillis() / 1000;
        Order order =  new Order(id, userService.getCurrentUser().getId(), type, OrderStatus.NEW);
        add(order);
        return order;
    }


    @Override
    public Order findById(Long id) {
        List<Order> orders = findAll();
        for (Order order : orders) {
            if (order.getId().equals(id)) return order;
        }
        return null;
    }

    @Override
    public List<Order> findByUserId(Long id) {
        List<Order> newOrders = new ArrayList<>();
        for (Order order : findAll()) {
            if (order.getId().equals(id)) newOrders.add(order);
        }
        return newOrders;
    }

    @Override
    public boolean existById(Long id) {
        return findById(id) != null;
    }

    @Override
    public void deleteById(Long id) {
        List<Order> orders = findAll();

        //class vo danh
        orders.removeIf(new Predicate<Order>() {
            @Override
            public boolean test(Order order) {
                return order.getId().equals(id);
            }
        });
        CSVUtils.write(PATH, orders);
    }
}
