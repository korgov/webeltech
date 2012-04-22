package ru.korgov.webeltech.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
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

import java.util.LinkedHashMap;

public class AddBookForm implements EntryPoint {

    private DynamicForm addBookForm;
    private TextItem bookName;
    private SelectItem bookAuthor;
    private SelectItem bookPublishing;

    private FloatItem bookPriceValue;
    private SelectItem bookPriceType;
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
                SC.say(
                        "Author: " + bookAuthor.getSelectedRecord().getSingleCellValue() + " :: "+ bookAuthor.getValueAsString() +
                                "\n book-name: " + bookName.getValueAsString() +
                                "\n count: " + bookCount.getValueAsInteger() +
                                "\n price: " + bookPriceValue.getValueAsFloat()


                );
            }
        });

        RootPanel.get().add(addBookForm);
        RootPanel.get().add(submitButton);
    }

    private void initForm(final ClientServiceAsync clientService) {
        addBookForm = new DynamicForm();
        bookName = new TextItem("book-name", "Название");
        bookPriceValue = new FloatItem("price-value", "Цена");
        bookCount = new IntegerItem("books-count", "Количество");
        bookKeywords = new TextAreaItem("keywords", "Ключевые слова");

        bookAuthor = new SelectItem("author", "Автор");
        bookPublishing = new SelectItem("publishing", "Издательство");
        bookPriceType = new SelectItem("price-type", "");

        addBookForm.setFields(bookAuthor, bookName, bookPublishing, bookPriceValue, bookPriceType, bookCount, bookKeywords);

        clientService.getAuthorsValueMap(setValuesMapByResult("authors", bookAuthor));
        clientService.getPublishingsValueMap(setValuesMapByResult("publishings", bookPublishing));
        clientService.getPriceTypesValueMap(setValuesMapByResult("price-types", bookPriceType));
    }

    private AsyncCallback<LinkedHashMap<String, String>> setValuesMapByResult(final String name, final SelectItem item) {
        return new AsyncCallback<LinkedHashMap<String, String>>() {
            @Override
            public void onFailure(final Throwable caught) {
                SC.say("Can't load " + name +" info: " + caught.getMessage());
            }

            @Override
            public void onSuccess(final LinkedHashMap<String, String> result) {
                item.setValueMap(result);
            }
        };
    }

}
