/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.dao.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import poly.cafe.entity.Bill;
import poly.cafe.util.XAuth;
import poly.cafe.util.XJdbc;
import poly.cafe.util.XQuery;

/**
 *
 * @author admin
 */

public class BillDAOImpl implements BillDAO {

    String createSql = "INSERT INTO Bills(Username, CardId, Checkin, Checkout, Status) VALUES(?, ?, ?, ?, ?)";
    String updateSql = "UPDATE Bills SET Username=?, CardId=?, Checkin=?, Checkout=?, Status=? WHERE Id=?";
    String deleteSql = "DELETE FROM Bills WHERE Id=?";
    String findAllSql = "SELECT * FROM Bills";
    String findByIdSql = "SELECT * FROM Bills WHERE Id=?";
    String findByUsernameSql = "SELECT * FROM Bills WHERE Username=?";
    String findByCardIdSql = "SELECT * FROM Bills WHERE CardId=?";
    String findByTimeRangeSql = "SELECT * FROM Bills WHERE Checkin BETWEEN ? AND ? ORDER BY Checkin DESC";

    @Override
    public Bill create(Bill entity) {
        if (entity.getCheckin() == null) {
            entity.setCheckin(new Date());  // Gán thời gian hiện tại nếu bị null
        }
        Object[] values = {
            entity.getUsername(),
            entity.getCardId(),
            entity.getCheckin(),
            entity.getCheckout(),
            entity.getStatus()
        };
        XJdbc.executeUpdate(createSql, values);
        return entity;
    }


    @Override
    public void update(Bill entity) {
        Object[] values = {
            entity.getUsername(),
            entity.getCardId(),
            entity.getCheckin(),
            entity.getCheckout(),
            entity.getStatus(),
            entity.getId(),};
        XJdbc.executeUpdate(updateSql, values);
    }

    @Override
    public List< Bill> findAll() {
        return XQuery.getBeanList(Bill.class, findAllSql);
    }

    @Override
    public List<Bill> findByUsername(String username) {
        return XQuery.getBeanList(Bill.class, findByUsernameSql, username);
    }

    @Override
    public List<Bill> findByCardId(Integer cardId) {
        return XQuery.getBeanList(Bill.class, findByCardIdSql, cardId);
    }

    @Override
    public void deleteById(Long id) {
        XJdbc.executeUpdate(deleteSql, id);
    }

    @Override
    public Bill findById(Long id) {
        return XQuery.getSingleBean(Bill.class, findByIdSql, id);
    }

    @Override
    public List<Bill> findByTimeRange(Date begin, Date end) {
        return XQuery.getBeanList(Bill.class, findByTimeRangeSql, begin, end);
    }

    @Override
    public Bill findServicingByCardId(Integer cardId) {
        String sql = "SELECT * FROM Bills WHERE CardId=? AND Status=0";
        Bill bill = XQuery.getSingleBean(Bill.class, sql, cardId);
        if (bill == null) { // không tìm thấy -> tạo mới
            Bill newBill = new Bill();
            newBill.setCardId(cardId);
            newBill.setCheckin(new Date());
            newBill.setStatus(0); // đang phục vụ
            newBill.setUsername(XAuth.user.getUsername());
            bill = this.create(newBill); // insert
        }
        return bill;
    }

    @Override
    public List<Bill> findByUserAndTimeRange(String username, Date begin, Date end) {
        // Kiểm tra nếu là quản lý thì bỏ lọc theo username
        if (isAdminOrManager(username)) {
            String sql = "SELECT * FROM Bills WHERE Checkin BETWEEN ? AND ? ORDER BY Checkin DESC";
            return XQuery.getBeanList(Bill.class, sql, begin, end);
        }

        // Nhân viên chỉ xem bill của chính họ
        String sql = "SELECT * FROM Bills WHERE Username=? AND Checkin BETWEEN ? AND ? ORDER BY Checkin DESC";
        return XQuery.getBeanList(Bill.class, sql, username, begin, end);
    }

    private boolean isAdminOrManager(String username) {
        if (username == null) {
            return false;
        }
        String sql = "SELECT Manager FROM Users WHERE Username = ?";
        Boolean isManager = XJdbc.getValue(sql, username);
        return isManager != null && isManager;
    }

    @Override
    public void insert(Bill bill) {
        String sql = "INSERT INTO Bills (CardId, Checkin, Status, Username) VALUES (?, ?, ?, ?)";
        XJdbc.executeUpdate(sql,
                bill.getCardId(),
                bill.getCheckin(),
                bill.getStatus(),
                bill.getUsername()
        );
    }
}
