package fpt.aptech.wcd_edunext.dao;

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

    // 📌 Thêm người dùng mới
    public int addUser(UserDTO user) {
        System.out.println("DAO Hello!");
        int row = 0;
        String sql = "INSERT INTO Users (userId, username, email, photo, password, roleId) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getUsername());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPhoto());
            pstmt.setString(5, user.getPassword());
            pstmt.setInt(6, (user.getRoleId() != null) ? user.getRoleId() : 2);
            System.out.println("DAO SQL: " + sql);
            row = pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Lỗi khi thêm user: " + ex.getMessage());
        } finally {
            closeResources();
        }
        return row;
    }

    // 📌 Lấy danh sách tất cả người dùng
    public List<UserDTO> getAllUsers() {
        List<UserDTO> users = new ArrayList<>();
        String sql = "SELECT * FROM Users";

        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                users.add(new UserDTO(
                        rs.getString("userId"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("photo"),
                        rs.getString("password"),
                        rs.getInt("roleId")
                ));
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi khi lấy danh sách user: " + ex.getMessage());
        } finally {
            closeResources();
        }
        return users;
    }

    // 📌 Lấy thông tin user theo ID
    public UserDTO getUserById(String userId) {
        String sql = "SELECT * FROM Users WHERE userId = ?";
        UserDTO user = null;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new UserDTO(
                        rs.getString("userId"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("photo"),
                        rs.getString("password"),
                        rs.getInt("roleId")
                );
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi khi lấy user theo ID: " + ex.getMessage());
        } finally {
            closeResources();
        }
        return user;
    }

    // 📌 Cập nhật thông tin người dùng
    public void updateUser(UserDTO user) {
        String sql = "UPDATE Users SET username = ?, email = ?, photo = ?, password = ?, roleId = ? WHERE userId = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPhoto());
            pstmt.setString(4, user.getPassword());
            pstmt.setInt(5, (user.getRoleId() != null) ? user.getRoleId() : 2);
            pstmt.setString(6, user.getUserId());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Lỗi khi cập nhật user: " + ex.getMessage());
        } finally {
            closeResources();
        }
    }

    // 📌 Xóa người dùng
    public void deleteUser(String userId) {
        String sql = "DELETE FROM Users WHERE userId = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Lỗi khi xóa user: " + ex.getMessage());
        } finally {
            closeResources();
        }
    }

    // 📌 Đóng tài nguyên để tránh rò rỉ
    private void closeResources() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi khi đóng tài nguyên: " + ex.getMessage());
        }
    }
}
