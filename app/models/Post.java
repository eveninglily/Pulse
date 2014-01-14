package models;

import java.util.*;
import controllers.*;
import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

@Entity
@Table(name="posts")
public class Post extends Model{

	@Column
	public String posterID;
	
	@Column
	public String message;
	
	@Column
	public String title;
	
	@Column
	public long id;
	
	@ManyToOne
	public ForumThread forumThread;
	
	public static Model.Finder<Long,Post> find = new Model.Finder(Long.class, Post.class);
	
	public Post(String posterID, String message, String title, ForumThread thread, long id){
		this.posterID = UserUtils.loginCheck().id;
		this.message = message;
		this.title = title;
		this.forumThread = thread;
		this.id = id;
		this.save();
		System.out.println(id + ": On Post Creation");
		//this.saveManyToManyAssociations("forumThread");
	}
	
	public static List<Post> getPosts(long thread){
		System.out.println(thread + ": On search");
        return find.where()
            .eq("id", thread)
            .findList();
	}
}