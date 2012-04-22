package ru.korgov.webeltech;

import org.apache.log4j.Logger;
import ru.korgov.webeltech.storage.LibraryService;
import ru.korgov.webeltech.storage.model.Author;
import ru.korgov.webeltech.storage.model.Book;

import java.util.List;


/**
 * @author Deepak Kumar
 *
 * http://www.roseindia.net
 * Hibernate example to inset data into Contact table
 */
public class FirstExample {
    public static final Logger log = Logger.getLogger(FirstExample.class);

    private FirstExample() {
    }

    public static void main(String[] args) {
//        configureSessionFactory();

        final List<Book> books = LibraryService.loadBooksByAuthor(new Author(1L), new Author(2L));
        log.info(books);
//        final Session session = sessionFactory.openSession();
//        final Object book = session.load(Book.class, 2L);
//        System.out.println(book);
    }
} 