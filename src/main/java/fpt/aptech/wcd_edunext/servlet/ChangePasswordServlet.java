package fpt.aptech.wcd_edunext.servlet;

import fpt.aptech.wcd_edunext.dao.UserDAO;
import fpt.aptech.wcd_edunext.dto.UserDTO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ChangePasswordServlet extends HttpServlet {

    private final UserDAO userDAO;

    public ChangePasswordServlet() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String userId = (String) session.getAttribute("userId");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        UserDTO user = userDAO.getUserById(userId);
        if (user == null) {
            System.out.println("❌ User not found in database.");
            request.setAttribute("error", "User not found.");
            request.getRequestDispatcher("detail.jsp").forward(request, response);
            return;
        }

        if (user.getPassword() == null) {
            System.out.println("❌ Password is NULL in object.");
            request.setAttribute("error", "Your password is not set. Please contact support.");
            request.getRequestDispatcher("detail.jsp").forward(request, response);
            return;
        }

        // Kiểm tra mật khẩu cũ có đúng không
        if (!user.getPassword().equals(oldPassword)) {
            request.setAttribute("error", "Old password is incorrect.");
            request.getRequestDispatcher("detail.jsp").forward(request, response);
            return;
        }

        // Kiểm tra mật khẩu mới có trùng với mật khẩu cũ không
        if (oldPassword.equals(newPassword)) {
            request.setAttribute("error", "New password cannot be the same as the old password.");
            request.getRequestDispatcher("detail.jsp").forward(request, response);
            return;
        }

        // Kiểm tra mật khẩu mới có trùng với xác nhận mật khẩu không
        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("error", "New passwords do not match.");
            request.getRequestDispatcher("detail.jsp").forward(request, response);
            return;
        }

        // Cập nhật mật khẩu mới
        boolean isUpdated = userDAO.changePassword(userId, newPassword);
        if (isUpdated) {
            request.setAttribute("message", "Password changed successfully!");
        } else {
            request.setAttribute("error", "Failed to change password.");
        }
        request.getRequestDispatcher("detail.jsp").forward(request, response);
    }
}
