package ru.korgov.webeltech.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextItem;

import java.util.Date;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class HelloWorld implements EntryPoint {

    private DynamicForm mainForm;
    private TextItem textItem;
    private IButton button;

    @Override
    public void onModuleLoad() {
        mainForm = new DynamicForm();
        textItem = new TextItem("People Name");
        button = new IButton("Push me");

        button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                SC.say("Hello, " + textItem.getValue() + " at " + new Date());
            }
        });


        mainForm.setFields(textItem);
        RootPanel.get().add(mainForm);
        RootPanel.get().add(button);
    }
}
