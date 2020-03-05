package by.htp.eduard.ps.webadmin.commands;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import by.htp.eduard.ps.mvc.model.ModelAndView;
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

	public ModelAndView showUserList(HttpServletRequest request) throws IOException, ServletException {
		ModelAndView modelAndView = new ModelAndView("/WEB-INF/pages/users/user-list.jsp");
		List<UserDto> allUsers = userService.getAllUsers();
//		request.setAttribute("allUsers", allUsers);
		modelAndView.addViewData("allUsers", allUsers);
		return modelAndView;
	}

	public ModelAndView addUser(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("/WEB-INF/pages/users/user-details.jsp");
		List<RoleDto> allRoles = roleService.getAllRoles();
//		request.setAttribute("allRoles", allRoles);
		modelAndView.addViewData("allRoles", allRoles);
		return modelAndView;
	}
	
	public ModelAndView showUserDetail(HttpServletRequest request) throws IOException, ServletException {
		return new ModelAndView("/WEB-INF/pages/users/user.jsp");
	}

	public ModelAndView saveUser(HttpServletRequest request) throws IOException, ServletException {
		Set<String> validationErrors = new HashSet<>();
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
		if(StringUtils.isBlank(name)) {
			validationErrors.add("user.name.empty");
		}
		String surname = request.getParameter("surname");
		if(StringUtils.isBlank(surname)) {
			validationErrors.add("user.surname.empty");
		}
		String address = request.getParameter("address");
		Integer roleId = HttpUtils.getIntParam("roleId", request);
		String passportSeries = request.getParameter("passportSeries");
		if(StringUtils.isBlank(passportSeries)) {
			validationErrors.add("user.passportSeries.empty");
		}
		String passportId = request.getParameter("passportId");
		if(StringUtils.isBlank(passportId)) {
			validationErrors.add("user.passportId.empty");
		}
		String codeWord = request.getParameter("codeWord");
		if(StringUtils.isBlank(codeWord)) {
			validationErrors.add("user.codeWord.empty");
		}
		String phoneNumber = request.getParameter("phoneNumber");
		if(StringUtils.isBlank(phoneNumber)) {
			validationErrors.add("user.phoneNumber.empty");
		}
		String residenceRegistr = request.getParameter("residenceRegistr");
		if(StringUtils.isBlank(residenceRegistr)) {
			validationErrors.add("user.residenceRegistr.empty");
		}
		
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
			ModelAndView modelAndView = new ModelAndView("/WEB-INF/pages/users/user-details.jsp");			
//			request.setAttribute("validationErrors", validationErrors);
			modelAndView.addAllValidationError(validationErrors);
			
			List<RoleDto> allRoles = roleService.getAllRoles();
//			request.setAttribute("allRoles", allRoles);
			modelAndView.addViewData("allRoles", allRoles);
			
//			request.setAttribute("user", user);
			modelAndView.addViewData("user", user);
			
			return modelAndView;
		}
		
		userService.saveUser(user);
		ModelAndView modelAndView = new ModelAndView("redirect:user-list");
		
		return modelAndView;
	}
	
	public ModelAndView editUser(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("/WEB-INF/pages/users/user-details.jsp");
		List<RoleDto> allRoles = roleService.getAllRoles();
//		request.setAttribute("allRoles", allRoles);
		modelAndView.addViewData("allRoles", allRoles);
		
		Integer id = HttpUtils.getIntParam("userId", request);
		UserDto user = userService.getUserById(id);
//		request.setAttribute("user", user);
		modelAndView.addViewData("user", user);
		
		return modelAndView;
	}
	
	public ModelAndView deleteUser(HttpServletRequest request) {
		Integer id = HttpUtils.getIntParam("userId", request);
		userService.deleteUser(id);
		return new ModelAndView("redirect:user-list");
	}
}
