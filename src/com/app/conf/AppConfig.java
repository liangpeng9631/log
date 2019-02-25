package com.app.conf;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.fw.little.base.util.LoggerThreadCache;

import com.app.conf.info.Conf;
import com.app.interceptor.GlobalActionInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.mongodb.MongoClient;
import com.web.util.RouterTools;

public class AppConfig extends JFinalConfig 
{

	private static volatile AppConfig appConfig = null;
	
	/**
	 * json工具类
	 * **/
	private ObjectMapper jsonMapper = new ObjectMapper();
	
	
	/**
	 * 其他扩展配置
	 * **/
	private Map<String,String> extendsConfig = null;
	
	/**
	 * 日志线程缓存
	 */
	private LoggerThreadCache loggerThreadCache = new LoggerThreadCache();
	public LoggerThreadCache getLoggerThreadCache() {
		return loggerThreadCache;
	}
	
	/**
	 * 线程池
	 * **/
	private ExecutorService fixedThreadPool;
	
	public AppConfig(){appConfig = this;}
	

	/**
	 * mongodb
	 */
	private MongoClient client;
	
	
	
	public MongoClient getClient() {
		return client;
	}
	
	public static AppConfig getAppConfig(){return appConfig;}

	/**
	 * 获取线程池
	 * **/
	public ExecutorService getFixedThreadPool(int size) 
	{
		if(null == fixedThreadPool)
			fixedThreadPool = Executors.newFixedThreadPool(size);
		
		return fixedThreadPool;
	}
	
	/**
	 * 常量配置
	 * 
	 * 路由信息
	 * 数据库视图信息
	 * 数据库连接信息
	 * 加载其他的拓展信息
	 * **/
	@Override
	public void configConstant(Constants constants)
	{
		//加载拓展配置
		extendsConfig = Conf.getExtendsConfig();
	}

	/**
	 * 
	 * **/
	@Override
	public void configHandler(Handlers handlers) 
	{
	}

	/**
	 * 注册系统拦截器
	 * **/
	@Override
	public void configInterceptor(Interceptors interceptors) 
	{
		interceptors.addGlobalActionInterceptor(new GlobalActionInterceptor());
	}

	/**
	 * 注册插件
	 * **/
	@Override
	public void configPlugin(Plugins plugins) 
	{	
		//数据库插件
		C3p0Plugin         cp;
		ActiveRecordPlugin actPlugin;
		
		for(Map<String,String> db : Conf.getDbs())
		{	
			cp = new C3p0Plugin(db.get("jdbcUrl"), db.get("userName"),db.get("password"));
			cp.setDriverClass(db.get("className"));
			cp.setMinPoolSize(Integer.valueOf(db.get("minPoolSize")));
			cp.setMaxPoolSize(Integer.valueOf(db.get("maxPoolSize")));
			cp.setInitialPoolSize(Integer.valueOf(db.get("minPoolSize")));
			cp.setMaxIdleTime(Integer.valueOf(db.get("maxIdleTime")));
			plugins.add(cp);
			
			actPlugin = new ActiveRecordPlugin(db.get("name"),cp);
			actPlugin.setShowSql(Conf.isShowSql());
			plugins.add(actPlugin);
		}
		
	}

	/**
	 * 注册路由,将URL与对应类匹配
	 * **/
	@Override
	public void configRoute(Routes routes) 
	{
//		for(String url : Conf.getRouters().keySet())
//			routes.add(url,Conf.getRouters().get(url));
//		
		new RouterTools().loadController(routes, "com");
	}
	
	
	/**
	 * 获取其他拓展配置信息(短信,支付宝等第三方的接口)
	 * **/
	public Map<String, String> getExtendsConfig() 
	{
		return extendsConfig;
	}
	
	/**
	 * json操作类
	 * **/
	public ObjectMapper getJsonMapper() 
	{
		return jsonMapper;
	}
	
	/**
	 * jfinal启动调用
	 * **/
	@Override
	public void afterJFinalStart() 
	{
	}
	
	@Override
	public void beforeJFinalStop() 
	{
	}
	
}