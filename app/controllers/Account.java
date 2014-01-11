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
		ranks.put("admin", Rank.Admin);
		ranks.put("senior", Rank.Senior);
		ranks.put("mod", Rank.Mod);
		ranks.put("user", Rank.User);
		ranks.put("guest", Rank.Guest);
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
			User user = UserUtils.loginCheck();
			if(user.id.equals("guest")){
				return badRequest(views.html.usercp.render(form, UserUtils.loginCheck()));
			}
			user.rank = ranks.get(form.field("rank").value());
			user.profileImage = form.field("profileImage").value();
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
	
		return ok(views.html.usercp.render(userForm, UserUtils.loginCheck()));
	}
}