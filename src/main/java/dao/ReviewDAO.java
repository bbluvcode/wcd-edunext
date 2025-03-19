/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import jakarta.persistence.*;
import java.util.*;
import models.*;

/**
 *
 * @author User
 */
public class ReviewDAO {

    EntityManagerFactory emf;
    EntityManager em;

    public ReviewDAO() {
        emf = Persistence.createEntityManagerFactory("mini");
        em = emf.createEntityManager();
    }

    public List<Book> findAllBooks() {
        return em.createNamedQuery("Book.findAll", Book.class).getResultList();
    }

    public List<Book> searchBooks(String keyword) {
        return em.createNamedQuery("Book.searchByTitleAuthorGenre", Book.class)
                .setParameter("keyword", keyword)
                .getResultList();
    }

    public List<Book> findAllBooksSorted(String sort) {
        String queryName;
        switch (sort) {
            case "totalReviews":
                queryName = "Book.sortByTotalReviews";
                break;
            case "averageRating":
                queryName = "Book.sortByAverageRating";
                break;
            default:
                queryName = "Book.findAll"; 
        }

        TypedQuery<Book> query = em.createNamedQuery(queryName, Book.class);
        return query.getResultList();
    }

    public User findOneUser(String id) {
        return em.createNamedQuery("User.findOne", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public Book findOneBook(int id) {
        return em.createNamedQuery("Book.findOne", Book.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public List<Review> findReviewsByBookId(int id, int rating, int page, int pageSize) {

        TypedQuery<Review> query = em.createNamedQuery("Review.findReviewsByBookId", Review.class)
                .setParameter("bookId", id)
                .setParameter("rating", rating == 0 ? null : rating);

        // Phan trang
        query.setFirstResult((page - 1) * pageSize);
        query.setMaxResults(pageSize);

        return query.getResultList();
    }

    public long countReviewsByBookId(int bookId, int rating) {
        return em.createNamedQuery("Review.countReviewsByBookId", Long.class)
                .setParameter("bookId", bookId)
                .setParameter("rating", rating == 0 ? null : rating)
                .getSingleResult();
    }

    public long countTotalReviews(int bookId) {
        return em.createNamedQuery("Review.countAllReviews", Long.class)
                .setParameter("bookId", bookId)
                .getSingleResult();
    }

    public double countgetAverageRating(int bookId) {
        Double avg = em.createNamedQuery("Review.getAverageRating", Double.class)
                .setParameter("bookId", bookId)
                .getSingleResult();

        return (avg != null) ? Math.round(avg * 10.0) / 10.0 : 0.0;
    }

    public List<Long> countReviewsByRating(int bookId) {
        List<Object[]> results = em.createNamedQuery("Review.countByRating", Object[].class)
                .setParameter("bookId", bookId)
                .getResultList();

        Map<Integer, Long> reviewMap = new HashMap<>();
        for (int i = 1; i <= 5; i++) {
            reviewMap.put(i, 0L);
        }

        for (Object[] row : results) {
            int rating = (int) row[0];
            long count = (long) row[1];
            reviewMap.put(rating, count);
        }

        return Arrays.asList(
                reviewMap.get(5),
                reviewMap.get(4),
                reviewMap.get(3),
                reviewMap.get(2),
                reviewMap.get(1)
        );
    }

    public void addReview(Review newObj) {
        try {
            em.getTransaction().begin();
            em.persist(newObj);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            em.getTransaction().rollback();
        }
    }

}
