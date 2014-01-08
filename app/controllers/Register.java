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
		form.get().refName = form.get().username.toLowerCase();
		form.get().rank = Rank.mod;
		if(User.find.byId(form.get().refName) != null){
		return badRequest(views.html.register.render(form, "That Username is already in use!"));
		}
		if(form.hasErrors()) {
			return badRequest(views.html.register.render(form, "Invalid Username or Password"));
		} else {
			User.create(form.get());
			return redirect(routes.Application.index());
		}
	}
	
	public static Result displayRegister() {
		return ok(views.html.register.render(registerForm,""));
	}
}