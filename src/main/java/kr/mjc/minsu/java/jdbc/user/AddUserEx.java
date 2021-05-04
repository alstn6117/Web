package kr.mjc.minsu.java.jdbc.user;

import kr.mjc.minsu.java.jdbc.DataAccessException;

public class AddUserEx {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoImplUsingRawJDBC();
        User user = new User();
        user.setEmail("alstn7686@naver.com");
        user.setPassword("2alstn");
        user.setName("박민수");
        try {
            userDao.addUser(user);
            System.out.format("사용자를 추가했습니다 : %s\n", user);
        } catch (DataAccessException e ) {
            System.err.println(e.getMessage());
        }
    }
}
