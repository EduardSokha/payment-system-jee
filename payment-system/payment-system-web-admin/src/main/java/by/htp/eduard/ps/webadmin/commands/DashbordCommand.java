package by.htp.eduard.ps.webadmin.commands;

import javax.servlet.http.HttpServletRequest;

public class DashbordCommand {
	
	public String showDashboardPage(HttpServletRequest request) {
		return "/WEB-INF/pages/dashboard/dashboard.jsp";
	}

}