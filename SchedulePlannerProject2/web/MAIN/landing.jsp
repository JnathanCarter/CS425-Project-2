
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Class System Main Page</title>
    </head>
    <body>
        <h1>JSU Class System</h1>      
        <a href="<%= request.getContextPath() %>/MAIN/search.jsp">Search for Classes</a>
        <br>
        <a href="<%= request.getContextPath() %>/MAIN/registration.jsp">Register for Classes</a>
        <br>
        <a href="<%= request.getContextPath() %>/MAIN/schedule.jsp">Look at Current Schedule</a>
        <br>
        <a href="<%= request.getContextPath() %>/MAIN/drop.jsp">Drop a Course</a>
        <br>
        <a href="<%= request.getContextPath() %>/MAIN/report0.jsp">Generate Schedule as PDF</a>
        <p>
            <input type="button" value="Logout" onclick="window.open('<%= request.getContextPath() %>/MAIN/logout.jsp', '_self', false);" />
        </p>
    </body>
</html>
