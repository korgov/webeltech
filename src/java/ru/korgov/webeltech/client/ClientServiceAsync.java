package ru.korgov.webeltech.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import java.sql.Date;
import java.util.LinkedHashMap;

public interface ClientServiceAsync {
    void getAuthorsValueMap(final AsyncCallback<LinkedHashMap<String, String>> async);

    void getPublishingsValueMap(final AsyncCallback<LinkedHashMap<String, String>> async);

    void getPriceTypesValueMap(final AsyncCallback<LinkedHashMap<String, String>> async);

    void addBook(long authorId, long publishingId, String name, int publishYear, double price, int count, String[] keywords, final AsyncCallback<Void> async);

    void addAuthor(String name, Date birthday, final AsyncCallback<Void> async);

    void addPublishing(String name, final AsyncCallback<Void> async);
}
