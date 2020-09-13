package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import models.Hobby;
import utils.DBUtil;

@WebServlet("/import")
//form の enctypeをmultipart/form-dataにすると、以下のアノテーションが必須。最大1Mまでのファイルを受け付ける
@MultipartConfig(maxFileSize = 1048576)
public class Import extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Import() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // import画面の表示
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/csv_form.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        // form の enctypeをmultipart/form-dataにすると、request.getPart(inputタグのname属性の値)でファイルを取得できる
        Part part = request.getPart("csv");

        // part.getInputStreamをInputStreamReaderで読み込み、BufferedReaderで1行ずつ読めるようにします
        // RequestBuilderの読み込みとか、RakutenAPIのResponse読み込みとかと同じ
        //BufferedReader reader = null; // 書き方考えてね★

        BufferedReader reader = new BufferedReader(new InputStreamReader(part.getInputStream(),"SHIFT_JIS"));

        String inputLine;


        final Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
       em.getTransaction().begin();


        while ((inputLine = reader.readLine()) != null) {

            // headerがあるCSVだと、1行目は無視したほうがいいかも。


            // とりこんだCSVの1行を[,] で区切って配列にする　inputLine.split(",")
            // 配列から固定長Listに変換する　Arrays.asList(変換したい配列)
            // Listから変動長のArrayListに変換する new ArrayList<String>(変換したいList)
            // 以下のnullを変えてね★
            String[] splitedCSV = inputLine.split(",");
            List fixedList = Arrays.asList(splitedCSV);
            List<String> csvElems =  new ArrayList<String>(fixedList);

            // a,b,c,d だと["a","b","c","d"]の配列
            // a,b,c, だと["a","b","c"]の配列で要素数が合わなくなる
            // CSVの項目に必須でない項目があると足りない要素がでてくるので検知して追加しよう
            // 私の例だとCSVの最後の要素[備考]だけ必須でないので行の最後が「,」になっているかだけチェックする
            /*一旦、CSVは完璧なものとしてifは省略
            if(inputLine.endsWith(",")){
                // csvElemsに空文字を追加しよう
            }
            */

            // マジックナンバーを避けるためのインデックス
            int i = 0;

            // DTOのinstanceを準備
            //TodoData n = new TodoData();
            Hobby h = new Hobby();
            Rakuten api = new Rakuten();
            RakutenCreds creds = api.getCredentials();
            Date report_date = new Date(System.currentTimeMillis());


            // csvのカラムの順番に沿って、インスタンスの値をセット
            /*TimeStamp型のセットがうまくいかないので使わない（CSVで取り込まない）
            try {

                h.setReport_date(new Timestamp(sdf.parse(csvElems.get(i++)).getTime()));

            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            */

            h.setTitle(csvElems.get(i++));
            h.setKind(csvElems.get(i++));
            h.setContent(csvElems.get(i++));
            h.setReport_date(report_date);

            h.setRakuten_picture(api.getFromKeyword(creds,h.getTitle()));
            // 作ったインスタンスを保存
            /*
             * ここに保存のコードを書いてね★
             */
          //  em.getTransaction().begin();
            em.persist(h);
          //  em.getTransaction().commit();
          //  em.close();

        }
        em.getTransaction().commit();
        em.close();


        // 保存しました。みたいなメッセージが出せるとユーザーに優しい
        request.getSession().setAttribute("flush", "登録が完了しました。");
        String contextPath = ((HttpServletRequest) request).getContextPath();
      //  response.sendRedirect(contextPath + "/index.html");
        response.sendRedirect(contextPath + "/index");
    }

}
