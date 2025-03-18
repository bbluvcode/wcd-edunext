package fpt.aptech.wcd_edunext.servlet;

import fpt.aptech.wcd_edunext.dao.LoginDAO;
import fpt.aptech.wcd_edunext.models.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

    private final LoginDAO dao;

    public LoginServlet() {
        dao = new LoginDAO();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        if (action == null) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        if ("login".equals(action)) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            User user = dao.checkLogin(username, password);

            if (user != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("userId", user.getUserId());
                session.setAttribute("roleId", user.getRoleId());

                // Điều hướng dựa vào roleId
                if (user.getRoleId() == 1) {
                    response.sendRedirect("RegisterServlet");
                } else if (user.getRoleId() == 2 || user.getRoleId() == 3) {
                    response.sendRedirect("detail.jsp");
                } else {
                    response.sendRedirect("error.jsp"); // Nếu có roleId không hợp lệ
                }

            } else {
                request.setAttribute("error", "Invalid credentials");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } else {
            throw new AssertionError();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Login Servlet";
    }
}
