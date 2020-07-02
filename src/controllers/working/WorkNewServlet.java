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
 * Servlet implementation class WorkNewServlet
 */
@WebServlet("/working/worknew")
public class WorkNewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkNewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();
        List<Company> company = em.createNamedQuery("getCompany", Company.class).getResultList();
        em.close();

        request.setAttribute("company", company);
        request.setAttribute("id", request.getParameter("id"));
        request.setAttribute("_token", request.getSession().getId());
        request.setAttribute("working", new Workingplace());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/working/worknew.jsp");
        rd.forward(request, response);
    }

}
