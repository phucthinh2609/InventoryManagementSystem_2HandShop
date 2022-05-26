package vn.mvpthinh.services;

import vn.mvpthinh.model.Item;

import java.util.List;

public interface IItemService {
    List<Item> findAll();

    void add(Item newItem);

    void update(Item newItem);

    void increaseItemAvailable(Long itemId, int quantity);

    void updateItemPrice(Long itemId, double price);

    Item findById(Long id);

    List<Item> findProductById(Long id);

    boolean existsById(Long id);

    void deleteById(Long id);

    void updateItemSold(Long itemId, int sold);
}
