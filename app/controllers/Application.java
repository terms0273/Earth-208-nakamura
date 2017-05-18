package controllers;

import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import models.User;
import play.data.*;
import static play.data.Form.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result login() {
        Form<User> form = new Form(User.class);
        return ok(loginpage.render(form));
    }

    public static Result index() {
        Form<User> form = new Form(User.class);
        return ok(index.render(form));
    }

    public static Result doLogin() {
        Form<User> form = new Form(User.class).bindFromRequest();
        if(!form.hasErrors()) {
            return redirect(routes.Application.index());
        } else {
            return redirect(routes.Application.loginpage());
        }
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

    public static Result setSession() {
        Form<User> form = new Form(User.class);
        return ok(index.render(form));
    }

    public static Result clearSession() {
        Form<User> form = new Form(User.class);
        return ok(index.render(form));
    }

    public static Result update() {
        Form<User> form = new Form(User.class);
        return ok(index.render(form));
    }

    public static Result passwordUpdate() {
        Form<User> form = new Form(User.class);
        return ok(index.render(form));
    }

    public static Result create() {
        Form<User> form = new Form(User.class);
        return ok(index.render(form));
    }
}
