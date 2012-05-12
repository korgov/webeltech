<%--
/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
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
--%>

<%@ page errorPage="error.jsp" %>
<%@ page import="net.sf.jasperreports.engine.JRRuntimeException" %>
<%@ page import="net.sf.jasperreports.engine.JasperFillManager" %>
<%@ page import="net.sf.jasperreports.engine.JasperPrint" %>
<%@ page import="net.sf.jasperreports.j2ee.servlets.BaseHttpServlet" %>
<%@ page import="ru.korgov.util.alias.Cf" %>
<%@ page import="ru.korgov.webeltech.reports.datasource.WebappDataSource" %>
<%@ page import="java.io.File" %>
<%@ page import="java.util.Map" %>

<%
	final String reportFileName = application.getRealPath("jreports/reports/WebappReport.jasper");
	final File reportFile = new File(reportFileName);
    if (!reportFile.exists()) {
        throw new JRRuntimeException("File WebappReport.jasper not found. The report design must be compiled first.");
    }

	final Map<String, Object> parameters = Cf.newMap();
	parameters.put("ReportTitle", "Address Report");
	parameters.put("BaseDir", reportFile.getParentFile());
				
	final JasperPrint jasperPrint =
		JasperFillManager.fillReport(
			reportFileName, 
			parameters, 
			new WebappDataSource()
			);
				
	session.setAttribute(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);
%>

<html>
<head>
<title>JasperReports - Web Application Sample</title>
<link rel="stylesheet" type="text/css" href="../stylesheet.css" title="Style">
</head>

<body bgcolor="white">

<span class="bold">The compiled report design was successfully filled with data.</span>

</body>
</html>

