package controllers;

import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import models.User;
import play.data.*;
import static play.data.Form.*;
import play.mvc.*;

import views.html.*;

public class Logout extends Controller {

    public static Result logout() {
        Form<User> form = new Form(User.class);
        return ok(index.render(form));
    }

    public static Result clearSession() {
        Form<User> form = new Form(User.class);
        return ok(index.render(form));
    }
}
