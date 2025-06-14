/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author admin
 */
public class RememberUtil {

    private static final String FILE_PATH = "remember.dat";

    public static void save(String username, String password) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(FILE_PATH))) {
            dos.writeUTF(username);
            dos.writeUTF(password);
        } catch (IOException e) {
        }
    }

    public static String[] load() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return null;
        }

        try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
            String username = dis.readUTF();
            String password = dis.readUTF();
            return new String[]{username, password};
        } catch (IOException e) {
            return null;
        }
    }

    public static void clear() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }
}
