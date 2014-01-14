package controllers;

import play.*;
import play.data.*;
import play.mvc.*;
import models.*;
import views.html.*;
import java.util.*;

public class ThreadView extends Controller {
	static Form<ForumThread> newThread = Form.form(ForumThread.class);

	public static Result showThread(long id){

		return ok(views.html.threadview.render(ForumThread.find.byId(1L)));
	}
	
	public static Result createThread(){
		Form<ForumThread> form = newThread.bindFromRequest();
		
		if(User.find.byId(session().get("user")) == null || User.find.byId(session().get("user")).id.equals("guest")){
		return badRequest(views.html.newthread.render(form, "You must be logged in to post!"));
		}

		if(form.hasErrors()) {
			return badRequest(views.html.newthread.render(form, "errors"));
		} else {
			ForumThread.create(new ForumThread(User.find.byId(session().get("user")), form.get().title, form.get().initialMessage));
			return redirect(routes.ThreadView.showThread(1));  //Lazy, I know, but only temp.
		}
	}
	
	public static Result newThread(){
		return ok(views.html.newthread.render(newThread, ""));
	}
	
	public static Result threads() {
		return ok(views.html.threads.render(ForumThread.all()));
	}
}