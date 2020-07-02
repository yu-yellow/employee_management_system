package controllers.working;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Workingplace;
import utils.DBUtil;

/**
 * Servlet implementation class WorkIndexServlet
 */
@WebServlet("/working/workindex")
public class WorkIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        int page = 1;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        } catch(NumberFormatException e) { }
        List<Workingplace> working = em.createNamedQuery("getWorkingplace", Workingplace.class)
                                     .setParameter("id", Long.parseLong(request.getParameter("id")))
                                     .setFirstResult(15 * (page - 1))
                                     .setMaxResults(15)
                                     .getResultList();

        long working_count = (long)em.createNamedQuery("getCount", Long.class)
                                       .setParameter("id", Long.parseLong(request.getParameter("id")))
                                       .getSingleResult();

        em.close();

        request.setAttribute("id", request.getParameter("id"));
        request.setAttribute("working", working);
        request.setAttribute("working_count", working_count);
        request.setAttribute("page", page);
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/working/workindex.jsp");
        rd.forward(request, response);
    }
}