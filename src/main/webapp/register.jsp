<%-- Document : register Created on : Mar 12, 2025, 1:57:06 PM Author : Admin --%>

    <%@page contentType="text/html" pageEncoding="UTF-8" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Register</title>
            <link href="static/bootstrap.min.css" rel="stylesheet" type="text/css" />
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
                    <h2 class="text-center mb-4">Register Account</h2>
                    <form action="RegisterServlet" method="post" enctype="multipart/form-data">
                        <!-- Username -->
                        <div class="mb-3">
                            <label class="form-label">Username</label>
                            <input type="text" class="form-control" name="txtUsername"
                                value="${empty username ? '' : username}" placeholder="Enter your username">
                            <c:if test="${not empty errors['username']}">
                                <p class="text-danger">${errors['username']}</p>
                            </c:if>
                        </div>

                        <!-- Email -->
                        <div class="mb-3">
                            <label class="form-label">Email</label>
                            <input type="email" class="form-control" name="txtEmail" value="${empty email ? '' : email}"
                                placeholder="Enter your email">
                            <c:if test="${not empty errors['email']}">
                                <p class="text-danger">${errors['email']}</p>
                            </c:if>
                        </div>

                        <!-- Password -->
                        <div class="mb-3">
                            <label class="form-label">Password</label>
                            <input type="password" class="form-control" name="txtPassword"
                                value="${empty password ? '' : password}" placeholder="Enter your password">
                            <c:if test="${not empty errors['password']}">
                                <p class="text-danger">${errors['password']}</p>
                            </c:if>
                        </div>

                        <!-- Confirm Password -->
                        <div class="mb-3">
                            <label class="form-label">Confirm Password</label>
                            <input type="password" class="form-control" name="txtConfirmPassword"
                                placeholder="Re-enter your password">
                            <c:if test="${not empty errors['confirmPassword']}">
                                <p class="text-danger">${errors['confirmPassword']}</p>
                            </c:if>
                        </div>

                        <!-- Upload Photo -->
                        <div class="mb-3">
                            <label class="form-label">Profile Picture</label>
                            <input type="file" class="form-control" name="file">
                            <c:if test="${not empty errors['photo']}">
                                <p class="text-danger">${errors['photo']}</p>
                            </c:if>
                        </div>

                        <!-- Submit Button -->
                        <input type="submit" class="btn btn-primary w-100" value="Register" name="action">

                        <!-- Login Link -->
                        <div class="text-center mt-3">
                            <p>Already have an account? <a href="login.jsp">Login</a></p>
                        </div>
                    </form>
                </div>
            </div>
        </body>

        </html>