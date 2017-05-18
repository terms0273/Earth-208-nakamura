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

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class viewsTest {


    /**
     * ログイン画面の表示テスト
     *
     */
    @Test
    public void loginpageCheck() {
        Content html = views.html.loginpage.render(new Form(Application.User.class));
        assertThat(contentType(html).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("LOGIN");
        assertThat(contentAsString(html)).contains("PASS");
        assertThat(contentAsString(html)).contains("ID");
    }

    /**
     * ユーザー登録画面の表示テスト
     *
     */
    public void singuppageCheck() {
        Content html = views.html.singuppage.render(new Form(Application.User.class));
        assertThat(contentType(html).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("CREATE");
        assertThat(contentAsString(html)).contains("PASS");
        assertThat(contentAsString(html)).contains("ID");
    }

    /**
     * ユーザー編集画面の表示テスト
     *
     */
    public void editpageCheck() {
        Content html = views.html.editpage.render(new Form(Application.User.class));
        assertThat(contentType(html).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("EDIT");
        assertThat(contentAsString(html)).contains("PASS");
        assertThat(contentAsString(html)).contains("ID");
    }

    /**
     * ユーザー一覧画面の表示テスト
     *
     */
    public void indexpageCheck() {
        Content html = views.html.indexpage.render(new Form(Application.User.class));
        assertThat(contentType(html).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("INDEX");
        assertThat(contentAsString(html)).contains("DELETE");
        assertThat(contentAsString(html)).contains("PASS");
        assertThat(contentAsString(html)).contains("ID");
    }

    /**
     * メイン画面の表示テスト
     *
     */
    public void meinpageCheck() {
        Content html = views.html.meinpage.render(new Form(Application.User.class));
        assertThat(contentType(html).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("Current weather in Tokyo");
    }

}
