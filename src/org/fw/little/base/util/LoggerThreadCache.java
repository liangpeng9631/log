package org.fw.little.base.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 简易线程缓存信息
 * **/
public class LoggerThreadCache {
	/**
	 * 日志
	 * **/
	private static final Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
	
	private static final Map<Long,String> threadCache = new ConcurrentHashMap<Long,String>();
	
	/**
	 * 获取缓存信息
	 * **/
	public String get()
	{
		return threadCache.get(Thread.currentThread().getId());
	}
	
	/**
	 * 设置缓存信息,覆盖原有信息
	 * **/
	public String set(String value)
	{
		threadCache.put(Thread.currentThread().getId(), value);
		return value;
	}
	
	/**
	 * 设置缓存信息,累加原有信息
	 * **/
	public String add(String value)
	{
		String cache = this.get();
		
		if(null == cache)
		{
			this.set(value);
		}
		else
		{
			this.set(cache+value);
		}
		
		return value;
	}
	
	/**
	 * 设置缓存信息,累加原有信息,自动换行
	 * **/
	public String addln(String value)
	{
		this.add(value+"\r\n");
		return value;
	}
	
	/**
     * 获取异常信息保存至缓存,累加原有信息,自动换行
     * **/
    public String addExceptionln(Exception exception)
    {
        StringWriter sw = null;
        String exeStr   = "";
        try
        {
            sw = new StringWriter();
            exception.printStackTrace(new PrintWriter(sw, true)); 
            
            exeStr = addln(sw.toString());
            sw.close();
        }
        catch (IOException e)
        {
            for(StackTraceElement stackTraceElement : e.getStackTrace())
            {
                exeStr += addln(stackTraceElement.toString());
            }
        }
        finally
        {
            sw = null;    
        }
        
        return exeStr;
    }
	
	/**
	 * 获取缓存信息
	 * **/
	public int count()
	{
		return threadCache.size();
	}
	
	/**
	 * 删除缓存信息
	 * **/
	public void del()
	{
		threadCache.remove(Thread.currentThread().getId());
	}
	
	public void info() {
		logger.info(this.get());
		this.del();
	}
	
	public void error() {
		logger.error(this.get());
		this.del();
	}
	
	public void info(Object obj) {
		logger.info(obj);
	}
	
	public void error(Object obj) {
		logger.error(obj);
	}
}