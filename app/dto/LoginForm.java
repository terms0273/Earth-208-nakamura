package dto;

import org.hibernate.validator.constraints.NotBlank;

public class LoginForm {
    @NotBlank(message = "入力してください。")
    public String userId;
    @NotBlank(message = "入力してください。")
    public String password;
}
