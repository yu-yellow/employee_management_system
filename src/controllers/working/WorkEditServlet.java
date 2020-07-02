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

import models.Company;
import models.Workingplace;
import utils.DBUtil;

/**
 * Servlet implementation class WorkEditServlet
 */
@WebServlet("/working/workedit")
public class WorkEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Workingplace w = em.find(Workingplace.class, Long.parseLong(request.getParameter("wid")));
        List<Company> company = em.createNamedQuery("getCompany", Company.class).getResultList();

        em.close();

        request.setAttribute("wid", request.getParameter("wid"));
        request.setAttribute("company", company);
        request.setAttribute("working", w);
        request.setAttribute("_token", request.getSession().getId());
//        request.getSession().setAttribute("employees_id", e.getId());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/working/workedit.jsp");
        rd.forward(request, response);
    }

}
