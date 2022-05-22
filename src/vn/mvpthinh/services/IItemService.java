package vn.mvpthinh.services;

import vn.mvpthinh.model.Item;
import vn.mvpthinh.model.Product;

import java.util.List;

public interface IItemService {
    List<Item> findAll();

    void add(Item newItem);

    void update(Item newItem);

    void increaseItemAvailable(Long itemId, int quantity);

    Item findById(Long id);

    List<Item> findByProductId(Long id);

    boolean existsById(Long id);

    void deleteById(Long id);

}
