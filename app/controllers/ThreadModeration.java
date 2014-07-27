package controllers;

import play.*;
import play.data.*;
import play.mvc.*;
import models.*;
import views.html.*;
import java.util.*;

public class ThreadModeration extends Controller {
	public static Result lockThread(long id){
	
		if(UserUtils.loginCheck().rank.equals(Rank.Admin)){
			ForumThread.toggleLock(id);
		} else{
		}
		
		return redirect("/thread/" + id);
	}
}