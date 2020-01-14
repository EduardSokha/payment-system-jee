package by.htp.eduard.ps.utils.http;

import javax.servlet.http.HttpServletRequest;

public class HttpUtils {
	public static Integer getIntParam(String paramName, HttpServletRequest request) {
		String idStr = request.getParameter(paramName);
		Integer value = null;
		try {
			value = Integer.parseInt(idStr);
		} catch(Exception e){
		}
		
		return value;
	}
	
	public static Double getDoubleParam(String paramName, HttpServletRequest request) {
		String idStr = request.getParameter(paramName);
		Double value = null;
		try {
			value = Double.parseDouble(idStr);
		} catch(Exception e){
		}
		
		return value;
	}

}
