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
                return badRequest(login.render("ユーザーID・パスワードに誤りがあるか、登録されていません", form));
            }
        } else {
            return badRequest(login.render("", form));
        }
    }

    private static void setSession(User user) {
        session("id", user.id.toString());
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

        User user = User.find.byId(Long.parseLong(session("id")));
        String nickName = user.nickName;

        if(!form.hasErrors()) {
            CreateCheck createcheck = new CreateCheck();

            user = createcheck.check(form);
            if (user != null) {
                user.save();
                return redirect(routes.UserController.index());
            } else {
                return badRequest(register.render(createcheck.message, nickName, form));
            }
        } else {
            return badRequest(register.render("", nickName, form));
        }
    }

    //read

    public static Result login() {
        Form<LoginForm> form = new Form(LoginForm.class);
        return ok(login.render("", form));
    }

    @Security.Authenticated(LoginFilter.class)
    public static Result index() {
        User user = User.find.byId(Long.parseLong(session("id")));
        return ok(index.render(user.nickName));
    }

    @Security.Authenticated(AdminFilter.class)
    public static Result register() {
        User user = User.find.byId(Long.parseLong(session("id")));

        Form<CreateForm> form = new Form(CreateForm.class);

        return ok(register.render("", user.nickName, form));
    }

    @Security.Authenticated(LoginFilter.class)
    public static Result edit() {
        User user = User.find.byId(Long.parseLong(session("id")));
        EditForm ef = new EditForm();

        ef.userId = user.userId;
        ef.nickName = user.nickName;

        Form<EditForm> eForm = new Form(EditForm.class).fill(ef);
        Form<EditPasswordForm> epForm = new Form(EditPasswordForm.class);

        return ok(edit.render("", user.nickName, eForm, epForm));
    }

    @Security.Authenticated(AdminFilter.class)
    public static Result userIndex() {
        User user = User.find.byId(Long.parseLong(session("id")));

        List<User> users = User.find.where().eq("deleteFlag", false).ne("id", Long.parseLong(session("id"))).findList();

        return ok(userIndex.render(user.nickName, users));
    }

    @Security.Authenticated(LoginFilter.class)
    public static Result weather() {
        User user = User.find.byId(Long.parseLong(session("id")));
        return ok(weather.render(user.nickName));
    }

    //update

    @Security.Authenticated(LoginFilter.class)
    public static Result update() {
        Form<EditForm> eForm = new Form(EditForm.class).bindFromRequest();
        Form<EditPasswordForm> epForm = new Form(EditPasswordForm.class);

        User user = User.find.byId(Long.parseLong(session("id")));
        String nickName = user.nickName;

        if(!eForm.hasErrors()) {
            UpdateCheck uc = new UpdateCheck();
            user = uc.check(eForm, Long.parseLong(session("id")));

            if(user != null) {
                user.update();
                return redirect(routes.UserController.index());
            }
            return badRequest(edit.render("そのIDは既に使われています", nickName, eForm, epForm));
        } else {
            return badRequest(edit.render("", nickName, eForm, epForm));
        }
    }

    @Security.Authenticated(LoginFilter.class)
    public static Result passwordUpdate() {
        Form<EditForm> eForm = new Form(EditForm.class);
        Form<EditPasswordForm> epForm = new Form(EditPasswordForm.class).bindFromRequest();

        User user = User.find.byId(Long.parseLong(session("id")));
        String nickName = user.nickName;

        if(!epForm.hasErrors()) {
            PasswordUpdateCheck puc = new PasswordUpdateCheck();
            user = puc.check(epForm, Long.parseLong(session("id")));

            if(user != null) {
                user.update();
                return redirect(routes.UserController.index());
            }
            return badRequest(edit.render(puc.getMessage(), nickName, eForm, epForm));
        } else {
            return badRequest(edit.render("", nickName, eForm, epForm));
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
