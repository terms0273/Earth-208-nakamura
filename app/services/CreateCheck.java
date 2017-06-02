package services;

import models.User;
import dto.*;
import play.data.*;
import static play.data.Form.*;
import play.mvc.*;
import org.mindrot.jbcrypt.BCrypt;

public class CreateCheck {
    public String message = "";
    public User check(Form<CreateForm> form) {
        User user = new User();
        int cnt = User.find.where().eq("userId", form.get().userId).findRowCount();

        if(cnt > 0) {
            message = "そのIDは既に使われています";
            return null;
        }

        if(!form.get().password.equals(form.get().rePassword)) {
            message = "二回目のパスワード入力が間違っています";
            return null;
        }

        user.userId = form.get().userId;
        user.password = BCrypt.hashpw(user.password, BCrypt.gensalt());
        user.nickName = form.get().nickName;
        user.type = form.get().type;

        return user;
    }
}
