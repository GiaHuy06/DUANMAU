/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package poly.cafe.ui.manager;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import poly.cafe.ui.PolyCafeController;
import poly.cafe.util.XAuth;
import poly.cafe.util.XIcon;

public final class PolyCafeJFrame extends javax.swing.JFrame implements PolyCafeController {

    public PolyCafeJFrame() {
        initComponents();
        this.init();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlCenter = new javax.swing.JPanel();
        pnlManager = new javax.swing.JPanel();
        btnDrinkManager = new javax.swing.JButton();
        btnBillManager = new javax.swing.JButton();
        btnCategoryManager = new javax.swing.JButton();
        btnUserManager = new javax.swing.JButton();
        btnCardManager = new javax.swing.JButton();
        btnRevenueManager = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblPhoto = new javax.swing.JLabel();
        lblFullname = new javax.swing.JLabel();
        btnSales = new javax.swing.JButton();
        btnHistory = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btnChangePassword = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnLogOut = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Poly Cafe");

        pnlCenter.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlManager.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnDrinkManager.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDrinkManager.setForeground(new java.awt.Color(255, 255, 255));
        btnDrinkManager.setText("ĐỒ UỐNG");
        btnDrinkManager.setContentAreaFilled(false);
        btnDrinkManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDrinkManagerActionPerformed(evt);
            }
        });
        pnlManager.add(btnDrinkManager, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, 100, 40));

        btnBillManager.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBillManager.setForeground(new java.awt.Color(255, 255, 255));
        btnBillManager.setText("PHIẾU BÁN HÀNG");
        btnBillManager.setContentAreaFilled(false);
        btnBillManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBillManagerActionPerformed(evt);
            }
        });
        pnlManager.add(btnBillManager, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 140, 40));

        btnCategoryManager.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCategoryManager.setForeground(new java.awt.Color(255, 255, 255));
        btnCategoryManager.setText("LOẠI ĐỒ UỐNG");
        btnCategoryManager.setContentAreaFilled(false);
        btnCategoryManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCategoryManagerActionPerformed(evt);
            }
        });
        pnlManager.add(btnCategoryManager, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 150, 40));

        btnUserManager.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUserManager.setForeground(new java.awt.Color(255, 255, 255));
        btnUserManager.setText("NGƯỜI SỬ DỤNG");
        btnUserManager.setContentAreaFilled(false);
        btnUserManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserManagerActionPerformed(evt);
            }
        });
        pnlManager.add(btnUserManager, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 140, 40));

        btnCardManager.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCardManager.setForeground(new java.awt.Color(255, 255, 255));
        btnCardManager.setText("THẺ ĐỊNH DANH");
        btnCardManager.setContentAreaFilled(false);
        btnCardManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCardManagerActionPerformed(evt);
            }
        });
        pnlManager.add(btnCardManager, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 10, 140, 40));

        btnRevenueManager.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRevenueManager.setForeground(new java.awt.Color(255, 255, 255));
        btnRevenueManager.setText("DOANH THU");
        btnRevenueManager.setContentAreaFilled(false);
        btnRevenueManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRevenueManagerActionPerformed(evt);
            }
        });
        pnlManager.add(btnRevenueManager, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 110, 40));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/cafe/icons/banner.png"))); // NOI18N
        pnlManager.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -40, 730, 470));

        pnlCenter.add(pnlManager, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 380));

        jLabel2.setText("jLabel2");
        jLabel2.setPreferredSize(new java.awt.Dimension(700, 448));
        pnlCenter.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 390));

        jPanel1.setBackground(new java.awt.Color(0, 51, 51));

        lblPhoto.setBackground(new java.awt.Color(204, 204, 204));
        lblPhoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPhoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 51, 0)));

        lblFullname.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblFullname.setForeground(new java.awt.Color(255, 255, 255));
        lblFullname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFullname.setToolTipText("");

        btnSales.setBackground(new java.awt.Color(204, 204, 204));
        btnSales.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSales.setForeground(new java.awt.Color(0, 51, 51));
        btnSales.setText("BÁN HÀNG");
        btnSales.setBorderPainted(false);
        btnSales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalesActionPerformed(evt);
            }
        });

        btnHistory.setBackground(new java.awt.Color(204, 204, 204));
        btnHistory.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHistory.setForeground(new java.awt.Color(0, 51, 51));
        btnHistory.setText(" LỊCH SỬ");
        btnHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoryActionPerformed(evt);
            }
        });

        btnExit.setBackground(new java.awt.Color(204, 204, 204));
        btnExit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnExit.setForeground(new java.awt.Color(0, 51, 51));
        btnExit.setText("KẾT THÚC");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        btnChangePassword.setBackground(new java.awt.Color(204, 204, 204));
        btnChangePassword.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnChangePassword.setForeground(new java.awt.Color(0, 51, 51));
        btnChangePassword.setText("ĐỔI MẬT KHẨU");
        btnChangePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangePasswordActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Chào mừng:");

        btnLogOut.setForeground(new java.awt.Color(255, 255, 255));
        btnLogOut.setText("ĐĂNG XUẤT");
        btnLogOut.setContentAreaFilled(false);
        btnLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnLogOut))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(lblFullname, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(23, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnExit, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                    .addComponent(btnSales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnHistory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnChangePassword, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))
                .addGap(28, 28, 28))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jLabel3))
                            .addComponent(btnLogOut))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFullname, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnChangePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSales, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCenter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(pnlCenter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDrinkManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDrinkManagerActionPerformed
        this.showDrinkManagerJDialog(this);
    }//GEN-LAST:event_btnDrinkManagerActionPerformed

    private void btnBillManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBillManagerActionPerformed
        this.showBillManagerJDialog(this);
    }//GEN-LAST:event_btnBillManagerActionPerformed

    private void btnCategoryManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCategoryManagerActionPerformed
        this.showCategoryManagerJDialog(this);
    }//GEN-LAST:event_btnCategoryManagerActionPerformed

    private void btnUserManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserManagerActionPerformed
        this.showUserManagerJDialog(this);
    }//GEN-LAST:event_btnUserManagerActionPerformed

    private void btnCardManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCardManagerActionPerformed
        this.showCardManagerJDialog(this);
    }//GEN-LAST:event_btnCardManagerActionPerformed

    private void btnRevenueManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRevenueManagerActionPerformed
        this.showRevenueManagerJDialog(this);
    }//GEN-LAST:event_btnRevenueManagerActionPerformed

    private void btnSalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalesActionPerformed
        this.showSalesJDialog(this);
    }//GEN-LAST:event_btnSalesActionPerformed

    private void btnHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoryActionPerformed
        this.showHistoryJDialog(this);
    }//GEN-LAST:event_btnHistoryActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        this.exit();
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnChangePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangePasswordActionPerformed
        this.showChangePasswordJDialog(this);
    }//GEN-LAST:event_btnChangePasswordActionPerformed

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed
        logout(this);
    }//GEN-LAST:event_btnLogOutActionPerformed

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
            java.util.logging.Logger.getLogger(PolyCafeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PolyCafeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PolyCafeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PolyCafeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new PolyCafeJFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBillManager;
    private javax.swing.JButton btnCardManager;
    private javax.swing.JButton btnCategoryManager;
    private javax.swing.JButton btnChangePassword;
    private javax.swing.JButton btnDrinkManager;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnHistory;
    private javax.swing.JButton btnLogOut;
    private javax.swing.JButton btnRevenueManager;
    private javax.swing.JButton btnSales;
    private javax.swing.JButton btnUserManager;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblFullname;
    private javax.swing.JLabel lblPhoto;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlManager;
    // End of variables declaration//GEN-END:variables
    @Override
    public void init() {
//        this.setIconImage(XIcon.getIcon("trump-small.png").getImage());
        this.setLocationRelativeTo(null);
        this.showWelcomeJDialog(this);
        this.showLoginJDialog(this);
        XIcon.setIcon(lblPhoto, "images/" + XAuth.user.getPhoto());
        lblFullname.setText(XAuth.user.getFullname());
        if (!XAuth.user.isManager()) {
            pnlCenter.remove(pnlManager);
        }
    }

    @Override
    public void exit() {
        PolyCafeController.super.exit();
    }

    public static void logout(JFrame currentFrame) {
        int confirm = JOptionPane.showConfirmDialog(currentFrame, "Bạn có chắc chắn muốn đăng xuất?", "Đăng xuất", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            XAuth.user = null;
            currentFrame.dispose(); // Đóng giao diện chính

            new PolyCafeJFrame().setVisible(true);
        }
    }

    @Override
    public void showJDialog(JDialog dialog) {
        PolyCafeController.super.showJDialog(dialog);
    }

    @Override
    public void showWelcomeJDialog(JFrame frame) {
        PolyCafeController.super.showWelcomeJDialog(frame);
    }

    @Override
    public void showLoginJDialog(JFrame frame) {
        PolyCafeController.super.showLoginJDialog(frame);
    }

    @Override
    public void showChangePasswordJDialog(JFrame frame) {
        PolyCafeController.super.showChangePasswordJDialog(frame);
    }

    @Override
    public void showSalesJDialog(JFrame frame) {
        PolyCafeController.super.showSalesJDialog(frame);
    }

    @Override
    public void showHistoryJDialog(JFrame frame) {
        PolyCafeController.super.showHistoryJDialog(frame);
    }
}
