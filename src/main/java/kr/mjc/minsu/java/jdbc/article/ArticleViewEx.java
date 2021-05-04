package kr.mjc.minsu.java.jdbc.article;

public class ArticleViewEx {
        public static void main(String[] args) {
            ArticleDao articleDao = new ArticleDaoImplUsingDbUtils();
            Article article = articleDao.articleView(57);
            System.out.println(article);
        }
}


