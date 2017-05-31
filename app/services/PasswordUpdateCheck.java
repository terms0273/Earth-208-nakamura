package services;

import models.User;
import dto.*;
import play.data.*;
import static play.data.Form.*;
import play.mvc.*;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordUpdateCheck {
    public User check(Form<EditPasswordForm> form, Long id) {
        User user = null;

        user = User.find.byId(id);

        if(!BCrypt.checkpw(form.get().password, user.password)) {
            return null;
        }

        if(!form.get().newPassword.equals(form.get().newRePassword)) {
            return null;
        }

        user.password = BCrypt.hashpw(form.get().newPassword, BCrypt.gensalt());

        return user;
    }
}
