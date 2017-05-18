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

    @Test
    public void loginTest() {
        Map<String, String> params = new HashMap<String,String>();
        params.put("id", "idAdmin");
        params.put("password", "passAdmin");

        Result result = route(
            fakeRequest(POST, "/mainpage")
            .withFormUrlEncodedBody(params)
        );

        assertThat(status(result)).isEqualTo(SEE_OTHER);
        assertThat(redirectLocation(result)).isEqualTo("/mainpage");
    }

    @Test
    public void setSessionTest(User id) {

    }

    @Test
    public void clearSessionTest() {

    }

    @Test
    public void updateTest() {

    }

    @Test
    public void passwordUpdateTest() {

    }

    @Test
    public void setSessionTest() {

    }

    @Test
    public void setSessionTest() {

    }
}
