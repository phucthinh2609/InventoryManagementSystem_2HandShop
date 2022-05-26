package vn.mvpthinh.view;

import vn.mvpthinh.model.Item;
import vn.mvpthinh.model.Order;
import vn.mvpthinh.model.Product;
import vn.mvpthinh.services.IItemService;
import vn.mvpthinh.services.IProductService;
import vn.mvpthinh.services.ItemService;
import vn.mvpthinh.services.ProductService;
import vn.mvpthinh.utils.AppUtils;
import vn.mvpthinh.utils.InstantUtils;

import java.time.Instant;
import java.util.Scanner;

public class ItemView {

    private final IItemService itemService;
    private final IProductService productService;
    private final Scanner scanner = new Scanner(System.in);

    public ItemView() {
        itemService = ItemService.getInstance();
        productService = ProductService.getInstance();
    }

    public void showItems(InputOption option) {
        System.out.println("-----------------------------------------DANH SÁCH HÀNG HOÁ-------------------------------------------");
        System.out.printf("%-18s %-20s %-18s %-15s %-10s %-18s %-18s", "Id", "Tên sản phẩm", "SKU", "Giá", "Số lượng", "Ngày tạo", "Ngày cập nhật");
        for (Item item : itemService.findAll()) {
            Product product = productService.findById(item.getProductId());
            System.out.printf("\n%-18s %-20s %-18s %-15s %-10s %-18s %-18s",
                    item.getId(),
                    product.getTitle(),
                    item.getSku(),
                    AppUtils.doubleToVND(item.getPrice()),
                    item.getQuantity(),
                    InstantUtils.instantToString(item.getCreatedAt()),
                    item.getUpdatedAt() == null ? "" : InstantUtils.instantToString(item.getUpdatedAt())
            );
        }
        System.out.println("--------------------------------------------------------------------------------------------------\n");

        if (option == InputOption.SHOW)
            AppUtils.isRetry(InputOption.SHOW);
    }

    public void showSummaryItems(InputOption option) {
        System.out.println("-----------------------------------------DANH SÁCH HÀNG Hoá-------------------------------------------");
        System.out.printf("%-18s %-20s %-18s %-18s %-10s %-10s", "Id", "Tên sản phẩm", "SKU", "Tổng lượng", "Đã bán", "Lượng còn lại");
        for (Item item : itemService.findAll()) {
            Product product = productService.findById(item.getProductId());
            System.out.printf("\n%-18s %-20s %-18s %-18s %-10s %-10s",
                    item.getId(),
                    product.getTitle(),
                    item.getSku(),
                    item.getQuantity(),
                    item.getSold(),
                    item.getAvailable()
            );
        }
        System.out.println("\n--------------------------------------------------------------------------------------------------\n");

        if (option == InputOption.SHOW)
            AppUtils.isRetry(InputOption.SHOW);
    }


    public void showItemsByProductId(InputOption option, Long productId) {
        System.out.println("-----------------------------------------DANH SÁCH HÀNG Hoá-------------------------------------------");
        System.out.printf("%-18s %-20s %-18s %-18s %-18s %-18s", "Id", "Tên sản phẩm", "SKU", "Ngày tạo", "Ngày cập nhật", "Mô tả");
        for (Item item : itemService.findProductById(productId)) {
            Product product = productService.findById(item.getProductId());
            System.out.printf("\n%-18s %-20s %-18s %-18s %-18s",
                    item.getId(),
                    product.getTitle(),
                    item.getSku(),
                    InstantUtils.instantToString(item.getCreatedAt()),
                    item.getUpdatedAt() == null ? "" : InstantUtils.instantToString(item.getUpdatedAt())
            );
        }
        System.out.println("\n--------------------------------------------------------------------------------------------------\n");

        if (option == InputOption.SHOW)
            AppUtils.isRetry(InputOption.SHOW);
    }

    public void showSalesItemsByProductId(InputOption option, Long productId) {
        System.out.println("-----------------------------------------DANH SÁCH HÀNG Hoá-------------------------------------------");
        System.out.printf("%-18s %-20s %-18s %-18s %-18s %-18s", "Id", "Tên sản phẩm", "SKU", "Giá", "Số lượng", "Mô tả");
        for (Item item : itemService.findProductById(productId)) {
            Product product = productService.findById(item.getProductId());
            System.out.printf("\n%-18s %-20s %-18s %-18s %-18s %-18s",
                    item.getId(),
                    product.getTitle(),
                    item.getSku(),
                    AppUtils.doubleToVND(item.getPrice()),
                    item.getAvailable(),
                    product.getContent()
            );
        }
        System.out.println("\n--------------------------------------------------------------------------------------------------\n");

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
                        System.err.println("Id không tồn tại. Nhập lại!!!");
                    }
                    isRetry = exist;
                    break;
                case UPDATE:
                    if (!exist)
                        System.err.println("Không tìm thấy id. Nhập lại!!!");
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
        System.out.print(" ⭆ ");
        return scanner.nextLine();
    }
}
