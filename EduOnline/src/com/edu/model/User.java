package com.edu.model;

import com.jfinal.plugin.activerecord.Model;

public class User extends Model<User> {
	public final static User me=new User();
}
