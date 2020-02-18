package by.htp.eduard.ps.webadmin.commands;

import javax.servlet.http.HttpServletRequest;

import by.htp.eduard.ps.mvc.model.ModelAndView;

public class DashbordCommand {
	
	public ModelAndView showDashboardPage(HttpServletRequest request) {
		return new ModelAndView("/WEB-INF/pages/dashboard/dashboard.jsp");
	}
}
