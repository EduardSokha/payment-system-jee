package by.htp.eduard.ps.web.commands;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.htp.eduard.ps.mvc.model.ModelAndView;
import by.htp.eduard.ps.service.AccountService;
import by.htp.eduard.ps.service.ServiceProvider;
import by.htp.eduard.ps.service.dto.AccountDto;
import by.htp.eduard.ps.service.dto.UserDto;

public class AccountCommands {
	
	private final AccountService accountService;

	public AccountCommands() {
		accountService = ServiceProvider.getInstance().getAccountService();
	}
	
	public ModelAndView showAllAccounts(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("/WEB-INF/pages/accounts/accounts-list.jsp");
		HttpSession session = request.getSession();	
		UserDto userDto = (UserDto)session.getAttribute("authentication");
		List<AccountDto> allAccounts = accountService.getAccountByIdUser(userDto.getId());
		modelAndView.addViewData("allAccounts", allAccounts);
		
		return modelAndView;
	}
}
