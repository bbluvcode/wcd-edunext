/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package fpt.aptech.wcd_edunext.servlet;

import fpt.aptech.wcd_edunext.dao.UserDAO;
import fpt.aptech.wcd_edunext.dto.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

/**
 *
 * @author Admin
 */
@MultipartConfig
public class HomeServlet extends HttpServlet {

    UserDAO dao;
    private String imagePath;

    public HomeServlet() {
        dao = new UserDAO();
        imagePath = getServletContext().getRealPath("/") + "src/main/webapp/images/";
//        File imageDir = new File(imagePath);
//        if (!imageDir.exists()) {
//            imageDir.mkdirs(); // Tạo thư mục nếu chưa có
//        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            System.out.println("project HELLO");
            String action = request.getParameter("action");
            if (null == action) {
//                List<UserDTO> bList = dao.namemethod();
//                request.setAttribute("list", bList);
                request.getRequestDispatcher("register.jsp").forward(request, response);
            } else {
                switch (action) {
                    case "Register":
                        registerUser(request, response);
                        break;

                    default:
                        throw new AssertionError();
                }
            }
        }
    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // 📌 Lấy dữ liệu từ form
            String username = request.getParameter("txtUsername");
            String email = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");
            Part photoPart = request.getPart("file");

            // 📌 Kiểm tra dữ liệu đầu vào
            if (username == null || email == null || password == null
                    || username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                request.setAttribute("error", "Vui lòng điền đầy đủ thông tin.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }

            // 📌 Lưu ảnh và lấy tên file
            String fileName = saveImage(photoPart);

            // 📌 Tạo đối tượng UserDTO
            UserDTO newUser = new UserDTO(null, username, email, fileName, password, 2);

            // 📌 Lưu vào database qua DAO
            int row = dao.addUser(newUser);
            if (row > 0) {
                response.sendRedirect("register_success.jsp");
            } else {
                request.setAttribute("error", "Đăng ký thất bại, vui lòng thử lại.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", "Lỗi database: " + e.getMessage());
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }

    private String saveImage(Part photoPart) throws IOException {
        if (photoPart != null && photoPart.getSize() > 0) {
            String fileName = photoPart.getSubmittedFileName();
            String filePath = imagePath + fileName;
            try (InputStream is = photoPart.getInputStream(); FileOutputStream os = new FileOutputStream(filePath)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = is.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
            }
            return fileName;
        }
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
