package filters;

import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;

public class LoginFilter extends Security.Authenticator {

    @Override
    public String getUsername(Context ctx) {
        return ctx.session().get("id");
    }

    @Override
    public Result onUnauthorized(Context ctx) {
        String returnUrl = ctx.request().uri();
        if(returnUrl == null) {
            returnUrl = "/";
        }
        ctx.session().put("returnUrl", returnUrl);
        return redirect(controllers.routes.UserController.login());
    }
}
