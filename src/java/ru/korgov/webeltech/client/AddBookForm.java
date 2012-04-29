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
import com.smartgwt.client.widgets.form.fields.FloatItem;
import com.smartgwt.client.widgets.form.fields.IntegerItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import ru.korgov.util.alias.Cu;
import ru.korgov.webeltech.client.async.CallbacksFactory;

public class AddBookForm implements EntryPoint {

    private DynamicForm addBookForm;
    private TextItem bookName;
    private SelectItem bookAuthor;
    private SelectItem bookPublishing;
    private IntegerItem bookPublishingYear;

    private FloatItem bookPriceValue;
    private IntegerItem bookCount;
    private TextAreaItem bookKeywords;

    private IButton submitButton;

    @Override
    public void onModuleLoad() {

        final ClientServiceAsync clientService = GWT.create(ClientService.class);
        initForm(clientService);

        submitButton = new IButton("Submit");

        submitButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                if(!addBookForm.validate(false)){
                    SC.say("Проверьте правильность заполнения полей!");
                    return;
                }

                clientService.addBook(
                        Long.valueOf(bookAuthor.getValueAsString()),
                        Long.valueOf(bookPublishing.getValueAsString()),
                        bookName.getValueAsString(),
                        bookPublishingYear.getValueAsInteger(),
                        bookPriceValue.getValueAsFloat(),
                        bookCount.getValueAsInteger(),
                        Cu.list(bookKeywords.getValueAsString().split("\\s*[,;]+\\s*")),
                        CallbacksFactory.<Void>newDoNothingCallback("Can't add author!")
                );
            }
        });

        addBookForm.setLayoutAlign(Alignment.CENTER);

        RootPanel.get().add(addBookForm);
        RootPanel.get().add(submitButton);
    }

    private void initForm(final ClientServiceAsync clientService) {
        addBookForm = new DynamicForm();

        addBookForm.setValidateOnChange(true);
        addBookForm.setNumCols(2);
        addBookForm.setWidth(400);
        addBookForm.setHeight("*");

        bookAuthor = new SelectItem("author", "Автор");
        bookAuthor.setColSpan(2);
        bookAuthor.setWidth(250);

        bookName = new TextItem("book-name", "Название");
        bookName.setColSpan(2);
        bookName.setWidth(250);

        bookPublishing = new SelectItem("publishing", "Издательство");
        bookPublishing.setColSpan(2);
        bookPublishing.setWidth(250);

        bookPublishingYear = new IntegerItem("publ-time", "Год издания");
        bookPublishingYear.setMask("####");
        bookPublishingYear.setColSpan(2);
        bookPublishingYear.setWidth(250);

        bookPriceValue = new FloatItem("price-value", "Цена, руб.");
        bookPriceValue.setColSpan(2);
        bookPriceValue.setWidth(100);

        bookCount = new IntegerItem("books-count", "Количество");
        bookCount.setColSpan(2);
        bookCount.setWidth(100);

        bookKeywords = new TextAreaItem("keywords", "Ключевые слова");
        bookKeywords.setColSpan(2);
        bookKeywords.setLength(4000);
        bookKeywords.setWidth(400);


        addBookForm.setFields(bookAuthor, bookName, bookPublishing, bookPublishingYear, bookPriceValue, bookCount, bookKeywords);


        loadDataForItems(clientService);
    }

    private void loadDataForItems(final ClientServiceAsync clientService) {
        clientService.getAuthorsValueMap(CallbacksFactory.newSetValueMapCallBack("authors", bookAuthor));
        clientService.getPublishingsValueMap(CallbacksFactory.newSetValueMapCallBack("publishings", bookPublishing));
    }



}
