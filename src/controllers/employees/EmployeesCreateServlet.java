package controllers.employees;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.BelongsNum;
import models.Employees;
import models.Password;
import models.Report;
import models.validators.EmployeeValidator;
import utils.DBUtil;
import utils.EncryptUtil;

/**
 * Servlet implementation class EmployeesCreateServlet
 */
@WebServlet("/employees/create")
public class EmployeesCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeesCreateServlet() {
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

            //instance
            Employees e = new Employees();
            Password p = new Password();
            Report r = new Report();

            //ログインユーザの取得
            HttpSession session = ((HttpServletRequest)request).getSession();
            Employees loginuser = (Employees)session.getAttribute("login_employee");

            e.setCode(request.getParameter("code"));
            p.setPassword_id(request.getParameter("code"));
            r.setReport_id(request.getParameter("code"));

            e.setName_kanzi(request.getParameter("name_kanzi"));
            e.setName_kana(request.getParameter("name_kana"));
            p.setPassword(
                    EncryptUtil.getPasswordEncrypt(
                            request.getParameter("password"),
                            (String)this.getServletContext().getAttribute("salt")
                            )
                    );
            e.setBelongs((BelongsNum)em.find(BelongsNum.class, request.getParameter("belongs_num")));

            Date birthday_at = new Date(System.currentTimeMillis());
            String ba_str = request.getParameter("birthday_at");
            if(ba_str != null && !ba_str.equals("")) {
                birthday_at = Date.valueOf(request.getParameter("birthday_at"));
                e.setBirthday_at(birthday_at);
            }

            Date join_at = new Date(System.currentTimeMillis());
            String ja_str = request.getParameter("join_at");
            if(ja_str != null && !ja_str.equals("")) {
                join_at = Date.valueOf(request.getParameter("join_at"));
                e.setJoin_at(join_at);
            }

            Date leave_at = new Date(System.currentTimeMillis());
            String la_str = request.getParameter("leave_at");
            if(la_str != null && !la_str.equals("")) {
                leave_at = Date.valueOf(request.getParameter("leave_at"));
                e.setLeave_at(leave_at);
            }

            e.setAdmin_flag(Integer.parseInt(request.getParameter("admin_flag")));

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            String user = loginuser.getName_kanzi();
            r.setCreate_at(currentTime);
            r.setCreate_name(user);
            r.setUpdated_at(currentTime);
            r.setReport_name(user);
            e.setDelete_flag(0);

            List<String> errors = EmployeeValidator.validate(e, p, true, true);
            if(errors.size() > 0) {
                List<BelongsNum> belongsnum = em.createNamedQuery("getAllBelongsNum", BelongsNum.class).getResultList();

                em.close();

                request.setAttribute("belongsnum", belongsnum);
                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("employees", e);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/employees/new.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.persist(e);
                em.persist(p);
                em.persist(r);
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "登録が完了しました。");

                response.sendRedirect(request.getContextPath() + "/");
            }
        }
    }

}