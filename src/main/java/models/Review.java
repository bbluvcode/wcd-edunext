/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import lombok.*;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author User
 */
@Entity
@Table(name = "Reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({
    @NamedQuery(
            name = "Review.findReviewsByBookId",
            query = "SELECT r FROM Review r WHERE r.book.bookId = :bookId"
            + " AND (:rating IS NULL OR r.rating = :rating)"
            + " ORDER BY r.reviewDate DESC"
    ),
    @NamedQuery(
            name = "Review.countReviewsByBookId",
            query = "SELECT COUNT(r) FROM Review r WHERE r.book.bookId = :bookId "
            + "AND (:rating IS NULL OR r.rating = :rating)"
    ),
    @NamedQuery(
            name = "Review.countByRating",
            query = "SELECT r.rating, COUNT(r) FROM Review r WHERE r.book.bookId = :bookId"
            + " GROUP BY r.rating "
            + "ORDER BY r.rating DESC"
    ),
    @NamedQuery(
            name = "Review.countAllReviews",
            query = "SELECT COUNT(r) FROM Review r WHERE r.book.bookId = :bookId"
    ),
    @NamedQuery(
            name = "Review.getAverageRating",
            query = "SELECT COALESCE(AVG(r.rating), 0) FROM Review r WHERE r.book.bookId = :bookId"
    )
})

public class Review implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "reviewId")
    private Integer reviewId;

    @Column(name = "rating")
    private Integer rating;

    @Lob
    @Column(name = "reviewContent")
    private String reviewContent;

    @Column(name = "reviewDate", updatable = false)
    private LocalDateTime reviewDate = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "bookid", nullable = false)
    private Book book;
}
