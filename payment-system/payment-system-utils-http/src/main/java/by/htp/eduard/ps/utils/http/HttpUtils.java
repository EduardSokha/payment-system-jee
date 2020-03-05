package by.htp.eduard.ps.utils.http;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class HttpUtils {
	
	private final static Logger logger = Logger.getLogger(HttpUtils.class);
	
	public static Integer getIntParam(String paramName, HttpServletRequest request) {
		String idStr = request.getParameter(paramName);
		Integer value = null;
		try {
			value = Integer.parseInt(idStr);
		} catch(Exception e){
			logger.error("Cannot parse integer value", e);
		}
		
		return value;
	}
	
	public static Double getDoubleParam(String paramName, HttpServletRequest request) {
		String idStr = request.getParameter(paramName);
		Double value = null;
		try {
			value = Double.parseDouble(idStr);
		} catch(Exception e){
			logger.error("Cannot parse double value", e);
		}
		
		return value;
	}
	
	public static String getPageContext(HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		ServletContext servletContext = request.getServletContext();
		String contextPath = servletContext.getContextPath();
		String pageContext = requestURI.replace(contextPath, "");
		
		return pageContext;
	}
	
	public static File getFileFromWebInf(String filePath, ServletContext context) {
		String fullPath = context.getRealPath(filePath);
		File file = new File(fullPath);
		
		return file;
	}
}
