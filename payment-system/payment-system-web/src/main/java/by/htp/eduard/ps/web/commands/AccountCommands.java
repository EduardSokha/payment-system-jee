package by.htp.eduard.ps.web.commands;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.htp.eduard.ps.mvc.model.ModelAndView;
import by.htp.eduard.ps.service.AccountService;
import by.htp.eduard.ps.service.CurrencyService;
import by.htp.eduard.ps.service.NameCardService;
import by.htp.eduard.ps.service.PaymentSystemService;
import by.htp.eduard.ps.service.ServiceProvider;
import by.htp.eduard.ps.service.StatusService;
import by.htp.eduard.ps.service.UserService;
import by.htp.eduard.ps.service.dto.AccountDto;
import by.htp.eduard.ps.service.dto.CurrencyDto;
import by.htp.eduard.ps.service.dto.NameCardDto;
import by.htp.eduard.ps.service.dto.PaymentSystemDto;
import by.htp.eduard.ps.service.dto.StatusDto;
import by.htp.eduard.ps.service.dto.UserDto;
import by.htp.eduard.ps.utils.http.HttpUtils;

public class AccountCommands {
	
	private final AccountService accountService;
	private final CurrencyService currencyService;
	private final NameCardService nameCardService;
	private final PaymentSystemService paymentSystemService;
	private final UserService userService;
	private final StatusService statusService;

	public AccountCommands() {
		accountService = ServiceProvider.getInstance().getAccountService();
		currencyService = ServiceProvider.getInstance().getCurrencyService();
		nameCardService = ServiceProvider.getInstance().getNameCardService();
		paymentSystemService = ServiceProvider.getInstance().getPaymentSystemService();
		userService = ServiceProvider.getInstance().getUserService();
		statusService = ServiceProvider.getInstance().getStatusService();
	}
	
	public ModelAndView showAllAccounts(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("/WEB-INF/pages/accounts/accounts-list.jsp");
		HttpSession session = request.getSession();	
		UserDto userDto = (UserDto)session.getAttribute("authentication");
		List<AccountDto> allAccounts = accountService.getAccountByIdUser(userDto.getId());
		modelAndView.addViewData("allAccounts", allAccounts);
		
		return modelAndView;
	}
	
	public ModelAndView addAccount(HttpServletRequest request) {	
		ModelAndView modelAndView = new ModelAndView("/WEB-INF/pages/accounts/accounts-details.jsp");
		List<CurrencyDto> allCurrencies = currencyService.getAllCurrencies();
		modelAndView.addViewData("allCurrencies", allCurrencies);
		
		List<UserDto> allUsers = userService.getAllUsers();
		modelAndView.addViewData("allUsers", allUsers);
		
		return modelAndView;
	}
	
	public ModelAndView saveAccount(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("redirect:accounts-list");
		Integer id = HttpUtils.getIntParam("id", request);
		Double balance = HttpUtils.getDoubleParam("balance", request);
		Integer idUser = HttpUtils.getIntParam("idUser", request);
		Integer idStatus = HttpUtils.getIntParam("idStatus", request);
		Integer idCurrency = HttpUtils.getIntParam("idCurrency", request);
		
		AccountDto account = new AccountDto();
		account.setId(id);
		account.setBalance(balance);
		account.setDate(new Date());
		account.setIdUser(idUser);
		account.setIdStatus(idStatus);
		account.setIdCurrency(idCurrency);
		
		accountService.saveAccount(account);
		
		return modelAndView;
	}

	public ModelAndView editAccount(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("/WEB-INF/pages/accounts/accounts-edit.jsp");
		Integer id = HttpUtils.getIntParam("accountId", request);
		AccountDto account = accountService.getAccountById(id);
		modelAndView.addViewData("account", account);
		
		List<StatusDto> allStatus = statusService.getAllStatus();
		modelAndView.addViewData("allStatus", allStatus);
		
		return modelAndView;
	}
	
	public ModelAndView deleteAccount(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("redirect:accounts-list");
		Integer id = HttpUtils.getIntParam("accountId", request);
		accountService.deleteAccount(id);
		return modelAndView;
	}
	
	public ModelAndView lockUnlockAccount(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("redirect:accounts-list");
		Integer id = HttpUtils.getIntParam("accountId", request);
		Integer idStatus = HttpUtils.getIntParam("idStatus", request);
		
		AccountDto account = new AccountDto();		
		account.setId(id);
		account.setIdStatus(idStatus);
		accountService.lockUnlockAccount(account);
		
		return modelAndView;
	}
	
	public ModelAndView addCard(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("/WEB-INF/pages/cards/card-details.jsp");
		Integer id = HttpUtils.getIntParam("accountId", request);
		
		AccountDto account = accountService.getAccountById(id);
		modelAndView.addViewData("account", account);
		
		List<NameCardDto> allTradeNamesCards = nameCardService.getAllNamesCard();
		modelAndView.addViewData("allTradeNamesCards", allTradeNamesCards);
		
		List<PaymentSystemDto> allPaymentSystems = paymentSystemService.getAllPaymentSystems();
		modelAndView.addViewData("allPaymentSystems", allPaymentSystems);
		
		List<AccountDto> allAccounts = accountService.getAllAccounts();
		modelAndView.addViewData("allAccounts", allAccounts);
		
		return modelAndView;
	}
}
