package vn.mvpthinh.view;

import vn.mvpthinh.services.IUserService;
import vn.mvpthinh.services.UserService;
import vn.mvpthinh.utils.AppUtils;

import java.util.Scanner;


public class AdminView {//Single Responsibility Principle (SOLID)
    private final IUserService userService; //Dependency Inversion Principle (SOLID)
    private final Scanner scanner = new Scanner(System.in);

    public AdminView() {
        userService = UserService.getInstance();
    }

    public void adminLogin() {
        boolean isRetry;
        System.out.println("✽ ✽ ✽ ✽ ✽ ✽ ✽ ✽ ĐĂNG NHẬP HỆ THỐNG ✽ ✽ ✽ ✽ ✽ ✽ ✽ ✽ ");
        do {
            System.out.println("Username");
            String username = AppUtils.retryString("Username");
            System.out.println("Mật khẩu");
            String password = AppUtils.retryString("Mật khẩu");
            if (userService.adminLogin(username, password) == null) {
                System.out.println("Tài khoản không hợp lệ ");
                isRetry = isRetry();
            } else {
                System.out.println("\nBạn đã đăng nhập thành công \uD83C\uDF8A \n");
                System.out.println("CHÀO MỪNG BẠN ĐÃ ĐẾN VỚI HỆ THỐNG QUẢN LÝ KHO 2HAND SHOP\n");
                isRetry = false;
            }
        } while (isRetry);
    }

    private boolean isRetry() {
        do {
            try {
                System.out.println("✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ CHỌN ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿");
                System.out.println("✿                                      ✿");
                System.out.println("✿   1.Nhấn 'y' để đăng nhập lại        ✿");
                System.out.println("✿   2.Nhấn 'n' để thoát chương trình   ✿");
                System.out.println("✿                                      ✿");
                System.out.println("✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿");
                System.out.print(" ⭆ ");
                String option = scanner.nextLine();
                switch (option) {
                    case "y":
                        return true;
                    case "n":
                        AppUtils.exit();
                        break;
                    default:
                        System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                        break;
                }

            } catch (Exception ex) {
                System.out.println("Nhập sai! vui lòng nhập lại");
                ex.printStackTrace();
            }
        } while (true);
    }
}
