package org.fw.little.base.core;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fw.little.base.annotation.Controller;
import org.fw.little.base.util.ActionTools;
import org.fw.little.base.util.ScanTools;


/**
 * 核心入口类
 * **/
public class LittleFilter implements Filter
{
	/**
	 * 字符集
	 * **/
	private String charSet = "UTF-8";
	
	/**
	 * 项目名称字符长度
	 * **/
	private int contextPathLength;
	
	/**
	 * url与执行方法集合
	 * **/
	private Map<String,LittleAction> actions;
	
	/**
	 * 配置信息
	 * **/
	private LittleConfig config;
	
	
	public void init(FilterConfig filterConfig) throws ServletException 
	{
		//加载配置文件
		initConf(filterConfig.getInitParameter("configClass"));
		
		//扫描包加载系统组件
		ScanTools scanTools      = new ScanTools();
		ActionTools actionTools  = new ActionTools();
		try 
		{
			scanTools.loadClassFile(config.getPackage());
			actions = actionTools.getActions(scanTools.getControllerList(Controller.class));
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		//获取项目名称,用于从url中提取请求url
		String contextPath = filterConfig.getServletContext().getContextPath();
		contextPathLength = (contextPath == null || "/".equals(contextPath) ? 0 : contextPath.length());
		
		config.start();
	}

	
	public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain chain) throws IOException, ServletException 
	{

		//转换请求和响应
		HttpServletRequest  request   = (HttpServletRequest)srequest;
		HttpServletResponse response  = (HttpServletResponse)sresponse;
		
		//设置编码
		request.setCharacterEncoding(charSet);
		
		try 
		{
			LittleAction action = actions.get(request.getRequestURI().substring(contextPathLength));
			
			if(null == action)
			{
				chain.doFilter(srequest, sresponse);return;
			}
			
			action.invoke(action.getController().newInstance(),request,response);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
	
	public void destroy() 
	{
		config.stop();
	}
	
	private void initConf(String className)
	{
		Object confObj;
		
		try 
		{
			confObj = Class.forName(className).newInstance();
		} 
		catch (Exception exe)
		{
			throw new RuntimeException(exe.getMessage());
		}
		
		if(confObj instanceof LittleConfig)
			config = (LittleConfig)confObj;
		else
			throw new RuntimeException("className不是一个LittleConfig");
	}

}