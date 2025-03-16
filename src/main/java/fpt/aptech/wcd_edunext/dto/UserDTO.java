package fpt.aptech.wcd_edunext.dto;

import lombok.*;

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

}
