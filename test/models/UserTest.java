package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.avaje.ebean.*;

import models.User;
import org.junit.*;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;
import org.mindrot.jbcrypt.BCrypt;
import apps.FakeApp;

public class UserTest extends FakeApp {

    /**
     *
     * Model Userのテスト
     * データベースに対して登録を行い、
     * データベースに正しく値が入っているかを確かめる
     */
    @Test
    public void setUserTest() {
        User user = new User();
        user.userId = "admin";
        user.nickName = "admin";
        user.password = BCrypt.hashpw("admin", BCrypt.gensalt());
        user.type = false;
        user.save();

        String sql = "SELECT * FROM user WHERE id = :id";

        List<SqlRow> result = Ebean.createSqlQuery(sql).setParameter("id", 1L).findList();
        assertThat(result.get(0).getString("userId")).isEqualTo("admin");
        assertThat(result.get(0).getString("nickName")).isEqualTo("admin");
        assertThat(BCrypt.checkpw("admin", result.get(0).getString("password"))).isEqualTo(true);
    }

    /**
     *
     * Model Userのテスト
     * データベースから値を取得し、
     * 登録した値と比較する
     */
    @Test
    public void getUserTest() {

        String sql = "INSERT INTO user (user_id, nick_name, password, type) VALUES (:user_id, :nick_name, :password, :type)";

        List<SqlRow> sqlRows = Ebean.createSqlQuery(sql)
                                .setParameter("user_id", "Admin")
                                .setParameter("nick_name", "Admin")
                                .setParameter("password", BCrypt.hashpw("admin", BCrypt.gensalt()))
                                .setParameter("type", false)
                                .findList();

        User result = User.find.byId(1L);

        assertThat(result.userId).isEqualTo("admin");
        assertThat(result.nickName).isEqualTo("admin");
        assertThat(BCrypt.checkpw("admin", result.password)).isEqualTo(true);
    }
}
