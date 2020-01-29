package by.htp.eduard.ps.webadmin.commands;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
	
	public String showAllAccounts(HttpServletRequest request) {
		List<AccountDto> allAccounts = accountService.getAllAccounts();
		request.setAttribute("allAccounts", allAccounts);
		
		return "/WEB-INF/pages/accounts/accounts-list.jsp";
	}
	
	public String addAccount(HttpServletRequest request) {	
		List<CurrencyDto> allCurrencies = currencyService.getAllCurrencies();
		request.setAttribute("allCurrencies", allCurrencies);
		
		List<UserDto> allUsers = userService.getAllUsers();
		request.setAttribute("allUsers", allUsers);
		
		return "/WEB-INF/pages/accounts/accounts-details.jsp";
	}
	
	public String saveAccount(HttpServletRequest request) {
		List<String> validationErrors = new ArrayList<>();
		Integer id = HttpUtils.getIntParam("id", request);
		Double balance = HttpUtils.getDoubleParam("balance", request);
		if(balance == null) {
			validationErrors.add("balance.empty");
		}
		Integer idUser = HttpUtils.getIntParam("idUser", request);
		Integer idStatus = HttpUtils.getIntParam("idStatus", request);
		Integer idCurrency = HttpUtils.getIntParam("idCurrency", request);
		
		if(!validationErrors.isEmpty()) {
			request.setAttribute("validationErrors", validationErrors);
			
			AccountDto account = accountService.getAccountById(id);
			request.setAttribute("account", account);
			
			List<StatusDto> allStatus = statusService.getAllStatus();
			request.setAttribute("allStatus", allStatus);
			
			return "/WEB-INF/pages/accounts/accounts-edit.jsp";
		}
		
		AccountDto account = new AccountDto();
		account.setId(id);
		account.setBalance(balance);
		account.setDate(new Date());
		account.setIdUser(idUser);
		account.setIdStatus(idStatus);
		account.setIdCurrency(idCurrency);
		
		accountService.saveAccount(account);
		
		return "redirect:accounts-list";
	}

	public String editAccount(HttpServletRequest request) {
		Integer id = HttpUtils.getIntParam("accountId", request);
		AccountDto account = accountService.getAccountById(id);
		request.setAttribute("account", account);
		
		List<StatusDto> allStatus = statusService.getAllStatus();
		request.setAttribute("allStatus", allStatus);
		
		return "/WEB-INF/pages/accounts/accounts-edit.jsp";
	}
	
	public String deleteAccount(HttpServletRequest request) {
		Integer id = HttpUtils.getIntParam("accountId", request);
		accountService.deleteAccount(id);
		return "redirect:accounts-list";
	}
	
	public String lockUnlockAccount(HttpServletRequest request) {
		Integer id = HttpUtils.getIntParam("accountId", request);
		Integer idStatus = HttpUtils.getIntParam("idStatus", request);
		
		AccountDto account = new AccountDto();		
		account.setId(id);
		account.setIdStatus(idStatus);
		accountService.lockUnlockAccount(account);
		return "redirect:accounts-list";
	}
	
	public String addCard(HttpServletRequest request) {
		
		Integer id = HttpUtils.getIntParam("accountId", request);
		AccountDto account = accountService.getAccountById(id);
		request.setAttribute("account", account);
		
		List<NameCardDto> allTradeNamesCards = nameCardService.getAllNamesCard();
		request.setAttribute("allTradeNamesCards", allTradeNamesCards);
		
		List<PaymentSystemDto> allPaymentSystems = paymentSystemService.getAllPaymentSystems();
		request.setAttribute("allPaymentSystems", allPaymentSystems);
		
		List<AccountDto> allAccounts = accountService.getAllAccounts();
		request.setAttribute("allAccounts", allAccounts);
		
		return "/WEB-INF/pages/cards/card-details.jsp";
	}
	
}
