package by.htp.eduard.ps.webadmin.commands;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import by.htp.eduard.ps.service.RoleService;
import by.htp.eduard.ps.service.ServiceProvider;
import by.htp.eduard.ps.service.dto.RoleDto;
import by.htp.eduard.ps.utils.http.HttpUtils;

public class RoleCommand {
	
	private final RoleService roleService;

	public RoleCommand() {
		roleService = ServiceProvider.getInstance().getRoleService();
	}
	
	public String showAllRoles(HttpServletRequest request) {
		List<RoleDto> allRoles = roleService.getAllRoles();
		request.setAttribute("allRoles", allRoles);
		
		return "/WEB-INF/pages/roles/roles-list.jsp";
	}
	
	public String addRole(HttpServletRequest request) {		
		return "/WEB-INF/pages/roles/role-details.jsp";
	}
	
	public String saveRole(HttpServletRequest request) {
		List<String> validationErrors = new ArrayList<>();
		Integer id = HttpUtils.getIntParam("id", request);
		String name = request.getParameter("newRole");
		
		if(StringUtils.isBlank(name)) {
			validationErrors.add("name.role.empty");
		}
		
		if(!validationErrors.isEmpty()) {
			request.setAttribute("validationErrors", validationErrors);
			
			RoleDto role = roleService.getNameRoleById(id);
			request.setAttribute("role", role);
			return "/WEB-INF/pages/roles/role-details.jsp";
		}
		
		RoleDto role = new RoleDto();
		role.setId(id);
		role.setName(name);
		
		roleService.saveRole(role);
		
		return "redirect:roles-list";
	}

	public String editRole(HttpServletRequest request) {
		Integer id = HttpUtils.getIntParam("roleId", request);
		RoleDto role = roleService.getNameRoleById(id);
		request.setAttribute("role", role);
		return "/WEB-INF/pages/roles/role-details.jsp";
	}
	
	public String deleteRole(HttpServletRequest request) {
		Integer id = HttpUtils.getIntParam("roleId", request);
		roleService.deleteRole(id);
		return "redirect:roles-list";
	}
}
