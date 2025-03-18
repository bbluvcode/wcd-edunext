package fpt.aptech.wcd_edunext.dto;

import lombok.*;

@Data // Lombok annotation to generate getters, setters, toString, equals, and hashCode
@NoArgsConstructor // Lombok annotation to generate a no-args constructor
@AllArgsConstructor // Lombok annotation to generate an all-args constructor
public class UserDTO {

    private String UserId;
    private String Username;
    private String Email;
    private String Photo;
    private String Password;
    private Integer RoleId;

    public UserDTO() {
    }

    public UserDTO(String UserId, String Username, String Email, String Photo, String Password, Integer RoleId) {
        this.UserId = UserId;
        this.Username = Username;
        this.Email = Email;
        this.Photo = Photo;
        this.Password = Password;
        this.RoleId = (RoleId != null) ? RoleId : 2;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String Photo) {
        this.Photo = Photo;
    }
    private String userId;
    private String username;
    private String email;
    private String photo;
    private String password;
    private Integer roleId;

    // If you prefer not to use Lombok, you can manually define the constructors, getters, and setters as shown below:
    // public UserDTO() {
    // }
    // public UserDTO(String userId, String username, String email, String photo, String password, Integer roleId) {
    //     this.userId = userId;
    //     this.username = username;
    //     this.email = email;
    //     this.photo = photo;
    //     this.password = password;
    //     this.roleId = (roleId != null) ? roleId : 2;
    // }
    // public String getUserId() {
    //     return userId;
    // }
    // public void setUserId(String userId) {
    //     this.userId = userId;
    // }
    // public String getUsername() {
    //     return username;
    // }
    // public void setUsername(String username) {
    //     this.username = username;
    // }
    // public String getEmail() {
    //     return email;
    // }
    // public void setEmail(String email) {
    //     this.email = email;
    // }
    // public String getPhoto() {
    //     return photo;
    // }
    // public void setPhoto(String photo) {
    //     this.photo = photo;
    // }
    // public String getPassword() {
    //     return password;
    // }
    // public void setPassword(String password) {
    //     this.password = password;
    // }
    // public Integer getRoleId() {
    //     return roleId;
    // }
    // public void setRoleId(Integer roleId) {
    //     this.roleId = roleId;
    // }
}
