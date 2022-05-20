package vn.mvpthinh.view;

import vn.mvpthinh.model.Product;
import vn.mvpthinh.services.IProductService;
import vn.mvpthinh.services.ProductService;
import vn.mvpthinh.utils.AppUtils;
import vn.mvpthinh.utils.InstantUtils;

import java.util.Scanner;

public class ProductView {

    private final IProductService productService;
    private final Scanner scanner = new Scanner(System.in);

    public ProductView() {
        productService = ProductService.getInstance();
    }


    public void add() {
        do {
            long id = System.currentTimeMillis()/1000;
            String title = inputTitle(InputOption.ADD);
            String content = inputContent(InputOption.ADD);
            Product product = new Product(id, title, content);
            productService.add(product);
            System.out.println("Thêm sản phẩm thành công!!!");

        } while (AppUtils.isRetry(InputOption.ADD));
    }

    public void update() {
        boolean isRetry;
        do {
            showProduct(InputOption.UPDATE);
            long id = inputId(InputOption.UPDATE);
            System.out.println("=================================================");
            System.out.println("|                                               |");
            System.out.println("|        1. Sửa tên sản phẩm                    |");
            System.out.println("|        2. Sửa mô tả                           |");
//            System.out.println("|        3.                                     |");
//            System.out.println("|                                               |");
            System.out.println("=================================================");
            System.out.println("<== 3: Quay lại                       0: Thoát");
            System.out.println("\nChọn chức năng ");

            Product newProduct = new Product();
            newProduct.setId(id);
            int choose = AppUtils.retryChoose(0,3);
            switch (choose) {
                case 1:
                    String title = inputTitle(InputOption.UPDATE);
                    newProduct.setTitle(title);
                    productService.update(newProduct);
                    System.out.println("Cập nhật tên sản phẩm thành công!");
                    break;
                case 2:
                    String content = inputContent(InputOption.UPDATE);
                    newProduct.setContent(content);
                    productService.update(newProduct);
                    System.out.println("Cập nhật mô tả thành công!");
                    break;
                case 3:
                    ProductViewLauncher.launch();
                    break;
                case 0:
                    AppUtils.exit();
                    break;
            }
            isRetry = choose != 4 && AppUtils.isRetry(InputOption.UPDATE);
        } while (isRetry);


    }

    public void showProduct(InputOption option) {
        System.out.println("-----------------------------------------DANH SÁCH SẢN PHẨM-------------------------------------------");
        System.out.printf("%-18s %-20s %-18s %-18s %-18s", "Id", "Tên sản phẩm", "Ngày tạo", "Ngày cập nhật", "Mô tả");
        for (Product product : productService.findAll()){
            System.out.printf("\n%-18s %-20s %-18s %-18s %-18s",
                    product.getId(),
                    product.getTitle(),
                    InstantUtils.instantToString(product.getCreatedAt()),
                    product.getUpdatedAt() == null ? "" : InstantUtils.instantToString(product.getUpdatedAt()),
                    product.getContent()
            );
        }
        System.out.println("--------------------------------------------------------------------------------------------------\n");

        if (option == InputOption.SHOW)
            AppUtils.isRetry(InputOption.SHOW);
    }

    private long inputId(InputOption option) {
        int id;
        switch (option){
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
            id = AppUtils.retryParseInt();
            boolean exist = productService.existsById(id);
            switch (option){
                case ADD:
                    if (exist){
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
        }while (isRetry);
        return id;
    }

    private String inputContent(InputOption option) {
        switch (option){
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
        switch (option){
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
