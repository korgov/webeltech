package ru.korgov.webeltech.reports.scriptlets;

import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;


public class WebappScriptlet extends JRDefaultScriptlet
{

    @Override
    public void afterGroupInit(final String groupName) throws JRScriptletException
    {
        final String allAuthors = (String)this.getVariableValue("AllAuthors");
        final String author = (String)this.getFieldValue("AuthorName");
        final StringBuilder sb = new StringBuilder();
        
        if (allAuthors != null)
        {
            sb.append(allAuthors);
            sb.append(", ");
        }
        
        sb.append(author);
        this.setVariableValue("AllAuthors", sb.toString());
    }

}
