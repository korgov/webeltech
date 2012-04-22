package ru.korgov.webeltech.storage;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import ru.korgov.util.alias.Cf;

import java.util.List;

/**
 * Author: Kirill Korgov (korgov@yandex-team.ru)
 * Date: 18.03.12
 */
public class StorageService {
    private static final Logger log = Logger.getLogger(StorageService.class);
    private static final SessionFactory sessionFactory = configureSessionFactory();

    private StorageService() {
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private static SessionFactory configureSessionFactory() throws HibernateException {
        final Configuration configuration = new Configuration().configure();
        final ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .buildServiceRegistry();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public static void doInSession(final SessionTask sessionTask) {
        try {
            final Session session = sessionFactory.openSession();
            try {
                sessionTask.doWithSession(session);
            } finally {
                session.flush();
                session.close();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public static void addObject(final Object author) {
        doInSession(new SessionTask() {
            @Override
            public void doWithSession(final Session session) {
                session.save(author);
            }
        });
    }

    public static <T> List<T> loadByCriteria(final Session session, final Class<T> clazz, final Criterion... criterions){
        final Criteria criteria = session.createCriteria(clazz);
        for(final Criterion criterion : criterions){
            criteria.add(criterion);
        }
        return Cf.newList(criteria.list(), clazz);
    }
}
