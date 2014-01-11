package controllers;

import play.*;
import play.data.*;
import play.mvc.*;
import models.*;
import views.html.*;
import java.util.*;

public class UserUtils extends Controller{
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