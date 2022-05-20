package vn.mvpthinh.view;

import vn.mvpthinh.utils.AppUtils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ProductViewLauncher {
    public static void launch() {
        int choose;
        do {
            menuProduct();
            Scanner scanner = new Scanner(System.in);
            ProductView productView = new ProductView();
//            MainLauncher mainLauncher = new MainLauncher();
            try {
                choose = Integer.parseInt(scanner.nextLine());
                switch (choose) {
                    case 1:
                        productView.add();
                        break;
                    case 2:
                        productView.update();
                        break;
                    case 3:
                        productView.showProduct(InputOption.SHOW);
                        break;
                    case 9:
                        MainLauncher.menuOption();
                        break;
                    case 0:
                        AppUtils.exit();
                        break;
                    default:
                        System.err.println("Chọn sai. Chọn lại!!!");
                }

            } catch (InputMismatchException ex) {
                System.err.println("Nhập sai. Nhập lại!!!");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } while (true);
    }

    public static void menuProduct() {
        System.out.println("\n=================================================");
        System.out.println("|            PURCHASE PRODUCT MANAGER           |");
        System.out.println("=================================================");
        System.out.println("|                                               |");
        System.out.println("|        1. Thêm sản phẩm                       |");
        System.out.println("|        2. Sửa sản phẩm                        |");
        System.out.println("|        3. Hiển thị danh sách kho nhập         |");
        System.out.println("|                                               |");
        System.out.println("=================================================");
        System.out.println("<== 9: Quay lại                       0: Thoát");
        System.out.println("\nChọn chức năng ");
        System.out.print("⭆ ");
    }
}
