package kr.mjc.minsu.java.jdbc.article;

import kr.mjc.minsu.java.jdbc.DataAccessException;

public class AddArticleEx {
    public static void main(String[] args) {
        ArticleDao articleDao = new ArticleDaoImplUsingDbUtils();
        Article article = new Article();
        article.setTitle("기사");
        article.setContent("기사내용");
        article.getUserId();
        article.getName();
        try {
            articleDao.addArticle(article);
            System.out.format("기사를 추가했습니다 : %s\n", article);
        } catch (DataAccessException e ) {
            System.err.println(e.getMessage());
        }
    }
}
