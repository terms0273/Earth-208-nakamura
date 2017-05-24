package filters;

import models.User;
import play.data.*;
import static play.data.Form.*;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;

public class AdminFilter extends Security.Authenticator {

    @Override
    public String getUsername(Context ctx) {
        if(ctx.session().get("id") == null) {
            return null;
        }

        User user = User.find.byId(Long.parseLong(ctx.session().get("id")));

        return user.type == false ? "0" : null;
    }

    @Override
    public Result onUnauthorized(Context ctx) {
        String returnUrl = ctx.request().uri();
        if(returnUrl == null) {
            returnUrl = "/";
        }
        ctx.session().put("returnUrl", returnUrl);
        return redirect(controllers.routes.UserController.index());
    }
}
