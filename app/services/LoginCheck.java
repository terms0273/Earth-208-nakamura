package services;

import models.User;
import dto.*;
import play.data.*;
import static play.data.Form.*;
import play.mvc.*;
import org.mindrot.jbcrypt.BCrypt;

public class LoginCheck {
    public User check(Form<LoginForm> form) {
        User user = null;
        user = User.find.where().eq("userId", form.get().userId).findUnique();

        if(user == null) {
            return null;
        }

        if(!BCrypt.checkpw(form.get().password, user.password)) {
            return null;
        }

        return user;
    }
}
