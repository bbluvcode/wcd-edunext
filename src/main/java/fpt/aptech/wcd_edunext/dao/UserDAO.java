package fpt.aptech.wcd_edunext.dao;

import fpt.aptech.wcd_edunext.dto.UserDTO;
import fpt.aptech.wcd_edunext.utils.ConnectDB;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public List<UserDTO> findAll() {
        List<UserDTO> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Users";
            pstmt = conn.prepareCall(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                UserDTO user = new UserDTO();
                user.setUserId(rs.getString("userId"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPhoto(rs.getString("photo"));
                user.setRoleId(rs.getInt("roleId"));
                user.setPassword("123");
                list.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
//    public List<UserDTO> findAll() {
//        List<UserDTO> list = new ArrayList<>();
//
//        try {
//            String sql = "SELECT * FROM Users";
//            pstmt = conn.prepareCall(sql);
//            rs = pstmt.executeQuery();
//            while (rs.next()) {
//                UserDTO b = new UserDTO();
//                b.setUserId(rs.getString(1));
//                b.setUsername(rs.getString(2));
//                b.setEmail(rs.getString(3));
//                b.setPhoto(rs.getString(4));
//                b.setRoleId(rs.getInt(5));
//                b.setPassword(rs.getString(6));
//
//                list.add(b);
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return list;
//    }

    public int saveUser(UserDTO newUser) {
        int row = 0;
        try {
            String sql = "INSERT INTO Users VALUES (?,?,?,?,?,?)";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newUser.getUserId());
            pstmt.setString(2, newUser.getUsername());
            pstmt.setString(3, newUser.getEmail());
            pstmt.setString(4, newUser.getPhoto());
            pstmt.setInt(5, (newUser.getRoleId() != null) ? newUser.getRoleId() : 2);
            pstmt.setString(6, newUser.getPassword());
            
            row = pstmt.executeUpdate();
            return row;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                        rs.getInt("roleId")));
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
                        rs.getInt("roleId"));
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
    // 📌 Cập nhật mật khẩu người dùng

    public boolean changePassword(String userId, String newPassword) {
        String sql = "UPDATE Users SET password = ? WHERE userId = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newPassword);
            pstmt.setString(2, userId);
            
            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            System.out.println("Lỗi khi cập nhật mật khẩu: " + ex.getMessage());
        } finally {
            closeResources();
        }
        return false;
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
