<%-- 
    Document   : index
    Created on : Mar 10, 2025, 2:16:14 PM
    Author     : Admin
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="static/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
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
                        <th>Password</th>
                        <th>Role ID</th>
                    </tr>
                </thead>
                <tbody>                    
                   <c:forEach var="it" items="${list}">
                    <tr>
                        <td>${it.userId}</td>
                        <td>${it.username}</td>
                        <td>${it.email}</td>
                        <td><img src="images/${it.photo}" alt="${it.username}" width="50" height="50"/></td>
                        <td>${it.password}</td>
                        <td>${it.roleId}</td>                      
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
