<%@page import="edu.jsu.mcis.scheduleplannerproject2.dao.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="daoFactory" class="edu.jsu.mcis.scheduleplannerproject2.dao.DAOFactory" scope="application"/>
<%
    SearchDAO dao = daoFactory.getSearchDAO();
%>
<!DOCTYPE html>
<html>
    
    <head>
        
        <title>Schedule Report</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="scripts/jquery-3.6.1.min.js"></script>
        <script src="scripts/UtilityScripts.js"></script>
        <link rel="stylesheet" href="style.css" />
        
    </head>
    
    <body>
       
        <form i method="GET" action="<%= request.getContextPath() %>/main/landing.jsp" >
            
            <fieldset>
                
                <legend>Select Term</legend>
        
                <p>
                    <label for="termid"><strong>Select Term:</strong><br /></label>
                    <%= dao.getTermListAsHTML() %>
                </p>

                <input type="submit" value="Submit">
                <input type="reset" value="Reset">
                
            </fieldset>
        
        </form>
        <p>
            <input type="button" value="Logout" onclick="window.open('<%= request.getContextPath() %>/MAIN/logout.jsp', '_self', false);" />
        </p>
    </body>
    
</html>