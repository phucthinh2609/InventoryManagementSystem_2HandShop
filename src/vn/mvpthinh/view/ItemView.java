package vn.mvpthinh.view;

import vn.mvpthinh.model.Item;
import vn.mvpthinh.model.Product;
import vn.mvpthinh.services.IItemService;
import vn.mvpthinh.services.IProductService;
import vn.mvpthinh.services.ItemService;
import vn.mvpthinh.services.ProductService;
import vn.mvpthinh.utils.AppUtils;
import vn.mvpthinh.utils.InstantUtils;

import java.util.Scanner;

public class ItemView {

    private final IItemService itemService;
    private final IProductService productService;
    private final Scanner scanner = new Scanner(System.in);

    public ItemView() {
        itemService = ItemService.getInstance();
        productService = ProductService.getInstance();
    }


//    public void add() {
//        do {
//            long id = System.currentTimeMillis() / 1000;
//            String title = inputTitle(InputOption.ADD);
//            String content = inputContent(InputOption.ADD);
//            Product product = new Product(id, title, content);
//            itemService.add(product);
//            System.out.println("Thêm sản phẩm thành công!!!");
//
//        } while (AppUtils.isRetry(InputOption.ADD));
//    }
//
//    private Long id;
//    private Long productId;
//    private Long orderId;
//    private double price;
//    private int quantity;
//    private int sold;
//    private int available;
//    private Instant createdAt;
//    private Instant updatedAt;
//    private Long createdBy;
//    private Long updatedBy;

    public void showItems(InputOption option) {
        System.out.println("-----------------------------------------DANH SÁCH HÀNG Hoá-------------------------------------------");
        System.out.printf("%-18s %-20s %-18s %-18s %-18s %-18s", "Id", "Tên sản phẩm", "SKU", "Ngày tạo", "Ngày cập nhật", "Mô tả");
        for (Item item : itemService.findAll()) {
            Product product = productService.findById(item.getProductId());
            System.out.printf("\n%-18s %-20s %-18s %-18s %-18s",
                    item.getId(),
                    product.getTitle(),
                    item.getSku(),
                    InstantUtils.instantToString(item.getCreatedAt()),
                    item.getUpdatedAt() == null ? "" : InstantUtils.instantToString(item.getUpdatedAt())
            );
        }
        System.out.println("--------------------------------------------------------------------------------------------------\n");

        if (option == InputOption.SHOW)
            AppUtils.isRetry(InputOption.SHOW);
    }

    public void showItemsByProductId(InputOption option, Long productId) {
        System.out.println("-----------------------------------------DANH SÁCH HÀNG Hoá-------------------------------------------");
        System.out.printf("%-18s %-20s %-18s %-18s %-18s %-18s", "Id", "Tên sản phẩm", "SKU", "Ngày tạo", "Ngày cập nhật", "Mô tả");
        for (Item item : itemService.findByProductId(productId)) {
            Product product = productService.findById(item.getProductId());
            System.out.printf("\n%-18s %-20s %-18s %-18s %-18s",
                    item.getId(),
                    product.getTitle(),
                    item.getSku(),
                    InstantUtils.instantToString(item.getCreatedAt()),
                    item.getUpdatedAt() == null ? "" : InstantUtils.instantToString(item.getUpdatedAt())
            );
        }
        System.out.println("--------------------------------------------------------------------------------------------------\n");

        if (option == InputOption.SHOW)
            AppUtils.isRetry(InputOption.SHOW);
    }

    private long inputId(InputOption option) {
        Long id;
        switch (option) {
            case ADD:
                System.out.println("Nhập id: ");
                break;
            case UPDATE:
                System.out.println("Nhập id muốn sửa: ");
                break;
            case DELETE:
                System.out.println("Nhập id muốn xoá");
                break;
        }
        boolean isRetry = false;
        do {
            id = AppUtils.retryParseLong();
            boolean exist = itemService.existsById(id);
            switch (option) {
                case ADD:
                    if (exist) {
                        System.out.println("Id không tồn tại. Nhập lại!!!");
                    }
                    isRetry = exist;
                    break;
                case UPDATE:
                    if (!exist)
                        System.out.println("Không tìm thấy id. Nhập lại!!!");
                    isRetry = !exist;
                    break;
            }
        } while (isRetry);
        return id;
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


    private String inputTitle(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Nhập tên sản phẩm: ");
                break;
            case UPDATE:
                System.out.println("Nhập tên sản phẩm muốn sửa: ");
                break;
        }
        System.out.print("==> ");
        return scanner.nextLine();
    }
}
