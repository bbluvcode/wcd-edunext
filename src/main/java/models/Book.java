/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import lombok.*;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author User
 */
@Entity
@Table(name = "Books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({
    @NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b"),
    @NamedQuery(name = "Book.findOne", query = "SELECT b FROM Book b WHERE b.bookId = :id"),
    @NamedQuery(
            name = "Book.searchByTitleAuthorGenre",
            query = "SELECT b FROM Book b WHERE "
            + "LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "
            + "LOWER(b.author) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "
            + "LOWER(b.genre) LIKE LOWER(CONCAT('%', :keyword, '%'))"
    )
})
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "bookId")
    private Integer bookId;
    @Basic(optional = false)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @Column(name = "author")
    private String author;
    @Basic(optional = false)
    @Column(name = "edition")
    private int edition;
    @Column(name = "genre")
    private String genre;
    @Basic(optional = false)
    @Column(name = "photo")
    private String photo;
    @Column(name = "description")
    private String description;

    @JoinColumn(name = "publisherId", referencedColumnName = "publisherId")
    @ManyToOne
    private Publisher publisherId;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Review> reviews;

}
