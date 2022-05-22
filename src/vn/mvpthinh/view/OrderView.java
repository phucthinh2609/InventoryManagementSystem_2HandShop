//package vn.mvpthinh.view;
//
//import vn.mvpthinh.model.Order;
//import vn.mvpthinh.model.OrderItem;
//import vn.mvpthinh.model.OrderType;
//import vn.mvpthinh.model.Product;
//import vn.mvpthinh.services.*;
//import vn.mvpthinh.utils.AppUtils;
//
//import static vn.mvpthinh.view.Menu.scanner;
//
//public class OrderView {
//
//    private static IProductService productService;
//
//    private static IOrderService orderService;
//
//    private static IOrderItemService orderItemService;
//
//    private static IItemService itemService;
//
//    public OrderView() {
//        productService = ProductService.getInstance();
//        orderService = OrderService.getInstance();
//        orderItemService = OrderItemService.getInstance();
//        itemService = ItemService.getInstance();
//    }
//
//    public void addOrder(OrderType orderType) {
//        do {
//            Long orderId = System.currentTimeMillis() / 1000;
//
//            OrderItem orderItem = addOrderItems(orderId);
////            String content = inputContent(InputOption.ADD);
//            Order order = new Order(orderId, orderType);
//            orderItemService.add(orderItem);
//            orderService.add(order);
//
//            System.out.println("Tạo đơn hàng thành công");
//
//            System.out.println("㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡");
//            System.out.println("㋡                                         ㋡");
//            System.out.println("㋡     1.Nhấn 'y' để tạo tiếp đơn hàng     ㋡");
//            System.out.println("㋡     2. Nhấn 'q' để trở lại              ㋡");
//            System.out.println("㋡     3. nhấp 'p' để in hoá đơn           ㋡");
//            System.out.println("㋡     4. Nhấn 't' để thoát                ㋡");
//            System.out.println("㋡                                         ㋡");
//            System.out.println("㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡");
//            System.out.print(" ⭆ ");
//
//            String option = scanner.nextLine();
//            switch (option) {
//                case "y":
//                    //addOrder();
//                    break;
//                case "q":
//                    OrderViewLauncher.launch();
//                    break;
//                case "p":
//                    showPaymentInfo(orderItem, order);
//                    break;
//                case "t":
//                    AppUtils.exit();
//                    break;
//                default:
//                    System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại");
//                    break;
//            }
//        } while (true);
//    }
//
//    private OrderItem addOrderItems(Long orderId) {
//        do {
//            ProductView productView = new ProductView();
//            productView.showProduct(InputOption.ADD);
//            long id = System.currentTimeMillis() / 1000;
//
//            Long productId = inputProductId(InputOption.ADD);
//            Product product = productService.findById(productId);
//
//            String productName = product.getTitle();
//
////            Long itemId = inputItemId(InputOption.ADD);
////            Item item = itemService.findById(itemId);
//
//            double price = inputPrice(InputOption.ADD);
//
//            int quantity = inputQuantity(InputOption.ADD);
//
//            String content = inputContent(InputOption.ADD);
//
//            OrderItem orderItem = new OrderItem(id, productId, orderId, price, quantity, content);
//
//            return orderItem;
//
//        } while (AppUtils.isRetry(InputOption.ADD));
//
//    }
//
//    public void showOrder() {
//
//    }
//
//    private void showPaymentInfo(OrderItem orderItem, Order order) {
//
//    }
//
//
//    private Long inputProductId(InputOption option) {
//        Long productId;
//        switch (option) {
//            case ADD:
//                System.out.println("Nhập id sản phẩm: ");
//                break;
//            case UPDATE:
//                System.out.println("Nhập id sản phẩm muốn sửa: ");
//                break;
//        }
//        boolean isRetry = false;
//        do {
//            productId = AppUtils.retryParseLong();
//            boolean exist = productService.existsById(productId);
//            switch (option) {
//                case ADD:
//                    if (!exist) {
//                        System.out.println("Id không tồn tại. Nhập lại!!!");
//                    }
//                    isRetry = !exist;
//                    break;
//                case UPDATE:
//                    if (exist) System.out.println("Không tìm thấy id. Nhập lại!!!");
//                    isRetry = exist;
//                    break;
//            }
//        } while (isRetry);
//        return productId;
//    }
//
//    private Long inputItemId(InputOption option) {
//        Long itemId;
//        switch (option) {
//            case ADD:
//                System.out.println("Nhập id item sản phẩm: ");
//                break;
//            case UPDATE:
//                System.out.println("Nhập id item sản phẩm muốn sửa: ");
//                break;
//        }
//        boolean isRetry = false;
//        do {
//            itemId = AppUtils.retryParseLong();
//            boolean exist = itemService.existsById(itemId);
//            switch (option) {
//                case ADD:
//                    if (!exist) {
//                        System.out.println("Id không tồn tại. Nhập lại!!!");
//                    }
//                    isRetry = !exist;
//                    break;
//                case UPDATE:
//                    if (exist) System.out.println("Không tìm thấy id. Nhập lại!!!");
//                    isRetry = exist;
//                    break;
//            }
//        } while (isRetry);
//        return itemId;
//    }
//
//    private Long inputOrderId(InputOption option) {
//        Long orderId;
//        switch (option) {
//            case ADD:
//                System.out.println("Nhập id order sản phẩm: ");
//                break;
//            case UPDATE:
//                System.out.println("Nhập id order sản phẩm muốn sửa: ");
//                break;
//        }
//        boolean isRetry = false;
//        do {
//            orderId = AppUtils.retryParseLong();
//            boolean exist = itemService.existsById(orderId);
//            switch (option) {
//                case ADD:
//                    if (!exist) {
//                        System.out.println("Id không tồn tại. Nhập lại!!!");
//                    }
//                    isRetry = !exist;
//                    break;
//                case UPDATE:
//                    if (exist) System.out.println("Không tìm thấy id. Nhập lại!!!");
//                    isRetry = exist;
//                    break;
//            }
//        } while (isRetry);
//        return orderId;
//    }
//
//    private double inputPrice(InputOption option) {
//        switch (option) {
//            case ADD:
//                System.out.println("Nhập giá sản phẩm: ");
//                break;
//            case UPDATE:
//                System.out.println("Nhập giá bạn muốn sửa: ");
//                break;
//        }
//        double price;
//        do {
//            price = AppUtils.retryParseDouble();
//            if (price <= 0)
//                System.out.println("Giá phải lớn hơn 0 (giá > 0)");
//        } while (price <= 0);
//        return price;
//    }
//
//    private int inputQuantity(InputOption option) {
//        switch (option) {
//            case ADD:
//                System.out.println("Nhập số lượng: ");
//                break;
//            case UPDATE:
//                System.out.println("Nhập số lượng bạn muốn sửa: ");
//                break;
//        }
//        int quantity;
//        do {
//            quantity = AppUtils.retryParseInt();
//            if (quantity <= 0)
//                System.out.println("Số lượng phải lớn hơn 0 (giá > 0)");
//        } while (quantity <= 0);
//        return quantity;
//    }
//
//    private String inputContent(InputOption option) {
//        switch (option) {
//            case ADD:
//                System.out.println("Nhập ghi chú sản phẩm: ");
//                break;
//            case UPDATE:
//                System.out.println("Nhập ghi chú sản phẩm muốn sửa: ");
//                break;
//        }
//        System.out.print("==> ");
//        return scanner.nextLine();
//    }
//
//    private OrderType setType() {
//        OrderType result = null;
//        System.out.println("= = SET TYPE = =");
//        System.out.println("∥   1. IN    ∥");
//        System.out.println("∥   2. OUT   ∥");
//        System.out.println("= = = =  = = = = ");
//        System.out.println("Chọn Role: ");
//        int option = AppUtils.retryChoose(1, 2);
//        switch (option) {
//            case 1:
//                result = OrderType.IN;
//                break;
//            case 2:
//                result = OrderType.OUT;
//                break;
//        }
//        return result;
//    }
//
//}
