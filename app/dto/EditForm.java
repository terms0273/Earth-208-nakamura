package dto;

import javax.persistence.*;
import org.hibernate.validator.constraints.NotBlank;
import play.data.validation.Constraints.*;

public class EditForm {
    @NotBlank(message = "入力してください。")
    @Pattern(value = "^[A-Za-z0-9]{2,20}$", message = "2~20文字の英数字で入力してください。")
    @Column(unique = true)
    public String userId;

    @NotBlank(message = "入力してください。")
    public String nickName;
}
