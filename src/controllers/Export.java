package controllers;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Hobby;
import utils.DBUtil;

/**
 * Servlet implementation class Export
 */
@WebServlet("/export")
public class Export extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Export() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //エクスポートする内容を以下.以降で指示すればOK
        //これはデフォルトの文。Appendされているものがcsv出力される
        //response.getWriter().append("Served at: ").append(request.getContextPath());

        EntityManager em = DBUtil.createEntityManager();

        //文字列を扱うインスタンス。高速処理できる。
        StringBuilder csvExport = new StringBuilder();

        //カンマ
        String COMMA = ",";
        //改行
        String NEW_LINE= "\r\n";

        //Hobbiesテーブルの内容を全て取得
        List<Hobby> hobbies = em.createNamedQuery("getAllHobbies", Hobby.class).getResultList();

        //Listを文字列に変換①
        for (int i = 0; i < hobbies.size(); i++) {

            csvExport.append(hobbies.get(i));
            System.out.println(i+"行目；"+csvExport);
            csvExport.append(NEW_LINE);

            /*
            csvExport.append(h.getTitle());
            csvExport.append(COMMA);
            csvExport.append(h.getKind());
            csvExport.append(COMMA);
            csvExport.append(h.getContent());
            csvExport.append(NEW_LINE);
             */
        }

        em.close();

        // 文字変換追加
        response.setCharacterEncoding("SHIFT-JIS");

        //CSV出力内容を返す
       response.getWriter().append(csvExport.toString());
    }

}
