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
 * Servlet implementation class EmployeesUpdateServlet
 */
@WebServlet("/employees/update")
public class EmployeesUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeesUpdateServlet() {
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

            Employees e = em.find(Employees.class, (Long)(request.getSession().getAttribute("employees_id")));
            Password p = em.find(Password.class, e.getCode());
            Report r = em.find(Report.class, e.getCode());

            //ログインユーザの取得
            HttpSession session = ((HttpServletRequest)request).getSession();
            Employees loginuser = (Employees)session.getAttribute("login_employee");



            // 現在の値と異なる従業員番号が入力されていたら
            // 重複チェックを行う指定をする
            Boolean code_duplicate_check = true;
            if(e.getCode().equals(request.getParameter("code"))) {
                code_duplicate_check = false;
            } else {
                e.setCode(request.getParameter("code"));
            }

            // パスワード欄に入力があったら
            // パスワードの入力値チェックを行う指定をする
            Boolean password_check_flag = true;
            String password = request.getParameter("password");
            if(password == null || password.equals("")) {
                password_check_flag = false;
            } else {
                p.setPassword(
                        EncryptUtil.getPasswordEncrypt(
                                password,
                                (String)this.getServletContext().getAttribute("salt")
                                )
                        );
            }

            e.setName_kanzi(request.getParameter("name_kanzi"));
            e.setName_kana(request.getParameter("name_kana"));

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

            r.setUpdated_at(new Timestamp(System.currentTimeMillis()));
            r.setReport_name(loginuser.getName_kanzi());
            e.setDelete_flag(0);

            List<String> errors = EmployeeValidator.validate(e, p, code_duplicate_check, password_check_flag);
            if(errors.size() > 0) {
                List<BelongsNum> belongsnum = em.createNamedQuery("getAllBelongsNum", BelongsNum.class).getResultList();
                em.close();

                request.setAttribute("belongsnum", belongsnum);
                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("employees", e);
//                request.setAttribute("password", p);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/employees/edit.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "更新が完了しました。");

                request.getSession().removeAttribute("employee_id");

                response.sendRedirect(request.getContextPath() + "/employees/index");
            }
        }
    }

}