package org.fw.little.base.core;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LittleController {
	protected HttpServletRequest request;

	protected HttpServletResponse response;

	public void init(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public String get(String name) {
		return request.getParameter(name);
	}

	public void responseTxt(String txt) {
		PrintWriter writer = null;
		try {
			response.setHeader("Pragma", "no-cache"); // HTTP/1.0 caches might
														// not implement
														// Cache-Control and
														// might only implement
														// Pragma: no-cache
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);

			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8"); // 与 contentType 分开设置

			writer = response.getWriter();
			writer.write(txt);
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null)
				writer.close();
		}
	}

	public void responseView(String view)
	{
		try
		{
			request.setAttribute("name", "snake");
			request.getRequestDispatcher("/WEB-INF/"+view+".jsp").forward(request, response);
			return;
		} 
		catch (ServletException e)
		{
			e.printStackTrace();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}