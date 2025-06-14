/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package poly.cafe.ui.manager;

import java.time.Duration;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;
import poly.cafe.dao.impl.PasswordResetDAO;
import poly.cafe.dao.impl.PasswordResetDAOImpl;
import poly.cafe.dao.impl.UserDAO;
import poly.cafe.dao.impl.UserDAOImpl;
import poly.cafe.entity.User;
import poly.cafe.ui.LoginController;
import poly.cafe.util.RememberUtil;
import poly.cafe.util.XAuth;
import poly.cafe.util.XDialog;
import poly.cafe.util.XMailer;

/**
 *
 * @author admin
 */
public class LoginJDialog extends javax.swing.JDialog implements LoginController {

    /**
     * Creates new form LoginJDialog2
     *
     * @param parent
     * @param modal
     */
    public LoginJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        String[] data = RememberUtil.load();
        if (data != null) {
            txtUsername.setText(data[0]);
            txtPassword.setText(data[1]);
            chkRemember.setSelected(true);
        }
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }
        });

    }

    @Override
    public void open() {
        this.setLocationRelativeTo(null);
    }

    @Override
    public void login() {
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText();

        if (username.isEmpty() && password.isEmpty()) {
            XDialog.alert("Vui lòng nhập tên đăng nhập và mật khẩu!");
            return;
        }

        if (username.isEmpty()) {
            XDialog.alert("Vui lòng nhập tên đăng nhập!");
            return;
        }

        if (password.isEmpty()) {
            XDialog.alert("Vui lòng nhập mật khẩu!");
            return;
        }

        UserDAO dao = new UserDAOImpl() {
        };
        User user = dao.findById(username);

        if (user == null) {
            XDialog.alert("Sai tên đăng nhập!");
        } else if (!password.equals(user.getPassword())) {
            XDialog.alert("Sai mật khẩu đăng nhập!");
        } else if (!user.isEnabled()) {
            XDialog.alert("Tài khoản của bạn đang tạm dừng!");
        } else {
            XAuth.user = user; // duy trì user đăng nhập
            XDialog.alert("Đăng nhập thành công!");
            if (chkRemember.isSelected()) {
                RememberUtil.save(username, password);
            } else {
                RememberUtil.clear();
            }

            this.dispose();
        }
    }

    @Override
    public void exit() {
        if (XDialog.confirm("Bạn có chắc chắn muốn thoát không?")) {
            System.exit(0);
        }
    }

    private final PasswordResetDAO otpDao = new PasswordResetDAOImpl();

    public void ForgotPassword() {
        UserDAO dao = new UserDAOImpl();

        // Bước 1: Nhập tên đăng nhập
        String username = JOptionPane.showInputDialog(this, "Nhập tên đăng nhập:");
        if (username == null || username.trim().isEmpty()) {
            XDialog.alert("Bạn chưa nhập tên đăng nhập.");
            return;
        }

        User user = dao.findById(username);
        if (user == null) {
            XDialog.alert("Không tìm thấy người dùng.");
            return;
        }

        // Kiểm tra xem tài khoản đã có email chưa
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            // Tài khoản chưa có email → yêu cầu nhập
            String emailInput = JOptionPane.showInputDialog(this, "Tài khoản chưa có email. Vui lòng nhập email để nhận mã OTP:");
            if (emailInput == null || emailInput.trim().isEmpty()) {
                XDialog.alert("Bạn chưa nhập email.");
                return;
            }

            // Kiểm tra email đã được dùng bởi tài khoản khác chưa
            User existing = dao.findByEmail(emailInput.trim());
            if (existing != null && !existing.getUsername().equals(user.getUsername())) {
                XDialog.alert("Email này đã được sử dụng bởi người dùng khác. Không thể gán email này cho tài khoản hiện tại.");
                return;
            }

            // Lưu email mới vào tài khoản
            user.setEmail(emailInput.trim());
            dao.update(user);
        }

        String email = user.getEmail();

        // Bước 2: Tạo mã OTP
        String otp = String.valueOf((int) (Math.random() * 900000 + 100000));

        // Bước 3: Gửi email chứa mã OTP
        try {
            XMailer.send(email, "Mã OTP khôi phục mật khẩu", "Mã OTP của bạn là: " + otp);
            otpDao.saveOtp(user.getUsername(), otp);
        } catch (Exception e) {
            XDialog.alert("Gửi email thất bại: " + e.getMessage());
            return;
        }

        // Bước 4: Nhập mã OTP
        String inputOtp = JOptionPane.showInputDialog(this, "Nhập mã OTP đã gửi đến email:");

        // Kiểm tra thời gian sống
        LocalDateTime createdAt = otpDao.getCreatedAt(user.getUsername());
        if (createdAt != null && Duration.between(createdAt, LocalDateTime.now()).toMinutes() > 5) {
            XDialog.alert("Mã OTP đã hết hạn! Vui lòng thử lại.");
            return;
        }

        if (!otp.equals(inputOtp)) {
            XDialog.alert("Sai mã OTP!");
            return;
        }

        // Bước 5: Nhập mật khẩu mới
        String newPass = JOptionPane.showInputDialog(this, "Nhập mật khẩu mới:");
        if (newPass == null || newPass.isBlank()) {
            XDialog.alert("Mật khẩu mới không hợp lệ.");
            return;
        }

        // Kiểm tra mật khẩu đúng 8 ký tự và có cả chữ lẫn số
        if (!newPass.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8}$")) {
            XDialog.alert("Mật khẩu phải đúng 8 ký tự và bao gồm cả chữ và số!");
            return;
        }

        user.setPassword(newPass); // nếu có mã hóa, gọi hàm mã hóa ở đây
        dao.update(user);
        XDialog.alert("Mật khẩu đã được đặt lại!");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator2 = new javax.swing.JSeparator();
        btnDangNhap = new javax.swing.JButton();
        btnKetThuc = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        btnForgotPassword = new javax.swing.JButton();
        chkRemember = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Đăng nhập");

        btnDangNhap.setText("Đăng nhập");
        btnDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangNhapActionPerformed(evt);
            }
        });

        btnKetThuc.setText("Kết thúc");
        btnKetThuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKetThucActionPerformed(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/cafe/icons/trump-small.png"))); // NOI18N
        jLabel5.setText("jLabel5");

        jLabel2.setText("Mật khẩu");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("ĐĂNG NHẬP");

        jLabel3.setText("Tên đăng nhập");

        btnForgotPassword.setText("Quên mật khẩu");
        btnForgotPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnForgotPasswordActionPerformed(evt);
            }
        });

        chkRemember.setText("Nhớ mật khẩu");
        chkRemember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRememberActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(chkRemember)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnDangNhap)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnKetThuc))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel2)
                                            .addComponent(txtPassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btnForgotPassword))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDangNhap)
                            .addComponent(btnKetThuc)
                            .addComponent(chkRemember)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnForgotPassword)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangNhapActionPerformed
        this.login();
    }//GEN-LAST:event_btnDangNhapActionPerformed

    private void btnKetThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKetThucActionPerformed
        this.exit();
    }//GEN-LAST:event_btnKetThucActionPerformed

    private void btnForgotPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnForgotPasswordActionPerformed
        this.ForgotPassword();
    }//GEN-LAST:event_btnForgotPasswordActionPerformed

    private void chkRememberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRememberActionPerformed
        if (chkRemember.isSelected()) {
            XDialog.alert("Mật khẩu sẽ được lưu khi bạn đăng nhập thành công.");
        } else {
            XDialog.alert("Mật khẩu sẽ không được lưu.");
        }
    }//GEN-LAST:event_chkRememberActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            LoginJDialog dialog = new LoginJDialog(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDangNhap;
    private javax.swing.JButton btnForgotPassword;
    private javax.swing.JButton btnKetThuc;
    private javax.swing.JCheckBox chkRemember;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
