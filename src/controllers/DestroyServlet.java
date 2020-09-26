package controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.DBUtil;

/**
 * Servlet implementation class DestroyServlet
 */
@WebServlet("/destroy")
public class DestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DestroyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            EntityManager em = DBUtil.createEntityManager();

//            Hobby h = em.find(Hobby.class, Integer.parseInt(request.getParameter("id")));

          //  Hobby h = em.find(Hobby.class, (Integer)(request.getSession().getAttribute("hobby_id")));

            //メッセージボードのDestroyコピペ
//            Message m = em.find(Message.class, (Integer)(request.getSession().getAttribute("message_id")));

            //メッセージボードのeditコピペ
//            Message m = em.find(Message.class, Integer.parseInt(request.getParameter("id")));


               //１がいけてほしい。
          System.out.println("1は"+Integer.parseInt(request.getParameter("id")));
          System.out.println("2は"+(Integer)(request.getSession().getAttribute("hobby_id")));
//          System.out.println("3は"+Integer.parseInt(request.getParameter("hobby_id")));
          System.out.println("4は"+(Integer)(request.getSession().getAttribute("id")));

            em.getTransaction().begin();
      //      em.remove(h);       // データ削除
            em.getTransaction().commit();
            em.close();

            // セッションスコープ上の不要になったデータを削除
      //      request.getSession().removeAttribute("hobby_id");

            // indexページへリダイレクト
            response.sendRedirect(request.getContextPath() + "/index");

        }
}
