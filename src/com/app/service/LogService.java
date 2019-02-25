package com.app.service;

import java.util.HashMap;
import java.util.Map;

import com.app.service.base.BaseService;

public class LogService extends BaseService {

	public Map<String, Object> logTest() throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("test", "业务逻辑内部日志");
		loggerThreadCache.info(resultMap.toString());
		loggerThreadCache.addln(resultMap.toString());
		return resultMap;
	}
}
