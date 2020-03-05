package by.htp.eduard.ps.web.commands;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	
	public String authorization(HttpServletRequest request) {
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		AuthenticationDto authentication = new AuthenticationDto();
		authentication.setLogin(login);
		authentication.setPassword(password);
		
		UserDto user = authenticationService.signIn(authentication);
		if(user==null) {
			ServletContext context = request.getServletContext();
			String contextPath = context.getContextPath();
			return "redirect:" + contextPath;
		}
		request.setAttribute("user", user);
		
		List<CardDto> cards = cardService.getCardByIdUser(user.getId());
		request.setAttribute("allCards", cards);
		
		List<AccountDto> allAccounts = accountService.getAccountByIdUser(user.getId());
		request.setAttribute("allAccounts", allAccounts);
		
		List<PayDto> allPayments = payService.getPayByIdUser(user.getId());
		request.setAttribute("allPayments", allPayments);
		
		return "/WEB-INF/pages/users/user-edit.jsp";
	}
	
	public ModelAndView registrationNewUser(HttpServletRequest request) {
		return new ModelAndView("/WEB-INF/pages/users/registration-new-user.jsp");
	}
	
	public String saveNewUser(HttpServletRequest request) {
		HttpSession session = request.getSession();	
		if(!request.getParameter("password").equals(request.getParameter("password2"))) {
			String response = "Password1 And Password2 Don't Match!";
			session.setAttribute("response", response);
			return "redirect:registration";
		}
		
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
		
		userService.saveUser(user);
		
		String response = "Registration Was Successful";
		session.setAttribute("successRegistr", response);
		ServletContext context = request.getServletContext();
		String contextPath = context.getContextPath();
		
		return "redirect:" + contextPath;
	}
	
	public ModelAndView forgetPassword(HttpServletRequest request) {
		return new ModelAndView("/WEB-INF/pages/authentication/forget-password.jsp");
	}

	public ModelAndView getForgetPassword(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("/WEB-INF/pages/authentication/forget-password.jsp");
		
		String passportSeries = request.getParameter("passportSeries");
		String passportId = request.getParameter("passportId");
		String codeWord = request.getParameter("codeWord");
		
		AuthenticationDto authentication = new AuthenticationDto();
		authentication.setPassportSeries(passportSeries);
		authentication.setPassportId(passportId);
		authentication.setCodeWord(codeWord);
		
		UserDto user = authenticationService.forgetPassword(authentication);
		modelAndView.addViewData("user", user);
		
		return modelAndView;
	}
}
