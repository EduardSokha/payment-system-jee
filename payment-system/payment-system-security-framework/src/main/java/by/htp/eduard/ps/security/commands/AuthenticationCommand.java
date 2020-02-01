package by.htp.eduard.ps.security.commands;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import by.htp.eduard.ps.service.AuthenticationService;
import by.htp.eduard.ps.service.ServiceProvider;
import by.htp.eduard.ps.service.dto.AuthenticationDto;
import by.htp.eduard.ps.service.dto.UserDto;

public class AuthenticationCommand {
	
	private final AuthenticationService authenticationService;

	public AuthenticationCommand() {
		authenticationService = ServiceProvider.getInstance().getAuthenticationService();
	}
	
	public String signIn(HttpServletRequest request) {
		return "/WEB-INF/pages/index.jsp";
	}
	
	public String authentication(HttpServletRequest request) {
		List<String> validationErrors = new ArrayList<>();
		
		String login = request.getParameter("login");
		if(StringUtils.isBlank(login)) {
			validationErrors.add("user.login.empty");
		}
		String password = request.getParameter("password");
		if(StringUtils.isBlank(password)) {
			validationErrors.add("user.password.empty");
		}
		
		AuthenticationDto authentication = new AuthenticationDto();
		authentication.setLogin(login);
		authentication.setPassword(password);
		
		if(!validationErrors.isEmpty()) {
			request.setAttribute("validationErrors", validationErrors);
			
			request.setAttribute("authentication", authentication);

			return "/WEB-INF/pages/index.jsp";
		}
		
		UserDto user = authenticationService.signIn(authentication);
		
		if(user == null) {
			validationErrors.add("no.such.user");
			request.setAttribute("validationErrors", validationErrors);
			
			return "/WEB-INF/pages/index.jsp";
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("authentication", user);
		
		String redirectUrl = (String)session.getAttribute("user-required-url");
		session.removeAttribute("user-required-url");
		
		if(redirectUrl == null) {
			return "redirect:dashboard";
		}
		
		if(!redirectUrl.startsWith("redirect:")) {
			redirectUrl = "redirect:" + redirectUrl;
		}
		return redirectUrl;
	}
	
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		
		ServletContext context = request.getServletContext();
		String contextPath = context.getContextPath();
		return "redirect:" + contextPath;
	}
}
