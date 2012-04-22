package ru.korgov.webeltech.storage;

import org.hibernate.Session;

/**
 * Author: Kirill Korgov (korgov@yandex-team.ru)
 * Date: 22.04.12
 */
public interface SessionTask {
    void doWithSession(final Session session);
}
