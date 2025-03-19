<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="static/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <title>User List</title>
    </head>
    <body>
        <div class="container">
            <h1>User List</h1>

            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>User ID</th>
                        <th>Username</th>
                        <th>Email</th>
                        <th>Photo</th>
                        <th>Role ID</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${userList}">
                    <tr>
                        <td>${user.userId}</td>
                        <td>${user.username}</td>
                        <td>${user.email}</td>
                        <td><img src="images/${user.photo}" alt="User Photo" width="50" height="50"/></td>
                        <td>${user.roleId}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <a href="LoginServlet?action=logout" class="btn btn-danger">Logout</a>
        </div>
    </body>
</html>