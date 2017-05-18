package controllers;

import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import models.User;
import play.data.*;
import static play.data.Form.*;
import play.mvc.*;

import views.html.*;

public class ViewPage extends Controller {

    public static Result login() {
        Form<User> form = new Form(User.class);
        return ok(login.render(form));
    }

    public static Result index() {
        Form<User> form = new Form(User.class);
        return ok(index.render(form));
    }

    public static Result register() {
        Form<User> form = new Form(User.class);
        return ok(index.render(form));
    }

    public static Result edit() {
        Form<User> form = new Form(User.class);
        return ok(index.render(form));
    }

    public static Result userIndex() {
        Form<User> form = new Form(User.class);
        return ok(index.render(form));
    }
}
