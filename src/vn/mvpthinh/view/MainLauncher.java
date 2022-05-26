package vn.mvpthinh.view;

import vn.mvpthinh.utils.AppUtils;

import java.time.Instant;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainLauncher {

    public static void launch() {
        AdminView adminView = new AdminView();
        adminView.adminLogin();
        menuOption();
    }

    public static void menuOption() {
        do {
            mainMenu();
            try {
                Scanner scanner = new Scanner(System.in);
                int choose = Integer.parseInt(scanner.nextLine());
                switch (choose) {
                    case 1:
                        ProductViewLauncher.launch();
                        break;
                    case 2:
                        OrderViewLauncher.purchaseLaunch();
                        break;
                    case 3:
                        OrderViewLauncher.salesLaunch();
                        break;
                    case 4:
                        ItemViewLauncher.launch();
                        break;
                    case 0:
                        AppUtils.exit();
                        break;
                    default:
                        System.err.println("Chọn sai. Nhập lại!!!");
                }

            } catch (InputMismatchException ex) {
                System.err.println("Nhập sai. Nhập lại!!");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        } while (true);
    }

    public static void mainMenu() {
        System.out.println("\n=================================================");
        System.out.println("|                  MAIN MENU                    |");
        System.out.println("=================================================");
        System.out.println("|                                               |");
        System.out.println("|        1. Quản lý sản phẩm                    |");
        System.out.println("|        2. Quản lý nhập hàng                    |");
        System.out.println("|        3. Quản lý xuất hàng                    |");
        System.out.println("|        4. Quản lý hàng hoá                    |");
        System.out.println("|                                               |");
        System.out.println("=================================================");
        System.out.println("                                         0: Thoát");
        System.out.println("\nChọn chức năng ");
        System.out.print("⭆ ");
    }
}
