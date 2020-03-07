package by.htp.eduard.ps.webadmin.commands;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import by.htp.eduard.ps.mvc.model.ModelAndView;
import by.htp.eduard.ps.security.dto.AuthenticationDto;
import by.htp.eduard.ps.security.service.AuthenticationService;
import by.htp.eduard.ps.security.service.AuthenticationServiceProvider;
import by.htp.eduard.ps.service.EmailService;
import by.htp.eduard.ps.service.dto.UserDto;
import by.htp.eduard.ps.service.impl.EmailServiceImpl;

public class SupportCommand {
	
	private final AuthenticationService authenticationService;

	public SupportCommand() {
		authenticationService = AuthenticationServiceProvider.getInstance().getAuthenticationService();
	}
	
	public ModelAndView registrationNewUser(HttpServletRequest request) {
		return new ModelAndView("/WEB-INF/pages/users/registration-new-user.jsp");
	}
	
	public ModelAndView forgetPassword(HttpServletRequest request) {
		return new ModelAndView("/WEB-INF/pages/authentication/forget-password.jsp");
	}

	public ModelAndView getForgetPassword(HttpServletRequest request) {
		Set<String> validationErrors = new HashSet<>();
		
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
		String emai = request.getParameter("emai");
		if(StringUtils.isBlank(emai)) {
			validationErrors.add("user.email.empty");
		}
		
		AuthenticationDto authentication = new AuthenticationDto();
		authentication.setPassportSeries(passportSeries);
		authentication.setPassportId(passportId);
		authentication.setCodeWord(codeWord);
		
		if(!validationErrors.isEmpty()) {
			ModelAndView modelAndView = new ModelAndView("/WEB-INF/pages/authentication/forget-password.jsp");
			modelAndView.addAllValidationError(validationErrors);
			modelAndView.addViewData("authentication", authentication);
			
			return modelAndView;
		}
		
		UserDto user = authenticationService.forgetPassword(authentication);
		
		if(user == null) {
			ModelAndView modelAndView = new ModelAndView("/WEB-INF/pages/authentication/forget-password.jsp");
			validationErrors.add("user.forgetPassword.invalid");
			
			modelAndView.addAllValidationError(validationErrors);
			modelAndView.addViewData("authentication", authentication);
			
			return modelAndView;
		}
		
		EmailService emailService = new EmailServiceImpl();
		emailService.sendEmail(emai, "Your restoried password", user.getPassword());
		
		ModelAndView modelAndView = new ModelAndView("redirect:sent-password");
		return modelAndView;
	}
	
	public ModelAndView sentForgetPassword(HttpServletRequest request) {
		return new ModelAndView("/WEB-INF/pages/authentication/forget-password-completed.jsp");
	}
}
