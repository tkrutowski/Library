package pl.sda.library.infrastructure.jpa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import pl.sda.library.domain.model.Book;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "books")
class BookDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_series")
    private SeriesDto series;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(name = "books_authors",
            joinColumns = {@JoinColumn(name = "id_book")},
            inverseJoinColumns = {@JoinColumn(name = "id_author")})
    private Set<AuthorDto> authors = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(name = "books_categories",
            joinColumns = {@JoinColumn(name = "id_book")},
            inverseJoinColumns = {@JoinColumn(name = "id_category")})
    private Set<CategoryDto> categories = new HashSet<>();

    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String cover;
    private int bookInSeriesNo;

    Book toDomain() {
        Book book = new Book();
        book.setId(getId());
        book.setSeries(series == null ? "" : getSeries().getTitle());
        book.setAuthors(getAuthorsAsString());
        book.setCategories(getCategoriesAsString());
        book.setTitle(getTitle());
        book.setDescription(getDescription());
        book.setCover(getCover());
        book.setBookInSeriesNo(getBookInSeriesNo());
        return book;
    }

    private String getAuthorsAsString() {
        String result = "";
        List<AuthorDto> sortedList = authors
                .stream()
                .sorted(Comparator.comparing(AuthorDto::getLastName))
                .collect(Collectors.toList());
        for (AuthorDto authorDto : sortedList) {
            result += authorDto.getFirstName() + " " + authorDto.getLastName() + ", ";
        }
        int index = result.lastIndexOf(',');
        return index > 0 ? result.substring(0, index) : result;
    }

    private String getCategoriesAsString() {
        String result = "";
        for (CategoryDto categoryDto : categories) {
            result += categoryDto.getName() + ", ";
        }
        int index = result.lastIndexOf(',');
        return index > 0 ? result.substring(0, index) : result;
    }
}
