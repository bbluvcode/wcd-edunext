package fpt.aptech.wcd_edunext.models;

public class User {

    private String userId;
    private String username;
    private String password;
    private String email;
    private String photo;
    private int roleId;

    public User() {
    }

    public User(String userId, String username, String password, String email, String photo, int roleId) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.photo = photo;
        this.roleId = roleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
