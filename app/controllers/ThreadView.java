package controllers;

import play.*;
import play.data.*;
import play.mvc.*;
import models.*;
import views.html.*;
import java.util.*;

public class ThreadView extends Controller {
	static Form<ForumThread> newThread = Form.form(ForumThread.class);
	static Form<Post> newPost = Form.form(Post.class);
	
	public static Result showThread(long id){

		return ok(views.html.threadview.render(ForumThread.find.byId(id), newPost));
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
	
	public static Result addPost(long id) {
		Form<Post> form = newPost.bindFromRequest();
		
		if(UserUtils.loginCheck() == null || User.find.byId(session().get("user")).id.equals("guest")){	
		return redirect(routes.ThreadView.showThread(id));
		}
		
		if(ForumThread.find.byId(id).isLocked) {
			return redirect(routes.ThreadView.showThread(id));
		}

		if(form.hasErrors()) {
			return redirect(routes.ThreadView.showThread(id));
		} else {
			Post post = new Post(UserUtils.loginCheck().id, form.get().message, ForumThread.find.byId(id).title, ForumThread.find.byId(id), id);
			return redirect(routes.ThreadView.showThread(id));  //Lazy, I know, but only temp.
		}
	}
}