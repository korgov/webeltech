
<%@ page errorPage="error.jsp" %>
<%@ page import="net.sf.jasperreports.engine.JRExporterParameter" %>
<%@ page import="net.sf.jasperreports.engine.JRRuntimeException" %>
<%@ page import="net.sf.jasperreports.engine.JasperFillManager" %>
<%@ page import="net.sf.jasperreports.engine.JasperPrint" %>
<%@ page import="net.sf.jasperreports.engine.JasperReport" %>
<%@ page import="net.sf.jasperreports.engine.design.JasperDesign" %>
<%@ page import="net.sf.jasperreports.engine.export.JRHtmlExporterParameter" %>
<%@ page import="net.sf.jasperreports.engine.export.JRXhtmlExporter" %>
<%@ page import="net.sf.jasperreports.engine.util.JRLoader" %>
<%@ page import="net.sf.jasperreports.j2ee.servlets.ImageServlet" %>
<%@ page import="ru.korgov.util.alias.Cf" %>
<%@ page import="ru.korgov.util.alias.Su" %>
<%@ page import="ru.korgov.webeltech.reports.datasource.WebappDataSource" %>
<%@ page import="java.io.File" %>
<%@ page import="java.util.Map" %>
<%@ page import="net.sf.jasperreports.engine.JasperCompileManager" %>
<%@ page import="java.io.FileInputStream" %>
<%@ page import="ru.korgov.webeltech.reports.datasource.BooksDataSource" %>
<%@ page import="ru.korgov.webeltech.storage.LibraryService" %>
<%@ page import="ru.korgov.webeltech.storage.StorageService" %>
<%@ page import="ru.korgov.webeltech.storage.SessionTask" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="java.util.concurrent.atomic.AtomicReference" %>
<%@ page import="net.sf.jasperreports.engine.JRException" %>

<%
    final AtomicReference<JasperPrint> jasperPrint = new AtomicReference<JasperPrint>((JasperPrint) session.getAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE));
    final String reportName = request.getParameter("report");

    final Session hiberSession = StorageService.getSessionFactory().openSession();

    if (request.getParameter("reload") != null || jasperPrint.get() == null) {
        final String s = "WebappReport.jasper";
        if (Su.isEmpty(reportName)) {
            throw new JRRuntimeException(
                    "Not enought param: 'report' " +
                            "(e.g. 'report=WebappReport' " +
                            "for 'jreports/reports/WebappReport.jasper')"
            );
        }
        final String reportFullName = "jreports/reports/" + reportName;
        final File compiledFile = new File(application.getRealPath(reportFullName + ".jasper"));
        final JasperReport jasperReport;
        if (!compiledFile.exists()) {
            final File sourceFile = new File(application.getRealPath(reportFullName + ".jrxml"));
            jasperReport = JasperCompileManager.compileReport(new FileInputStream(sourceFile));
        } else {
            jasperReport = (JasperReport) JRLoader.loadObject(compiledFile);
        }

        final Map<String, Object> parameters = Cf.newMap();
        parameters.put("ReportTitle", "Books by Authors");
        parameters.put("BaseDir", compiledFile.getParentFile());

        jasperPrint.set(
                JasperFillManager.fillReport(
                        jasperReport,
                        parameters,
                        new BooksDataSource(LibraryService.loadBooks(hiberSession))
                )
        );

        session.setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint.get());
    }

    final JRXhtmlExporter exporter = new JRXhtmlExporter();

    int lastPageIndex = 0;
    if (jasperPrint.get().getPages() != null) {
        lastPageIndex = jasperPrint.get().getPages().size() - 1;
    }

    final String pageStr = request.getParameter("page");
    int pageIndex = 0;
    try {
        pageIndex = Integer.parseInt(pageStr);
    } catch (Exception ignored) {
    }

    if (pageIndex < 0) {
        pageIndex = 0;
    }

    if (pageIndex > lastPageIndex) {
        pageIndex = lastPageIndex;
    }

    final StringBuffer sbuffer = new StringBuffer();

    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint.get());
    exporter.setParameter(JRExporterParameter.OUTPUT_STRING_BUFFER, sbuffer);
    exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, "../servlets/image?image=");
    exporter.setParameter(JRExporterParameter.PAGE_INDEX, Integer.valueOf(pageIndex));
    exporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, "");
    exporter.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML, "");
    exporter.setParameter(JRHtmlExporterParameter.HTML_FOOTER, "");

    exporter.exportReport();

    hiberSession.flush();
    hiberSession.close();
%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

    <style type="text/css">
        a {
            text-decoration: none
        }
        .but{
            weight: bold;
            text-decoration: underline;
            font-size: 12pt;
        }
    </style>
</head>
<body text="#000000" link="#000000" alink="#000000" vlink="#000000">
<table width="100%" cellpadding="0" cellspacing="0" border="0">
    <tr>
        <td width="50%">&nbsp;</td>
        <td align="left">
            <hr size="1" color="#000000">
            <table width="100%" cellpadding="0" cellspacing="0" border="0">
                <tr>
                    <td><a href="xviewer.jsp?report=<%=reportName%>&reload=true"><img src="../images/reload.GIF" border="0"></a></td>
                    <td>&nbsp;&nbsp;&nbsp;</td>
                    <%
                        if (pageIndex > 0) {
                    %>
                    <td><a href="xviewer.jsp?report=<%=reportName%>&page=0"><img src="../images/first.GIF" border="0"></a></td>
                    <td><a href="xviewer.jsp?report=<%=reportName%>&page=<%=pageIndex - 1%>"><img src="../images/previous.GIF" border="0"></a>
                    </td>
                    <%
                        } else {
                    %>
                    <td><img src="../images/first_grey.GIF" border="0"></td>
                    <td><img src="../images/previous_grey.GIF" border="0"></td>
                    <%
                        }

                        if (pageIndex < lastPageIndex) {
                    %>
                    <td><a href="xviewer.jsp?report=<%=reportName%>&page=<%=pageIndex + 1%>"><img src="../images/next.GIF" border="0"></a></td>
                    <td><a href="xviewer.jsp?report=<%=reportName%>&page=<%=lastPageIndex%>"><img src="../images/last.GIF" border="0"></a></td>
                    <%
                        } else {
                    %>
                    <td><img src="../images/next_grey.GIF" border="0"></td>
                    <td><img src="../images/last_grey.GIF" border="0"></td>
                    <%
                        }
                    %>
                    <td>&nbsp;&nbsp;&nbsp;</td>
                    <td><a href="../servlets/pdf" class="but">Save&nbsp;as&nbsp;PDF</a></td>
                    <td width="100%">&nbsp;</td>
                </tr>
            </table>
            <hr size="1" color="#000000">
        </td>
        <td width="50%">&nbsp;</td>
    </tr>
    <tr>
        <td width="50%">&nbsp;</td>
        <td align="center">

            <%=sbuffer.toString()%>

        </td>
        <td width="50%">&nbsp;</td>
    </tr>
</table>
</body>
</html>

