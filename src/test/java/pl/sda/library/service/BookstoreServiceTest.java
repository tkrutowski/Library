package pl.sda.library.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.library.LibraryApplication;
import pl.sda.library.domain.model.Bookstore;
import pl.sda.library.domain.model.exception.BookstoreAlreadyExistException;
import pl.sda.library.domain.model.exception.BookstoreDoesNotExistException;
import pl.sda.library.domain.service.BookstoreService;

import static org.junit.Assert.*;
import static org.junit.Assert.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LibraryApplication.class)
public class BookstoreServiceTest {

    @Autowired
    BookstoreService bookstoreService;

    @Test
    @Transactional
    public void should_return_true_when_bookstore_added() {
        //when
        Bookstore bookstore = new Bookstore(null, "Helion", "www.helion.pl");
        Long unexpected = 0L;
        //given
        Long result = bookstoreService.addBookstore(bookstore);

        //then
        assertNotEquals(unexpected, result);
    }

    @Test
    @Transactional
    public void should_return_size__plus_3_when_3_authors_added() {
        //when
        final int SIZE = bookstoreService.findAllBookstores().size() + 3;
        bookstoreService.addBookstore(new Bookstore(10L, "Empik1", "www.empik.com"));
        bookstoreService.addBookstore(new Bookstore(20L, "PWN1", "www.pwn.pl"));
        bookstoreService.addBookstore(new Bookstore(30L, "Legimi1", "www.legimi.pl"));

        //given
        int result = bookstoreService.findAllBookstores().size();

        //then
        assertEquals(SIZE, result);
    }

    @Test
    @Transactional
    public void should_return_size__minus_1_when_one_author_deleted() {
        //when
        final int SIZE = bookstoreService.findAllBookstores().size();
        Long id = bookstoreService.addBookstore(new Bookstore(null, "Gandalf32333", "www.gandalf.com"));
        bookstoreService.deleteBookstore(id);

        //given
        int result = bookstoreService.findAllBookstores().size();

        //then
        assertEquals(SIZE, result);
    }

    @Test
    @Transactional
    public void should_throw_BookstoreAlreadyExistException_when_bookstore_already_exist() {
        //when
        Bookstore bookstore = new Bookstore(10L, "Gandalf33", "www.gandalf.com");
        bookstoreService.addBookstore(bookstore);

        //then
        assertThrows(BookstoreAlreadyExistException.class, () -> bookstoreService.addBookstore(bookstore));
    }

    @Test
    @Transactional
    public void should_return_changed_name_while_edit() {
        //when
        Bookstore bookstore = new Bookstore(null, "Arsenał2", "www.arsenal.com");
        Long id = bookstoreService.addBookstore(bookstore);
        Bookstore toEdit = bookstoreService.findBookstore(id);
        toEdit.setName("Arsenał_update");

        //given
        Bookstore afterEdit = bookstoreService.editBookstore(toEdit, id);

        //then
        assertEquals("Arsenał_update", afterEdit.getName());
    }

    @Test
    @Transactional
    public void should_throw_BookstoreDoesNotExistException_when_bookstore_doesnt_exist() {
        //when
        Bookstore bookstore = new Bookstore(null, "Arsenał", "www.arsenal.com");
        Long id = bookstoreService.addBookstore(bookstore);
        Bookstore toEdit = bookstoreService.findBookstore(id);
        Long notExistID = 0L;
        toEdit.setId(0L);
        toEdit.setName("Arsenał22");

        //then
        assertThrows(BookstoreDoesNotExistException.class, () -> bookstoreService.editBookstore(toEdit, notExistID));
    }

    @Test
    @Transactional
    public void should_return_changed_www_while_edit() {
        //when
        Bookstore bookstore = new Bookstore(10L, "Gandalf", "www.gandalf.com");
        Long id = bookstoreService.addBookstore(bookstore);
        Bookstore toEdit = bookstoreService.findBookstore(id);
        toEdit.setUrl("www.gandalf.com.pl");


        //given
        Bookstore afterEdit = bookstoreService.editBookstore(toEdit, id);

        //then
        assertEquals("www.gandalf.com.pl", afterEdit.getUrl());
    }
}