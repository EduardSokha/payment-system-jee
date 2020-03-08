package by.htp.eduard.ps.web.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import by.htp.eduard.ps.mvc.model.ModelAndView;

public class ErrorPageCommand {
	
	public ModelAndView showErrorPage(HttpServletRequest request) throws IOException, ServletException {
		return new ModelAndView("/WEB-INF/pages/error/error-page.jsp");
	}
	
	public ModelAndView showNotFoundPage(HttpServletRequest request) throws IOException, ServletException {
		return new ModelAndView("/WEB-INF/pages/error/page-404.jsp");
	}

}
