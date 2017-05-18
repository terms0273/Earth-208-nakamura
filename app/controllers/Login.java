package controllers;

import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import models.User;
import play.data.*;
import static play.data.Form.*;
import play.mvc.*;

import views.html.*;

public class Login extends Controller {

    public static Result doLogin() {
        Form<User> form = new Form(User.class).bindFromRequest();
        if(!form.hasErrors()) {
            User user = form.get();
            user.save();
            return redirect(routes.ViewPage.index());
        } else {
            return redirect(routes.ViewPage.login());
        }
    }

    public static Result setSession() {
        Form<User> form = new Form(User.class);
        return ok(index.render(form));
    }
}
