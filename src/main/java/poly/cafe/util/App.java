/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.util;

import poly.cafe.ui.manager.LoginJDialog;
import poly.cafe.ui.manager.PolyCafeJFrame;

/**
 *
 * @author admin
 */
public class App {

    public static void main(String[] args) {
        while (true) {
            // Hiển thị dialog đăng nhập
            LoginJDialog login = new LoginJDialog(null, true);
            login.setVisible(true);

            if (XAuth.user == null) {
                break; // không đăng nhập, thoát chương trình
            }

            // Mở giao diện chính
            PolyCafeJFrame mainUI = new PolyCafeJFrame();
            mainUI.setVisible(true);
        }

        // Đóng app
        System.exit(0);
    }
}
