package ru.korgov.webeltech.client.async;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.fields.SelectItem;

import java.util.LinkedHashMap;

/**
 * Author: Kirill Korgov (korgov@yandex-team.ru)
 * Date: 30.04.12
 */
public class CallbacksFactory {



    private CallbacksFactory() {
    }

    public static <T> AsyncCallback<T> newDoNothingCallback(final String errorMessage){
        return new AsyncCallback<T>() {
            @Override
            public void onFailure(final Throwable t) {
                SC.say(errorMessage);
            }
            @Override
            public void onSuccess(final T ignored) {
            }
        };
    }

    public static AsyncCallback<LinkedHashMap<String, String>> newSetValueMapCallBack(final String name, final SelectItem item) {
        return new AsyncCallback<LinkedHashMap<String, String>>() {
            @Override
            public void onFailure(final Throwable caught) {
                SC.say("Can't load " + name + " info: " + caught.getMessage());
            }

            @Override
            public void onSuccess(final LinkedHashMap<String, String> result) {
                item.setValueMap(result);
            }
        };
    }
}
