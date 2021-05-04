package kr.mjc.minsu.java.jdbc.user;

import kr.mjc.minsu.java.jdbc.DataAccessException;
import org.mariadb.jdbc.MariaDbDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImplUsingRawJDBC implements UserDao{
    private static  final String DB_URL
            = "jdbc:mariadb://irafe.com:3306/cafe?user=cafe&password=xxxx";

    private static final  String LIST_USERS
            = "select userId, email, name from user order by userId desc limit ?,?";

    private static final String ADD_USER
            = "insert user(email, password, name) values(?, sha2(?,256), ?)";

    private static final String LOGIN
            = "select userId, email, name from user where (email, password) = (?, sha2(?,256))";

    private static final String GET_USER
            = "select userId, email, name from user where userId=?";

    private static final String UPDATE_EMAIL
            = "update user set email=? where userId=?";

    private static final String UPDATE_PASSWORD
            = "update user set password=sha2(?,256) where userId=? and password=sha2(?,256)";
    private DataSource dataSource;

    public UserDaoImplUsingRawJDBC() {
        dataSource = new MariaDbDataSource(DB_URL);
    }
    @Override
    public List<User> listUsers(int offset, int count)
        throws DataAccessException {
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(LIST_USERS)) {
            ps.setInt(1, offset);
            ps.setInt(2, count);
            ResultSet rs = ps.executeQuery();
            List<User> userList = new ArrayList<>();
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("userId"));
                user.setEmail(rs.getString("email"));
                user.setName(rs.getString("name"));
                userList.add(user);
            }
            rs.close();
            return userList;
        }catch (SQLException e) {
                throw new DataAccessException(e);
            }
        }
        @Override
        public void addUser(User user) throws DataAccessException {
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(ADD_USER)) {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }
        @Override
        public User login(String email, String password) {
            try (Connection con = dataSource.getConnection();
                 PreparedStatement ps = con.prepareStatement(LOGIN)) {
                ps.setString(1, email);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                User user = null;
                if(rs.next()) {
                    user = new User();
                    user.setUserId(rs.getInt("userId"));
                    user.setEmail(rs.getString("email"));
                    user.setName(rs.getString("name"));
                }
                rs.close();
                return user;
            } catch (SQLException e) {
                throw new DataAccessException(e);
            }
        }
        @Override
        public User getUser(int userId) {
            try (Connection con = dataSource.getConnection();
                 PreparedStatement ps = con.prepareStatement(GET_USER)) {
                ps.setInt(1, userId);
                ResultSet rs = ps.executeQuery();
                User user = null;
                if(rs.next()) {
                    user = new User();
                    user.setUserId(rs.getInt("userId"));
                    user.setEmail(rs.getString("email"));
                    user.setName(rs.getString("name"));
                }
                rs.close();
                return user;
            } catch (SQLException e) {
                throw new DataAccessException(e);
            }
        }
        @Override
        public int updateEmail(int userId, String email) {
            try (Connection con = dataSource.getConnection();
                 PreparedStatement ps = con.prepareStatement(UPDATE_EMAIL)) {
                ps.setString(1, email);
                ps.setInt(2, userId);
                return ps.executeUpdate();
            } catch (SQLException e) {
                throw new DataAccessException(e);
            }
        }
        @Override
        public int updatePassword(int userId, String oldPassword, String newPassword) {
            try (Connection con = dataSource.getConnection();
                 PreparedStatement ps = con.prepareStatement(UPDATE_PASSWORD)) {
                ps.setString(1, newPassword);
                ps.setInt(2, userId);
                ps.setString(3, oldPassword);
                return ps.executeUpdate();
            } catch (SQLException e) {
                throw new DataAccessException(e);
            }
        }

}
