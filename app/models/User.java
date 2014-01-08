package models;

import java.util.*;
import controllers.*;
import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

@Entity
@Table(name="user")
public class User extends Model{
	
	@Id
	public String refName;
	
	@Required
	public String username;
	
	@Required
	public String password;
	
	@Column
	public Rank rank;
	
	@Column
	public String profileImage;
	
	@Column
	public int postCount;
	
	public static play.db.ebean.Model.Finder<String, User> find = new Finder<String, User>(String.class, User.class);
	
	public User(String refName, String username, String password) {
      this.refName = refName;
      this.username = username;
      this.password = password;
	  this.rank = Rank.basic;
    }
	
	public static List<User> all() {
		return find.all();
	}
	
	public static void create(User user){
		user.save();
	}
}