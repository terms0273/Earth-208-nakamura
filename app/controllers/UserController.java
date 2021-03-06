package controllers;

import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.persistence.*;
import org.hibernate.validator.constraints.NotBlank;
import play.data.*;
import static play.data.Form.*;
import play.mvc.*;

import models.User;
import views.html.*;
import services.*;
import filters.*;
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
                return badRequest(login.render(form));
            }
        } else {
            return badRequest(login.render(form));
        }
    }

    private static void setSession(User user) {
        session("id", user.id.toString());
        session("nickName", user.nickName);
        session("type", String.valueOf(user.type));
    }

    @Security.Authenticated(LoginFilter.class)
    public static Result logout() {
        clearSession();
        Form<LoginForm> form = new Form(LoginForm.class);
        return redirect(routes.UserController.login());
    }

    private static void clearSession() {
        session().clear();
    }

    //create

    @Security.Authenticated(AdminFilter.class)
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

    @Security.Authenticated(LoginFilter.class)
    public static Result index() {
        return ok(index.render(session("nickName")));
    }

    @Security.Authenticated(AdminFilter.class)
    public static Result register() {
        Form<CreateForm> form = new Form(CreateForm.class);
        return ok(register.render(form));
    }

    @Security.Authenticated(LoginFilter.class)
    public static Result edit() {
        User user = User.find.byId(Long.parseLong(session("id")));
        EditForm ef = new EditForm();

        ef.userId = user.userId;
        ef.nickName = user.nickName;

        Form<EditForm> eForm = new Form(EditForm.class).fill(ef);
        Form<EditPasswordForm> epForm = new Form(EditPasswordForm.class);

        return ok(edit.render(eForm, epForm));
    }

    @Security.Authenticated(AdminFilter.class)
    public static Result userIndex() {
        List<User> users = User.find.where().eq("deleteFlag", false).findList();
        return ok(userIndex.render(users));
    }

    //update

    @Security.Authenticated(LoginFilter.class)
    public static Result update() {
        Form<EditForm> eForm = new Form(EditForm.class).bindFromRequest();
        Form<EditPasswordForm> epForm = new Form(EditPasswordForm.class);

        if(!eForm.hasErrors()) {
            UpdateCheck uc = new UpdateCheck();
            User user = uc.check(eForm, Long.parseLong(session("id")));

            if(user != null) {
                setSession(user);
                user.update();
                return redirect(routes.UserController.index());
            }
            return badRequest(edit.render(eForm, epForm));
        } else {
            return badRequest(edit.render(eForm, epForm));
        }
    }

    @Security.Authenticated(LoginFilter.class)
    public static Result passwordUpdate() {
        Form<EditForm> eForm = new Form(EditForm.class);
        Form<EditPasswordForm> epForm = new Form(EditPasswordForm.class).bindFromRequest();

        if(!epForm.hasErrors()) {
            PasswordUpdateCheck puc = new PasswordUpdateCheck();
            User user = puc.check(epForm, Long.parseLong(session("id")));

            if(user != null) {
                user.update();
                return redirect(routes.UserController.index());
            }
            return badRequest(edit.render(eForm, epForm));
        } else {
            return badRequest(edit.render(eForm, epForm));
        }
    }

    //delete

    @Security.Authenticated(AdminFilter.class)
    public static Result delete(Long id) {
        User user = User.find.byId(id);
        user.deleteFlag = true;
        user.update();
        return redirect(routes.UserController.index());
    }
}
