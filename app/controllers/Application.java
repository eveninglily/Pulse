package controllers;

import play.*;
import play.data.*;
import play.mvc.*;
import models.*;
import views.html.*;

public class Application extends Controller {
	
    public static Result index() {
	//return ok(views.html.welcome.render(User.find.byId(session().get("user"))));
		return ok(views.html.welcome.render(UserUtils.loginCheck()));
	}
}