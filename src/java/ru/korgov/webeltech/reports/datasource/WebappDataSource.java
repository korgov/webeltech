package ru.korgov.webeltech.reports.datasource;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class WebappDataSource implements JRDataSource {

    private Object[][] data =
            {
                    {Long.valueOf(1L), "Book 11 Name", Double.valueOf(15.99), Integer.valueOf(30), Long.valueOf(1L), "Korgov1"},
                    {Long.valueOf(2L), "Book 12 Name", Double.valueOf(15.99), Integer.valueOf(30), Long.valueOf(1L), "Korgov1"},
                    {Long.valueOf(3L), "Book 21 Name", Double.valueOf(15.99), Integer.valueOf(30), Long.valueOf(2L), "Korgov2"},
                    {Long.valueOf(4L), "Book 22 Name", Double.valueOf(15.99), Integer.valueOf(30), Long.valueOf(2L), "Korgov2"}

            };

    private int index = -1;

    public WebappDataSource() {
    }


    /**
     *
     */
    @Override
    public boolean next() throws JRException {
        index++;

        return (index < data.length);
    }

    @Override
    public Object getFieldValue(final JRField field) throws JRException {
        Object value = null;

        final String fieldName = field.getName();

        if ("Id".equals(fieldName)) {
            value = data[index][0];
        } else if ("Name".equals(fieldName)) {
            value = data[index][1];
        } else if ("Price".equals(fieldName)) {
            value = data[index][2];
        } else if ("Count".equals(fieldName)) {
            value = data[index][3];
        } else if ("AuthorId".equals(fieldName)) {
            value = data[index][4];
        } else if ("AuthorName".equals(fieldName)) {
            value = data[index][5];
        }

        return value;
    }


}
