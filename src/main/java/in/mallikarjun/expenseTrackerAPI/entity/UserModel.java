package in.mallikarjun.expenseTrackerAPI.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserModel {

    @NotBlank(message = "Name Should not be Empty")
    private String name;

    @NotNull(message = "Email should not be empty")
    @Email(message = "Enter Valid email")
    private String email;

    @NotNull(message = "Password should not be empty")
    @Size(min = 5,message = "Password should be at leat 5 characters")
    private String password;

    private Long age= 0L;

}
