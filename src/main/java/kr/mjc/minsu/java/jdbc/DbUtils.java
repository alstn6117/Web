package kr.mjc.minsu.java.jdbc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbUtils {
    DataSource dataSource;

    public DbUtils(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int update(String sql, Object... params) throws DataAccessException{
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            setParams(ps, params);
            return ps.executeUpdate();
        }catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    public <T> List<T> list(String sql, ResultSetHandler<T> h, Object... params)
        throws DataAccessException {
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            setParams(ps, params);
            ResultSet rs = ps.executeQuery();
            List<T> list = new ArrayList<>();
            while (rs.next()) {
                list.add(h.handle(rs));
            }
            return list;
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }
    public <T> T get(String sql, ResultSetHandler<T> h, Object... params)
            throws DataAccessException {
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            setParams(ps, params);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                T t = h.handle(rs);
                return t;
            }
            return null;
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    private void setParams(PreparedStatement ps, Object... params)
        throws SQLException {
        if (params != null) {
            for (int i =0; i< params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
        }
    }

}
