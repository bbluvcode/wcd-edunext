<%-- 
    Document   : indexBook
    Created on : Mar 12, 2025, 11:38:47 AM
    Author     : User
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <title>Book List</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="css/indexBook.css">
    </head>
    <body style="background: linear-gradient(to bottom right, #fceabb, #f8b500);
          font-family: Arial, sans-serif;
          min-height: 100vh;
          display: flex;
          flex-direction: column;
          align-items: center;">
        <div class="container mt-4">
            <h2 class="text-center mb-4">Book List</h2>
            <div class="d-flex justify-content-center mt-3 mb-3">
                <form action="BookServlet" method="GET" class="d-flex w-50">
                    <input type="text" name="txtSearch" class="form-control me-2" placeholder="Search by name, author, or genre" value="${param.txtSearch}">
                    <button type="submit" class="btn btn-primary" name="action" value="Search">Search</button>
                </form>
            </div>
            <div class="row">
                <c:forEach items="${blist}" var="bookList">
                    <c:set var="book" value="${bookList.book}" />
                    <c:set var="averageRating" value="${bookList.averageRating}" />
                    <c:set var="totalReviewsBook" value="${bookList.totalReviewsBook}" />
                    <div class="col-md-4 mb-4">
                        <div class="card h-100 shadow-sm custom-card">
                            <div class="row g-0">
                                <div class="col-md-4 d-flex flex-column align-items-center">
                                    <img src="ImageBooks/${book.getPhoto()}" class="mt-3 ms-3 img-fluid rounded"
                                         style="width: 150px; height: 180px; object-fit: cover;" 
                                         alt="Book Image">
                                </div>
                                <div class="col-md-8">
                                    <div class="card-body">
                                        <h4 class="card-title text-center">${book.getTitle()}</h4>
                                        <p class="card-text">
                                            <strong>Author: </strong> ${book.getAuthor()} <br>
                                            <strong>Publisher: </strong> ${book.publisherId.getName()} <br>                                           
                                            <strong>Edition: </strong> ${book.getEdition()} <br>
                                            <strong>Genre: </strong> ${book.getGenre()} <br>
                                        </p>
                                    </div>
                                </div>
                                <div class="mt-auto d-flex justify-content-between align-items-center">
                                    <p class="mt-2 ms-2 text-center fs-5">${averageRating} ⭐ (${totalReviewsBook} reviews)</p>
                                    <button class="me-5 mb-4 detailCard">
                                        <a href="BookServlet?action=Detail&id=${book.getBookId()}">Detail</a>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </body>
</html>