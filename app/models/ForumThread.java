package models;

import java.util.*;
import controllers.*;
import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

@Entity
@Table(name="threads")
public class ForumThread extends Model{
	
	@Id
	public double id;
	
	@Column
	@Required
	public String title;
	
	@Column
	@Required
	public User creator;
	
	@Column
	@Required
	public List<Post> posts;
	
	public static play.db.ebean.Model.Finder<Double, ForumThread> find = new Finder<Double, ForumThread>(Double.class, ForumThread.class);
	
	public ForumThread(User creator, String title, Post initialPost) {
      this.creator = creator;
	  this.title = title;
	  this.posts = new LinkedList<Post>();
	  this.posts.add(initialPost);
	  // System.out.println(creator);
	  // System.out.println(title);
	  // System.out.println(post);
	  System.out.println(creator.username);
	  create(this);
    }
	
	public static void create(ForumThread thread){
		thread.save();
	}
	
	public static List<ForumThread> all(){
		return find.all();
	}
}