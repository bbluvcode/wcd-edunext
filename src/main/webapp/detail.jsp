<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Change Password</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <div class="card shadow-lg p-4">
            <h2 class="text-center">Change Password</h2>
            <form action="ChangePasswordServlet" method="post">
                <div class="mb-3">
                    <label class="form-label">Old Password:</label>
                    <input type="password" name="oldPassword" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">New Password:</label>
                    <input type="password" name="newPassword" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Confirm New Password:</label>
                    <input type="password" name="confirmPassword" class="form-control" required>
                </div>
                <button type="submit" class="btn btn-primary w-100">Change Password</button>
            </form>

            <% if (request.getAttribute("error") != null) { %>
                <div class="alert alert-danger mt-3"><%= request.getAttribute("error") %></div>
            <% } %>

            <% if (request.getAttribute("message") != null) { %>
                <div class="alert alert-success mt-3"><%= request.getAttribute("message") %></div>
            <% } %>
        </div>
    </div>
</body>
</html>
