package org.fw.little.base.core;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 控制器
 * **/
public class LittleAction 
{	
	private Method method;
	
	private Class<? extends LittleController> controller;
	
	private String url;
	
	private Object[] objs;
	
	public LittleAction(String url,Class<? extends LittleController> clzz,Method method) throws Exception
	{
		this.url        = url;
		this.controller = clzz;
		this.method     = method;
	}
	
	public String getUrl()
	{
		return url;
	}
	
	public Class<? extends LittleController> getController()
	{
		return controller;
	}
	
	/**
	 * 调用控制器方法
	 * **/
	public void invoke(LittleController controller,HttpServletRequest request, HttpServletResponse response)throws Exception
	{
		controller.init(request,response);
		method.invoke(controller,objs);
	}
}