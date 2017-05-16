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
public class ApplicationTest {


    /**
     *ログインページの画面表示
     *
     */
    @Test
    public void loginCheck() {
        Content html = views.html.loginpage.render(new Form(Application.User.class));
        assertThat(contentType(html).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("LOGIN");
        assertThat(contentAsString(html)).contains("PASS");
        assertThat(contentAsString(html)).contains("ID");
    }

    /**
     *
     */
    @Test
    public void loginSucces() {
        FakeApplication app;

        Map<String, String> map = new HashMap<String, String>();
        map.put("id", "admin");
        map.put("pass", "admin");




    }


        /**
         *ログインページの画面表示
         *
         */
        @Test
        public void loginCheck() {
            Content html = views.html.loginpage.render(new Form(Application.User.class));
            assertThat(contentType(html).isEqualTo("text/html");
            assertThat(contentAsString(html)).contains("LOGIN");
            assertThat(contentAsString(html)).contains("PASS");
            assertThat(contentAsString(html)).contains("ID");
        }




}
