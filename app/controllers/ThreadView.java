package controllers;

import play.*;
import play.data.*;
import play.mvc.*;
import models.*;
import views.html.*;
import java.util.*;

public class ThreadView extends Controller {
	public static Result showThread(double id){
		System.out.println(UserUtils.loginCheck().username);
		ForumThread t = new ForumThread(UserUtils.loginCheck(),"Hi", new Post(UserUtils.loginCheck(), "Lorem Ipsum", "hi"));
		t.posts.add(new Post(UserUtils.loginCheck(), "Lorem Ipsum", "hi"));
		t.posts.add(new Post(UserUtils.loginCheck(), "Lorem Ipsum", "hi"));
		t.posts.add(new Post(UserUtils.loginCheck(), "Lorem Ipsum", "hi"));
		
		return ok(views.html.threadview.render(t));
	}
	
	public static Result threads() {
		return ok(views.html.threads.render(ForumThread.all()));
	}
}