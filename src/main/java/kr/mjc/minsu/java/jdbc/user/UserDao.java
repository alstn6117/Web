package kr.mjc.minsu.java.jdbc.user;

import java.util.List;

public interface UserDao {
        List<User> listUsers(int offset, int count);

        void addUser(User user);

        User login(String email, String password);

        User getUser(int userId);

        int updateEmail(int userId, String email);

        int updatePassword(int userId, String oldPassword, String newpassword);
    }

