package vn.mvpthinh.services;

import vn.mvpthinh.model.Item;
import vn.mvpthinh.model.User;
import vn.mvpthinh.utils.CSVUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ItemService implements IItemService {
    private static final String PATH = "data/items.csv";
    private IUserService userService;
    private static ItemService instance;

    public ItemService() {
        userService = UserService.getInstance();
    }

    public static ItemService getInstance() {
        if (instance == null) instance = new ItemService();
        return instance;
    }


    @Override
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        List<String> records = CSVUtils.read(PATH);
        for (String record : records) {
            items.add(Item.parse(record));
        }
        return items;
    }

    @Override
    public void add(Item newItem) {
        List<Item> items = findAll();
        newItem.setCreatedAt(Instant.now());
        newItem.setCreatedBy(userService.getCurrentUser().getId());
        items.add(newItem);
        CSVUtils.write(PATH, items);
    }

    @Override
    public void update(Item newItem) {
        List<Item> items = findAll();
        newItem.setUpdatedBy(userService.getCurrentUser().getId());
        newItem.setUpdatedAt(Instant.now());
        for (Item item : items) {

        }
    }

    @Override
    public void increaseItemAvailable(Long itemId, int quantity) {
        Item item = findById(itemId);
        item.setAvailable(item.getAvailable() + quantity);
        item.setQuantity(item.getQuantity() + quantity);
        update(item);

    }

    @Override
    public Item findById(Long id) {
        List<Item> items = findAll();
        for (Item item : items) {
            if (item.getId().equals(id))
                return item;
        }
        return null;
    }

    @Override
    public List<Item> findByProductId(Long productId) {
        List<Item> items = findAll();
        List<Item> newItems = new ArrayList<>();
        for (Item item : items) {
            if (item.getProductId().equals(productId)) {
                newItems.add(item);
            }
        }
        // items.stream().filter(item->item.getProductId().equals(productId)).collect(Collectors.toList());
        return newItems;
    }

    @Override
    public boolean existsById(Long id) {
        return findById(id) != null;
    }

    @Override
    public void deleteById(Long id) {

    }

}
