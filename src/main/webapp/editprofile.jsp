<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Edit Profile</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow p-4">
                <h2 class="text-center mb-4">Edit Profile</h2>
                <% String error = (String) request.getAttribute("error"); %>
                <% if (error != null) { %>
                <div class="alert alert-danger" role="alert">
                    <%= error %>
                </div>
                <% } %>

                <form action="EditProfile" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="txtId" value="${user.getUserId()}">
                    <input type="hidden" name="txtRoleId" value="${user.getRoleId()}">

                    <div class="mb-3">
                        <label class="form-label">Username</label>
                        <input type="text" class="form-control" name="txtName" value="${user.getUsername()}" readonly>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Password</label>
                        <input type="password" class="form-control" name="txtPassword" value="${user.password}" placeholder="Enter password">
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Email</label>
                        <input type="email" class="form-control" name="txtEmail" value="${user.email}">
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Profile Image</label>
                        <input type="text" class="form-control" name="txtPhoto" value="${user.photo}">
                        <input type="file" class="form-control" name="txtImage">
                    </div>

                    <div class="text-center mb-3">
                                                <img src="images/${user.photo}?v=${Math.random()}" class="img-thumbnail" width="100" alt="${user.getPhoto()}">
                    </div>

                    <div class="text-center">
                        <button type="submit" class="btn btn-primary" name="action-edit">Update</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>