package org.fw.little.base.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 类扫描工具类
 * **/
public class LittleScan 
{
	private String basePath;
	
	public LittleScan()
	{
		basePath = Thread.currentThread().getContextClassLoader().getResource("/").getPath();
	}
	
	/**
	 * 加载控制器
	 * **/
	public List<String> loadClassNames(String basePackage) throws Exception
	{
		File file               = new File(basePath+basePackage.replaceAll("\\.", "/"));
		String[] names          =  file.list();
		List<String> classNames = new ArrayList<String>();
		
		for(int i=0;i<names.length;i++)
		{
			if(names[i].endsWith(".class"))
			{
				classNames.add(basePackage+"."+names[i].replace(".class",""));
			}
		}
		return classNames;
	}

}