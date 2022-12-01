package edu.jsu.mcis.scheduleplannerproject2;

import edu.jsu.mcis.scheduleplannerproject2.dao.DAOFactory;
import edu.jsu.mcis.scheduleplannerproject2.dao.RegistrationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        DAOFactory daoFactory = null;

        ServletContext context = request.getServletContext();
        Bean projBean = (Bean)request.getSession().getAttribute("projBean");
        if (context.getAttribute("daoFactory") == null) {
            System.err.println("*** Creating new DAOFactory ...");
            daoFactory = new DAOFactory();
            context.setAttribute("daoFactory", daoFactory);
        }
        else {
            daoFactory = (DAOFactory) context.getAttribute("daoFactory");
        }
        
        RegistrationDAO dao = daoFactory.getRegistrationDAO();
        
        response.setContentType("application/json; charset=UTF-8");
        
        try ( PrintWriter out = response.getWriter()) {
            int studentid = dao.getStudentId(request.getRemoteUser());
            int termid = Integer.parseInt(projBean.getParameters().get("term"));
            
            out.println(dao.find(termid, studentid));
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOFactory daoFactory = null;

        ServletContext context = request.getServletContext();

        if (context.getAttribute("daoFactory") == null) {
            System.err.println("*** Creating new DAOFactory ...");
            daoFactory = new DAOFactory();
            context.setAttribute("daoFactory", daoFactory);
        }
        else {
            daoFactory = (DAOFactory) context.getAttribute("daoFactory");
        }
        
        RegistrationDAO dao = daoFactory.getRegistrationDAO();
        Bean projBean = (Bean)request.getSession().getAttribute("projBean");
        
        response.setContentType("application/json; charset=UTF-8");
        
        try ( PrintWriter out = response.getWriter()) {
            int studentid = dao.getStudentId(request.getRemoteUser());
            int termid = Integer.parseInt(projBean.getParameters().get("term"));
            int crn = Integer.parseInt(request.getParameter("crn"));
             System.err.println("studentid-------"+studentid);
            System.err.println("termid-------"+termid);
            System.err.println("crn-------"+crn);
            out.println(dao.create(studentid, termid, crn));
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOFactory daoFactory = null;
           System.err.println("got into doDelete()");
        ServletContext context = request.getServletContext();

        if (context.getAttribute("daoFactory") == null) {
            System.err.println("*** Creating new DAOFactory ...");
            daoFactory = new DAOFactory();
            context.setAttribute("daoFactory", daoFactory);
        }
        else {
            daoFactory = (DAOFactory) context.getAttribute("daoFactory");
        }
        
        RegistrationDAO dao = daoFactory.getRegistrationDAO();
        Bean projBean = (Bean)request.getSession().getAttribute("projBean");
        
        response.setContentType("application/json; charset=UTF-8");
        
        try ( PrintWriter out = response.getWriter()) {
            int studentid = dao.getStudentId(request.getRemoteUser());
            int termid = Integer.parseInt(projBean.getParameters().get("term"));
            int crn = Integer.parseInt(request.getParameter("crn"));
           
            System.err.println("studentid-------"+studentid);
            System.err.println("termid-------"+termid);
            System.err.println("crn-------"+crn);
            
            out.println(dao.delete(termid, studentid, crn));
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    
   

}
