/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;
import javax.persistence.*;
import org.hibernate.validator.constraints.NotBlank;
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

    @NotBlank
    @Column(unique = true)
    public String userid;

    @NotBlank
    public String nickname;

    @NotBlank
    public String password;

    @Column(columnDefinition ="default 'false'")
    public boolean deleteflag;

    public static Finder<Long, User> find = new Finder<Long, User>(Long.class, User.class);
}
