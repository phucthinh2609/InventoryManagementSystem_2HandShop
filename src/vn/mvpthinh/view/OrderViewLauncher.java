package vn.mvpthinh.view;

import vn.mvpthinh.model.OrderType;
import vn.mvpthinh.utils.AppUtils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class OrderViewLauncher {
    public static void launch() {
        int choose;
        do {
            Scanner scanner = new Scanner(System.in);
            OrderVieww orderView = new OrderVieww();
            menuPurchaseProduct();
            try {
                System.out.println("Chọn chức năng: ");
                System.out.print("==> ");
                choose = Integer.parseInt(scanner.nextLine());
                switch (choose) {
                    case 1:
                        orderView.purchaseOrder();
                        break;
                    case 2:
                        orderView.showOrder();
                        break;
                    case 3:
                        MainLauncher.mainMenu();
                        break;
                    case 0:
                        AppUtils.exit();
                }

            } catch (InputMismatchException ex) {
                System.err.println("Nhập sai. Chọn lại!!!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }


        } while (true);
    }


    public static void menuPurchaseProduct() {
        System.out.println("\n=================================================");
        System.out.println("|                QUẢN LÝ KHO NHẬP               |");
        System.out.println("=================================================");
        System.out.println("|                                               |");
        System.out.println("|        1. Thêm sản phẩm                       |");
        System.out.println("|        2. Hiển thị danh sách kho nhập         |");
        System.out.println("|                                               |");
        System.out.println("=================================================");
        System.out.println("<== 3: Quay lại                       0: Thoát");
        System.out.println("\nChọn chức năng ");
        System.out.print("⭆ ");

    }
}
