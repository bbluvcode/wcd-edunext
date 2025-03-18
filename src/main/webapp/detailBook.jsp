<%-- 
    Document   : detailBook
    Created on : Mar 12, 2025, 11:59:47 AM
    Author     : User
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <title>Book Detail</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="css/detailBook.css">
    </head>
    <body>
        <div class="container shadow-lg mt-4">
            <h2 class="text-center mb-4">Book Detail</h2>

            <!-- Book -->
            <div class="card p-4">
                <div class="row g-0">
                    <div class="col-md-4 d-flex align-items-center justify-content-center">
                        <img src="ImageBooks/${book.getPhoto()}" class="img-fluid w-100 rounded"
                             style="width: 100%; height: 300px; object-fit: contain;" 
                             alt="Book Image">
                    </div>
                    <div class="col-md-8">
                        <div class="card-body">
                            <h2 class="card-title text-center">${book.getTitle()}</h2>
                            <table class="table table-bordered fs-5">
                                <tbody>
                                    <tr>
                                        <th>Author</th>
                                        <td>${book.getAuthor()}</td>
                                    </tr>
                                    <tr>
                                        <th>Edition</th>
                                        <td>${book.getEdition()}</td>
                                    </tr> 
                                    <tr>
                                        <th>Genre</th>
                                        <td>${book.getGenre()}</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div>
                        <h5><strong>Description:</strong></h5>
                        <p id="description" class="text-muted fs-5" style="max-height: 60px; overflow: hidden;">
                            ${book.getDescription()}
                        </p>
                        <button class="btn btn-link p-0" onclick="toggleDescription()">Show More...</button>
                    </div>
                </div>
            </div>

            <!-- Add review -->
            <div class="card p-4 mt-5 w-75 mx-auto">
                <div class="d-flex justify-content-between align-items-center">
                    <h2 class="mb-0">Your Review</h2>
                    <form action="BookServlet?action=AddReview" method="post" class="w-75">
                        <input type="hidden" name="bookId" value="${book.getBookId()}">
                        <input type="hidden" name="userId" value="${userId}">
                        <div class="mb-3 d-flex">
                            <label class="form-label mt-2 me-5">Rating:</label>
                            <div class="rating">
                                <input value="5" name="rating" id="star5" type="radio" />
                                <label title="5 stars" for="star5">
                                    <svg
                                        stroke-linejoin="round"
                                        stroke-linecap="round"
                                        stroke-width="2"
                                        stroke="#000000"
                                        fill="none"
                                        viewBox="0 0 24 24"
                                        height="35"
                                        width="35"
                                        xmlns="http://www.w3.org/2000/svg"
                                        class="svgOne"
                                        >
                                    <polygon
                                        points="12 2 15.09 8.26 22 9.27 17 14.14 18.18 21.02 12 17.77 5.82 21.02 7 14.14 2 9.27 8.91 8.26 12 2"
                                        ></polygon>
                                    </svg>
                                    <svg
                                        stroke-linejoin="round"
                                        stroke-linecap="round"
                                        stroke-width="2"
                                        stroke="#000000"
                                        fill="none"
                                        viewBox="0 0 24 24"
                                        height="35"
                                        width="35"
                                        xmlns="http://www.w3.org/2000/svg"
                                        class="svgTwo"
                                        >
                                    <polygon
                                        points="12 2 15.09 8.26 22 9.27 17 14.14 18.18 21.02 12 17.77 5.82 21.02 7 14.14 2 9.27 8.91 8.26 12 2"
                                        ></polygon>
                                    </svg>
                                    <div class="ombre"></div>
                                </label>

                                <input value="4" name="rating" id="star4" type="radio" />
                                <label title="4 stars" for="star4">
                                    <svg
                                        stroke-linejoin="round"
                                        stroke-linecap="round"
                                        stroke-width="2"
                                        stroke="#000000"
                                        fill="none"
                                        viewBox="0 0 24 24"
                                        height="35"
                                        width="35"
                                        xmlns="http://www.w3.org/2000/svg"
                                        class="svgOne"
                                        >
                                    <polygon
                                        points="12 2 15.09 8.26 22 9.27 17 14.14 18.18 21.02 12 17.77 5.82 21.02 7 14.14 2 9.27 8.91 8.26 12 2"
                                        ></polygon>
                                    </svg>
                                    <svg
                                        stroke-linejoin="round"
                                        stroke-linecap="round"
                                        stroke-width="2"
                                        stroke="#000000"
                                        fill="none"
                                        viewBox="0 0 24 24"
                                        height="35"
                                        width="35"
                                        xmlns="http://www.w3.org/2000/svg"
                                        class="svgTwo"
                                        >
                                    <polygon
                                        points="12 2 15.09 8.26 22 9.27 17 14.14 18.18 21.02 12 17.77 5.82 21.02 7 14.14 2 9.27 8.91 8.26 12 2"
                                        ></polygon>
                                    </svg>
                                    <div class="ombre"></div>
                                </label>

                                <input value="3" name="rating" id="star3" type="radio" />
                                <label title="3 stars" for="star3">
                                    <svg
                                        stroke-linejoin="round"
                                        stroke-linecap="round"
                                        stroke-width="2"
                                        stroke="#000000"
                                        fill="none"
                                        viewBox="0 0 24 24"
                                        height="35"
                                        width="35"
                                        xmlns="http://www.w3.org/2000/svg"
                                        class="svgOne"
                                        >
                                    <polygon
                                        points="12 2 15.09 8.26 22 9.27 17 14.14 18.18 21.02 12 17.77 5.82 21.02 7 14.14 2 9.27 8.91 8.26 12 2"
                                        ></polygon>
                                    </svg>
                                    <svg
                                        stroke-linejoin="round"
                                        stroke-linecap="round"
                                        stroke-width="2"
                                        stroke="#000000"
                                        fill="none"
                                        viewBox="0 0 24 24"
                                        height="35"
                                        width="35"
                                        xmlns="http://www.w3.org/2000/svg"
                                        class="svgTwo"
                                        >
                                    <polygon
                                        points="12 2 15.09 8.26 22 9.27 17 14.14 18.18 21.02 12 17.77 5.82 21.02 7 14.14 2 9.27 8.91 8.26 12 2"
                                        ></polygon>
                                    </svg>
                                    <div class="ombre"></div>
                                </label>

                                <input value="2" name="rating" id="star2" type="radio" />
                                <label title="2 stars" for="star2">
                                    <svg
                                        stroke-linejoin="round"
                                        stroke-linecap="round"
                                        stroke-width="2"
                                        stroke="#000000"
                                        fill="none"
                                        viewBox="0 0 24 24"
                                        height="35"
                                        width="35"
                                        xmlns="http://www.w3.org/2000/svg"
                                        class="svgOne"
                                        >
                                    <polygon
                                        points="12 2 15.09 8.26 22 9.27 17 14.14 18.18 21.02 12 17.77 5.82 21.02 7 14.14 2 9.27 8.91 8.26 12 2"
                                        ></polygon>
                                    </svg>
                                    <svg
                                        stroke-linejoin="round"
                                        stroke-linecap="round"
                                        stroke-width="2"
                                        stroke="#000000"
                                        fill="none"
                                        viewBox="0 0 24 24"
                                        height="35"
                                        width="35"
                                        xmlns="http://www.w3.org/2000/svg"
                                        class="svgTwo"
                                        >
                                    <polygon
                                        points="12 2 15.09 8.26 22 9.27 17 14.14 18.18 21.02 12 17.77 5.82 21.02 7 14.14 2 9.27 8.91 8.26 12 2"
                                        ></polygon>
                                    </svg>
                                    <div class="ombre"></div>
                                </label>

                                <input value="1" name="rating" id="star1" type="radio" />
                                <label title="1 star" for="star1">
                                    <svg
                                        stroke-linejoin="round"
                                        stroke-linecap="round"
                                        stroke-width="2"
                                        stroke="#000000"
                                        fill="none"
                                        viewBox="0 0 24 24"
                                        height="35"
                                        width="35"
                                        xmlns="http://www.w3.org/2000/svg"
                                        class="svgOne"
                                        >
                                    <polygon
                                        points="12 2 15.09 8.26 22 9.27 17 14.14 18.18 21.02 12 17.77 5.82 21.02 7 14.14 2 9.27 8.91 8.26 12 2"
                                        ></polygon>
                                    </svg>
                                    <svg
                                        stroke-linejoin="round"
                                        stroke-linecap="round"
                                        stroke-width="2"
                                        stroke="#000000"
                                        fill="none"
                                        viewBox="0 0 24 24"
                                        height="35"
                                        width="35"
                                        xmlns="http://www.w3.org/2000/svg"
                                        class="svgTwo"
                                        >
                                    <polygon
                                        points="12 2 15.09 8.26 22 9.27 17 14.14 18.18 21.02 12 17.77 5.82 21.02 7 14.14 2 9.27 8.91 8.26 12 2"
                                        ></polygon>
                                    </svg>
                                    <div class="ombre"></div>
                                </label>
                            </div>
                        </div>
                        <div class="mb-3 d-flex">
                            <label class="form-label me-4">Comment:</label>
                            <textarea name="comment" class="form-control" rows="3"></textarea>
                        </div>
                        <button type="submit" class="btn btn-primary">Submit Your Review</button>
                    </form>
                </div>
            </div>
            <!-- All reviews -->
            <div class="card p-4 mt-5">
                <!-- Filter -->
                <div class="d-flex align-items-center mb-3">
                    <h4>All Reviews</h4>                 
                    <form method="GET" action="BookServlet" class="d-flex align-items-center gap-2 ms-4">
                        <input type="hidden" name="action" value="Detail" />
                        <input type="hidden" name="id" value="${book.bookId}" />
                        <select name="stars" id="starFilter" class="form-select form-select-sm">
                            <option value="">All Reviews (${totalCount})</option>
                            <option value="5" ${starsFilter == 5 ? 'selected' : ''}>⭐⭐⭐⭐⭐ (${rCount[0]})</option>
                            <option value="4" ${starsFilter == 4 ? 'selected' : ''}>⭐⭐⭐⭐ (${rCount[1]})</option>
                            <option value="3" ${starsFilter == 3 ? 'selected' : ''}>⭐⭐⭐ (${rCount[2]})</option>
                            <option value="2" ${starsFilter == 2 ? 'selected' : ''}>⭐⭐ (${rCount[3]})</option>
                            <option value="1" ${starsFilter == 1 ? 'selected' : ''}>⭐ (${rCount[4]})</option>
                        </select>
                        <button type="submit" class="btn btn-primary btn-sm">Filter</button>
                    </form>
                </div>
                <!-- Reviews -->
                <p class="text-danger text-center fs-5">${emptyList}</p>
                <c:forEach items="${rlist}" var="review">
                    <div class="card mb-3">
                        <div class="card-body">
                            <h6><strong>${review.getUser().getUsername()}</strong> - 
                                <c:forEach var="i" begin="1" end="${review.rating}">
                                    ⭐
                                </c:forEach>
                            </h6>
                            <p>${review.getReviewContent()}</p>
                            <small class="text-muted">${review.getReviewDate()}</small>
                        </div>
                    </div>
                </c:forEach>
                <!-- Pagination -->
                <nav aria-label="Page navigation">
                    <ul class="pagination justify-content-center">
                        <c:if test="${currentPage > 1}">
                            <li class="page-item">
                                <a class="page-link" href="${pageContext.request.contextPath}/BookServlet?action=Detail&id=${book.bookId}&page=${currentPage - 1}&stars=${starsFilter}" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:if>

                        <li class="page-item disabled">
                            <span class="page-link">Page ${currentPage} of ${totalPages}</span>
                        </li>

                        <c:if test="${currentPage < totalPages}">
                            <li class="page-item">
                                <a class="page-link" href="${pageContext.request.contextPath}/BookServlet?action=Detail&id=${book.bookId}&page=${currentPage + 1}&stars=${starsFilter}" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                </nav>

            </div>
            <div class="mt-4 text-center">
                <a href="BookServlet" class="btn btn-secondary">Back to Book List</a>
            </div>
        </div>
        <script>
            function toggleDescription() {
                let desc = document.getElementById("description");
                let btn = event.target;
                if (desc.style.maxHeight === "60px") {
                    desc.style.maxHeight = "none";
                    btn.innerText = "Hide...";
                } else {
                    desc.style.maxHeight = "60px";
                    btn.innerText = "Show More...";
                }
            }
        </script>
    </body>
</html>
