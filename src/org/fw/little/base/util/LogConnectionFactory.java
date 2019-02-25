package org.fw.little.base.util;

import java.sql.Connection;
import java.util.Map;

import javax.sql.DataSource;

import com.app.conf.info.Conf;
import com.mchange.v2.c3p0.ComboPooledDataSource;




public class LogConnectionFactory {
	private static LogConnectionFactory connectionFactory; 
	// 单例模式保证多线程安全
    private static synchronized LogConnectionFactory init() {
        if (null == connectionFactory){
        	connectionFactory = new LogConnectionFactory();
        }
        return connectionFactory;
    }

    private DataSource dataSource = null;

    private LogConnectionFactory() {
    	// 获取数据库连接
    	Map<String, String> dbMap = Conf.getLoggerDb();

        try {
        	ComboPooledDataSource source = new ComboPooledDataSource();
        	source.setDriverClass(dbMap.get("className"));
        	source.setJdbcUrl(dbMap.get("jdbcUrl"));
        	source.setUser(dbMap.get("userName"));
        	source.setPassword(dbMap.get("password"));
        	source.setMaxPoolSize(Integer.valueOf(dbMap.get("maxPoolSize")));
        	source.setMinPoolSize(Integer.valueOf(dbMap.get("minPoolSize")));
        	source.setMaxIdleTime(Integer.valueOf(dbMap.get("maxIdleTime")));
        	source.setIdleConnectionTestPeriod(Integer.valueOf(dbMap.get("idleConnectionTestPeriod")));
            this.dataSource = source;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getDatabaseConnection() throws Exception {
        return LogConnectionFactory.init().dataSource.getConnection();
    }
}
