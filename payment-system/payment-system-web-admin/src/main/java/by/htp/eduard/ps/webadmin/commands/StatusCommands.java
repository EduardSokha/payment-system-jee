package by.htp.eduard.ps.webadmin.commands;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import by.htp.eduard.ps.mvc.model.ModelAndView;
import by.htp.eduard.ps.service.ServiceProvider;
import by.htp.eduard.ps.service.StatusService;
import by.htp.eduard.ps.service.dto.StatusDto;
import by.htp.eduard.ps.utils.http.HttpUtils;

public class StatusCommands {
	
	private final StatusService statusService;

	public StatusCommands() {
		statusService = ServiceProvider.getInstance().getStatusService();
	}
	
	public ModelAndView showAllStatus(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("/WEB-INF/pages/status/status-list.jsp");
		List<StatusDto> allStatus = statusService.getAllStatus();
		modelAndView.addViewData("allStatus", allStatus);
		
		return modelAndView;
	}
	
	public ModelAndView addStatus(HttpServletRequest request) {		
		return new ModelAndView("/WEB-INF/pages/status/status-details.jsp");
	}
	
	public ModelAndView saveStatus(HttpServletRequest request) {
		Set<String> validationErrors = new HashSet<>();		
		Integer id = HttpUtils.getIntParam("id", request);
		String name = request.getParameter("nameStatus");
		
		if(StringUtils.isBlank(name)) {
			validationErrors.add("name.status.empty");
		}
		
		if(!validationErrors.isEmpty()) {
			ModelAndView modelAndView = new ModelAndView("/WEB-INF/pages/status/status-details.jsp");
			modelAndView.addAllValidationError(validationErrors);
			
			StatusDto status = statusService.getNameStatusById(id);
			modelAndView.addViewData("status", status);
			
			return modelAndView;
		}
		
		StatusDto status = new StatusDto();
		status.setId(id);
		status.setName(name);
		
		statusService.saveStatus(status);
		ModelAndView modelAndView = new ModelAndView("redirect:status-list");
		
		return modelAndView;
	}

	public ModelAndView editStatus(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("/WEB-INF/pages/status/status-details.jsp");
		Integer id = HttpUtils.getIntParam("statusId", request);
		StatusDto status = statusService.getNameStatusById(id);
		modelAndView.addViewData("status", status);
		
		return modelAndView;
	}
	
	public ModelAndView deleteStatus(HttpServletRequest request) {
		Integer id = HttpUtils.getIntParam("statusId", request);
		statusService.deleteStatus(id);
		
		return new ModelAndView("redirect:status-list");
	}
}
