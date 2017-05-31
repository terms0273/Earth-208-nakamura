package views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.*;

import play.mvc.*;
import play.test.*;
import play.data.DynamicForm;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.i18n.Lang;
import play.libs.F;
import play.libs.F.*;

import play.data.*;
import static play.data.Form.*;
import models.User;
import views.html.*;
import dto.*;
import apps.FakeApp;

import org.specs2.mock.Mockito;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;

import play.filters.csrf.CSRF;
import play.filters.csrf.CSRFConf$;

/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ViewsTest extends FakeApp {




    /**
     * ログイン画面の表示テスト
     *
     */
    @Test
    public void loginTest() {

        // Map<String, String> body = new HashMap<String, String>();
        // String csrf_token = tokenProvider.generateToken();
        // body.put(CSRF.TokenName(), csrf_token);
        // Result result = callAction(controllers.routes.ref.Application.save() ,  fakeRequest().withFormUrlEncodedBody(body).withSession(CSRF.TokenName(), csrf_token));


        Form<LoginForm> form = new Form(LoginForm.class);

        Content html = login.render(form);
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("LOGIN");
        assertThat(contentAsString(html)).contains("PASS");
        assertThat(contentAsString(html)).contains("ID");
    }

    /**
     * ユーザー登録画面の表示テスト
     *
     */
    @Test
    public void registerTest() {
        Content html = register.render(new Form(CreateForm.class));
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("CREATE");
        assertThat(contentAsString(html)).contains("PASS");
        assertThat(contentAsString(html)).contains("ID");
    }

    /**
     * ユーザー編集画面の表示テスト
     *
     */
    @Test
    public void editTest() {
        Content html = edit.render(new Form(EditForm.class), new Form(EditPasswordForm.class));
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("EDIT");
        assertThat(contentAsString(html)).contains("PASS");
        assertThat(contentAsString(html)).contains("ID");
    }

    /**
     * ユーザー一覧画面の表示テスト
     *
     */
    @Test
    public void userIndexTest() {
        List<User> users = User.find.where().eq("deleteFlag", false).findList();
        Content html = userIndex.render(users);
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("INDEX");
        assertThat(contentAsString(html)).contains("DELETE");
        assertThat(contentAsString(html)).contains("ID");
    }

    /**
     * メイン画面の表示テスト
     *
     */
    @Test
    public void indexTest() {
        Content html = index.render("admin");
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("Current weather in Tokyo");
    }

}
