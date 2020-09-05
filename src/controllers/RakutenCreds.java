package controllers;

import java.io.InputStream;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;


public class RakutenCreds {

    private String appID;
    private String secret;
    private String affID;


    //tkg  getter setter
    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getAffID() {
        return affID;
    }

    public void setAffID(String affID) {
        this.affID = affID;
    }

    public void load() {
        try {
            Yaml yaml = new Yaml();
            InputStream inputStream = this.getClass()
              .getClassLoader()
              .getResourceAsStream("META-INF/config.yml");

            //this.getclass：このrakutenCredsのクラス情報を取得する。（どんなプロパティを持ってるかなど。メタ情報）
            //.getclassloader:このclassを読み込んだclassloaderを取得する
            //                classloader：このプロジェクトで使用するクラスをまとめて取得する。(1プロジェクトで4つくらいある）
            //                              classloaderで読み込んだものを他のclassでimportで参照して使えるようになる。
            //.getResourceAsStream：classloaderが読めるフォルダ(自分の管理対象下)にある"META-INF～"を取得

            Map<String, Object> obj = yaml.load(inputStream);
            //キーがString型、valueがobject型(int,foatのようなプリミティブ型以外)　宣言
            //マップ型のobjに.yamlファイル情報を入力
            //yamlの左側がキー値。

//        System.out.println(obj.toString());
            //tkg デバッグ用と思われる

  //         System.out.println(obj.get("application_id").toString());
    //        System.out.println(obj.get("secret"));
      //      System.out.println(obj.get("affiliateId"));




            //.get（●●）で●●をキーとしてValueを取得する。
            //ここのtoString()で文字列型に変換。toStringはこの下で定義しているtoString()メソッドではない？記載しなくてもいいやつ？
            //https://www.kenschool.jp/blog/?p=4020
            this.setAppID(obj.get("application_id").toString());
            this.setSecret(obj.get("secret").toString());
            this.setAffID(obj.get("affiliateId").toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //tkg stringbuilder.appendで文字列の連結 以下丸ごとデバック用？？それとも↑のthis.setAppIDの3行で参照されてる？
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        sb.append("appID : ");
        sb.append(this.appID);
        sb.append(", secret : ");
        sb.append(this.secret);
        sb.append(", affID :");
        sb.append(this.affID);
        sb.append(" }");
        return sb.toString();
    }

    //tkg　引数なしコンストラクタ
    public RakutenCreds(){}


}
