package vn.mvpthinh.view;

import vn.mvpthinh.model.*;
import vn.mvpthinh.services.*;
import vn.mvpthinh.utils.AppUtils;
import vn.mvpthinh.utils.InstantUtils;
import vn.mvpthinh.utils.ValidateUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderView {

    private final IUserService userService;

    private IProductService productService;

    private IOrderService orderService;

    private IOrderItemService orderItemService;

    private IItemService itemService;

    public OrderView() {
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
            System.out.println("Tạo đơn hàng thành công");
        } else {
            orderService.deleteById(order.getId());
            System.out.println("Đơn hàng đã được huỷ");
        }
    }

    public void salesOrder() {
        Order order = orderService.createNewOrder(OrderType.OUT);
        // orderItemService.add(orderItem);

        List<OrderItem> orderItems = addSalesOrderItems(order.getId());

        if (orderItems.size() == 0){
            return;
        }

        boolean isConfirm = confirmSalesOrderItems(orderItems);

        if (isConfirm) {
            order.setOrderItems(orderItems);

            orderService.salesStock(order);
            System.out.println("Tạo đơn hàng thành công");
        } else {
            orderService.deleteById(order.getId());
            System.out.println("Đơn hàng đã được huỷ");
        }
    }

    private boolean confirmSalesOrderItems(List<OrderItem> orderItems) {
        showConFirmSalesOrderItems(orderItems);
        System.out.println("\n+-------------------------+");
        System.out.println("|   Xác nhận đơn hàng      |");
        System.out.println("+--------------------------+");
        return AppUtils.isRetry(InputOption.CONFIRM);
    }

    private boolean confirmOrderItems(List<OrderItem> orderItems) {
        showConFirmOrderItems(orderItems);
        System.out.println("\n+-------------------------+");
        System.out.println("|   Xác nhận đơn hàng      |");
        System.out.println("+--------------------------+");
        return AppUtils.isRetry(InputOption.CONFIRM);
    }

    private void showOrderItems(InputOption option, List<OrderItem> orderItems) {
        System.out.println("-----------------------------------------DANH SÁCH HÀNG HOÁ-------------------------------------------");
        System.out.printf("%-18s %-20s %-18s %-18s %-18s %-18s", "Id", "Tên sản phẩm", "SKU", "Ngày tạo", "Ngày cập nhật", "Mô tả");
        for (OrderItem orderItem : orderItems) {
            Product product = productService.findById(orderItem.getProductId());
            System.out.printf("\n%-18s %-20s %-18s %-18s %-18s",
                    orderItem.getId(),
                    product.getTitle(),
                    orderItem.getItem().getSku(),
                    InstantUtils.instantToString(orderItem.getCreatedAt()),
                    orderItem.getUpdatedAt() == null ? "" : InstantUtils.instantToString(orderItem.getUpdatedAt())
            );
        }
        System.out.println("\n--------------------------------------------------------------------------------------------------\n");
        if (option == InputOption.SHOW)
            AppUtils.isRetry(InputOption.SHOW);
    }

    private void showConFirmOrderItems(List<OrderItem> orderItems) {
        System.out.println("-----------------------------------------XÁC NHẬN ĐƠN HÀNG-------------------------------------------");
        System.out.printf("%-18s %-20s %-18s %-18s %-18s %-18s %-18s", "Id", "Tên sản phẩm", "SKU", "Giá", "Số lượng", "Tổng giá", "Mô tả");
        int count =0;
        Double total = 0.0;
        for (OrderItem orderItem : orderItems) {
            Product product = productService.findById(orderItem.getProductId());
            total += orderItem.getPrice()*orderItem.getQuantity();
            System.out.printf("\n%-18s %-20s %-18s %-18s %-18s %-18s %-18s",
                    orderItem.getId(),
                    product.getTitle(),
                    orderItem.getItem().getSku(),
                    AppUtils.doubleToVND(orderItem.getPrice()),
                    orderItem.getQuantity(),
                    orderItem.getQuantity()*orderItem.getPrice(),
                    orderItem.getContent()
            );
            count++;
        }
        System.out.println("\n--------------------------------------------------------------------------------------------------");
        System.out.println(">>TỔNG SỐ ĐƠNG HÀNG:   : " + count);
        System.out.println(">>TỔNG TIỀN            : " + AppUtils.doubleToVND(total));
        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    private void showConFirmSalesOrderItems(List<OrderItem> orderItems) {
        System.out.println("-----------------------------------------XÁC NHẬN ĐƠN HÀNG-------------------------------------------");
        System.out.printf("%-18s %-20s %-18s %-18s %-18s %-18s %-18s", "Id", "Tên sản phẩm", "SKU", "Giá", "Số lượng", "Tổng giá", "Mô tả");
        int count =0;
        Double total = 0.0;
        for (OrderItem orderItem : orderItems) {
            Product product = productService.findById(orderItem.getProductId());
            total += orderItem.getQuantity()*orderItem.getItem().getPrice();
            System.out.printf("\n%-18s %-20s %-18s %-18s %-18s %-18s %-18s",
                    orderItem.getId(),
                    product.getTitle(),
                    orderItem.getItem().getSku(),
                    AppUtils.doubleToVND(orderItem.getItem().getPrice()),
                    orderItem.getQuantity(),
                    AppUtils.doubleToVND(orderItem.getQuantity()*orderItem.getItem().getPrice()),
                    product.getContent()
            );
            count++;
        }
        System.out.println("\n--------------------------------------------------------------------------------------------------");
        System.out.println(">>TỔNG SỐ ĐƠNG HÀNG:   : " +count);
        System.out.println(">>TỔNG TIỀN            : " + AppUtils.doubleToVND(total));
        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    private List<OrderItem> addOrderItems(Long orderId) {
        List<OrderItem> orderItems = new ArrayList<>();
        do {
            ProductView productView = new ProductView();
            productView.showProduct(InputOption.ADD);
            long id = System.currentTimeMillis() / 1000;

            Long productId = inputProductId(InputOption.ADD);
            Product product = productService.findById(productId);

            List<Item> items = itemService.findProductById(productId);
            Item item = new Item();
            if (items.size() != 0) {
                System.out.println("Chọn hàng hoá\n");
                ItemView itemView = new ItemView();
                itemView.showItemsByProductId(InputOption.UPDATE, productId);
                long itemId = inputItemId(InputOption.ADD);
                item.setId(itemId);
            } else {
                String sku = inputSKU();
                item.setSku(sku);
            }

            String productName = product.getTitle();

            double price = inputPrice(InputOption.ADD);

            int quantity = inputQuantity(InputOption.ADD);

            String content = inputContent(InputOption.ADD);

            OrderItem orderItem = new OrderItem(id, productId, item.getId(), orderId, price, quantity, content);
            orderItem.setItem(item);
            orderItems.add(orderItem);

        } while (AppUtils.isRetry(InputOption.ADD));
        return orderItems;
    }

    private List<OrderItem> addSalesOrderItems(Long orderId) {
        List<OrderItem> orderItems = new ArrayList<>();

        do {
            ProductView productView = new ProductView();
            productView.showProduct(InputOption.ADD);
            long id = System.currentTimeMillis() / 1000;

            Long productId = inputProductId(InputOption.ADD);
            Product product = productService.findById(productId);

            List<Item> items = itemService.findProductById(productId);
            Long itemId;
            if (items.size() != 0) {
                System.out.println(">>Chọn hàng hoá<<\n");
                ItemView itemView = new ItemView();
                itemView.showSalesItemsByProductId(InputOption.UPDATE, productId);
                itemId = inputItemId(InputOption.ADD);
//                item.setId(itemId);
            } else {
                System.err.println("Mặt hàng chưa tồn tại trong kho!!");
                return orderItems;
            }


            String productName = product.getTitle();

//            Long itemId = inputItemId(InputOption.ADD);
//            Item item = itemService.findById(itemId);

//            double price = inputPrice(InputOption.ADD);

            int quantity = inputSalesQuantity(InputOption.ADD, itemId);

//            String content = inputContent(InputOption.ADD);

            OrderItem orderItem = new OrderItem(id, productId, itemId, orderId, quantity);
            Item item = itemService.findById(itemId);
            orderItem.setPrice(item.getPrice());
            orderItem.setItem(item);
            orderItems.add(orderItem);

        } while (AppUtils.isRetry(InputOption.ADD));
        return orderItems;
    }

    private int inputSalesQuantity(InputOption option, Long itemId) {
        Item item = itemService.findById(itemId);

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
            if (quantity <= 0 || quantity > item.getQuantity())
                System.err.println("Số lượng phải lớn hơn 0 (giá > 0) và bé hơn số lượng đang có");
        } while (quantity <= 0 || quantity > item.getQuantity());

        return quantity;
    }

    private String inputSKU() {
        String sku = "";
        do {
            System.out.println("Nhập SKU");
            sku = AppUtils.retryString("SKU");
            if (!ValidateUtils.isSkuValid(sku))
                System.err.println("Vui lòng nhập đúng cú pháp (Ví dụ: 'US-AP-1100')");

        } while (!ValidateUtils.isSkuValid(sku));

        return sku;
    }

    public void showPurchaseOrder(InputOption option) {
        System.out.println("-----------------------------------------DANH SÁCH HÀNG NHẬP-------------------------------------------");
        System.out.printf("%-18s %-20s %-18s %-18s %-18s", "Id" ,"Tổng giá", "Ngày tạo", "Ngày cập nhật", "Status");
        for (Order order : orderService.findAllByType(OrderType.IN)) {
            if (order.getType().equals(OrderType.IN)) {
                System.out.printf("\n%-18s %-20s %-18s %-18s %-18s",
                        order.getId(),
                        AppUtils.doubleToVND(order.getGrandTotal()),
                        InstantUtils.instantToString(order.getCreatedAt()),
                        order.getUpdateAt() == null ? "" : InstantUtils.instantToString(order.getUpdateAt()),
                        order.getStatus()
                );
            }
        }
        System.out.println("--------------------------------------------------------------------------------------------------\n");

        if (option == InputOption.SHOW)
            AppUtils.isRetry(InputOption.SHOW);
    }

    public void showSalesOrder(InputOption option) {
        System.out.println("-----------------------------------------DANH SÁCH HÀNG NHẬP-------------------------------------------");
        System.out.printf("%-18s %-20s %-18s %-18s %-18s", "Id" ,"Tổng giá", "Ngày tạo", "Ngày cập nhật", "Status");
        for (Order order : orderService.findAllByType(OrderType.OUT)) {
            if (order.getType().equals(OrderType.OUT)) {
                System.out.printf("\n%-18s %-20s %-18s %-18s %-18s",
                        order.getId(),
                        AppUtils.doubleToVND(order.getGrandTotal()),
                        InstantUtils.instantToString(order.getCreatedAt()),
                        order.getUpdateAt() == null ? "" : InstantUtils.instantToString(order.getUpdateAt()),
                        order.getStatus()
                );
            }
        }
        System.out.println("--------------------------------------------------------------------------------------------------\n");

        if (option == InputOption.SHOW)
            AppUtils.isRetry(InputOption.SHOW);
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
                        System.err.println("Id không tồn tại. Nhập lại!!!");
                    }
                    isRetry = !exist;
                    break;
                case UPDATE:
                    if (exist) System.err.println("Không tìm thấy id. Nhập lại!!!");
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
                        System.err.println("Id không tồn tại. Nhập lại!!!");
                    }
                    isRetry = !exist;
                    break;
                case UPDATE:
                    if (exist) System.err.println("Không tìm thấy id. Nhập lại!!!");
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
                        System.err.println("Id không tồn tại. Nhập lại!!!");
                    }
                    isRetry = !exist;
                    break;
                case UPDATE:
                    if (exist) System.err.println("Không tìm thấy id. Nhập lại!!!");
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
            if (price <= 0) System.err.println("Giá phải lớn hơn 0 (giá > 0)");
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
            if (quantity <= 0) System.err.println("Số lượng phải lớn hơn 0 (giá > 0)");
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
        Scanner scanner = new Scanner(System.in);
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
