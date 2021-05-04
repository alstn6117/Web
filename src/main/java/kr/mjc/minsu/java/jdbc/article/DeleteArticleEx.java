package kr.mjc.minsu.java.jdbc.article;

import kr.mjc.minsu.java.jdbc.DataAccessException;

public class DeleteArticleEx {
    public static void main(String[] args) {
        ArticleDao articleDao = new ArticleDaoImplUsingDbUtils();

        try {
            articleDao.deleteArticle(103,56);
            System.out.format("기사를 삭제했습니다. \n");
        } catch (DataAccessException e ) {
            System.err.println(e.getMessage());
        }
    }
}
