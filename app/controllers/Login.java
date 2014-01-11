package controllers;

import play.*;
import play.data.*;
import play.mvc.*;
import models.*;
import views.html.*;

public class Login extends Controller {
    static Form<User> loginForm = Form.form(User.class);
	
    public static Result logout() {
		session().clear();
        return redirect(routes.Application.index());
    }
	
	public static Result login() {
		return ok(views.html.login.render(loginForm,""));
	}
	
	public static Result authenticate() {
		Form<User> form = loginForm.bindFromRequest();
		
		if(User.find.byId(form.get().username.toLowerCase()) == null){
		return badRequest(views.html.login.render(form, "Invalid Username or Password!"));
		}
		if(form.hasErrors()) {
			return badRequest(views.html.login.render(form, "Invalid Username or Password"));
		} else {
			if(User.find.byId(form.get().username.toLowerCase()).password().equals(form.get().password())) {
			session().clear();
			session("user", form.get().username.toLowerCase());
				return redirect(routes.Application.index());
			}
			else {
				return badRequest(views.html.login.render(form, "Invalid Username or Password"));
			}
		}
	}
	
}