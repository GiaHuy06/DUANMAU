/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.cafe.dao.impl;

import java.time.LocalDateTime;

/**
 *
 * @author admin
 */
public interface PasswordResetDAO {
    void saveOtp(String username, String otp);
    String getOtp(String username);
    LocalDateTime getCreatedAt(String username);
    void delete(String username);
}
