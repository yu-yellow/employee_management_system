package controllers.working;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
import models.validators.WorkingValidator;
import utils.DBUtil;

/**
 * Servlet implementation class WorkUpdateServlet
 */
@WebServlet("/working/workupdate")
public class WorkUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Workingplace w = em.find(Workingplace.class, Long.parseLong(request.getParameter("wid")));

//            w.setEmployee((Employees)em.find(Employees.class, Long.parseLong(request.getParameter("id"))));
            w.setCompany((Company)em.find(Company.class, Long.parseLong(request.getParameter("company_id"))));
            w.setField_manager(request.getParameter("field_manager"));
            try{
                w.setBreaktime(Integer.parseInt(request.getParameter("breaktime")));
            }catch(NumberFormatException e){}
            w.setContent(request.getParameter("content"));
            w.setNote(request.getParameter("note"));
            w.setEnd_flag(Integer.parseInt(request.getParameter("end_flag")));
            w.setDelete_flag(0);

            Time open = new Time(System.currentTimeMillis());
            String op_str = request.getParameter("open");
            if(op_str != null && !op_str.equals("")) {
                open = Time.valueOf(LocalTime.parse(request.getParameter("open"),
                                                     DateTimeFormatter.ofPattern("HH:mm")));
                w.setOpen(open);
            }

            Time close = new Time(System.currentTimeMillis());
            String cl_str = request.getParameter("close");
            if(cl_str != null && !cl_str.equals("")) {
                close = Time.valueOf(LocalTime.parse(request.getParameter("close"),
                                                      DateTimeFormatter.ofPattern("HH:mm")));
                w.setClose(close);
            }

            Date start_at = new Date(System.currentTimeMillis());
            String st_str = request.getParameter("start_at");
            if(st_str != null && !st_str.equals("")) {
                start_at = Date.valueOf(request.getParameter("start_at"));
                w.setStart_at(start_at);
            }

            Date end_at = new Date(System.currentTimeMillis());
            String en_str = request.getParameter("end_at");
            if(en_str != null && !en_str.equals("")) {
                end_at = Date.valueOf(request.getParameter("end_at"));
                w.setEnd_at(end_at);
            }

            List<String> errors = WorkingValidator.validate(w);
            if(errors.size() > 0) {
                List<Company> company = em.createNamedQuery("getCompany", Company.class).getResultList();

                em.close();

                request.setAttribute("wid", request.getParameter("wid"));
                request.setAttribute("company", company);
                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("working", w);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/working/workedit.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.persist(w);
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "更新が完了しました。");

                response.sendRedirect(request.getContextPath() + "/working/workindex?id="+ w.getEmployee().getId());
            }
        }

    }
}
