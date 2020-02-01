package by.htp.eduard.ps.web.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class IndexCommand {
	
	public String showIndexPage(HttpServletRequest request) {
		HttpSession session = request.getSession();	
		Object successRegistr = session.getAttribute("successRegistr");
		session.removeAttribute("successRegistr");
		
		request.setAttribute("successRegistr", successRegistr);
		
		return "/WEB-INF/pages/users/user-edit.jsp";
	}

}
