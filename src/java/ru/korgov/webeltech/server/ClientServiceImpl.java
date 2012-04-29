package ru.korgov.webeltech.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import ru.korgov.util.alias.Cf;
import ru.korgov.webeltech.client.ClientService;
import ru.korgov.webeltech.storage.LibraryService;
import ru.korgov.webeltech.storage.SessionTask;
import ru.korgov.webeltech.storage.StorageService;
import ru.korgov.webeltech.storage.model.Author;
import ru.korgov.webeltech.storage.model.Book;
import ru.korgov.webeltech.storage.model.Keyword;
import ru.korgov.webeltech.storage.model.Price;
import ru.korgov.webeltech.storage.model.PriceType;
import ru.korgov.webeltech.storage.model.Publishing;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Author: Kirill Korgov (korgov@yandex-team.ru)
 * Date: 22.04.12
 */
public class ClientServiceImpl extends RemoteServiceServlet implements ClientService {

    public static final Logger log = Logger.getLogger(ClientServiceImpl.class);

    private static final DateFormat DATE_FORMAT_YEAR_ONLY = new SimpleDateFormat("yyyy");

    @Override
    public LinkedHashMap<String, String> getAuthorsValueMap(){
        final LinkedHashMap<String, String> out = Cf.newLinkedMap();
        StorageService.doInSession(new SessionTask() {
            @Override
            public void doWithSession(final Session session) {
                final List<Author> authors = LibraryService.loadAuthors(session);
                for (final Author author : authors) {
                    log.warn(author);
                    out.put(String.valueOf(author.getId()), authorText(author));
                }
            }
        });
        log.info(out);
        return out;
    }

    private static String authorText(final Author author) {
        return author.getName() + safeDateFormat(author);
    }

    private static String safeDateFormat(final Author author) {
        final Date birthday = author.getBirthday();
        return birthday == null ? "" : DATE_FORMAT_YEAR_ONLY.format(birthday);
    }

    @Override
    public LinkedHashMap<String, String> getPublishingsValueMap(){
        final LinkedHashMap<String, String> out = Cf.newLinkedMap();
        StorageService.doInSession(new SessionTask() {
            @Override
            public void doWithSession(final Session session) {
                final List<Publishing> publishings = LibraryService.loadPublishings(session);
                for (final Publishing publishing : publishings) {
                    out.put(String.valueOf(publishing.getId()), publishing.getName());
                }
            }
        });
        return out;
    }

    @Override
    public LinkedHashMap<String, String> getPriceTypesValueMap() {
        final LinkedHashMap<String, String> out = Cf.newLinkedMap();
        StorageService.doInSession(new SessionTask() {
            @Override
            public void doWithSession(final Session session) {
                final List<PriceType> priceTypes = LibraryService.loadPriceTypes(session);
                for (final PriceType priceType : priceTypes) {
                    out.put(String.valueOf(priceType.getId()), priceType.getName());
                }
            }
        });
        return out;
    }


    @Override
    public void addBook(final long authorId, final long publishingId, final String name, final int publishYear, final double price, final int count, final List<String> keywords){
        try {
            final Book book = new Book(-1L);
            book.setAuthor(new Author(authorId));
            book.setCount(count);
            book.setName(name);
            book.setPrice(new Price(-1L, price));
            book.setPublishing(new Publishing(publishingId));
            book.setArrivalTime(new Date(System.currentTimeMillis()));
            book.setPublishTime(Date.valueOf(String.valueOf(publishYear + "-01-01")));
            book.setKeywords(Keyword.createFromStrings(keywords));
            LibraryService.addBook(book);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
