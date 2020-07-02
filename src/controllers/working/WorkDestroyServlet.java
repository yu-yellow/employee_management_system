package controllers.working;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Workingplace;
import utils.DBUtil;

/**
 * Servlet implementation class WorkDestroyServlet
 */
@WebServlet("/working/workdestroy")
public class WorkDestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkDestroyServlet() {
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

            w.setDelete_flag(1);

            em.getTransaction().begin();
            em.getTransaction().commit();
            em.close();
            request.getSession().setAttribute("flush", "削除が完了しました。");
            request.setAttribute("id", request.getParameter("id"));

            response.sendRedirect(request.getContextPath() + "/working/workindex?id=" + w.getEmployee().getId());
        }
    }

}