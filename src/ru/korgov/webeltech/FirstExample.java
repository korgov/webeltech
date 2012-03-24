package ru.korgov.webeltech;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import ru.korgov.webeltech.storage.model.Book;
import ru.korgov.webeltech.storage.model.Price;
import ru.korgov.webeltech.storage.model.PriceType;

import java.io.Serializable;


/**
 * @author Deepak Kumar
 *
 * http://www.roseindia.net
 * Hibernate example to inset data into Contact table
 */
public class FirstExample {

    private static SessionFactory sessionFactory;

    private static SessionFactory configureSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        configuration.configure();
        final ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }


    public static void main(String[] args) {
        configureSessionFactory();

        try{
            final Session session = sessionFactory.openSession();
            try{
                System.out.println("Inserting Record");
//                final Author author = new Author(-1L, "Korgov", new Date(System.currentTimeMillis()));
//                final Publishing publishing = new Publishing(-1L, "Korgov corp");
                final PriceType priceType = new PriceType(2L, null);
//                session.save(author);
//                session.save(publishing);
//                session.save(priceType);

//
                final Price price = new Price(-1L, 99999, priceType);
                final Serializable newPriceId = session.save(price);
                System.out.println("newPriceId: " + newPriceId);
//                Book book = new Book();
//                book.setAuthor(author);
//                book.setName("Book1");
//                book.setArrivalTime(new Date(System.currentTimeMillis()));
//                book.setCount(5);
//                book.setKeywords(Cu.list(new Keyword("some keyword")));
//                book.setPublishing(publishing);
//                book.setPrice(price);
//                session.save(book);

                System.out.println("Done");
            }finally {
                session.flush();
                session.close();
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }


        final Session session = sessionFactory.openSession();
        final Object book = session.load(Book.class, 2L);
        System.out.println(book);
    }
} 