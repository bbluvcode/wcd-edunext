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
import jakarta.servlet.http.*;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
@MultipartConfig(maxFileSize = 161721111)
public class RegisterServlet extends HttpServlet {

    UserDAO dao;

    public RegisterServlet() {
        dao = new UserDAO();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");
            if (null == action) {
                List<UserDTO> bList = dao.findAll();
                request.setAttribute("list", bList);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } else {
                switch (action) {//
                    case "Register":
                        String userName = request.getParameter("txtUsername");
                        String email = request.getParameter("txtEmail");
                        String photo = request.getParameter("file");
                        String password = request.getParameter("txtPassword");
                        Part p = request.getPart("file");

                        Map<String, String> errors = new HashMap<>();
                        if (p != null) {
                            if (userName.isEmpty()) {
                                errors.put("username", "Username is required");
                            } else {
                                if (userName.length() < 6) {
                                    errors.put("username", "Username must be greater than 6 characters");
                                }
                            }
                            if (email.isEmpty()) {
                                errors.put("email", "Email is required");
                            } else {
                                if (!email.matches("\\w+@\\w+\\.\\w+")) {
                                    errors.put("email", "Email is invalid");
                                }
                            }
                            if (password.isEmpty()) {
                                errors.put("password", "Password is required");
                            } else {
                                if (password.length() < 6) {
                                    errors.put("password", "Password must be greater than 6 characters");
                                }
                            }
                            if (!errors.isEmpty()) {
                                request.setAttribute("errors", errors);
                                request.setAttribute("username", userName);
                                request.setAttribute("email", email);
                                request.setAttribute("password", password);
                                System.out.println("Validation errors: " + errors); // Debug log
                                request.getRequestDispatcher("register.jsp").forward(request, response);
                                return; // Prevent further execution if there are validation errors
                            }
                            ///////////////////////////////////////////////
                            ////////////////////////////////
                            String fileName = p.getSubmittedFileName();
                            UserDTO b = new UserDTO(email, userName, email, fileName, password, 2);
                            int row = dao.saveUser(b);
                            if (row > 0) {
                                System.out.println("User registered successfully: " + userName); // Debug log
                                List<UserDTO> ulist = dao.findAll();
                                request.setAttribute("list", ulist);
                                request.getRequestDispatcher("index.jsp").forward(request, response);
                            } else {
                                System.out.println("Failed to register user: " + userName); // Debug log
                                request.setAttribute("error", "Failed to register user. Please try again.");
                                request.getRequestDispatcher("register.jsp").forward(request, response);
                            }
                            // Save file to directory
                            try {
                                String dir = "D:/APTECH/Term4/WCD/demo/wcd_edunext/src/main/webapp/images/" + fileName;
                                FileOutputStream os = new FileOutputStream(dir);
                                InputStream is = p.getInputStream();
                                byte[] data = new byte[is.available()];
                                is.read(data);
                                os.write(data);
                                os.close();
                                is.close();
                                System.out.println("File uploaded successfully: " + fileName); // Debug log
                                return;
                            } catch (Exception e) {
                                System.out.println("File upload error: " + e.getMessage()); // Debug log
                            }
                        } else {
                            errors.put("photo", "Photo is required");
                        }
                        break;
                    case "Edit":
                        break;
                    case "Delete":
                        break;
                    default:
                        throw new AssertionError();
                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
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
