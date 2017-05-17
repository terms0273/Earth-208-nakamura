package models;

import models.User;
import org.junit.*;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

public class UserTest {

    /**
     *
     * Model Userのテスト
     * データベースに対して登録、取得を行い
     * 登録したものと、取得したものを比較する。
     */
    @Test
    public void findByUser() {
        start(fakeApplication(inMemoryDatabase()));
        User user = new User("idAdmin", "nickAdmin", "passAdmin");
        user.save();

        User requestuser = User.find.byId("idAdmin");

        assertThat(requestuser).isEqualTo(user);
    }
}
