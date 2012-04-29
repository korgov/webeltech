package ru.korgov.webeltech;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import ru.korgov.webeltech.storage.LibraryService;
import ru.korgov.webeltech.storage.SessionTask;
import ru.korgov.webeltech.storage.StorageService;
import ru.korgov.webeltech.storage.model.Author;
import ru.korgov.webeltech.storage.model.PriceType;
import ru.korgov.webeltech.storage.model.Publishing;

import java.sql.Date;


public class FirstExample {
    public static final Logger log = Logger.getLogger(FirstExample.class);

    private FirstExample() {
    }

    public static void main(final String[] args) {
//        configureSessionFactory();
        StorageService.doInSession(new SessionTask() {
            @Override
            public void doWithSession(final Session session) {
                LibraryService.addAuthor(new Author(-1L, "Ivanov K.", new Date(System.currentTimeMillis())));
                LibraryService.addPublishing(new Publishing(-1L, "Izdatelstv."));
                LibraryService.addPublishing(new Publishing(-1L, "Publish org."));
                LibraryService.addPriceType(new PriceType(-1L, "руб"));
                LibraryService.addPriceType(new PriceType(-1L, "euro"));
                LibraryService.addPriceType(new PriceType(-1L, "$"));


//                final Book book = new Book(0L);
//                book.setAuthor(new Author(1L, "From Book1", new Date(System.currentTimeMillis())));
//                book.setCount(1);
//                book.setName("book name1");
//                book.setPrice(Cf.list(new Price(100.0)));
//                book.setPublishing(new Publishing(1L));
//                book.setArrivalTime(new Date(System.currentTimeMillis()));
//                book.setPublishTime(Date.valueOf(String.valueOf("2007-01-01")));
//                book.setKeywords(Keyword.createFromStrings(Cu.list("key1", "key2")));
//                LibraryService.addBook(book);
            }
        });

//        final Session session = sessionFactory.openSession();
//        final Object book = session.load(Book.class, 2L);
//        System.out.println(book);
    }
} 