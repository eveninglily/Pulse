package controllers;

import play.*;
import play.data.*;
import play.mvc.*;
import models.*;
import views.html.*;
import java.util.*;

public class Account extends Controller {
	
	public static HashMap<String, Rank> ranks = new HashMap<String, Rank>();
	
	public static Result showSelf() {
		return TODO;
	}
	
	public static Result showUser(String user) {
		return TODO;
	}
	
	public static void setupRanks() {
		ranks.put("admin", Rank.admin);
		ranks.put("senior", Rank.senior);
		ranks.put("mod", Rank.mod);
		ranks.put("user", Rank.user);
	}
	
	static Form<UserInfo> userForm = Form.form(UserInfo.class);
	
	public static Result updateInfo() {
		Form<UserInfo> form = userForm.bindFromRequest();
	
		if(ranks.keySet().size() == 0){
			setupRanks();
		}
		
		if(form.hasErrors()) {
			return badRequest(views.html.usercp.render(form, User.find.byId(session().get("user"))));
		}
		else {
			
			User user = User.find.byId(session().get("user"));
			user.rank = ranks.get(form.field("rank").value());
			user.save();
			return redirect(routes.Application.index());
		}		
	}
	
	public static Result allMembers() {
		return ok(views.html.members.render(User.all()));
	}
	
	public static Result displayControlPanel() {
		if(session().get("user") != null){
		if(User.find.byId(session().get("user")) != null){
			return ok(views.html.usercp.render(userForm,User.find.byId(session().get("user"))));
		}
	}
	
	//Todo: Replace w/ static method
	try{if(User.find.byId("guest") == null)User.create(new User("guest", "Guest", ""));} catch(Exception e){}
		return ok(views.html.usercp.render(userForm, User.find.byId("guest")));
	}
}