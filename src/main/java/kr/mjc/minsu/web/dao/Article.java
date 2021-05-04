package kr.mjc.minsu.web.dao;

import lombok.Data;

@Data
public class Article {
    int articleId;
    String title;
    String content;
    int userId;
    String name;
    String cdate;
    String udate;

    @Override
    public String toString() {
        return "Article{" + "articleId=" + articleId + ", title='" + title + '\'' +
                ", content='" + content + '\'' + ", userId='" + userId + '\'' + ", name='" +
                name + '\'' + ", cdate='" + cdate + '\'' + ", udate='" + udate + '\n' + '}';
    }
}