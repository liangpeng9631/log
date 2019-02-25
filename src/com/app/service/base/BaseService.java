package com.app.service.base;

import org.fw.little.base.util.LoggerThreadCache;

import com.app.conf.AppConfig;

public class BaseService {
	protected LoggerThreadCache loggerThreadCache = AppConfig.getAppConfig().getLoggerThreadCache();
}
