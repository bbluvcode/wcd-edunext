/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fpt.aptech.wcd_edunext.dao;

/**
 *
 * @author Admin
 */
import fpt.aptech.wcd_edunext.dto.UserDTO;
import fpt.aptech.wcd_edunext.utils.ConnectDB;
import java.sql.*;
import java.util.*;

public class UserDAO {

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public UserDAO() {
        conn = ConnectDB.setConnect();
    }

    // 📌 Lấy danh sách tất cả người dùng
    public List<UserDTO> getAllUsers() throws SQLException {
        List<UserDTO> users = new ArrayList<>();
        String sql = "SELECT * FROM Users";

        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                UserDTO user = new UserDTO(
                        rs.getString("userId"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("photo")
                );
                users.add(user);
            }
        }
        return users;
    }

    // 📌 Lấy thông tin user theo ID
    public UserDTO getUserById(String userId) throws SQLException {
        String sql = "SELECT * FROM Users WHERE userId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new UserDTO(
                            rs.getString("userId"),
                            rs.getString("username"),
                            rs.getString("email"),
                            rs.getString("photo")
                    );
                }
            }
        }
        return null;
    }

    // 📌 Thêm người dùng mới
    public void addUser(UserDTO user) throws SQLException {
        String sql = "INSERT INTO Users (userId, username, email, photo, roleId) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUserId());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPhoto());
            stmt.executeUpdate();
        }
    }

    // 📌 Cập nhật thông tin người dùng
    public void updateUser(UserDTO user) throws SQLException {
        String sql = "UPDATE Users SET username = ?, email = ?, photo = ?, roleId = ? WHERE userId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPhoto());
            stmt.setString(5, user.getUserId());
            stmt.executeUpdate();
        }
    }

    // 📌 Xóa người dùng
    public void deleteUser(String userId) throws SQLException {
        String sql = "DELETE FROM Users WHERE userId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userId);
            stmt.executeUpdate();
        }
    }
}
