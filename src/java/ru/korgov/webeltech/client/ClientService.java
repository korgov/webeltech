package ru.korgov.webeltech.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.LinkedHashMap;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("valuesMap")
public interface ClientService extends RemoteService {

    LinkedHashMap<String, String> getAuthorsValueMap();

    LinkedHashMap<String, String> getPublishingsValueMap();

    LinkedHashMap<String, String> getPriceTypesValueMap();

    void addBook(long authorId, long publishingId, String name, int publishYear, double price, int count, String[] keywords);
}
