package controllers;

import play.*;
import play.data.*;
import play.mvc.*;
import models.*;
import views.html.*;

public class Register extends Controller {
    static Form<User> registerForm = Form.form(User.class);
	
	public static Result register() {
		Form<User> form = registerForm.bindFromRequest();
		form.get().id = form.get().username.toLowerCase();
		form.get().rank = Rank.Guest;
		if(User.find.byId(form.get().id) != null){
		return badRequest(views.html.register.render(form, "That Username is already in use!"));
		}
		if(form.hasErrors()) {
			return badRequest(views.html.register.render(form, "Invalid Username or Password"));
		} else {
			User user = new User(form.get().username.toLowerCase(), form.get().username, form.get().password());
			return redirect(routes.Application.index());
		}
	}
	
	public static Result displayRegister() {
		return ok(views.html.register.render(registerForm,""));
	}
}