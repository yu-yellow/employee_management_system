package controllers.employees;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.BelongsNum;
import models.Employees;
import utils.DBUtil;

/**
 * Servlet implementation class EmployeesIndexServlet
 */
@WebServlet("/employees/index")
public class EmployeesIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeesIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        String search_code = new String();
        String search_name = new String();
        String search_belongs = new String();

        try{
            search_code = request.getParameter("search_code");
            search_name = request.getParameter("search_name")+"%";
            search_belongs = request.getParameter("search_belongs");

            if(search_code.equals("")){
                search_code = null;
            }
            if(search_name.equals("%")){
                search_name = null;
            }
            if(search_belongs.equals("")){
                search_belongs = null;
            }
        } catch(NullPointerException e) {}

        int page = 1;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        } catch(NumberFormatException e) { }
        List<Employees> employees = em.createNamedQuery("search", Employees.class)
                                     .setParameter("code", search_code)
                                     .setParameter("name", search_name)
                                     .setParameter("belongs_num", search_belongs)
                                     .setFirstResult(15 * (page - 1))
                                     .setMaxResults(15)
                                     .getResultList();

        long employees_count = (long)em.createNamedQuery("searchCount", Long.class)
                                .setParameter("code", search_code)
                                .setParameter("name", search_name)
                                .setParameter("belongs_num", search_belongs)
                                .getSingleResult();

        List<BelongsNum> belongsnum = em.createNamedQuery("getAllBelongsNum", BelongsNum.class).getResultList();

        em.close();

        request.setAttribute("belongsnum", belongsnum);
        request.setAttribute("search_code", search_code);
        request.setAttribute("search_name", request.getParameter("search_name"));
        request.setAttribute("search_belongs", search_belongs);
        request.setAttribute("employees", employees);
        request.setAttribute("employees_count", employees_count);
        request.setAttribute("page", page);
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/employees/index.jsp");
        rd.forward(request, response);
    }
}