package ru.korgov.webeltech.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import ru.korgov.webeltech.client.async.CallbacksFactory;

import java.sql.Date;

public class AddAuthorForm implements EntryPoint {

    private DynamicForm addAuthorForm;
    private TextItem authorName;
    private DateItem authorBirthday;

    private IButton submitButton;

    @Override
    public void onModuleLoad() {

        final ClientServiceAsync clientService = GWT.create(ClientService.class);
        initForm(clientService);

        submitButton = new IButton("Submit");

        submitButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                if(!addAuthorForm.validate(false)){
                    SC.say("Проверьте правильность заполнения полей!");
                    return;
                }


                clientService.addAuthor(
                        authorName.getValueAsString(),
                        new Date(authorBirthday.getValueAsDate().getTime()),
                        CallbacksFactory.<Void>newDoNothingCallback("Can't add author!")
                );
            }
        });

        addAuthorForm.setLayoutAlign(Alignment.CENTER);

        RootPanel.get().add(addAuthorForm);
        RootPanel.get().add(submitButton);
    }

    private void initForm(final ClientServiceAsync clientService) {
        addAuthorForm = new DynamicForm();

        addAuthorForm.setValidateOnChange(true);
        addAuthorForm.setNumCols(2);
        addAuthorForm.setWidth(400);
        addAuthorForm.setHeight("*");

        authorName = new TextItem("author-name", "Имя");
        authorName.setColSpan(2);
        authorName.setWidth(250);

        authorBirthday = new DateItem("author-birthday", "Дата рождения");
        authorBirthday.setColSpan(2);
        authorBirthday.setWidth(250);

        addAuthorForm.setFields(authorName, authorBirthday);
    }

}
