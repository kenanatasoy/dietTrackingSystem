package api.login;

import lombok.Data;

@Data
public class UserDto {
    private Integer id;
    private String emailAddress;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
}
