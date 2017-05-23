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
import services.*;
import dto.*;
import org.mindrot.jbcrypt.BCrypt;

public class UserController extends Controller {

    public static Result doLogin() {
        Form<LoginForm> form = new Form(LoginForm.class).bindFromRequest();

        User user = null;
        if(!form.hasErrors()) {
            LoginCheck logincheck = new LoginCheck();

            user = logincheck.check(form);

            if(user != null) {
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
        session("id", String.valueOf(user.id));
        session("nickName", user.nickName);
        session("type", String.valueOf(user.type));
    }

    public static Result logout() {
        clearSession();
        Form<LoginForm> form = new Form(LoginForm.class);
        return ok(login.render(form));
    }

    public static void clearSession() {
        session().clear();
    }

    //create

    public static Result create() {
        Form<CreateForm> form = new Form(CreateForm.class).bindFromRequest();

        User user = null;
        if(!form.hasErrors()) {
            CreateCheck createcheck = new CreateCheck();

            user = createcheck.check(form);
            if (user != null) {
                user.save();
                return redirect(routes.UserController.index());
            } else {
                return badRequest(register.render(form));
            }
        } else {
            return badRequest(register.render(form));
        }
    }

    //read

    public static Result login() {
        Form<LoginForm> form = new Form(LoginForm.class);
        return ok(login.render(form));
    }

    public static Result index() {
        return ok(index.render());
    }

    public static Result register() {
        Form<CreateForm> form = new Form(CreateForm.class);
        return ok(register.render(form));
    }

    public static Result edit() {
        User requestUser = User.find.byId(Long.parseLong(session("id")));
        EditForm ef = new EditForm();

        ef.userId = requestUser.userId;
        ef.nickName = requestUser.nickName;

        Form<EditForm> eForm = new Form(EditForm.class).fill(ef);
        Form<EditPasswordForm> epForm = new Form(EditPasswordForm.class);

        return ok(edit.render(eForm, epForm));
    }

    public static Result userIndex() {
        List<User> users = User.find.where().eq("deleteFlag", false).findList();
        return ok(userIndex.render(users));
    }

    //update

    public static Result update() {
        Form<EditPasswordForm> epForm = new Form(EditPasswordForm.class);
        Form<EditForm> eForm = new Form(EditForm.class).bindFromRequest();

        if(!eForm.hasErrors()) {
            User requestUser = User.find.byId(Long.parseLong(session("id")));

            requestUser.userId = eForm.get().userId;
            requestUser.nickName = eForm.get().nickName;

            requestUser.update();
        } else {
            return badRequest(edit.render(eForm, epForm));
        }
        return ok(index.render());
    }

    public static Result passwordUpdate() {
        Form<EditPasswordForm> form = new Form(EditPasswordForm.class).bindFromRequest();
        return ok(index.render());
    }

    //delete

    public static Result delete(Long id) {
        User requestUser = User.find.byId(id);
        requestUser.deleteFlag = true;
        requestUser.update();
        return redirect(routes.UserController.index());
    }
}
