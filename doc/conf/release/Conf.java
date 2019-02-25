package com.app.conf.info;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.app.util.sys.SYS_TABLE;


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
	
	
}