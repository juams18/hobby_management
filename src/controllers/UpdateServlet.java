package controllers;

import java.io.IOException;
import java.sql.Date;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Hobby;
import utils.DBUtil;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            EntityManager em = DBUtil.createEntityManager();

            Hobby h = em.find(Hobby.class, (Integer)(request.getSession().getAttribute("hobby_id")));

            h.setReport_date(Date.valueOf(request.getParameter("report_date")));
            h.setTitle(request.getParameter("title"));
            h.setContent(request.getParameter("content"));
            h.setKind(request.getParameter("kind"));

            em.getTransaction().begin();
            em.getTransaction().commit();
            em.close();

            request.getSession().removeAttribute("hobby_id");

            response.sendRedirect(request.getContextPath() + "/index");

    }
}
