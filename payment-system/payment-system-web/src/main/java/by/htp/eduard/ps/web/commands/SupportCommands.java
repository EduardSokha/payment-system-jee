package by.htp.eduard.ps.web.commands;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import by.htp.eduard.ps.mvc.model.ModelAndView;
import by.htp.eduard.ps.security.dto.AuthenticationDto;
import by.htp.eduard.ps.security.service.AuthenticationService;
import by.htp.eduard.ps.security.service.AuthenticationServiceProvider;
import by.htp.eduard.ps.service.AccountService;
import by.htp.eduard.ps.service.CardService;
import by.htp.eduard.ps.service.PayService;
import by.htp.eduard.ps.service.ServiceProvider;
import by.htp.eduard.ps.service.UserService;
import by.htp.eduard.ps.service.dto.AccountDto;
import by.htp.eduard.ps.service.dto.CardDto;
import by.htp.eduard.ps.service.dto.PayDto;
import by.htp.eduard.ps.service.dto.UserDto;
import by.htp.eduard.ps.utils.http.HttpUtils;

public class SupportCommands {
	
	private final AuthenticationService authenticationService;
	private final UserService userService;
	private final AccountService accountService;
	private final CardService cardService;
	private final PayService payService;

	public SupportCommands() {
		authenticationService = AuthenticationServiceProvider.getInstance().getAuthenticationService();
		userService = ServiceProvider.getInstance().getUserService();
		accountService = ServiceProvider.getInstance().getAccountService();
		cardService = ServiceProvider.getInstance().getCardService();
		payService = ServiceProvider.getInstance().getPayService();
	}
	
	public ModelAndView registrationNewUser(HttpServletRequest request) {
		return new ModelAndView("/WEB-INF/pages/users/registration-new-user.jsp");
	}
	
	public ModelAndView saveNewUser(HttpServletRequest request) {
		Set<String> validationErrors = new HashSet<>();
		HttpSession session = request.getSession();	
		
		if(!request.getParameter("password").equals(request.getParameter("password2"))) {
			validationErrors.add("password.match");
		}
		
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
			ModelAndView modelAndView = new ModelAndView("/WEB-INF/pages/users/registration-new-user.jsp");
			modelAndView.addAllValidationError(validationErrors);
			
			modelAndView.addViewData("user", user);
			return modelAndView;
		}
		
		userService.saveUser(user);
		
		session.setAttribute("successRegistr", true);
		
		ServletContext context = request.getServletContext();
		String contextPath = context.getContextPath();
		ModelAndView modelAndView = new ModelAndView("redirect:" + contextPath);
		
		return modelAndView;
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
		
		ModelAndView modelAndView = new ModelAndView("/WEB-INF/pages/authentication/forget-password.jsp");		
		modelAndView.addViewData("user", user);
		
		return modelAndView;
	}
	
	public ModelAndView home(HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserDto user = (UserDto)session.getAttribute("authentication");
		
		ModelAndView modelAndView = new ModelAndView("/WEB-INF/pages/users/user-edit.jsp");
		
		List<CardDto> cards = cardService.getCardByIdUser(user.getId());
		modelAndView.addViewData("allCards", cards);
		
		List<AccountDto> allAccounts = accountService.getAccountByIdUser(user.getId());
		modelAndView.addViewData("allAccounts", allAccounts);
		
		List<PayDto> allPayments = payService.getPayByIdUser(user.getId());
		modelAndView.addViewData("allPayments", allPayments);
		
		return modelAndView;
	}
}
