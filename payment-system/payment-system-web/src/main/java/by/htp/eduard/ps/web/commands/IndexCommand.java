package by.htp.eduard.ps.web.commands;

import javax.servlet.http.HttpServletRequest;

import by.htp.eduard.ps.mvc.model.ModelAndView;

public class IndexCommand {
	
	public ModelAndView showIndexPage(HttpServletRequest request) {		
		return new ModelAndView("/WEB-INF/pages/users/user-edit.jsp");
	}
	
}