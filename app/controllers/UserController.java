package controllers;

import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import models.User;
import javax.persistence.*;
import org.hibernate.validator.constraints.NotBlank;
import play.data.*;
import static play.data.Form.*;
import play.mvc.*;

import views.html.*;
import util.BCrypt;

public class UserController extends Controller {

    public static Result doLogin() {
        Form<LoginForm> form = new Form(LoginForm.class).bindFromRequest();

        User user = null;
        if(!form.hasErrors()) {
            String uId = form.get().userId;
            String pass = form.get().password;
            user = User.find.where().eq("userId", uId).eq("password", pass).findUnique();

            if(!(user == null)) {
                setSession(user);
                return redirect(routes.UserController.index());
            } else {
                return redirect(routes.UserController.login());
            }
        } else {
            return badRequest(login.render(form));
        }
    }

    public static void setSession(User user) {
        session("id",String.valueOf(user.id));

        System.out.println(session("id"));
    }

    public static Result logout() {
        Form<LoginForm> form = new Form(LoginForm.class);
        return ok(login.render(form));
    }

    public static void clearSession() {
        session().clear();
    }

    //create

    public static Result create() {
        Form<User> form = new Form(User.class).bindFromRequest();
        if(!form.hasErrors()) {
            User user = form.get();
            user.password = BCrypt.hashpw(user.password, BCrypt.gensalt());
            user.save();
            return redirect(routes.UserController.index());
        } else {
            return badRequest(register.render(form));
        }
    }

    //read

    public static class LoginForm {
        @NotBlank(message = "入力してください。")
        public String userId;
        @NotBlank(message = "入力してください。")
        public String password;
    }

    public static Result login() {
        Form<LoginForm> form = new Form(LoginForm.class);
        return ok(login.render(form));
    }

    public static Result index() {
        Form<User> form = new Form(User.class);
        return ok(index.render(form));
    }

    public static Result register() {
        Form<User> form = new Form(User.class);
        return ok(register.render(form));
    }

    public static Result edit() {
        Form<User> form = new Form(User.class);
        return ok(edit.render(form));
    }

    public static Result userIndex() {
        Form<User> form = new Form(User.class);
        return ok(userIndex.render(form));
    }

    //update

    public static Result update() {
        Form<User> form = new Form(User.class);
        return ok(index.render(form));
    }

    public static Result passwordUpdate() {
        Form<User> form = new Form(User.class);
        return ok(index.render(form));
    }

    //delete

    public static Result delete() {
        Form<User> form = new Form(User.class);
        return ok(index.render(form));
    }
}
