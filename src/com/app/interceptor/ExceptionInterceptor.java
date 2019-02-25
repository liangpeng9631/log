package com.app.interceptor;

import java.util.Map;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

/**
 * action过滤器
 **/
public class ExceptionInterceptor implements Interceptor {
    @Override
    public void intercept(Invocation inv) {
        Controller controller = inv.getController();

        try {
            inv.invoke();

            /** Controller处理完毕**/
            Map<String, Object> collorMap = inv.getReturnValue();
            
            controller.renderJson(collorMap);
            
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        return;
    }

}