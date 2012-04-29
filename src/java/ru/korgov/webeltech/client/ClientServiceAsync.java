package ru.korgov.webeltech.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.LinkedHashMap;
import java.util.List;

public interface ClientServiceAsync {
    void getAuthorsValueMap(final AsyncCallback<LinkedHashMap<String, String>> async);

    void getPublishingsValueMap(final AsyncCallback<LinkedHashMap<String, String>> async);

    void getPriceTypesValueMap(final AsyncCallback<LinkedHashMap<String, String>> async);

    void addBook(long authorId, long publishingId, String name, int publishYear, double price, int count, List<String> keywords, final AsyncCallback<Void> async);
}
