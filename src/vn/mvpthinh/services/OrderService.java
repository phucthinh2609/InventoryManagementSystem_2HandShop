package vn.mvpthinh.services;

import com.sun.org.apache.xpath.internal.operations.Or;
import vn.mvpthinh.model.*;
import vn.mvpthinh.utils.CSVUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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
    public List<Order> findAllByType(OrderType type) {
        List<Order> orders = findAll();
        List<Order> newOrders = new ArrayList<>();
        for (Order order: orders){
            if (order.getType() == type)
                newOrders.add(order);
        }
        return newOrders;
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
        newOrder.setUpdatedAt(Instant.now());
        newOrder.setType(OrderType.IN);
        List<Order> orders = findAll();
        for (Order order : orders) {
            if (order.getId().equals(newOrder.getId())) {
                order.setStatus(OrderStatus.PAID);

                double grandTotal = 0;
                List<OrderItem> orderItems = newOrder.getOrderItems();
                for (OrderItem orderItem : orderItems) {
                    int quantity = orderItem.getQuantity();
                    double price = orderItem.getPrice();

                    grandTotal += (quantity * orderItem.getPrice());

                    Long itemId = orderItem.getItemId();
                    if (itemId == null) {
                        Item item = orderItem.getItem();
                        item.setId(itemId = System.currentTimeMillis());
                        orderItem.setItemId(itemId);
                        item.setOrderId(newOrder.getId());
                        item.setProductId(orderItem.getProductId());
                        //  item.setPrice(orderItem.getPrice());
                        // item.setQuantity(orderItem.getQuantity());
                        itemService.add(item);

                    }
                    itemService.increaseItemAvailable(itemId, quantity);
                    itemService.updateItemPrice(itemId, price);
                    orderItemService.add(orderItem);
                }
                order.setGrandTotal(grandTotal);

                String content = newOrder.getContent();
                if (content != null && !content.isEmpty())
                    order.setContent(content);

                CSVUtils.write(PATH, orders);
                break;
            }
        }
    }

    @Override
    public void salesStock(Order newOrder) {
        newOrder.setUpdatedAt(Instant.now());
        newOrder.setType(OrderType.OUT);
        List<Order> orders = findAll();
        for (Order order : orders) {
            if (order.getId().equals(newOrder.getId())) {
                order.setStatus(OrderStatus.COMPLETE);

                double grandTotal = 0;
                List<OrderItem> orderItems = newOrder.getOrderItems();
                for (OrderItem orderItem : orderItems) {
                    int quantity = orderItem.getQuantity();
//                    Item item = orderItem.getItem();
                    int sold = orderItem.getQuantity();
                    grandTotal += (quantity * orderItem.getPrice());
                    Long itemId = orderItem.getItemId();
//                    if (itemId == null) {
//                        item.setId(itemId = System.currentTimeMillis());
//                        orderItem.setItemId(itemId);
//                        item.setOrderId(newOrder.getId());
//                        item.setProductId(orderItem.getProductId());
//                        item.setPrice(orderItem.getPrice());
//                        item.setQuantity(orderItem.getQuantity());
//                        itemService.add(item);
//                    }
                    itemService.updateItemSold(itemId, sold);
                    orderItemService.add(orderItem);
                }
                order.setGrandTotal(grandTotal);

                String content = newOrder.getContent();
                if (content != null && !content.isEmpty())
                    order.setContent(content);

                CSVUtils.write(PATH, orders);
                break;
            }
        }
    }

    @Override
    public Order createNewOrder(OrderType type) {
        Long id = System.currentTimeMillis() / 1000;
        OrderStatus status = inputOrderStatus(type);
        Order order = new Order(id, userService.getCurrentUser().getId(), type, OrderStatus.NEW);
        add(order);
        return order;
    }

    private OrderStatus inputOrderStatus(OrderType type) {
        OrderStatus result = OrderStatus.NEW;
        Scanner scanner = new Scanner(System.in);
        switch (type){
            case IN:
                System.out.println("= = SET STATUS = =");
                System.out.println("∥   1. BILLED    ∥");
                System.out.println("∥   2. PAID      ∥");
                System.out.println("= = = =  = = = = ");
                System.out.println("Chọn Status: ");
                System.out.print(" ⭆ ");
                int option = scanner.nextInt();
                scanner.nextLine();
                switch (option) {
                    case 1:
                        result = OrderStatus.BILLED;
                        break;
                    case 2:
                        result = OrderStatus.PAID;
                        break;
                    default:
                        System.err.println("Nhập không đúng! Vui lòng nhập lại");
                }
                break;
                case OUT:
                    System.out.println("= = SET STATUS = =");
                    System.out.println("∥   1. SHIPPED    ∥");
                    System.out.println("∥   2. DELIVERED  ∥");
                    System.out.println("∥   3. COMPLETE   ∥");
                    System.out.println("∥   4. FAILED     ∥");
                    System.out.println("= = = =  = = = = ");
                    System.out.println("Chọn Status: ");
                    System.out.print(" ⭆ ");
                    int choose = scanner.nextInt();
                    scanner.nextLine();
                    switch (choose) {
                        case 1:
                            result = OrderStatus.SHIPPED;
                            break;
                        case 2:
                            result = OrderStatus.DELIVERED;
                            break;
                        case 3:
                            result = OrderStatus.COMPLETE;
                            break;
                        case 4:
                            result = OrderStatus.FAILED;
                            break;
                        default:
                            System.err.println("Nhập không đúng! Vui lòng nhập lại");
                    }
                    break;
        }
        return result;
    }

    @Override
    public Order findById(Long id) {
        List<Order> orders = findAll();
        for (Order order : orders) {
            if (order.getId().equals(id))
                return order;
        }
        return null;
    }

    @Override
    public List<Order> findByUserId(Long userId) {
        List<Order> newOrders = new ArrayList<>();
        for (Order order : findAll()) {
            if (order.getId().equals(userId))
                newOrders.add(order);
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
