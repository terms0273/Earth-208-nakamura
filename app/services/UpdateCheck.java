package services;

import models.User;
import dto.*;
import play.data.*;
import static play.data.Form.*;
import play.mvc.*;
import org.mindrot.jbcrypt.BCrypt;

public class UpdateCheck {
    public User check(Form<EditForm> form, Long id) {
        User user = null;

        user = User.find.byId(id);

        int rowCnt = User.find.where().eq("userId", form.get().userId).findRowCount();

        if(!form.get().userId.equals(user.userId) && rowCnt > 0) {
            return null;
        }

        user.userId = form.get().userId;
        user.nickName = form.get().nickName;

        return user;
    }
}
