/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package fpt.aptech.wcd_edunext.servlet;

import dao.*;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.util.*;
import models.*;

/**
 *
 * @author User
 */
public class BookServlet extends HttpServlet {

    ReviewDAO dao;

    public BookServlet() {
        dao = new ReviewDAO();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            String sort = request.getParameter("sort");
            if (null == action) {
                List<Book> books;

                if (sort != null) {
                    books = dao.findAllBooksSorted(sort);
                } else {
                    books = dao.findAllBooks(); 
                }

                List<Map<String, Object>> bookList = new ArrayList<>();

                for (Book book : books) {
                    double avgRating = dao.countgetAverageRating(book.getBookId());
                    long totalReviewsBook = dao.countTotalReviews(book.getBookId());
                    Map<String, Object> bookData = new HashMap<>();
                    bookData.put("averageRating", avgRating);
                    bookData.put("totalReviewsBook", totalReviewsBook);
                    bookData.put("book", book);
                    bookList.add(bookData);
                }
                request.setAttribute("blist", bookList);
                request.setAttribute("sort", sort);
                request.getRequestDispatcher("indexBook.jsp").forward(request, response);
            } else {
                switch (action) {
                    case "Search":
                        String keyword = request.getParameter("txtSearch");
                        if (keyword == null || keyword.trim().isEmpty()) {
                            response.sendRedirect("BookServlet");
                            return;
                        }
                        List<Book> searchResults = dao.searchBooks(keyword);
                        List<Map<String, Object>> searchBookList = new ArrayList<>();

                        for (Book book : searchResults) {
                            double avgRating = dao.countgetAverageRating(book.getBookId());
                            long totalReviewsBook = dao.countTotalReviews(book.getBookId());
                            Map<String, Object> bookData = new HashMap<>();
                            bookData.put("averageRating", avgRating);
                            bookData.put("totalReviewsBook", totalReviewsBook);
                            bookData.put("book", book);
                            searchBookList.add(bookData);
                        }

                        request.setAttribute("blist", searchBookList);
                        request.setAttribute("txtSearch", keyword);
                        request.getRequestDispatcher("indexBook.jsp").forward(request, response);
                        break;
                    case "Detail":
                        int deId = Integer.parseInt(request.getParameter("id"));
                        Book b = dao.findOneBook(deId);
                        request.setAttribute("book", b);

                        //Phan trang
                        int page = 1;
                        int pageSize = 5;
                        int rating = 0;

                        if (request.getParameter("page") != null) {
                            page = Integer.parseInt(request.getParameter("page"));
                        }
                        if (request.getParameter("stars") != null && !request.getParameter("stars").isEmpty()) {
                            rating = Integer.parseInt(request.getParameter("stars"));
                        }
                        List<Review> rlist = dao.findReviewsByBookId(deId, rating, page, pageSize);
                        request.setAttribute("rlist", rlist);
                        if (rlist.isEmpty()) {
                            request.setAttribute("emptyList", "📢 No reviews yet! Be the first to review this book");
                        }
                        long totalReviews = dao.countReviewsByBookId(deId, rating);
                        int totalPages = (int) Math.ceil(totalReviews * 1.0 / pageSize);

                        request.setAttribute("currentPage", page);
                        request.setAttribute("totalPages", totalPages);
                        request.setAttribute("starsFilter", rating);
                        List<Long> reviewCounts = dao.countReviewsByRating(deId);
                        long totalCount = dao.countTotalReviews(deId);
                        request.setAttribute("totalCount", totalCount);
                        request.setAttribute("rCount", reviewCounts);
                        request.getRequestDispatcher("detailBook.jsp").forward(request, response);
                        break;
                    case "AddReview":
                        String userId = request.getParameter("userId");
                        int bookId = Integer.parseInt(request.getParameter("bookId"));
                        String ratingParam = request.getParameter("rating");
                        String comment = request.getParameter("comment");

                        if (ratingParam == null || ratingParam.trim().isEmpty()) {
                            request.setAttribute("error", "Please select rating before submit!");
                            request.getRequestDispatcher("BookServlet?action=Detail&id=" + bookId).forward(request, response);
                            break;
                        }
                        int newRating = Integer.parseInt(ratingParam);

                        Review newReview = new Review();
                        newReview.setUser(dao.findOneUser(userId));
                        newReview.setBook(dao.findOneBook(bookId));
                        newReview.setRating(newRating);
                        newReview.setReviewContent(comment);
                        dao.addReview(newReview);
                        response.sendRedirect("BookServlet?action=Detail&id=" + bookId + "#filterReview");
                        break;
                    default:
                        throw new AssertionError();
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
