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
                background: linear-gradient(135deg, #1e1e2f, #12121b);
                font-family: 'Roboto', sans-serif;
                color: #eaeaea;
                margin: 0;
                padding: 0;
            }

            .register-container {
                max-width: 500px;
                margin: 50px auto;
                padding: 30px;
                background: linear-gradient(135deg, #2a2a3b, #1e1e2f);
                border-radius: 15px;
                box-shadow: 0 10px 20px rgba(0, 0, 0, 0.5);
                border: 1px solid #3a3a4f;
            }

            .register-container h2 {
                font-size: 2rem;
                font-weight: bold;
                color: #00d4ff;
                text-align: center;
                margin-bottom: 20px;
                text-shadow: 0 2px 4px rgba(0, 212, 255, 0.5);
            }

            .form-label {
                font-weight: 500;
                margin-bottom: 5px;
                color: #00d4ff;
                text-transform: uppercase;
                letter-spacing: 0.5px;
            }

            .form-control {
                border-radius: 8px;
                border: 1px solid #3a3a4f;
                background-color: #1e1e2f;
                color: #eaeaea;
                padding: 12px;
                font-size: 0.95rem;
                transition: border-color 0.3s ease, box-shadow 0.3s ease;
            }

            .form-control:focus {
                border-color: #00d4ff;
                box-shadow: 0 0 10px rgba(0, 212, 255, 0.7);
            }

            .btn-primary {
                background: linear-gradient(135deg, #00d4ff, #007bff);
                border: none;
                padding: 12px 20px;
                font-size: 1rem;
                font-weight: bold;
                border-radius: 8px;
                color: #1e1e2f;
                text-transform: uppercase;
                transition: background 0.3s ease, box-shadow 0.3s ease;
            }

            .btn-primary:hover {
                background: linear-gradient(135deg, #00a3cc, #0056b3);
                box-shadow: 0 8px 16px rgba(0, 163, 204, 0.7);
            }

            .text-danger {
                font-size: 0.85rem;
                margin-top: 5px;
                color: #ff4d4d;
            }

            .text-center a {
                color: #00d4ff;
                text-decoration: none;
                font-weight: bold;
                transition: color 0.3s ease;
            }

            .text-center a:hover {
                color: #00a3cc;
                text-decoration: underline;
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
                    <div class="text-center mt-3"></div>
                        <p>Already have an account? <a href="login.jsp">Login</a></p>
                    </div>
                </form>
            </div>
        </div>
    </body>

</html>