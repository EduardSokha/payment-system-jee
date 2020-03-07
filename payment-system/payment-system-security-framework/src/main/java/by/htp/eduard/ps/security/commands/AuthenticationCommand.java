package by.htp.eduard.ps.security.commands;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import by.htp.eduard.ps.mvc.model.ModelAndView;
import by.htp.eduard.ps.security.config.SecurityConfig;
import by.htp.eduard.ps.security.dto.AuthenticationDto;
import by.htp.eduard.ps.security.service.AuthenticationService;
import by.htp.eduard.ps.security.service.AuthenticationServiceProvider;
import by.htp.eduard.ps.service.AccountService;
import by.htp.eduard.ps.service.CardService;
import by.htp.eduard.ps.service.PayService;
import by.htp.eduard.ps.service.ServiceProvider;
import by.htp.eduard.ps.service.dto.AccountDto;
import by.htp.eduard.ps.service.dto.CardDto;
import by.htp.eduard.ps.service.dto.PayDto;
import by.htp.eduard.ps.service.dto.UserDto;

public class AuthenticationCommand {
	
	private final AuthenticationService authenticationService;
	private final AccountService accountService;
	private final CardService cardService;
	private final PayService payService;

	public AuthenticationCommand() {
		authenticationService = AuthenticationServiceProvider.getInstance().getAuthenticationService();
		accountService = ServiceProvider.getInstance().getAccountService();
		cardService = ServiceProvider.getInstance().getCardService();
		payService = ServiceProvider.getInstance().getPayService();
	}
	
	public ModelAndView signIn(HttpServletRequest request) {
		String viewName = SecurityConfig.getConfig().getLoginPage();
		ModelAndView modelAndView = new ModelAndView(viewName);
		return modelAndView;
	}
	
	public ModelAndView authentication(HttpServletRequest request) {
		Set<String> validationErrors = new HashSet<>();
		
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
			
			String viewName = SecurityConfig.getConfig().getLoginPage();
			ModelAndView modelAndView = new ModelAndView(viewName);
			modelAndView.addAllValidationError(validationErrors);
			modelAndView.addViewData("authentication", authentication);

			return modelAndView;
		}
		
		UserDto user = authenticationService.signIn(authentication);
		
		if(user == null) {
			String viewName = SecurityConfig.getConfig().getLoginPage();
			ModelAndView modelAndView = new ModelAndView(viewName);
			modelAndView.addValidationError("no.such.user");
			
			return modelAndView;
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("authentication", user);
		
		String redirectUrl = (String)session.getAttribute("user-required-url");
		session.removeAttribute("user-required-url");
		
		if(redirectUrl == null) {
			String viewName = "redirect:" + SecurityConfig.getConfig().getSuccsessLoginUrl();
			ModelAndView modelAndView = new ModelAndView(viewName);
			
			List<CardDto> cards = cardService.getCardByIdUser(user.getId());
			modelAndView.addViewData("allCards", cards);
			
			List<AccountDto> allAccounts = accountService.getAccountByIdUser(user.getId());
			modelAndView.addViewData("allAccounts", allAccounts);
			
			List<PayDto> allPayments = payService.getPayByIdUser(user.getId());
			modelAndView.addViewData("allPayments", allPayments);
			
			return modelAndView;
		}
		
		if(!redirectUrl.startsWith("redirect:")) {
			redirectUrl = "redirect:" + redirectUrl;
		}
		
		ModelAndView modelAndView = new ModelAndView(redirectUrl);
		
		List<CardDto> cards = cardService.getCardByIdUser(user.getId());
		modelAndView.addViewData("allCards", cards);
		
		List<AccountDto> allAccounts = accountService.getAccountByIdUser(user.getId());
		modelAndView.addViewData("allAccounts", allAccounts);
		
		List<PayDto> allPayments = payService.getPayByIdUser(user.getId());
		modelAndView.addViewData("allPayments", allPayments);
		
		return modelAndView;
	}
	
	public ModelAndView logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		
		ServletContext context = request.getServletContext();
		String contextPath = context.getContextPath();
		
		ModelAndView modelAndView = new ModelAndView("redirect:" + contextPath);
		return modelAndView;
	}
}
