package ru.korgov.webeltech.storage;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import ru.korgov.webeltech.storage.model.Author;
import ru.korgov.webeltech.storage.model.Book;
import ru.korgov.webeltech.storage.model.PriceType;
import ru.korgov.webeltech.storage.model.Publishing;

import java.util.List;

/**
 * Author: Kirill Korgov (korgov@yandex-team.ru)
 * Date: 18.03.12
 */
public class LibraryService {
    public static final Logger log = Logger.getLogger(LibraryService.class);

    private LibraryService() {
    }

    public static void addAuthor(final Author author){
        StorageService.addObject(author);
    }

    public static void addPublishing(final Publishing publishing){
        StorageService.addObject(publishing);
    }

    public static void addBook(final Book book){
        StorageService.addObject(book);
    }

    public static void addPriceType(final PriceType priceType){
        StorageService.addObject(priceType);
    }

    public static List<Book> loadBooksByAuthor(final Session session, final Author... authors){
        return StorageService.loadByCriteria(session, Book.class, null, Restrictions.in("author", authors));
    }

    public static List<Author> loadAuthors(final Session session){
        return StorageService.loadByCriteria(session, Author.class);
    }

    public static List<Book> loadBooks(final Session session){
        return StorageService.loadByCriteria(session, Book.class, Order.asc("author"));
    }

    public static List<Publishing> loadPublishings(final Session session){
        return StorageService.loadByCriteria(session, Publishing.class);
    }

    public static List<PriceType> loadPriceTypes(final Session session){
        return StorageService.loadByCriteria(session, PriceType.class);
    }

}
