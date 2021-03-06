package controllers.employees;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employees;
import models.Report;
import models.Workingplace;
import utils.DBUtil;

/**
 * Servlet implementation class EmployeesShowServlet
 */
@WebServlet("/employees/show")
public class EmployeesShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeesShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Employees e = em.find(Employees.class, Long.parseLong(request.getParameter("id")));
        Report r = em.find(Report.class, e.getCode());

        Workingplace w = new Workingplace();
        try{
            w = em.createNamedQuery("getNowWork", Workingplace.class)
                .setParameter("emp_id", Long.parseLong(request.getParameter("id")))
                .getSingleResult();
        }catch(NoResultException n){
            w = null;
        }

        em.close();

        request.setAttribute("employees", e);
        request.setAttribute("report", r);
        request.setAttribute("working", w);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/employees/show.jsp");
        rd.forward(request, response);
    }

}