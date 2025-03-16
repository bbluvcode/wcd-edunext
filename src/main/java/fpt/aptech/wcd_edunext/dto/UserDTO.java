/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fpt.aptech.wcd_edunext.dto;

import lombok.*;

/**
 *
 * @author Admin
 */
@Getter
@Setter
public class UserDTO {

    public String UserId;
    public String Username;
    public String Email;
    public String Photo;

    public UserDTO() {
    }

    public UserDTO(String UserId, String Username, String Email, String Photo) {
        this.UserId = UserId;
        this.Username = Username;
        this.Email = Email;
        this.Photo = Photo;
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
