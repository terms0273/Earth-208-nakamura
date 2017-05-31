/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;
import javax.persistence.*;
import org.hibernate.validator.constraints.NotBlank;
import play.data.validation.Constraints.*;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;





/**
 *
 * @author d-nakamura
 */

@Entity
public class User extends Model {
    @Id
    public Long id;

    @NotBlank(message = "入力してください。")
    @Pattern(value = "^[A-Za-z0-9]{2,20}$", message = "2~20文字の英数字で入力してください。")
    @Column(unique = true)
    public String userId;

    @NotBlank(message = "入力してください。")
    public String nickName;

    @NotBlank(message = "入力してください。")
    @Pattern(value = "^[A-Za-z0-9]{2,20}$", message = "2~20文字の英数字で入力してください。")
    public String password;

    public boolean type;

    public boolean deleteFlag;

    public static Finder<Long, User> find = new Finder<Long, User>(Long.class, User.class);
}
