package vn.mvpthinh.services;

import vn.mvpthinh.model.Order;

import java.util.List;

public interface IOrderService {
    List<Order> findAll();

    void add(Order newOrder);

    void update();

    boolean existsByPhone(String phone);

    Order findById(long id);

    List<Order> findByUserId(long id);

    boolean existById(long id);

}

