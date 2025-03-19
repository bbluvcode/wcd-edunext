package fpt.aptech.wcd_edunext.servlet;

import fpt.aptech.wcd_edunext.dao.UserDAO;
import fpt.aptech.wcd_edunext.dto.UserDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;

import java.io.*;

@MultipartConfig(maxFileSize = 16172110)
public class EditProfile extends HttpServlet {
    private final UserDAO dao;

    public EditProfile() {
        dao = new UserDAO();
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String action = req.getParameter("action-edit");
            String userId = req.getParameter("userId");

            if (action == null) {
                UserDTO user = dao.getUserById(userId);
                req.setAttribute("user", user);
                req.getRequestDispatcher("editprofile.jsp").forward(req, resp);
            } else {
                String id = req.getParameter("txtId");
                String name = req.getParameter("txtName");
                String email = req.getParameter("txtEmail");
                String password = req.getParameter("txtPassword");
                String roleId = req.getParameter("txtRoleId");
                String photo = req.getParameter("txtPhoto");
                System.out.println(photo);

                if (password.length() < 6 || password.length() > 20) {
                    req.setAttribute("error", "Password must be between 6 and 20 characters.");
                    req.setAttribute("user", new UserDTO(id, name, email, photo, password, Integer.parseInt(roleId))); // Giữ lại thông tin
                    req.getRequestDispatcher("editprofile.jsp?userId=" + id).forward(req, resp);
                    return;
                }

                Part part = req.getPart("txtImage");
                String fileName = part.getSubmittedFileName();
                if (fileName != null && !fileName.isEmpty()) {
                    String targetImagePath = getServletContext().getRealPath("/images/") + File.separator + fileName;

                    String projectImagePath = "D:/T1.2308.A0/16.Jakarta_Server_Service/WCD/wcd-edunext/src/main/webapp/images/" + fileName;

                    File targetDir = new File(getServletContext().getRealPath("/images/"));
                    if (!targetDir.exists()) {
                        targetDir.mkdirs();
                    }

                    File projectDir = new File("D:/T1.2308.A0/16.Jakarta_Server_Service/WCD/wcd-edunext/src/main/webapp/images/");
                    if (!projectDir.exists()) {
                        projectDir.mkdirs();
                    }

                    try (InputStream inputStream = part.getInputStream();
                         FileOutputStream targetOutputStream = new FileOutputStream(targetImagePath);
                         FileOutputStream projectOutputStream = new FileOutputStream(projectImagePath)) {

                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            targetOutputStream.write(buffer, 0, bytesRead);
                            projectOutputStream.write(buffer, 0, bytesRead);
                        }
                    }
                }
                assert fileName != null;
                if (fileName.isEmpty())
                    fileName = req.getParameter("txtPhoto");

                UserDTO userDAO = new UserDTO(id, name, email, fileName, password, Integer.parseInt(roleId));
                dao.updateUser(userDAO);

                resp.sendRedirect("index.jsp");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}