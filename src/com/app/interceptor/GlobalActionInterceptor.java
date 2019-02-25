package com.app.interceptor;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fw.little.base.util.LoggerThreadCache;

import com.alibaba.fastjson.JSON;
import com.app.conf.AppConfig;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

public class GlobalActionInterceptor implements Interceptor
{
	private LoggerThreadCache cache = AppConfig.getAppConfig().getLoggerThreadCache();
	// 存放不需要记录日志的action
	private static List<String> actionList = new ArrayList<String>();
	static {
		actionList.add("/logger/logTest");
	}
	
	@Override
	public void intercept(Invocation invocation)
	{
		Controller controller = invocation.getController();
		String actionKey = invocation.getActionKey();
		try
		{
			cache.addln(actionKey);
    		Enumeration<String> params = controller.getParaNames();
            String param = "";
            Map<String, Object> paramMap = new HashMap<String, Object>();
            while (params.hasMoreElements()) {
                param = params.nextElement();
                paramMap.put(param, controller.getPara(param));
            }
            // 为空则不记录
            if (!paramMap.isEmpty()) {
            	String paramStr = JSON.toJSONString(paramMap);
            	cache.addln("requestParams:" + paramStr);
            }
			
			invocation.invoke();
			
			Object returnValue = invocation.getReturnValue();
			// 为空则不记录
			if (!(null == returnValue)) {
				cache.addln("response:" + returnValue);
			}
	        
			// 不需要记录的日志，直接删除释放内存
			if (actionList.contains(actionKey)) {
				cache.del();
			} else {
				cache.info();
			} 
        } catch (Exception ex) {
        	cache.addln(ex.getMessage());
        	cache.error();
        }
	}

}
