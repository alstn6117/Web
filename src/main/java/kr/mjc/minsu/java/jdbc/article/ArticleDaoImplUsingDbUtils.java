package kr.mjc.minsu.java.jdbc.article;

import kr.mjc.minsu.java.jdbc.DbUtils;
import kr.mjc.minsu.java.jdbc.ResultSetHandler;
import org.mariadb.jdbc.MariaDbDataSource;
import kr.mjc.minsu.java.jdbc.user.User;
import kr.mjc.minsu.java.jdbc.user.UserDao;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class ArticleDaoImplUsingDbUtils implements ArticleDao {

    private static final String LIST_ARTICLES
            = "select articleId, title, content, name, userId, cdate, udate from article order by articleId desc limit ?,?";

    private static final String ARTICLE_VIEW
            = "select articleId, title, name, content, userId, name, cdate, udate from article where articleId=?";

    private static final String ADD_ARTICLE
            = "insert article(title, content, userId, name) values(?, ?, ?,?)";

    private static final String UPDATE_ARTICLE
            = "update article set title=?, content=? where articleId=?";

    private static final String DELETE_ARTICLE
            = "delete from article where articleId=? and userId=?";
    private DbUtils dbUtils;

    ResultSetHandler<Article> h = (rs) -> {
        Article article = new Article();
        article.setArticleId(rs.getInt("articleId"));
        article.setContent(rs.getString("content"));
        article.setTitle(rs.getString("title"));
        article.setName(rs.getString("name"));
        article.setUserId(rs.getInt("userId"));
        article.setCdate(rs.getString("cdate"));
        article.setUdate(rs.getString("udate"));
        return article;
    };

    public ArticleDaoImplUsingDbUtils() {
        Properties props = new Properties();
        try (InputStream in = getClass().getClassLoader()
                .getResourceAsStream("db.properties")) {
            props.load(in);
            DataSource dataSource = new MariaDbDataSource(props.getProperty("db.url"));
            dbUtils = new DbUtils(dataSource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


        @Override
        public List<Article> listArticles(int offset, int count) {
            return dbUtils.list(LIST_ARTICLES, h, offset, count);
        }

        @Override
        public void addArticle(Article article) {
            dbUtils.update(ADD_ARTICLE, article.getTitle(), article.getContent(), article.getUserId(),article.getName());
        }

        @Override
        public Article articleView(int articleId) {
            return dbUtils.get(ARTICLE_VIEW, h, articleId);
        }

        @Override
        public int updateArticle(Article article) {
            return dbUtils.update(UPDATE_ARTICLE, article.getTitle(),article.getContent(),article.getArticleId());
        }

        @Override
        public int deleteArticle(int articleId, int userId) {
            return dbUtils.update(DELETE_ARTICLE, articleId, userId);
        }
}
