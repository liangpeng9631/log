package org.fw.little.base.core;

/**
 * 配置
 * **/
public abstract class LittleConfig
{
	//启动运行
	public void start(){}
	
	//关闭运营
	public void stop(){}
	
	//数据库
	public void initDataBase(){}
	
	//缓存
	public void initCache(){}
	
	//其他拓展
	public void extend(){}
	
	//获取需要扫描的包
	public String getPackage(){return "";}
}
