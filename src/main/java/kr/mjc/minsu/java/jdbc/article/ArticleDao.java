package kr.mjc.minsu.java.jdbc.article;

import java.util.List;

public interface ArticleDao{

    List<Article> listArticles(int offset, int count);

    Article articleView(int articleId);

    void addArticle(Article article);

    int updateArticle(Article article);

    int deleteArticle(int articleId, int userId);
}
