package com.edu.index;

import java.util.List;

import com.edu.model.User;
import com.jfinal.core.Controller;

public class UserController extends Controller {

	//��¼
	public void login(){
		User user=getModel(User.class);		
		String username=getPara("user.name");		
		String password=getPara("user.password");
		List<User> l=User.me.find("select * from edu_user where name='"+username+"' and password='"+password+"'");
		
		if(l.size()!=0){
			user=l.get(0);
			setSessionAttr("loginer",user);
			render("success.jsp");
//			view();
		}else{
			render("error.jsp");
		}
	}
	
	//ע��
	public void register(){
		try{
			getModel(User.class).save();
			render("success.jsp");
		}catch(Exception e){
			e.printStackTrace();
		}		
		render("register.jsp");
	}

	//��ѯ�����û�
	public void index(){
		setAttr("allusers","select * from edu_user");
		render("");
	}
	
	//�޸��û���Ϣ
	public void update(){
		User user=getModel(User.class);
		Integer id=user.getInt("id");
		if(id!=null&&id>0){
			user.update();
		}
		index();
	}
	
	//�鿴�û���Ϣ
	public void select(){
		User user=getModel(User.class);
		Integer id=user.getInt("id");
		if(id!=null&id>0)
			setAttr("userinfo",User.me.findById(id));
	}
}
