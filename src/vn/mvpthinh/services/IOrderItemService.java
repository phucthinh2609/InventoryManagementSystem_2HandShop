package vn.mvpthinh.services;

import vn.mvpthinh.model.Order;
import vn.mvpthinh.model.OrderItem;

import java.util.List;

public interface IOrderItemService {
    List<OrderItem> findAll();

    void add(OrderItem newOrderItem);

    void update(OrderItem newOrderItem);

    OrderItem getOrderItemById(int id);
}
