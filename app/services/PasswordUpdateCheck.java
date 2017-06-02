package services;

import models.User;
import dto.*;
import play.data.*;
import static play.data.Form.*;
import play.mvc.*;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordUpdateCheck {
    private String message = "";

    public String getMessage() {
        return message;
    }

    public User check(Form<EditPasswordForm> form, Long id) {
        User user = null;

        user = User.find.byId(id);

        if(!BCrypt.checkpw(form.get().password, user.password)) {
            message = "[OLD PASS]の入力が間違っています";
            return null;
        }

        if(!form.get().newPassword.equals(form.get().newRePassword)) {
            message = "二回目の[NEW PASS]の入力が間違っています";
            return null;
        }

        user.password = BCrypt.hashpw(form.get().newPassword, BCrypt.gensalt());

        return user;
    }
}
