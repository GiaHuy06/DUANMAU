package poly.cafe.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import poly.cafe.util.XJdbc;

public class PasswordResetDAOImpl implements PasswordResetDAO {

    @Override
    public void saveOtp(String username, String otp) {
        String sqlDelete = "DELETE FROM PasswordReset WHERE Username = ?";
        String sqlInsert = "INSERT INTO PasswordReset (Username, Otp, CreatedAt) VALUES (?, ?, ?)";
        try (Connection con = XJdbc.getConnection()) {
            PreparedStatement delete = con.prepareStatement(sqlDelete);
            delete.setString(1, username);
            delete.executeUpdate();

            PreparedStatement insert = con.prepareStatement(sqlInsert);
            insert.setString(1, username);
            insert.setString(2, otp);
            insert.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            insert.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getOtp(String username) {
        String sql = "SELECT Otp FROM PasswordReset WHERE Username = ?";
        try (Connection con = XJdbc.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("Otp");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public LocalDateTime getCreatedAt(String username) {
        String sql = "SELECT CreatedAt FROM PasswordReset WHERE Username = ?";
        try (Connection con = XJdbc.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Timestamp ts = rs.getTimestamp("CreatedAt");
                return ts != null ? ts.toLocalDateTime() : null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void delete(String username) {
        String sql = "DELETE FROM PasswordReset WHERE Username = ?";
        try (Connection con = XJdbc.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
