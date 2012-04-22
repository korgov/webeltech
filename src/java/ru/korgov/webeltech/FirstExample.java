package ru.korgov.webeltech;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import ru.korgov.webeltech.storage.LibraryService;
import ru.korgov.webeltech.storage.SessionTask;
import ru.korgov.webeltech.storage.StorageService;
import ru.korgov.webeltech.storage.model.Author;
import ru.korgov.webeltech.storage.model.Book;

import java.util.List;


public class FirstExample {
    public static final Logger log = Logger.getLogger(FirstExample.class);

    private FirstExample() {
    }

    public static void main(final String[] args) {
//        configureSessionFactory();
        StorageService.doInSession(new SessionTask() {
            @Override
            public void doWithSession(final Session session) {
                final List<Book> books = LibraryService.loadBooksByAuthor(session, new Author(1L), new Author(2L));
                log.info(books);
            }
        });

//        final Session session = sessionFactory.openSession();
//        final Object book = session.load(Book.class, 2L);
//        System.out.println(book);
    }
} 