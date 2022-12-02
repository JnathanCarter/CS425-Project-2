<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Drop Course</title>
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
        <h1>Drop a Course:</h1>
        <form name="delform" id="delform">
            <fieldset>
                <legend>Enter a CRN:</legend>
                <p>
                    CRN:
                    <input id="crn" type="number" name="crn" />
                </p>
                <input
                    type="submit"
                    value="Submit"
                    onclick=" Project2.unregister();"
                />
            </fieldset>
        </form>
        <div id="output" name="output"></div>
        <p>
            <input
                type="button"
                value="Logout"
                onclick="window.open('<%= request.getContextPath() %>/MAIN/logout.jsp', '_self', false);"
            />
        </p>

        <script>
            $("#delform").click(function (e) {
                e.preventDefault();
            });
            Project2.getSchedule();
        </script>
    </body>
</html>
