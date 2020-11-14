package pl.sda.library.domain.model;


import lombok.*;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Book {
    private Long idBook;
    private String userLogin;
    private String bookstore; //nazwa księgarni np. Empik
    private String series; //cykl książek np 'Jack Reacher'   !!!!!!!!!!
    private String authors; //autorzy: imie nazwisko            !!!!!!!!!!!
    private String categories; //kategorie np. Thriller, kryminał  !!!!!!!!!
    private String title; //tytuł                                 !!!!!!!!!!
    private String subtitle;//podtytuł jeżęli jest              !!!!!!!!!!
    private String description; //krótki opis książki           !!!!!!!!!!
    private String cover; //link do okładki                     !!!!!!!!!
    private EditionType editionType; //typ: EBOOK, AUDIOBOOK lub BOOK   !!!!!!!!!!!!
    private ReadingStatus readingStatus;
    private OwnershipStatus ownershipStatus;
    private LocalDate readFrom; //zaczęto czytać
    private LocalDate readTo; //skończono czytać
    private String info; //jakieś swoje zapiski
    private Boolean isRead;//czy przeczytana
    private String isbn;  //                    !!!!!!!!!!!!!!!!!!!!!!!!!!!
    private int volume;
}