package by.htp.eduard.ps.webadmin.commands;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import by.htp.eduard.ps.mvc.model.ModelAndView;
import by.htp.eduard.ps.service.RoleService;
import by.htp.eduard.ps.service.ServiceProvider;
import by.htp.eduard.ps.service.dto.RoleDto;
import by.htp.eduard.ps.utils.http.HttpUtils;

public class RoleCommand {
	
	private final RoleService roleService;

	public RoleCommand() {
		roleService = ServiceProvider.getInstance().getRoleService();
	}
	
	public ModelAndView showAllRoles(HttpServletRequest request) {
		
		List<RoleDto> allRoles = roleService.getAllRoles();
		request.setAttribute("allRoles", allRoles);
		ModelAndView modelAndView = new ModelAndView("/WEB-INF/pages/roles/roles-list.jsp");
		
		return modelAndView;
	}
	
	public ModelAndView addRole(HttpServletRequest request) {		
		return new ModelAndView("/WEB-INF/pages/roles/role-details.jsp");
	}
	
	public ModelAndView saveRole(HttpServletRequest request) {
		Set<String> validationErrors = new HashSet<>();
		Integer id = HttpUtils.getIntParam("id", request);
		String name = request.getParameter("newRole");
		
		if(StringUtils.isBlank(name)) {
			validationErrors.add("name.role.empty");
		}
		
		if(!validationErrors.isEmpty()) {
			
			RoleDto role = roleService.getNameRoleById(id);
			
			ModelAndView modelAndView = new ModelAndView("/WEB-INF/pages/roles/role-details.jsp");
			
			modelAndView.addViewData("role", role);
			modelAndView.addAllValidationError(validationErrors);
			
			return modelAndView;
		}
		
		RoleDto role = new RoleDto();
		role.setId(id);
		role.setName(name);
		
		roleService.saveRole(role);
		
		ModelAndView modelAndView = new ModelAndView("redirect:roles-list");
		
		return modelAndView;
	}

	public ModelAndView editRole(HttpServletRequest request) {
		Integer id = HttpUtils.getIntParam("roleId", request);
		RoleDto role = roleService.getNameRoleById(id);
		ModelAndView modelAndView = new ModelAndView("/WEB-INF/pages/roles/role-details.jsp");
		modelAndView.addViewData("role", role);
		return modelAndView;
	}
	
	public ModelAndView deleteRole(HttpServletRequest request) {
		Integer id = HttpUtils.getIntParam("roleId", request);
		roleService.deleteRole(id);
		return new ModelAndView("redirect:roles-list");
	}
}
