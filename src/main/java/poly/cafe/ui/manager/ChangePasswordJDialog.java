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
import poly.cafe.ui.ChangePasswordController;
import poly.cafe.util.XAuth;
import poly.cafe.util.XDialog;
import poly.cafe.util.XMailer;

/**
 *
 * @author admin
 */
public class ChangePasswordJDialog extends javax.swing.JDialog implements ChangePasswordController {

    /**
     * Creates new form ChangePasswordJDialog2
     *
     * @param parent
     * @param modal
     */
    public ChangePasswordJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    UserDAO dao = new UserDAOImpl() {
    };

    @Override
    public void open() {
        this.setLocationRelativeTo(null);
    }

    @Override
    public void close() {
        this.dispose();
    }

    @Override
    public void save() {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String newpass = txtNewpass.getText();
        String confirm = txtConfirm.getText();

        if (!newpass.equals(confirm)) {
            XDialog.alert("Xác nhận mật khẩu không đúng!");
        } else if (!username.equals(XAuth.user.getUsername())) {
            XDialog.alert("Sai tên đăng nhập!");
        } else if (!password.equals(XAuth.user.getPassword())) {
            XDialog.alert("Sai mật khẩu!");
        } else {
            XAuth.user.setPassword(newpass);
            dao.update(XAuth.user);
            XDialog.alert("Đổi mật khẩu thành công!");
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

        jLabel1 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        txtNewpass = new javax.swing.JPasswordField();
        txtConfirm = new javax.swing.JPasswordField();
        btnForgotPassword = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Đổi mật khẩu");
        setFocusableWindowState(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("ĐỔI MẬT KHẨU");

        btnSave.setText("Lưu");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnClose.setText("Đóng");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        jLabel2.setText("Tên đăng nhập");

        jLabel3.setText("Mật khẩu hiện tại");

        jLabel4.setText("Mật khẩu mới");

        jLabel5.setText("Xác nhận mật khẩu mới");

        btnForgotPassword.setText("Quên mật khẩu");
        btnForgotPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnForgotPasswordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtNewpass, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                        .addComponent(txtUsername))
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4))
                                .addGap(70, 70, 70)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel3)
                                    .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(txtConfirm)))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jSeparator3)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClose)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnForgotPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(134, 134, 134))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNewpass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnForgotPassword)
                    .addComponent(btnClose)
                    .addComponent(btnSave))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        save();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        close();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnForgotPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnForgotPasswordActionPerformed

    }//GEN-LAST:event_btnForgotPasswordActionPerformed

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
            java.util.logging.Logger.getLogger(ChangePasswordJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChangePasswordJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChangePasswordJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChangePasswordJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            ChangePasswordJDialog dialog = new ChangePasswordJDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnForgotPassword;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JPasswordField txtConfirm;
    private javax.swing.JPasswordField txtNewpass;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
