package by.htp.eduard.ps.webadmin.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class ErrorPageCommand {
	
	public String showErrorPage(HttpServletRequest request) throws IOException, ServletException {
		return "/WEB-INF/pages/error/error-page.jsp";
	}
	
	public String showNotFoundPage(HttpServletRequest request) throws IOException, ServletException {
		return "/WEB-INF/pages/error/page-404.jsp";
	}

}
