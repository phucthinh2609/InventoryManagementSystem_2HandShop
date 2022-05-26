package vn.mvpthinh.services;

import vn.mvpthinh.model.OrderItem;
import vn.mvpthinh.utils.CSVUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class OrderItemService implements IOrderItemService {
    private final static String PATH = "data/order-items.csv";

    private static OrderItemService instance;

    private OrderItemService() {
    }

    public static OrderItemService getInstance() {
        if (instance == null)
            instance = new OrderItemService();
        return instance;
    }

    @Override
    public List<OrderItem> findAll() {
        List<OrderItem> orderItems = new ArrayList<>();
        List<String> records = CSVUtils.read(PATH);
        for (String record : records) {
            orderItems.add(OrderItem.parse(record));
        }
        return orderItems;
    }

    @Override
    public OrderItem findById(Long id) {
        List<OrderItem> orders = findAll();
        for (OrderItem orderItem : orders) {
            if (orderItem.getId().equals(id))
                return orderItem;
        }
        return null;
    }

    @Override
    public void add(OrderItem newOrderItem) {
        List<OrderItem> orderItems = findAll();
        newOrderItem.setCreatedAt(Instant.now());
        orderItems.add(newOrderItem);
        CSVUtils.write(PATH, orderItems);
    }


    @Override
    public void update(OrderItem newOrderItem) {
        List<OrderItem> orderItems = findAll();
        newOrderItem.setUpdatedAt(Instant.now());
        CSVUtils.write(PATH, orderItems);
    }

    @Override
    public OrderItem getOrderItemById(Long id) {
        List<OrderItem> orderItems = findAll();
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getId().equals(id))
                return orderItem;
        }
        return null;
    }

    @Override
    public List<OrderItem> findByOrderId(Long orderId) {
        List<OrderItem> newOrderItems = new ArrayList<>();
        for (OrderItem orderItem : findAll()) {
            if (orderItem.getOrderId().equals(orderId))
                newOrderItems.add(orderItem);
        }
        return newOrderItems;
    }
}
