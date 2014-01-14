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
	
	public String initialMessage;
	
	public static play.db.ebean.Model.Finder<Long, ForumThread> find = new Finder<Long, ForumThread>(Long.class, ForumThread.class);
	
	public ForumThread(User creator, String title, String initialMessage) {
		this.creator = creator;
		this.title = title;
		
		create(this);
		System.out.println(this.id + ": On Thread Creation");
		Post post = new Post(creator.id, initialMessage, title, this, this.id);
    }
	
	public static void create(ForumThread thread){
		thread.save();
	}
	
	public static List<ForumThread> all(){
		return find.all();
	}
}