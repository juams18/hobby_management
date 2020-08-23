package controllers;
import java.io.InputStream;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class RakutenCreds {

    private String appID;
    private String secret;
    private String affID;

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
            Map<String, Object> obj = yaml.load(inputStream);
            System.out.println(obj.toString());
            this.setAppID(obj.get("application_id").toString());
            this.setSecret(obj.get("secret").toString());
            this.setAffID(obj.get("affiliateId").toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    public RakutenCreds(){}
}
