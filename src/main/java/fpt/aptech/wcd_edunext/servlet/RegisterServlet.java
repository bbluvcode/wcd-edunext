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
import java.util.List;

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
                        if (p != null) {
                            String fileName = p.getSubmittedFileName();
                            UserDTO b = new UserDTO(email, userName, email, fileName, password, 2);
                            int row = dao.saveUser(b);
                            if (row > 0) {
                                out.println("<h3>Completed...</h3>");
                                request.getRequestDispatcher("index.jsp").include(request, response);

                            }
                            //Luu file vao thu muc
                            try {
                                String dir = "D:/APTECH/Term4/WCD/demo/wcd_edunext/src/main/webapp/images/" + fileName;
                                FileOutputStream os = new FileOutputStream(dir);
                                InputStream is = p.getInputStream();
                                byte[] data = new byte[is.available()];
                                is.read(data);
                                os.write(data);
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
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
