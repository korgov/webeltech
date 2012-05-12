package ru.korgov.webeltech.reports.datasource;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import ru.korgov.util.alias.Cu;
import ru.korgov.webeltech.storage.model.Book;
import ru.korgov.webeltech.storage.model.Price;

import java.util.Iterator;
import java.util.List;

public class BooksDataSource implements JRDataSource {

    private Iterator<Book> iterator;
    private Book currentValue;

    public BooksDataSource(final List<Book> books) {
        this.iterator = books.iterator();
    }

    @Override
    public Object getFieldValue(final JRField field) throws JRException {
        final String fieldName = field.getName();
        if ("Id".equals(fieldName)) {
            return currentValue.getId();
        }
        if ("Name".equals(fieldName)) {
            return currentValue.getName();
        }
        if ("Price".equals(fieldName)) {
            final Price price = Cu.firstOrNull(currentValue.getPrice());
            return price != null ? price.getValue() : null;
        }
        if ("Count".equals(fieldName)) {
            return currentValue.getCount();
        }
        if ("AuthorId".equals(fieldName)) {
            return currentValue.getAuthor().getId();
        }
        if("AuthorName".equals(fieldName)){
            return currentValue.getAuthor().getName();
        }
        return null;
    }

    @Override
    public boolean next() throws JRException {
        currentValue = iterator.hasNext() ? iterator.next() : null;
        return (currentValue != null);
    }

}
