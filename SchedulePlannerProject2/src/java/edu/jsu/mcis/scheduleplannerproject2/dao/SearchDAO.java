package edu.jsu.mcis.scheduleplannerproject2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class SearchDAO {

    private final DAOFactory daoFactory;

    private final String QUERY_SUBJECT_LIST = "SELECT * FROM subject ORDER BY id";
    private final String QUERY_LEVEL_LIST = "SELECT * FROM level ORDER BY id";
    private final String QUERY_SCHEDULETYPE_LIST = "SELECT * FROM scheduletype ORDER BY id";
    private final String QUERY_TERM_LIST = "SELECT * FROM term ORDER BY id";
    private static final String QUERY_FIND = "SELECT course.*, section.*, "
            + "term.name AS termname, term.`start` AS termstart, term.`end` AS termend, "
            + "scheduletype.description as scheduletype, `level`.description as `level` "
            + "FROM ((((section JOIN scheduletype ON section.scheduletypeid = scheduletype.id) "
            + "JOIN course ON section.subjectid = course.subjectid AND section.num = course.num) "
            + "JOIN `level` ON course.levelid = `level`.id) "
            + "JOIN term ON section.termid = term.id) "
            + "WHERE ((? IS NULL OR course.subjectid = ?) "
            + "AND (? IS NULL OR course.num = ?) "
            + "AND (? IS NULL OR `level`.id = ?) "
            + "AND (? IS NULL OR section.scheduletypeid = ?) "
            + "AND (? IS NULL OR section.`start` >= ?) "
            + "AND (? IS NULL OR section.`end` <= ?) "
            + "AND (? IS NULL OR section.days REGEXP ?) "
            + "AND (section.termid = ?)) "
            + "ORDER BY course.num, section";

    SearchDAO(DAOFactory dao) {
        this.daoFactory = dao;
    }

    public String find(HashMap<String, String> params) {
        JSONObject json = new JSONObject();
        JSONArray sections = new JSONArray();

        json.put("success", false);

        Connection conn = daoFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(QUERY_FIND);
            ps.setString(1, params.get("subject"));
            ps.setString(2, params.get("subject"));

            ps.setString(3, params.get("coursenumber"));
            ps.setString(4, params.get("courseNumber"));

            ps.setString(5, params.get("courseLevel"));
            ps.setString(6, params.get("courseLevel"));

            ps.setString(7, params.get("scheduleType"));
            ps.setString(8, params.get("scheduleType"));

            ps.setString(9, params.get("start"));
            ps.setString(10, params.get("start"));

            ps.setString(11, params.get("end"));
            ps.setString(12, params.get("end"));

            ps.setString(13, params.get("days"));
            ps.setString(14, params.get("days"));

            ps.setString(15, params.get("term"));
            System.err.print("QUERY STRING----");
            System.err.println(ps.toString());

            boolean hasresults = ps.execute();

            if (hasresults) {
                json.put("success", hasresults);

                rs = ps.getResultSet();

                DateTimeFormatter date = DateTimeFormatter.ofPattern("LLL dd, YYYY");
                DateTimeFormatter time = DateTimeFormatter.ofPattern("h:mm a");

                while (rs.next()) {
                    JSONObject section = new JSONObject();

                    section.put("subjectid", rs.getString("subjectid"));
                    section.put("num", rs.getString("num"));
                    section.put("levelid", rs.getString("levelid"));
                    section.put("scheduletypeid", rs.getString("scheduletypeid"));
                    section.put("start", time.format(LocalTime.parse(rs.getString("start"))));
                    section.put("end", time.format(LocalTime.parse(rs.getString("end"))));
                    section.put("days", rs.getString("days"));
                    section.put("description", rs.getString("description"));
                    section.put("crn", rs.getString("crn"));
                    section.put("termid", rs.getString("termid"));
                    section.put("where", rs.getString("where"));
                    section.put("termstart", date.format(LocalDate.parse(rs.getString("termstart"))));
                    section.put("termend", date.format(LocalDate.parse(rs.getString("termend"))));
                    section.put("termname", rs.getString("termname"));
                    section.put("instructor", rs.getString("instructor"));
                    section.put("section", rs.getString("section"));
                    section.put("level", rs.getString("level"));
                    section.put("credits", String.format("%.3f", Double.parseDouble(rs.getString("credits"))));
                    section.put("scheduletype", rs.getString("scheduletype"));

                    sections.add(section);
                }
                json.put("sections", sections);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                    ps = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

        return JSONValue.toJSONString(json);
    }

    /*
     * HTML METHODS
     */
    public String getSubjectListAsHTML() {

        StringBuilder myStringBuilder = new StringBuilder();

        Connection conn = daoFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            ps = conn.prepareStatement(QUERY_SUBJECT_LIST);

            boolean hasresults = ps.execute();

            if (hasresults) {

                rs = ps.getResultSet();

                myStringBuilder.append("<select name=\"subject\" id=\"subject\" size=\"10\">");

                while (rs.next()) {

                    String id = rs.getString("id");
                    String description = rs.getString("name");

                    myStringBuilder.append("<option value=\"").append(id).append("\">").append(description)
                            .append("</option>");

                }

                myStringBuilder.append("</select>");

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                    ps = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

        return myStringBuilder.toString();

    }

    public String getScheduleTypeListAsHTML() {

        StringBuilder myStringBuilder = new StringBuilder();

        Connection conn = daoFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            ps = conn.prepareStatement(QUERY_SCHEDULETYPE_LIST);

            boolean hasresults = ps.execute();

            if (hasresults) {

                rs = ps.getResultSet();

                myStringBuilder.append("<select name=\"scheduleType\" id=\"scheduleType\"size=\"3\">");
                myStringBuilder.append("<option value=\"0\" selected=\"\">All</option>");

                while (rs.next()) {

                    String id = rs.getString("id");
                    String description = rs.getString("description");

                    myStringBuilder.append("<option value=\"").append(id).append("\">").append(description)
                            .append("</option>");
                }

                myStringBuilder.append("</select>");

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                    ps = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

        return myStringBuilder.toString();

    }

    public String getLevelListAsHTML() {

        StringBuilder myStringBuilder = new StringBuilder();

        Connection conn = daoFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            ps = conn.prepareStatement(QUERY_LEVEL_LIST);

            boolean hasresults = ps.execute();

            if (hasresults) {

                rs = ps.getResultSet();

                myStringBuilder.append("<select name=\"courseLevel\" id=\"courseLevel\">");

                myStringBuilder.append("<option value=\"0\" selected=\"\">All</option>");

                while (rs.next()) {

                    String id = rs.getString("id");
                    String description = rs.getString("description");

                    myStringBuilder.append("<option value=\"").append(id).append("\">").append(description)
                            .append("</option>");
                }

                myStringBuilder.append("</select>");

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                    ps = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

        return myStringBuilder.toString();

    }

    public String getTermListAsHTML() {

        StringBuilder myStringBuilder = new StringBuilder();

        Connection conn = daoFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            ps = conn.prepareStatement(QUERY_TERM_LIST);

            boolean hasresults = ps.execute();

            if (hasresults) {

                rs = ps.getResultSet();

                myStringBuilder.append("<select name=\"term\" id=\"term\">");

                while (rs.next()) {

                    String id = rs.getString("id");
                    String description = rs.getString("name");

                    myStringBuilder.append("<option value=\"").append(id).append("\">").append(description)
                            .append("</option>");

                }

                myStringBuilder.append("</select>");

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                    ps = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

        return myStringBuilder.toString();

    }

    public String getTimeFieldsAsHTML(String type) {
        StringBuilder sb = new StringBuilder();

        sb.append("Hour <select id=\"").append(type).append("hour\" name=\"").append(type)
                .append("hour\" size=\"1\">");
        for (int i = 0; i <= 12; i++) {
            sb.append("<option value=\"").append(i).append("\">")
                    .append(String.format("%02d", i)).append("</option>");
        }
        sb.append("</select>");

        sb.append(" Minute <select id=\"").append(type).append("min\" name=\"").append(type)
                .append("min\" size=\"1\">");
        for (int i = 0; i <= 55; i += 5) {
            sb.append("<option value=\"").append(i).append("\">")
                    .append(String.format("%02d", i)).append("</option>");
        }
        sb.append("</select>");

        sb.append(" AM/PM <select id=\"").append(type).append("ap\" name=\"").append(type)
                .append("ap\" size=\"1\"><option value=\"a\">am</option><option value=\"p\">pm</option></select>");

        return sb.toString();
    }

}