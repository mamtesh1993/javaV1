package com.mypack.jdbc;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InsertImage {

	public static void main(String[] args) {

        String cs = "jdbc:mysql://localhost:3306/jdbc";
        String user = "root";
        String password = "1234";

        String sql = "INSERT INTO Images(Data) VALUES(?)";

        try (Connection con = DriverManager.getConnection(cs, user, password); PreparedStatement pst = con.prepareStatement(sql)) {

            File myFile = new File("./image.PNG");
            try (FileInputStream fin = new FileInputStream(myFile)) {

                pst.setBinaryStream(1, fin, (int) myFile.length());
                pst.executeUpdate();

            } catch (IOException ex) {

                Logger lgr = Logger.getLogger(InsertImage.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }
        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(InsertImage.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

	}

}
