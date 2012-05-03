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
import com.smartgwt.client.widgets.form.fields.TextItem;
import ru.korgov.webeltech.client.async.CallbacksFactory;

public class AddPublishingForm implements EntryPoint {

    private DynamicForm addPublishingForm;
    private TextItem publishingName;

    private IButton submitButton;

    @Override
    public void onModuleLoad() {

        final ClientServiceAsync clientService = GWT.create(ClientService.class);
        initForm(clientService);

        submitButton = new IButton("Добавить издательство");


        submitButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                if(!addPublishingForm.validate(false)){
                    SC.say("Проверьте правильность заполнения полей!");
                    return;
                }

                clientService.addPublishing(
                        publishingName.getValueAsString(),
                        CallbacksFactory.<Void>newDoNothingCallback("Can't add publishing!")
                );
            }
        });

        addPublishingForm.setLayoutAlign(Alignment.CENTER);

        RootPanel.get().add(addPublishingForm);
        RootPanel.get().add(submitButton);
    }

    private void initForm(final ClientServiceAsync clientService) {
        addPublishingForm = new DynamicForm();

        addPublishingForm.setValidateOnChange(true);
        addPublishingForm.setNumCols(2);
        addPublishingForm.setWidth(400);
        addPublishingForm.setHeight("*");

        publishingName = new TextItem("publishing-name", "Название");
        publishingName.setColSpan(2);
        publishingName.setWidth(250);

        addPublishingForm.setFields(publishingName);
    }

}
