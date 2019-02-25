package com.app.controller.base;

import org.fw.little.base.util.LoggerThreadCache;

import com.app.conf.AppConfig;
import com.jfinal.core.Controller;

public class BaseController extends Controller {
	protected LoggerThreadCache loggerThreadCache = AppConfig.getAppConfig().getLoggerThreadCache();
}
