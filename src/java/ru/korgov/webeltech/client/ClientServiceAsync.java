package ru.korgov.webeltech.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.LinkedHashMap;

public interface ClientServiceAsync {
    void getAuthorsValueMap(final AsyncCallback<LinkedHashMap<String, String>> async);

    void getPublishingsValueMap(final AsyncCallback<LinkedHashMap<String, String>> async);

    void getPriceTypesValueMap(final AsyncCallback<LinkedHashMap<String, String>> async);
}
