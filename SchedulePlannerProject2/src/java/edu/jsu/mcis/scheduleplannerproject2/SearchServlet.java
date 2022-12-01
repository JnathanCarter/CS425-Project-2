package edu.jsu.mcis.scheduleplannerproject2;

import edu.jsu.mcis.scheduleplannerproject2.dao.DAOFactory;
import edu.jsu.mcis.scheduleplannerproject2.dao.SearchDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOFactory daoFactory = null;

        ServletContext context = request.getServletContext();
        Bean projBean = (Bean) request.getSession().getAttribute("projBean");

        if (context.getAttribute("daoFactory") == null) {
            System.err.println("*** Creating new DAOFactory ...");
            daoFactory = new DAOFactory();
            context.setAttribute("daoFactory", daoFactory);
        } else {
            daoFactory = (DAOFactory) context.getAttribute("daoFactory");
        }

        try (PrintWriter out = response.getWriter()) {
            response.setContentType("application/json; charset=UTF-8");

            SearchDAO dao = daoFactory.getSearchDAO();
            System.out.println("------------------");
            System.out.println(projBean.getParameters());
            out.println(dao.find(projBean.getParameters()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
