package vn.mvpthinh.view;

import vn.mvpthinh.utils.AppUtils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ItemViewLauncher {
    public static void launch() {
        int choose;
        do {
            Scanner scanner = new Scanner(System.in);
            ItemView itemView = new ItemView();
            menuItem();
            try {
                System.out.println("Chọn chức năng: ");
                System.out.print(" ⭆ ");
                choose = AppUtils.retryChoose(0,3);
                switch (choose) {
                    case 1:
                        itemView.showItems(InputOption.SHOW);
                        break;
                    case 2:
                        itemView.showSummaryItems(InputOption.SHOW);
                        break;
                    case 3:
                        MainLauncher.menuOption();
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


    public static void menuItem() {
        System.out.println("\n=================================================");
        System.out.println("|                QUẢN LÝ HÀNG HOÁ               |");
        System.out.println("=================================================");
        System.out.println("|                                               |");
        System.out.println("|        1. Danh sách chi tiết hàng hoá         |");
        System.out.println("|        2. Danh sách tổng kho                  |");
        System.out.println("|                                               |");
        System.out.println("=================================================");
        System.out.println("<== 3: Quay lại                       0: Thoát");
        System.out.println("\nChọn chức năng ");
        System.out.print("⭆ ");

    }
}
