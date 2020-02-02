package by.htp.eduard.ps.webadmin.commands;

import javax.servlet.http.HttpServletRequest;

public class IndexCommand {
	
	public String showIndexPage(HttpServletRequest request) {
//		HttpSession session = request.getSession();	
//		Object successRegistr = session.getAttribute("successRegistr");
//		session.removeAttribute("successRegistr");
		
//		request.setAttribute("successRegistr", successRegistr);
		
		return "redirect:dashboard";
	}

}
