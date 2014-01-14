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
	public String id;

	@Required
	public String username;
	
	@Required
	public String password;
	
	@Column
	public Rank rank;
	
	@Column
	public String profileImage = "http://i.imgur.com/6x8qlfy.png";
	
	@Column
	public int postCount;
	
	@Column
	public int repCount;
	
	public static play.db.ebean.Model.Finder<String, User> find = new Finder<String, User>(String.class, User.class);
	
	public User(String id, String username, String password) {
      this.id = id;
      this.username = username;
      this.password = password;
	  this.rank = Rank.Guest;
	  
	  profileImage = "http://i.imgur.com/6x8qlfy.png";
	  postCount = 0;
	  repCount = 0;
	  create(this);
    }
	
	public static List<User> all() {
		return find.all();
	}
	
	public static void create(User user){
		user.save();
	}

	public String password() {
		return password;
	}
}