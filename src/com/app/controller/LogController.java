package com.app.controller;

import java.util.Map;

import com.app.controller.base.BaseController;
import com.app.interceptor.ExceptionInterceptor;
import com.app.service.LogService;
import com.jfinal.aop.Before;
import com.jfinal.kit.HttpKit;
import com.web.annotation.UrlMapper;

@UrlMapper(val="/logger")
public class LogController extends BaseController {
	private LogService logService = new LogService();
	
	@Before(ExceptionInterceptor.class)
	public Map<String, Object> logTest() throws Exception {
		System.out.println("###"+getParaMap());
		System.out.println("json"+HttpKit.readData(getRequest()));
		return logService.logTest();
	}
	
	@Before(ExceptionInterceptor.class)
	public Map<String, Object> logTest2() throws Exception {
		System.out.println("###"+getParaMap());
		System.out.println("json"+HttpKit.readData(getRequest()));
		return logService.logTest();
	}
	
	public void logNull() throws Exception {
		renderJson(logService.logTest());
		return;
	}
}
