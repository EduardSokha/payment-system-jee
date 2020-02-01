package by.htp.eduard.ps.webadmin.commands;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import by.htp.eduard.ps.service.AuthenticationService;
import by.htp.eduard.ps.service.ServiceProvider;
import by.htp.eduard.ps.service.UserService;
import by.htp.eduard.ps.service.dto.AuthenticationDto;
import by.htp.eduard.ps.service.dto.UserDto;
import by.htp.eduard.ps.utils.http.HttpUtils;

public class SupportCommand {
	
	private final AuthenticationService authenticationService;
	private final UserService userService;

	public SupportCommand() {
		authenticationService = ServiceProvider.getInstance().getAuthenticationService();
		userService = ServiceProvider.getInstance().getUserService();
	}
	
	public String registrationNewUser(HttpServletRequest request) {
		return "/WEB-INF/pages/users/registration-new-user.jsp";
	}
	
	public String saveNewUser(HttpServletRequest request) {
		List<String> validationErrors = new ArrayList<>();
		HttpSession session = request.getSession();	
		if(!request.getParameter("password").equals(request.getParameter("password2"))) {
			String response = "Password1 and Password2 don't match!";
			
			request.setAttribute("response", response);
			return "/WEB-INF/pages/users/registration-new-user.jsp";
		}
		
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
			request.setAttribute("validationErrors", validationErrors);
			
			request.setAttribute("user", user);
			return "/WEB-INF/pages/users/registration-new-user.jsp";
		}
		
		userService.saveUser(user);
		
		session.setAttribute("successRegistr", true);
		
		ServletContext context = request.getServletContext();
		String contextPath = context.getContextPath();
		return "redirect:" + contextPath;
	}
	
	public String forgetPassword(HttpServletRequest request) {
		return "/WEB-INF/pages/authentication/forget-password.jsp";
	}

	public String getForgetPassword(HttpServletRequest request) {
		List<String> validationErrors = new ArrayList<>();
		
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
		
		AuthenticationDto authentication = new AuthenticationDto();
		authentication.setPassportSeries(passportSeries);
		authentication.setPassportId(passportId);
		authentication.setCodeWord(codeWord);
		
		if(!validationErrors.isEmpty()) {
			request.setAttribute("validationErrors", validationErrors);
			
			request.setAttribute("authentication", authentication);
			return "/WEB-INF/pages/authentication/forget-password.jsp";
		}
		
		UserDto user = authenticationService.forgetPassword(authentication);
		
		request.setAttribute("user", user);
		return "/WEB-INF/pages/authentication/forget-password.jsp";
	}
}
