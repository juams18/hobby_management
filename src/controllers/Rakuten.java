package controllers;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Rakuten {

    //これなに？
    private final String USER__AGENT = "Mozilla/5.0";


    //メインメソッド。変数入れて呼び出すメソッドに書き換える
    public static void main(String[] args) {

    //public String getRakutenUrl(String keyword) {
        //Raktenインスタンスの作成
        Rakuten api = new Rakuten();

        //以下で定義しているRakutencredsをcredsで呼び出し。
        //Rakutenのapiインスタンスにクレデンシャル情報を保存
        RakutenCreds creds = api.getCredentials();

        //デバッグ用。クレデンシャル取得できてるか確認。
       // System.out.println(creds.toString());

        //左側にクレデンシャル情報入力。右側に検索文字列を入力。★ここを変数にする必要あり★
        //修正前api.getFromKeyword(creds, "DEBUG HACKS");

        //デバック用
        String keyword = "サッカー戦術クロニクル";
        String imageUrl = (String) api.getFromKeyword(creds, keyword);

        //return imageUrl;

    }

    //↓publicにしないと呼び出せないかも。★★
    public RakutenCreds getCredentials() {

        //RactenCredsのインスタンス作成
        RakutenCreds creds = new RakutenCreds();

        //credsにload()メソッドを実行しクレデンシャル情報を入力（creds.appIDにID情報etc・・・）
        creds.load();

        return creds;
    }



   //修正前public Map<String, Object> getFromKeyword(RakutenCreds creds, String keyword) {
    public String getFromKeyword(RakutenCreds creds, String keyword) {
        //楽天から受け取ったデータの入れ物。使用しなそうなのでコメントアウト
       // Map<String, Object> res = new HashMap<String, Object>();

        String urlRaw = "https://app.rakuten.co.jp/services/api/IchibaItem/Search/20170706?";

        String largeImageUrl = null;
        //修正前String urlRaw = "https://app.rakuten.co.jp/services/api/BooksTotal/Search/20170404?";

        try {

            //StringBuilderで文字列を結合。検索用のURL作成
            StringBuilder sb = new StringBuilder();
            sb.append(urlRaw);
            sb.append("applicationId=");
            sb.append(URLEncoder.encode(creds.getAppID(),"UTF-8"));
            sb.append("&keyword=");
            sb.append(URLEncoder.encode(keyword,"UTF-8"));

            //デバック用
        //   System.out.println(sb.toString());

            //URL型のインスタンス作成。以下でurl型のメソッド実行するため。
            URL url = new URL(sb.toString());


            //https接続を実行するインスタンスの作成？
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

            //URL要求のメソッドを次のどれかに設定(get,post,head,options...)
            //ページを開くことをリクエストするのでGET
            con.setRequestMethod("GET");


            //setDoOutput＝URL接続を出力用として使用する予定である場合はdoOutputフラグをtrueに設定し、そうでない場合はfalseに設定します。デフォルトはfalseです。
            con.setDoOutput(true);


            //getOutputStream=この接続に書込みを行う出力ストリームを返します。  どういうこと？
            //DataOutputStream=データ出力ストリームを使うと、アプリケーションはプリミティブ型のJavaデータを移植性のある形で出力ストリームに書き込むことができます。
            //https接続するためにURLをstreamインスタンスに格納？このインスタンスができたくらいでstreamでURLへのアクセスがはじまる。
            DataOutputStream stream = new DataOutputStream(con.getOutputStream());

            //streamを実行＝流れてきたデータをメモリに一括保存
            stream.flush();

            stream.close();

            //getResponseCode=HTTP 応答メッセージから状態コードを取得します。(http接続が成功してるかどうか確認用？通信処理の場合分け用）
            int responseCode = con.getResponseCode();

            //InputStreamReader=バイト・ストリームから文字ストリームへの橋渡しの役目を持ちます。バイトを読み込み、指定されたcharsetを使用して文字にデコードします
            //getInputStream=この接続からの入力を受け取る入力ストリームを返します。
            //conのURLで要求しかえってきたレスポンスをinに保持。
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;

            //文字列を扱うインスタンス。高速処理できる。
            StringBuilder response = new StringBuilder();

            //readLine=1行読み込む。
            //空白行に達したらWhile終了
            while ((inputLine = in.readLine()) != null) {

                //読み込んだ文字列を[response]に追記
                response.append(inputLine);
                System.out.println("responseは"+response);
            }

            //デバック用
            //System.out.println(response.toString());

            //RequestBuilderと同じようにMapに変換するのがよい★★
            //ここに２を編集して追記///////////////////////////////////


             ObjectMapper mapper = new ObjectMapper();
             //以下String LargeImageurlまでコピペ。responseをmapにキャスト
             Map reqMap =    mapper.readValue(response.toString(), new TypeReference<Map>() {});
             System.out.println("reqMapは"+reqMap);

             //mapを配列にキャスト
             ArrayList items = (ArrayList) reqMap.get("Items");
             System.out.println("itemsは"+items);

             //配列をマップにキャスト
             Map firstElem = (Map)items.get(0);
             System.out.println("firstElemは"+firstElem);

             //検索して1つ目にヒットしたitemの情報をitemマップに保存
             Map<String,String> item = (Map)firstElem.get("Item");
             System.out.println("itemは"+item);




             //LargeImageUrlの値を保存。
//             largeImageUrl = (String) item.get("largeImageUrl");
             largeImageUrl = (String) item.get("mediumImageUrls");

             System.out.println("largeImageUrlは"+largeImageUrl);

             for (String key : item.keySet()) {
                 System.out.println(key );
             }

             for(String val : item.values()){
                 System.out.println(val);
             }



            in.close();


        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return largeImageUrl;
    }

}
