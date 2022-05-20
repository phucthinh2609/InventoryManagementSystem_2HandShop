package vn.mvpthinh.services;

import vn.mvpthinh.model.Order;
import vn.mvpthinh.utils.CSVUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderService implements IOrderService {
    private final static String PATH = "data/orders.csv";
    IUserService userService;
    private static OrderService instance;

    private OrderService(){
        userService = UserService.getInstance();
    }

    public static OrderService getInstance(){
        if (instance == null)
            instance = new OrderService();
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
        List<Order> orders = findAll();
        userService.getCurrentUser();
        orders.add(newOrder);
        CSVUtils.write(PATH, orders);
    }

    @Override
    public void update() {
        List<Order> orders = findAll();
        CSVUtils.write(PATH, orders);
    }

    @Override
    public boolean existsByPhone(String phone) {
//        List<Order> orders = findAll();
//        for (Order order : orders) {
//            if (order.getMobile().equals(phone))
//                return true;
//        }
        return false;
    }


    @Override
    public Order findById(long id) {
        List<Order> orders = findAll();
        for (Order order : orders) {
            if (order.getId() == id)
                return order;
        }
        return null;
    }

    @Override
    public List<Order> findByUserId(long id) {
        List<Order> newOrders = new ArrayList<>();
        for (Order order : findAll()) {
            if (order.getId() == id)
                newOrders.add(order);
        }
        return newOrders;
    }

    @Override
    public boolean existById(long id) {
        return findById(id) != null;
    }
}
