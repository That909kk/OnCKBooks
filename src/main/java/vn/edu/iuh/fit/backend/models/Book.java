package vn.edu.iuh.fit.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "book")
public class Book {
    @Id
    @ColumnDefault("''")
    @Column(name = "isbn", nullable = false, length = 13)
    private String isbn;

    @Column(name = "title", length = 250)
    private String title;

    @Column(name = "authors", length = 300)
    private String authors;

    @Column(name = "total_pages")
    private Integer totalPages;

    @Column(name = "published_date")
    private LocalDate publishedDate;

}