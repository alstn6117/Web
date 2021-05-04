package kr.mjc.minsu.java.jdbc.article;

import kr.mjc.minsu.java.jdbc.DataAccessException;

public class UpdateArticleEx {
    public static void main(String[] args) {
        ArticleDao articleDao = new ArticleDaoImplUsingDbUtils();
        Article article = new Article();
        article.setTitle("기사업데이트");
        article.setContent("기사내용업데이트");
        article.getArticleId();
        try {
            articleDao.updateArticle(article);
            System.out.format("기사를 수정했습니다 : %s\n", article);
        } catch (DataAccessException e ) {
            System.err.println(e.getMessage());
        }
    }
}
