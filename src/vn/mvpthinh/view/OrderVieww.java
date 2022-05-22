package vn.mvpthinh.view;

import vn.mvpthinh.model.*;
import vn.mvpthinh.services.*;
import vn.mvpthinh.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

import static vn.mvpthinh.view.Menu.scanner;

public class OrderVieww {

    private final IUserService userService;

    private IProductService productService;

    private IOrderService orderService;

    private IOrderItemService orderItemService;

    private IItemService itemService;

    public OrderVieww() {
        productService = ProductService.getInstance();
        orderService = OrderService.getInstance();
        orderItemService = OrderItemService.getInstance();
        itemService = ItemService.getInstance();
        userService = UserService.getInstance();

    }

    public void purchaseOrder() {
//            String content = inputContent(InputOption.ADD);
        Order order = orderService.createNewOrder(OrderType.IN);
        // orderItemService.add(orderItem);

        List<OrderItem> orderItems = addOrderItems(order.getId());

        boolean isConfirm = confirmOrderItems(orderItems);

        if (isConfirm) {
            order.setOrderItems(orderItems);

            orderService.purchaseStock(order);
        } else {
            orderService.deleteById(order.getId());
        }
    }

    private boolean confirmOrderItems(List<OrderItem> orderItems) {
        return true;
    }

    private List<OrderItem> addOrderItems(Long orderId) {
        List<OrderItem> orderItems = new ArrayList<>();
        do {
            ProductView productView = new ProductView();
            productView.showProduct(InputOption.ADD);
            long id = System.currentTimeMillis() / 1000;

            Long productId = inputProductId(InputOption.ADD);
            Product product = productService.findById(productId);

            List<Item> items = itemService.findByProductId(productId);
            Item item = new Item();
            if (items.size() != 0) {
                System.out.println("chon hang hoa");
                ItemView itemView = new ItemView();
                itemView.showItemsByProductId(InputOption.UPDATE, productId);
                long itemId = inputItemId(InputOption.ADD);
                item.setId(itemId);
            } else {
                String sku = inputSKU();
                item.setSku(sku);
            }


            String productName = product.getTitle();

//            Long itemId = inputItemId(InputOption.ADD);
//            Item item = itemService.findById(itemId);

            double price = inputPrice(InputOption.ADD);

            int quantity = inputQuantity(InputOption.ADD);

            String content = inputContent(InputOption.ADD);

            OrderItem orderItem = new OrderItem(id, productId, item.getId(), orderId, price, quantity, content);
            orderItem.setItem(item);
            orderItems.add(orderItem);

        } while (AppUtils.isRetry(InputOption.ADD));
        return orderItems;

    }

    private String inputSKU() {
        System.out.println("Nhập SKU");
        return AppUtils.retryString("SKU");
    }

    public void showOrder() {
    }

    private void showPaymentInfo(OrderItem orderItem, Order order) {

    }


    private Long inputProductId(InputOption option) {
        Long productId;
        switch (option) {
            case ADD:
                System.out.println("Nhập id sản phẩm: ");
                break;
            case UPDATE:
                System.out.println("Nhập id sản phẩm muốn sửa: ");
                break;
        }
        boolean isRetry = false;
        do {
            productId = AppUtils.retryParseLong();
            boolean exist = productService.existsById(productId);
            switch (option) {
                case ADD:
                    if (!exist) {
                        System.out.println("Id không tồn tại. Nhập lại!!!");
                    }
                    isRetry = !exist;
                    break;
                case UPDATE:
                    if (exist) System.out.println("Không tìm thấy id. Nhập lại!!!");
                    isRetry = exist;
                    break;
            }
        } while (isRetry);
        return productId;
    }

    private Long inputItemId(InputOption option) {
        Long itemId;
        switch (option) {
            case ADD:
                System.out.println("Nhập id item sản phẩm: ");
                break;
            case UPDATE:
                System.out.println("Nhập id item sản phẩm muốn sửa: ");
                break;
        }
        boolean isRetry = false;
        do {
            itemId = AppUtils.retryParseLong();
            boolean exist = itemService.existsById(itemId);
            switch (option) {
                case ADD:
                    if (!exist) {
                        System.out.println("Id không tồn tại. Nhập lại!!!");
                    }
                    isRetry = !exist;
                    break;
                case UPDATE:
                    if (exist) System.out.println("Không tìm thấy id. Nhập lại!!!");
                    isRetry = exist;
                    break;
            }
        } while (isRetry);
        return itemId;
    }

    private Long inputOrderId(InputOption option) {
        Long orderId;
        switch (option) {
            case ADD:
                System.out.println("Nhập id order sản phẩm: ");
                break;
            case UPDATE:
                System.out.println("Nhập id order sản phẩm muốn sửa: ");
                break;
        }
        boolean isRetry = false;
        do {
            orderId = AppUtils.retryParseLong();
            boolean exist = itemService.existsById(orderId);
            switch (option) {
                case ADD:
                    if (!exist) {
                        System.out.println("Id không tồn tại. Nhập lại!!!");
                    }
                    isRetry = !exist;
                    break;
                case UPDATE:
                    if (exist) System.out.println("Không tìm thấy id. Nhập lại!!!");
                    isRetry = exist;
                    break;
            }
        } while (isRetry);
        return orderId;
    }

    private double inputPrice(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Nhập giá sản phẩm: ");
                break;
            case UPDATE:
                System.out.println("Nhập giá bạn muốn sửa: ");
                break;
        }
        double price;
        do {
            price = AppUtils.retryParseDouble();
            if (price <= 0) System.out.println("Giá phải lớn hơn 0 (giá > 0)");
        } while (price <= 0);
        return price;
    }

    private int inputQuantity(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Nhập số lượng: ");
                break;
            case UPDATE:
                System.out.println("Nhập số lượng bạn muốn sửa: ");
                break;
        }
        int quantity;
        do {
            quantity = AppUtils.retryParseInt();
            if (quantity <= 0) System.out.println("Số lượng phải lớn hơn 0 (giá > 0)");
        } while (quantity <= 0);
        return quantity;
    }

    private String inputContent(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Nhập ghi chú sản phẩm: ");
                break;
            case UPDATE:
                System.out.println("Nhập ghi chú sản phẩm muốn sửa: ");
                break;
        }
        System.out.print("==> ");
        return scanner.nextLine();
    }

    private OrderType setType() {
        OrderType result = null;
        System.out.println("= = SET TYPE = =");
        System.out.println("∥   1. IN    ∥");
        System.out.println("∥   2. OUT   ∥");
        System.out.println("= = = =  = = = = ");
        System.out.println("Chọn Role: ");
        int option = AppUtils.retryChoose(1, 2);
        switch (option) {
            case 1:
                result = OrderType.IN;
                break;
            case 2:
                result = OrderType.OUT;
                break;
        }
        return result;
    }

}
