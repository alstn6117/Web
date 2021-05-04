package kr.mjc.minsu.java.jdbc.user;

public class User {
    private int userId;
    private String email;
    private String password;
    private String name;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", email='" + email + '\'' +
                ", name='" + name + '\'' + '}';
    }

}
