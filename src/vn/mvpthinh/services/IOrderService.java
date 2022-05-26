package vn.mvpthinh.services;

import vn.mvpthinh.model.Order;
import vn.mvpthinh.model.OrderType;

import java.util.List;

public interface IOrderService {
    List<Order> findAll();

    List<Order> findAllByType(OrderType type);

    void add(Order newOrder);

    //void update(Order newOrder);

    //    private Long id;
//    private Long userId;
//    private OrderType orderType;
//    private OrderStatus status;
//    private Double grandTotal;
//    private Instant createdAt;
//    private Instant updateAt;
//    private String content;
//    @Override
//    public void update(Order newOrder) {
//        List<Order> orders = findAll();
//        for (Order order : orders) {
//            if (order.getId().equals(newOrder.getId())) {
//                OrderStatus status = newOrder.getStatus();
//                if (status != null) order.setStatus(status);
//                Double grandTotal = newOrder.getGrandTotal();
//                if (grandTotal != null) order.setGrandTotal(grandTotal);
//                String content = newOrder.getContent();
//                if (content != null && !content.isEmpty()) order.setContent(content);
//                newOrder.setUpdateAt(Instant.now());
//                CSVUtils.write(PATH, orders);
//                break;
//            }
//            CSVUtils.write(PATH, orders);
//        }
//
//    }
    void purchaseStock(Order newOrder);

    Order createNewOrder(OrderType type);

    Order findById(Long id);

    List<Order> findByUserId(Long id);

    boolean existById(Long id);

    void deleteById(Long id);

    void salesStock(Order order);
}

