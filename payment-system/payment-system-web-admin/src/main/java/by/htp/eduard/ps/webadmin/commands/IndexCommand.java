package by.htp.eduard.ps.webadmin.commands;

import javax.servlet.http.HttpServletRequest;

import by.htp.eduard.ps.mvc.model.ModelAndView;

public class IndexCommand {
	
	public ModelAndView showIndexPage(HttpServletRequest request) {
//		HttpSession session = request.getSession();	
//		Object successRegistr = session.getAttribute("successRegistr");
//		session.removeAttribute("successRegistr");
		
//		request.setAttribute("successRegistr", successRegistr);
		ModelAndView modelAndView = new ModelAndView("redirect:dashboard");
		return modelAndView;
	}

}
