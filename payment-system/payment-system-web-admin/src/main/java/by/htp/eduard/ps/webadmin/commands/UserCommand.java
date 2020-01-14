package by.htp.eduard.ps.webadmin.commands;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import by.htp.eduard.dto.RoleDto;
import by.htp.eduard.entities.User;
import by.htp.eduard.service.RoleService;
import by.htp.eduard.service.ServiceProvider;
import by.htp.eduard.service.UserService;
import by.htp.eduard.utils.HttpUtils;

public class UserCommand {
	
	private final UserService userService;
	private final RoleService roleService;
	
	public UserCommand() {
		userService = ServiceProvider.getInstance().getUserService();
		roleService = ServiceProvider.getInstance().getRoleService();
	}

	public String showUserList(HttpServletRequest request) throws IOException, ServletException {
		List<User> allUsers = userService.getAllUsers();
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
		Integer id = HttpUtils.getIntParam("id", request);
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String address = request.getParameter("address");
		Integer roleId = HttpUtils.getIntParam("roleId", request);
		String passportSeries = request.getParameter("passportSeries");
		String passportId = request.getParameter("passportId");
		String codeWord = request.getParameter("codeWord");
		String phoneNumber = request.getParameter("phoneNumber");
		String residenceRegistr = request.getParameter("residenceRegistr");
		
		User user = new User();
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
		
		userService.saveUser(user);
		
		return "redirect:user-list";
	}
	
	public String editUser(HttpServletRequest request) {
		List<RoleDto> allRoles = roleService.getAllRoles();
		request.setAttribute("allRoles", allRoles);
		Integer id = HttpUtils.getIntParam("userId", request);
		User user = userService.getUserById(id);
		request.setAttribute("user", user);
		return "/WEB-INF/pages/users/user-details.jsp";
	}
	
	public String deleteUser(HttpServletRequest request) {
		Integer id = HttpUtils.getIntParam("userId", request);
		userService.deleteUser(id);
		return "redirect:user-list";
	}
}
