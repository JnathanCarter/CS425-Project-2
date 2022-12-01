<%@page import="java.util.Locale"%> <%@page
import="java.time.format.DateTimeFormatter"%> <%@page
import="java.time.LocalTime"%> <%@page contentType="text/html"
pageEncoding="UTF-8"%> <%@page
import="edu.jsu.mcis.scheduleplannerproject2.dao.*"%>

<jsp:useBean
    id="projBean"
    class="edu.jsu.mcis.scheduleplannerproject2.Bean"
    scope="session"
/>
<jsp:setProperty name="projBean" property="*" />

<% DAOFactory daoFactory = null; ServletContext context =
request.getServletContext(); if (context.getAttribute("daoFactory") == null) {
System.err.println("*** Creating new DAOFactory ..."); daoFactory = new
DAOFactory(); context.setAttribute("daoFactory", daoFactory); } else {
daoFactory = (DAOFactory) context.getAttribute("daoFactory"); }
System.err.println("random code in searchresult"); %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Class System Main Page</title>
    </head>
    <body>
        <h1>JSU Class System</h1>      
        <a href="<%= request.getContextPath() %>/main/search.jsp">Search for Classes</a>
        <br>
        <a href="<%= request.getContextPath() %>/main/registration.jsp">Register for Classes</a>
        <br>
        <a href="<%= request.getContextPath() %>/main/schedule.jsp">Look at Current Schedule</a>
        <br>
        <a href="<%= request.getContextPath() %>/main/drop.jsp">Drop a Course</a>
        <br>
        <a href="<%= request.getContextPath() %>/main/report.jsp">Generate Schedule as PDF</a>
        <p>
            <input type="button" value="Logout" onclick="window.open('<%= request.getContextPath() %>/MAIN/logout.jsp', '_self', false);" />
        </p>
    </body>
</html>
