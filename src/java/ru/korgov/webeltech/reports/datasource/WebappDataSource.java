/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package ru.korgov.webeltech.reports.datasource;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: WebappDataSource.java 4595 2011-09-08 15:55:10Z teodord $
 */
public class WebappDataSource implements JRDataSource {


    /**
     *
     */
    private Object[][] data =
            {
                    {"Berne", Integer.valueOf(22), "Bill Ott", "250 - 20th Ave."},
                    {"Berne", Integer.valueOf(9), "James Schneider", "277 Seventh Av."},
                    {"Boston", Integer.valueOf(32), "Michael Ott", "339 College Av."},
                    {"Boston", Integer.valueOf(23), "Julia Heiniger", "358 College Av."},
                    {"Chicago", Integer.valueOf(39), "Mary Karsen", "202 College Av."},
                    {"Chicago", Integer.valueOf(35), "George Karsen", "412 College Av."},
                    {"Chicago", Integer.valueOf(11), "Julia White", "412 Upland Pl."},
                    {"Dallas", Integer.valueOf(47), "Janet Fuller", "445 Upland Pl."},
                    {"Dallas", Integer.valueOf(43), "Susanne Smith", "2 Upland Pl."},
                    {"Dallas", Integer.valueOf(40), "Susanne Miller", "440 - 20th Ave."},
                    {"Dallas", Integer.valueOf(36), "John Steel", "276 Upland Pl."},
                    {"Dallas", Integer.valueOf(37), "Michael Clancy", "19 Seventh Av."},
                    {"Dallas", Integer.valueOf(19), "Susanne Heiniger", "86 - 20th Ave."},
                    {"Dallas", Integer.valueOf(10), "Anne Fuller", "135 Upland Pl."},
                    {"Dallas", Integer.valueOf(4), "Sylvia Ringer", "365 College Av."},
                    {"Dallas", Integer.valueOf(0), "Laura Steel", "429 Seventh Av."},
                    {"Lyon", Integer.valueOf(38), "Andrew Heiniger", "347 College Av."},
                    {"Lyon", Integer.valueOf(28), "Susanne White", "74 - 20th Ave."},
                    {"Lyon", Integer.valueOf(17), "Laura Ott", "443 Seventh Av."},
                    {"Lyon", Integer.valueOf(2), "Anne Miller", "20 Upland Pl."},
                    {"New York", Integer.valueOf(46), "Andrew May", "172 Seventh Av."},
                    {"New York", Integer.valueOf(44), "Sylvia Ott", "361 College Av."},
                    {"New York", Integer.valueOf(41), "Bill King", "546 College Av."},
                    {"Oslo", Integer.valueOf(45), "Janet May", "396 Seventh Av."},
                    {"Oslo", Integer.valueOf(42), "Robert Ott", "503 Seventh Av."},
                    {"Paris", Integer.valueOf(25), "Sylvia Steel", "269 College Av."},
                    {"Paris", Integer.valueOf(18), "Sylvia Fuller", "158 - 20th Ave."},
                    {"Paris", Integer.valueOf(5), "Laura Miller", "294 Seventh Av."},
                    {"San Francisco", Integer.valueOf(48), "Robert White", "549 Seventh Av."},
                    {"San Francisco", Integer.valueOf(7), "James Peterson", "231 Upland Pl."}
            };

    private int index = -1;


    /**
     *
     */
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


    /**
     *
     */
    @Override
    public Object getFieldValue(final JRField field) throws JRException {
        Object value = null;

        final String fieldName = field.getName();

        if ("City".equals(fieldName)) {
            value = data[index][0];
        } else if ("Id".equals(fieldName)) {
            value = data[index][1];
        } else if ("Name".equals(fieldName)) {
            value = data[index][2];
        } else if ("Street".equals(fieldName)) {
            value = data[index][3];
        }

        return value;
    }


}
