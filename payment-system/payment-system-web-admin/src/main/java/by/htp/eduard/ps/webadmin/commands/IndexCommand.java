package by.htp.eduard.ps.webadmin.commands;

import javax.servlet.http.HttpServletRequest;

import by.htp.eduard.ps.mvc.model.ModelAndView;

public class IndexCommand {
	
	public ModelAndView showIndexPage(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("redirect:dashboard");
		return modelAndView;
	}

}
