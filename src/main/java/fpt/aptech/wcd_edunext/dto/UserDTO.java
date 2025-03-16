package fpt.aptech.wcd_edunext.dto;

import lombok.*;

@Getter
@Setter
public class UserDTO {

    private String UserId;
    private String Username;
    private String Email;
    private String Photo;
    private String Password;
    private Integer RoleId;

    public UserDTO() {
    }

    // Constructor đầy đủ thông tin
    public UserDTO(String UserId, String Username, String Email, String Photo, String Password, Integer RoleId) {
        this.UserId = UserId;
        this.Username = Username;
        this.Email = Email;
        this.Photo = Photo;
        this.Password = Password;
        this.RoleId = (RoleId != null) ? RoleId : 2;
    }
}
