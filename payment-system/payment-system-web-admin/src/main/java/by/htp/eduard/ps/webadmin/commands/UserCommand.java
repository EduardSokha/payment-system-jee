package by.htp.eduard.ps.webadmin.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import by.htp.eduard.ps.service.RoleService;
import by.htp.eduard.ps.service.ServiceProvider;
import by.htp.eduard.ps.service.UserService;
import by.htp.eduard.ps.service.dto.RoleDto;
import by.htp.eduard.ps.service.dto.UserDto;
import by.htp.eduard.ps.utils.http.HttpUtils;

public class UserCommand {
	
	private final UserService userService;
	private final RoleService roleService;
	
	public UserCommand() {
		userService = ServiceProvider.getInstance().getUserService();
		roleService = ServiceProvider.getInstance().getRoleService();
	}

	public String showUserList(HttpServletRequest request) throws IOException, ServletException {
		List<UserDto> allUsers = userService.getAllUsers();
		request.setAttribute("allUsers", allUsers);
		return "/WEB-INF/pages/users/user-list.jsp";
	}

	public String addUser(HttpServletRequest request) {
		List<RoleDto> allRoles = roleService.getAllRoles();
		request.setAttribute("allRoles", allRoles);
		return "/WEB-INF/pages/users/user-details.jsp";
	}
	
	public String showUserDetail(HttpServletRequest request) throws IOException, ServletException {
		return "/WEB-INF/pages/users/user.jsp";
	}

	public String saveUser(HttpServletRequest request) throws IOException, ServletException {
		List<String> validationErrors = new ArrayList<>();
		Integer id = HttpUtils.getIntParam("id", request);
		String login = request.getParameter("login");
		if(StringUtils.isBlank(login)) {
			validationErrors.add("user.login.empty");
		}
		if(userService.isLoginExists(login)) {
			validationErrors.add("user.login.duplicate");
		}
		String password = request.getParameter("password");
		if(StringUtils.isBlank(password)) {
			validationErrors.add("user.password.empty");
		}
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String address = request.getParameter("address");
		Integer roleId = HttpUtils.getIntParam("roleId", request);
		String passportSeries = request.getParameter("passportSeries");
		String passportId = request.getParameter("passportId");
		String codeWord = request.getParameter("codeWord");
		String phoneNumber = request.getParameter("phoneNumber");
		String residenceRegistr = request.getParameter("residenceRegistr");
		
		UserDto user = new UserDto();
		user.setId(id);
		user.setLogin(login);
		user.setPassword(password);
		user.setName(name);
		user.setSurname(surname);
		user.setAddress(address);
		user.setRoleId(roleId);
		user.setPassportSeries(passportSeries);
		user.setPassportId(passportId);
		user.setCodeWord(codeWord);
		user.setPhoneNumber(phoneNumber);
		user.setResidenceRegistr(residenceRegistr);
		
		if(!validationErrors.isEmpty()) {
			request.setAttribute("validationErrors", validationErrors);
			
			List<RoleDto> allRoles = roleService.getAllRoles();
			request.setAttribute("allRoles", allRoles);
			
			request.setAttribute("user", user);
			return "/WEB-INF/pages/users/user-details.jsp";
		}
		
		userService.saveUser(user);
		
		return "redirect:user-list";
	}
	
	public String editUser(HttpServletRequest request) {
		List<RoleDto> allRoles = roleService.getAllRoles();
		request.setAttribute("allRoles", allRoles);
		Integer id = HttpUtils.getIntParam("userId", request);
		UserDto user = userService.getUserById(id);
		request.setAttribute("user", user);
		return "/WEB-INF/pages/users/user-details.jsp";
	}
	
	public String deleteUser(HttpServletRequest request) {
		Integer id = HttpUtils.getIntParam("userId", request);
		userService.deleteUser(id);
		return "redirect:user-list";
	}
}
