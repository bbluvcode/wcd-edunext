<%-- 
    Document   : register
    Created on : Mar 12, 2025, 1:57:06 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <link href="static/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <style>
            body {
                background-color: #f8f9fa;
            }
            .register-container {
                max-width: 500px;
                margin: 50px auto;
                padding: 30px;
                background: white;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="register-container">
                <h2 class="text-center mb-4">Đăng ký tài khoản</h2>
                <form action="HomeServlet" method="post" enctype="multipart/form-data">
                    <!-- Username -->
                    <div class="mb-3">
                        <label class="form-label">Tên người dùng</label>
                        <input type="text" class="form-control" placeholder="Nhập tên của bạn" required>
                    </div>

                    <!-- Email -->
                    <div class="mb-3">
                        <label class="form-label">Email</label>
                        <input type="email" class="form-control" placeholder="Nhập email" required>
                    </div>

                    <!-- Password -->
                    <div class="mb-3">
                        <label class="form-label">Mật khẩu</label>
                        <input type="password" class="form-control" placeholder="Nhập mật khẩu" required>
                    </div>

                    <!-- Confirm Password -->
                    <div class="mb-3">
                        <label class="form-label">Xác nhận mật khẩu</label>
                        <input type="password" class="form-control" placeholder="Nhập lại mật khẩu" required>
                    </div>

                    <!-- Upload Photo -->
                    <div class="mb-3">
                        <label class="form-label">Ảnh đại diện</label>
                        <input type="file" class="form-control">
                    </div>

                    <!-- Submit Button -->
                    <input type="submit" class="btn btn-primary w-100" value="Register" name="action">

                    <!-- Login Link -->
                    <div class="text-center mt-3">
                        <p>Đã có tài khoản? <a href="#">Đăng nhập</a></p>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
