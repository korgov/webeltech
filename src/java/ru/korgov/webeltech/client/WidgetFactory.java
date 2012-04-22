package ru.korgov.webeltech.client;

import com.smartgwt.client.widgets.form.fields.SelectItem;
import org.hibernate.Session;
import ru.korgov.webeltech.server.ClientServiceImpl;

import java.util.LinkedHashMap;

/**
 * Author: Kirill Korgov (korgov@yandex-team.ru)
 * Date: 22.04.12
 */
public class WidgetFactory {
    private WidgetFactory() {
    }

    public static SelectItem createSelectItem(final String name, final String title, final LinkedHashMap<String, String> values) {
        final SelectItem item = new SelectItem(name, title);
        item.setValueMap(values);
        return item;
    }

}
