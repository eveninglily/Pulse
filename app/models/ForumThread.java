package models;

import java.util.*;
import controllers.*;
import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;
import javax.persistence.ElementCollection;

@Entity
@Table(name="threads")
public class ForumThread extends Model{
	
	@Id
	public long id;
	
	@Column
	@Required
	public String title;
	
	@Column
	public User creator;
	
	@Column
	public boolean isLocked;
	
	public String initialMessage;
	
	public static play.db.ebean.Model.Finder<Long, ForumThread> find = new Finder<Long, ForumThread>(Long.class, ForumThread.class);
	
	public ForumThread(User creator, String title, String initialMessage) {
		this.creator = creator;
		this.title = title;
		isLocked = false;
		create(this);
		Post post = new Post(creator.id, initialMessage, title, this, this.id);
    }
	
	public static void create(ForumThread thread){
		thread.save();
	}
	
	public static List<ForumThread> all(){
		return find.all();
	}
	
	public static void toggleLock(long id) {
		ForumThread thread = ForumThread.find.byId(id);
		if(thread.isLocked ) {
			thread.isLocked = false;
			System.out.println("unlock");
		}
		else {
			thread.isLocked = true;
			System.out.println("lock");
		}
		thread.save();
	}
}