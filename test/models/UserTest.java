package models;

import models.User;
import org.junit.*;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

public class UserTest extends FakeApp{

    /**
     *
     * Model Userのテスト
     * データベースに対して登録を行い、
     * データベースに正しく値が入っているかを確かめる
     */
    @Test
    public void setUserTest() {
        User user = new User("idAdmin", "nickAdmin", "passAdmin");
        user.save();

        String sql = " SELECT * FROM user WHERE id = :id";

        List<SqlRow> sqlRows = Ebean.createSqlQuery(sql).setParameter("id", "idAdmin").findList();
        assertThat(result.get(0).getString("nickname")).isEqualTo("nickAdmin");
        assertThat(result.get(0).getString("password")).isEqualTo("passAdmin");
    }

    /**
     *
     * Model Userのテスト
     * データベースから値を取得し、
     * 登録した値と比較する
     */
    @Test
    public void getUserTest() {
        User user = new User("idAdmin", "nickAdmin", "passAdmin");
        user.save();

        User requestUser = User.find.byId("idAdmin");

        assertThat(requestUser.nickname).isEqualTo("nickAdmin");
        assertThat(requestUser.password).isEqualTo("passAdmin");
    }
}
