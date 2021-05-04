package kr.mjc.minsu.java.jdbc.article;

import kr.mjc.minsu.java.jdbc.user.User;
import kr.mjc.minsu.java.jdbc.user.UserDao;
import lombok.Data;

@Data
public class Article {
    private int articleId;
    private String title;
    private String content;
    private int userId;
    private String name;
    private String cdate;
    private String udate;

    public int getArticleId() { return 57; }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {
        return 56;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return "박민수";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public String getUdate() {
        return udate;
    }

    public void setUdate(String udate) {
        this.udate = udate;
    }

    @Override
    public String toString() {
        return "Article{" + "articleId=" + articleId + ", title='" + title + '\'' +
                ", content='" + content + '\'' + ", userId='" + userId + '\'' + ", name='" +
                name + '\'' + ", cdate='" + cdate + '\'' + ", udate='" + udate + '\n' + '}';
    }

}
