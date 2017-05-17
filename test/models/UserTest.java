package models;

import models.User;
import org.junit.*;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

public class UserTest {
    @Test
    public void findByEmail() {
        start(fakeApplication(inMemoryDatabase()));
        User user = new User("idAdmin", "nickAdmin", "passAdmin");
        user.save();

        User requestuser = User.find.byId("idAdmin");

        assertThat(requestuser).isEqualTo(user);
    }
}
