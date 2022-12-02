var Project2 = (function () {
    var displaySectionsTable = function (data) {
        var sections = data.sections;
        var output = document.getElementById("output");

        var text = " ";

        for (var i = 0; i < sections.length; ++i) {
            var section = sections[i];

            text += ("<table>");
            text += ("<tbody>");

            //create header

            text += ("<h3>" + section.description + " - " + section.crn + " - " +
                section.subjectid + " " + section.num + " - " + section.section)
            text += ("</h3>");

            //daterow

            text += ("<tr>");
            text += ("<td>");

            text += ("<span> Associated Term :" + section.termname + "</span><br>");
            text += ("<span>Level :" + section.level + "</span><br>");
            text += ("<span>" + section.credits + " Credits</span><br>");


            text += ("<tr>");

            text += ("<th scope = \"col\">Time</th>");
            text += ("<th scope = \"col\">Days</th>");
            text += ("<th scope = \"col\">Where</th>");
            text += ("<th scope = \"col\">Date Ranger</th>");
            text += ("<th scope = \"col\">Schedule Type</th>");
            text += ("<th scope = \"col\">Instructor(s)</th>");

            text += ("</tr>");

            text += ("<tr>");

            text += ("<td>" + section.start + " - " + section.end + "</td>");
            text += ("<td>" + section.days + "</td>");
            text += ("<td>" + section.where + "</td>");
            text += ("<td>" + section.termstart + " - " + section.termend + "</td>");
            text += ("<td>" + section.scheduletype + "</td>");
            text += ("<td>" + section.instructor + "</td>");


            text += ("</tr>");



            text += ("</td>");
            text += ("</tr>");

            text += ("</tbody>");
            text += ("</table>");
        }

        output.innerHTML = text;
    };
    return {
        getSchedule: function () {
            $.ajax({
                url: "http://localhost:8180/SchedulePlannerProject2/main/register",
                method: 'GET',
                dataType: 'json',
                success: function (data) {
                    if (data.sections.length === 0) {
                        $("#output").html("No classes have been registered! Register for classes here:"
                            + "<br><br><a href=\"term_1.jsp\">"
                            + "Register for Classes</a>");
                    }
                    else {
                        displaySectionsTable(data);
                    }
                }
            });
        },
        search: function () {
            $.ajax({
                url: "http://localhost:8180/SchedulePlannerProject2/main/search",
                method: 'GET',
                dataType: 'json',
                success: function (data) {
                    if (data.sections.length === 0) {
                        $("#output").html("No classes were found that meet your search criteria ");
                    }
                    else {
                        console.log(data);
                        displaySectionsTable(data);
                    }
                }
            });
        },
        register: function () {
            $.ajax({
                url: "http://localhost:8180/SchedulePlannerProject2/main/register",
                method: 'POST',
                data: $('#regform').serialize(),
                dataType: 'json',
                success: function (data) {
                    if (data.sections.length === 0) {
                        $("#output").html("No classes were found that meet your search criteria ");
                    }
                    else {
                        displaySectionsTable(data);
                    }

                }

            });

        },
        unregister: function () {
            var that = this;
            console.log("got into delete");
            var url = "http://localhost:8180/SchedulePlannerProject2/main/register?";
            url = url + $("#delform").serialize();
            $.ajax({
                url: url,
                method: 'DELETE',
                dataType: 'json',
                success: function (data) {
                    $("#output").prepend("Course dropped successfully!");
                    that.getSchedule();
                }
            });
        }
    };
})();