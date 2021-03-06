package pl.sda.library.infrastructure.upolujebooka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.library.domain.model.Book;
import pl.sda.library.domain.model.EditionType;

@Data
@Builder (toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class UpolujebookaProductDto {
    private String bookstore;
    private String authors;
    private String title;
    private String description;
    private String cover;
    private String editionType;
    private String isbn;

    public Book toDomain(){
        return Book.builder()
                .authors(this.authors)
                .title(this.title)
                .description(this.description)
                .cover(this.cover)
                .build();
    }

    private EditionType convertEditionType(String editionType) {
        EditionType returnedValue;
        //    BOOK, EBOOK, AUDIOBOOK
        if(editionType == "BOOK") {
            returnedValue = EditionType.BOOK;
        }else if (editionType == "EBOOK"){
            returnedValue = EditionType.EBOOK;
        }
        else {
            returnedValue = EditionType.AUDIOBOOK;
        }
        return returnedValue;
    }
}
