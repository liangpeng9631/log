package com.app.conf.info;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Conf 
{	
	/**
	 * 站点根路径
	 * **/
	public static final String www = "/pub";
	
	//是否显示SQL
	private static boolean showSql = true;
	//是否显示SQL
	public static boolean isShowSql(){return showSql;}
	
	// 业务数据库
	private static List<Map<String,String>> dbs = new ArrayList<Map<String,String>>();
	static
	{
		
	}
	
	//获取数据库配置
	public static List<Map<String,String>> getDbs(){return dbs;}
	
	
	// 日志数据库
	private static Map<String,String> loggerDB =  new HashMap<String,String>();
	static
	{
		// 日志数据库配置
		loggerDB.put("name", "ykb_logs");
		loggerDB.put("type", "mysql");
		loggerDB.put("className", "com.mysql.jdbc.Driver");
		loggerDB.put("jdbcUrl", "jdbc:mysql://192.168.32.214:3306/ykb_logs");
		loggerDB.put("userName", "root");
		loggerDB.put("password", "000000");
		loggerDB.put("minPoolSize", "10");
		loggerDB.put("maxPoolSize", "15");
		loggerDB.put("maxIdleTime", "9");
		loggerDB.put("idleConnectionTestPeriod", "60");
	
	}
	
	/**
	 * 获取日志数据库配置
	 * @return
	 */
	public static Map<String,String> getLoggerDb(){return loggerDB;}
	
	
	/**
	 * mongo配置
	 * **/
	private static Map<String,String> mongoConfig = new HashMap<String, String>();
	static
	{
		/*//地址
		mongoConfig.put("host", "192.168.10.126:27017");
		
		//与目标数据库能够建立的最大connection数量
		mongoConfig.put("connectionsPerHost", "50");
		
		//等待队列大小
		mongoConfig.put("threadsAllowedToBlockForConnectionMultiplier", "50");
		
		//等待时间
		mongoConfig.put("maxWaitTime", "12000");
		
		//连接时间
		mongoConfig.put("connectTimeout", "12000");
		
		//
		mongoConfig.put("sms_db", "cccloud_log");*/
	}
	
	public static Map<String, String> getMongoConfig() {
		return mongoConfig;
	}
	
	/**
     * redis配置
     **/
    private static List<Map<String, String>> redis = new ArrayList<Map<String, String>>();

    /*private static Map<String, String> redis_00 = new HashMap<String, String>();

    static {
        redis_00.put("name", "redis_00");
        redis_00.put("host", "192.168.32.217");
        redis_00.put("port", "6379");
        redis_00.put("timeout", "600");
        redis.add(redis_00);
    }*/

    public static List<Map<String, String>> getRedis() {
        return redis;
    }
	
	/**
	 * 拓展配置
	 */
	private static Map<String,String> extendsConfig = new HashMap<String, String>();
	static
	{
	}
	public static Map<String,String> getExtendsConfig(){return extendsConfig;}
}