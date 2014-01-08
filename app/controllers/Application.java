package controllers;

import play.*;
import play.data.*;
import play.mvc.*;
import models.*;
import views.html.*;

public class Application extends Controller {
	
    public static Result index() {
	//return ok(views.html.welcome.render(User.find.byId(session().get("user"))));
		return ok(views.html.welcome.render(loginCheck()));
	}
	
	public static User loginCheck() {		
		if(session().get("user") != null){
			if(User.find.byId(session().get("user")) != null){
				return User.find.byId(session().get("user"));
			} else {
			createGuest();
			return User.find.byId("guest");
			}
		} else {
			createGuest();
			return User.find.byId("guest");
		}
		
	}
	
	public static void createGuest() {
		try{
			if(User.find.byId("guest") == null)
				User.create(new User("guest", "Guest", ""));
		}catch(Exception e){}
	}
}
