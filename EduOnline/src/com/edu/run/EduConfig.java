package com.edu.run;


//import com.demo.blog.Blog;

//import com.demo.blog.BlogController;
import com.edu.index.IndexController;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;

/**
 * API����ʽ����
 */
public class EduConfig extends JFinalConfig {
	
	/**
	 * ���ó���
	 */
	public void configConstant(Constants me) {
		// ����������Ҫ���ã�������PropKit.get(...)��ȡֵ
		PropKit.use("init.properties");
		me.setDevMode(PropKit.getBoolean("devMode", false));
	}
	
	/**
	 * ����·��
	 */
	public void configRoute(Routes me) {

		me.add("/", IndexController.class, "/index");	// ����������Ϊ��Controller����ͼ���·��
	//	me.add("/blog", BlogController.class);			// ����������ʡ��ʱĬ�����һ������ֵ��ͬ���ڴ˼�Ϊ "/blog"
	}
	
	/**
	 * ���ò��
	 */
	public void configPlugin(Plugins me) {
		// ����C3p0���ݿ����ӳز��
		C3p0Plugin c3p0Plugin = new C3p0Plugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
		me.add(c3p0Plugin);
		
		// ����ActiveRecord���
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		me.add(arp);
		//arp.addMapping("blog", Blog.class);	// ӳ��blog �� Blogģ��
	}
	
	/**
	 * ����ȫ��������
	 */
	public void configInterceptor(Interceptors me) {
		
	}
	
	/**
	 * ���ô�����
	 */
	public void configHandler(Handlers me) {
		
	}
	
	/**
	 * ����ʹ�� JFinal �ֲ��Ƽ��ķ�ʽ������Ŀ
	 * ���д� main ��������������Ŀ����main�������Է����������Class�ඨ���У���һ��Ҫ���ڴ�
	 */
	public static void main(String[] args) {
		JFinal.start("WebRoot", 80, "/", 5);
	}
}
