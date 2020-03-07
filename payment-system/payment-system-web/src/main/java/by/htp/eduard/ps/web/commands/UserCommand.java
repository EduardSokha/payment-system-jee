package by.htp.eduard.ps.web.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.htp.eduard.ps.mvc.model.ModelAndView;
import by.htp.eduard.ps.service.ServiceProvider;
import by.htp.eduard.ps.service.UserService;
import by.htp.eduard.ps.service.dto.UserDto;
import by.htp.eduard.ps.utils.http.HttpUtils;

public class UserCommand {
	
	private final UserService userService;

	public UserCommand() {
		userService = ServiceProvider.getInstance().getUserService();
	}

	public ModelAndView saveUser(HttpServletRequest request) throws IOException, ServletException {
		ModelAndView modelAndView = new ModelAndView("redirect:home");
		HttpSession session = request.getSession();	
//		List<String> validationErrors = new ArrayList<>();
		Integer id = HttpUtils.getIntParam("id", request);
		String login = request.getParameter("login");
//		if(StringUtils.isBlank(login)) {
//			validationErrors.add("user.login.empty");
//		}
//		if(userService.isLoginExists(login)) {
//			validationErrors.add("user.login.duplicate");
//		}
		String password = request.getParameter("password");
//		if(StringUtils.isBlank(password)) {
//			validationErrors.add("user.password.empty");
//		}
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String address = request.getParameter("address");
		Integer roleId = HttpUtils.getIntParam("roleId", request);
		String passportSeries = request.getParameter("passportSeries");
		String passportId = request.getParameter("passportId");
		String codeWord = request.getParameter("codeWord");
		String phoneNumber = request.getParameter("phoneNumber");
		String residenceRegistr = request.getParameter("residenceRegistr");
		
//		if(!validationErrors.isEmpty()) {
//			request.setAttribute("validationErrors", validationErrors);
//			List<RoleDto> allRoles = roleService.getAllRoles();
//			request.setAttribute("allRoles", allRoles);
//			return "/WEB-INF/pages/users/user-details.jsp";
//		}
		
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
		
		user = userService.saveUser(user);
		session.setAttribute("authentication", user);
		
		return modelAndView;
	}
}
