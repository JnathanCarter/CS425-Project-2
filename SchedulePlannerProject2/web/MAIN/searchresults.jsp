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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Search Results</title>
        <script type="text/javascript" src="scripts/UtilityScripts.js"></script>
        <script
            type="text/javascript"
            src="scripts/jquery-3.6.1.min.js"
        ></script>
        <link
            rel="stylesheet"
            type="text/css"
            media="screen"
            href="style.css"
        />
    </head>
    <body>
        <style>
            h3 {
                color: #cc0000;
                background-color: #e3e5ee;
                background-color: #e3e5ee;
                font-family: Verdana, Arial Narrow, helvetica, sans-serif;
                font-weight: bold;
                font-size: 90%;
                font-style: normal;
                text-align: left;
                vertical-align: top;
                padding-bottom: 1em;
            }
            table,
            th,
            td {
                border: 1px solid black;
                border-collapse: collapse;
            }
        </style>
        <a href="landing.jsp">Back</a>
        <h1>Sections Found:</h1>
        <div id="output" name="output"></div>

        <script type="text/javascript">
            Project2.getSearchResults();
        </script>
        <p>
            <input
                type="button"
                value="Logout"
                onclick="window.open('<%= request.getContextPath() %>/MAIN/logout.jsp', '_self', false);"
            />
        </p>
    </body>
</html>
