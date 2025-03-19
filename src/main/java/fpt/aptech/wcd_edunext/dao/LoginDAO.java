package fpt.aptech.wcd_edunext.dao;

import fpt.aptech.wcd_edunext.models.User;
import fpt.aptech.wcd_edunext.utils.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginDAO {

    private Connection conn;

    public LoginDAO() {
        conn = ConnectDB.setConnect();
    }

    public User checkLogin(String username, String password) {
        String sql = "SELECT * FROM Users WHERE username = ? AND password = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getString("userId"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("email"),
                            rs.getString("photo"),
                            rs.getInt("roleId")
                    );
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}