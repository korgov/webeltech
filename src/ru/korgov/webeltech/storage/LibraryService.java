package ru.korgov.webeltech.storage;

import org.hibernate.SessionFactory;
import ru.korgov.webeltech.storage.model.Author;

/**
 * Author: Kirill Korgov (korgov@yandex-team.ru)
 * Date: 18.03.12
 */
public class LibraryService {
    public static void addAuthor(final Author author){
        final SessionFactory sessionFactory = StorageService.getSessionFactory();
    }
}
