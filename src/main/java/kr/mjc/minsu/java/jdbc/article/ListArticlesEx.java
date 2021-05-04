package kr.mjc.minsu.java.jdbc.article;

import java.util.List;

public class ListArticlesEx {
        public static void main(String[] args) {
            ArticleDao articleDao = new ArticleDaoImplUsingDbUtils();
            List<Article> articleList = articleDao.listArticles(0, 10);
            for (Article article : articleList) {
                System.out.println(article);
            }
        }
    }

