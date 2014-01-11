package models;

import java.util.*;
import controllers.*;
import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

public class Post{
	public User poster;
	public String message;
	public String title;
	
	public Post(User poster, String message, String title){
		this.poster = poster;
		this.message = message;
		this.title = title;
	}
}