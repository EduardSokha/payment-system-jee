package by.htp.eduard.ps.webadmin.commands;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.htp.eduard.ps.service.ServiceProvider;
import by.htp.eduard.ps.service.StatusService;
import by.htp.eduard.ps.service.dto.StatusDto;
import by.htp.eduard.ps.utils.http.HttpUtils;

public class StatusCommands {
	
	private final StatusService statusService;

	public StatusCommands() {
		statusService = ServiceProvider.getInstance().getStatusService();
	}
	
	public String showAllStatus(HttpServletRequest request) {
		List<StatusDto> allStatus = statusService.getAllStatus();
		request.setAttribute("allStatus", allStatus);
		
		return "/WEB-INF/pages/status/status-list.jsp";
	}
	
	public String addStatus(HttpServletRequest request) {		
		return "/WEB-INF/pages/status/status-details.jsp";
	}
	
	public String saveStatus(HttpServletRequest request) {
		Integer id = HttpUtils.getIntParam("id", request);
		String name = request.getParameter("nameStatus");
		
		StatusDto status = new StatusDto();
		status.setId(id);
		status.setName(name);
		
		statusService.saveStatus(status);
		
		return "redirect:status-list";
	}

	public String editStatus(HttpServletRequest request) {
		Integer id = HttpUtils.getIntParam("statusId", request);
		StatusDto status = statusService.getNameStatusById(id);
		request.setAttribute("status", status);
		return "/WEB-INF/pages/status/status-details.jsp";
	}
	
	public String deleteStatus(HttpServletRequest request) {
		Integer id = HttpUtils.getIntParam("statusId", request);
		statusService.deleteStatus(id);
		return "redirect:status-list";
	}

}
