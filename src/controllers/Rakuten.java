package controllers;

import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Rakuten {

    private final String USER__AGENT = "Mozilla/5.0";

    public static void main(String[] args) {
        // TODO 自動生成されたメソッド・スタブ
        Rakuten api = new Rakuten();
        RakutenCreds creds = api.getCredentials();
        System.out.println(creds.toString());
        api.getFromKeyword(creds, "DEBUG HACKS");
    }

    private RakutenCreds getCredentials() {
        RakutenCreds creds = new RakutenCreds();
        creds.load();
        return creds;
    }

    public Map<String, Object> getFromKeyword(RakutenCreds creds, String keyword) {
        Map<String, Object> res = new HashMap<String, Object>();
        String urlRaw = "https://app.rakuten.co.jp/services/api/BooksTotal/Search/20170404?";
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(urlRaw);
            sb.append("applicationId=");
            sb.append(URLEncoder.encode(creds.getAppID(),"UTF-8"));
            sb.append("&keyword=");
            sb.append(URLEncoder.encode(keyword,"UTF-8"));
            System.out.println(sb.toString());
            URL url = new URL(sb.toString());

            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setDoOutput(true);

            DataOutputStream stream = new DataOutputStream(con.getOutputStream());
            stream.flush();
            stream.close();

            int responseCode = con.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            System.out.println(response.toString());
            in.close();

        } catch (Exception e) {
            // TODO 自動生成された catch ブロック
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return res;
    }

}
