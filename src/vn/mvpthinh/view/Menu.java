package vn.mvpthinh.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    static Scanner scanner = new Scanner(System.in);

    public static void showHomePage(){
        System.out.println("+------------------------------------------+");
        System.out.println("|        Welcome to The 2Hand Shop's       |");
        System.out.println("|         INVENTORY MANAGER SYSTEM         |");
        System.out.println("+------------------------------------------+");
//        Menu.selection();
    }

//    public static void selection(){
//        do {
//            mainMenu();
//            try {
//                int choice = Integer.parseInt(scanner.nextLine());
//                switch (choice){
//                    case 1:
//                        PurchaseProductView.run();
//                        break;
//                    case 2:
//                        SalesProductView.run();
//                        break;
//                    case 3:
//                        ProductDetailView.run();
//                        break;
//                    default:
//                        System.err.println("Nhập sai. Nhập lại!!!");
////                        selection();
//                }
//            } catch (InputMismatchException io) {
//                System.err.println("Nhập sai! Vui lòng nhập lại");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }while (true);
//    }

    public static void exit(){
        System.out.println("\nXin chào và hẹn gặp lại!");
    }

    public static void mainMenu(){
        System.out.println("\n=================================================");
        System.out.println("|                  MAIN MENU                    |");
        System.out.println("=================================================");
        System.out.println("|                                               |");
        System.out.println("|        1. Quản lý kho nhập                    |");
        System.out.println("|        2. Quản lý kho xuất                    |");
        System.out.println("|        3. Quản lý tổng kho                    |");
        System.out.println("|                                               |");
        System.out.println("=================================================");
        System.out.println("\nChọn chức năng ");
        System.out.print("⭆ ");
    }

    public static void purchaseProductMenu() {
        System.out.println("\n=================================================");
        System.out.println("|            PURCHASE PRODUCT MANAGER           |");
        System.out.println("=================================================");
        System.out.println("|                                               |");
        System.out.println("|        1. Thêm sản phẩm                       |");
        System.out.println("|        2. Hiển thị danh sách kho nhập         |");
        System.out.println("|                                               |");
        System.out.println("=================================================");
        System.out.println("<== 9: Quay lại                       0: Thoát");
        System.out.println("\nChọn chức năng ");
        System.out.print("⭆ ");

    }

    public static void salesProductMenu() {
        System.out.println("\n=================================================");
        System.out.println("|          SALES PRODUCT MANAGER                |");
        System.out.println("=================================================");
        System.out.println("|                                               |");
        System.out.println("|        1. Sửa sản phẩm                        |");
        System.out.println("|        2. Hiển thị danh sách kho xuất         |");
        System.out.println("|                                               |");
        System.out.println("=================================================");
        System.out.println("<== 9: Quay lại                       0: Thoát");
        System.out.println("\nChọn chức năng ");
        System.out.print("⭆ ");
    }

    public static void productDetailMenu() {
        System.out.println("\n=================================================");
        System.out.println("|          PRODUCT DETAIL MANAGER               |");
        System.out.println("=================================================");
        System.out.println("|                                               |");
        System.out.println("|        1. Thêm sản phẩm                       |");
        System.out.println("|        2. Sửa sản phẩm                        |");
        System.out.println("|        3. Hiển thị danh sách tổng kho         |");
        System.out.println("|                                               |");
        System.out.println("=================================================");
        System.out.println("<== 9: Quay lại                       0: Thoát");
        System.out.println("\nChọn chức năng ");
        System.out.print("⭆ ");
    }
}
