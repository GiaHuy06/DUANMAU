/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.util;



/**
 *
 * @author admin
 */
public class Test {
    public static void main(String[] args) {
        String sql = "INSERT INTO Categories (Id, Name) VALUES(?, ?)";
        XJdbc.executeUpdate(sql, "L01", "Loại 1");
        XJdbc.executeUpdate(sql, "L02", "Loại 2");
    }
}
