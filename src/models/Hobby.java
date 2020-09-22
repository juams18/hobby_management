package models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;



@Table(name = "hobbies")
@NamedQueries({
    @NamedQuery(
            name = "getAllHobbies",
            query = "SELECT r FROM Hobby AS r ORDER BY r.id DESC"
            ),
    @NamedQuery(
            name = "getHobbiesCount",
            query = "SELECT COUNT(r) FROM Hobby AS r"
            ),
})

@Entity

public class Hobby {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //kind = 分類(映画、ゲームetc..)
    @Column(name = "kind", length = 255, nullable = false)
    private String kind;

    @Column(name = "title", length = 255, nullable = false)
    private String title;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "report_date", nullable = false)
    private Date report_date;

    //rakutenの画像へのハイパーリンク（rakutenAPIから新規登録・更新時に受け取る）
    @Column(name = "rakuten_picture")
    private String rakuten_picture;

    public Integer getId() {
        return id;
    }

    public String getKind() {
        return kind;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getReport_date() {
        return report_date;
    }

    public String getRakuten_picture() {
        return rakuten_picture;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setReport_date(Date report_date) {
        this.report_date = report_date;
    }

    public void setRakuten_picture(String rakuten_picture) {
        this.rakuten_picture = rakuten_picture;
    }

    public String toString(){

        //カンマ
        String COMMA = ",";

        StringBuilder sb = new StringBuilder();

        sb.append(this.getTitle());
        sb.append(COMMA);
        sb.append(this.getKind());
        sb.append(COMMA);
        sb.append(this.getContent());

        return sb.toString();
    }

}
