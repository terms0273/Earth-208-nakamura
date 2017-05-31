package services;

import models.User;
import dto.*;
import play.data.*;
import static play.data.Form.*;
import play.mvc.*;
import org.mindrot.jbcrypt.BCrypt;

public class CreateCheck {
    public User check(Form<CreateForm> form) {
        User user = new User();
        int cnt = User.find.where().eq("userId", form.get().userId).findRowCount();

        if(cnt > 0) {
            return null;
        }

        if(!form.get().password.equals(form.get().rePassword)) {
            return null;
        }

        user.userId = form.get().userId;
        user.password = BCrypt.hashpw(user.password, BCrypt.gensalt());
        user.nickName = form.get().nickName;
        user.type = form.get().type;

        return user;
    }
}
