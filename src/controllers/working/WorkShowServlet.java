package controllers.working;

import java.io.IOException;

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
 * Servlet implementation class WorkShowServlet
 */
@WebServlet("/working/workshow")
public class WorkShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        EntityManager em = DBUtil.createEntityManager();

        Workingplace w = em.find(Workingplace.class, Long.parseLong(request.getParameter("wid")));

        em.close();

        request.setAttribute("working", w);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/working/workshow.jsp");
        rd.forward(request, response);
    }

}