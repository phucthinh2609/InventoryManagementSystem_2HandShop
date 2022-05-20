package vn.mvpthinh.view;

import vn.mvpthinh.utils.AppUtils;

import java.time.Instant;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainLauncher {

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

                        break;
                    case 3:
                        break;
                    case 4:
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
        System.out.println("|        2. Quản lý kho nhập                    |");
        System.out.println("|        3. Quản lý kho xuất                    |");
        System.out.println("|        4. Quản lý tổng kho                    |");
        System.out.println("|                                               |");
        System.out.println("=================================================");
        System.out.println("                                         0: Thoát");
        System.out.println("\nChọn chức năng ");
        System.out.print("⭆ ");
    }
}
