package querydsl.tonels.web.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserDto {
    @NotNull
    @Size(min = 1, message = "error ...")
    private String firstName;

    @NotNull
    @Size(min = 1, message = "error ...")
    private String lastName;

    private String password;

    @NotNull
    @Size(min = 1)
    private String matchingPassword;

    @NotNull
    @Size(min = 1, message = "error ...")
    private String email;

    private boolean isUsing2FA;

}
