package by.htp.eduard.ps.webadmin.commands;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.htp.eduard.ps.service.AccountService;
import by.htp.eduard.ps.service.CardService;
import by.htp.eduard.ps.service.PayService;
import by.htp.eduard.ps.service.ServiceProvider;
import by.htp.eduard.ps.service.dto.AccountDto;
import by.htp.eduard.ps.service.dto.CardDto;
import by.htp.eduard.ps.service.dto.PayDto;
import by.htp.eduard.ps.service.exceptions.NegativeAmountException;
import by.htp.eduard.ps.service.exceptions.NegativeBalanceException;
import by.htp.eduard.ps.utils.http.HttpUtils;

public class PayCommands {
	
	private final PayService payService;
	private final CardService cardService;	
	private final AccountService accountService;

	public PayCommands() {
		payService = ServiceProvider.getInstance().getPayService();
		cardService = ServiceProvider.getInstance().getCardService();
		accountService = ServiceProvider.getInstance().getAccountService();
	}
	
	public String showAllPayments(HttpServletRequest request) {
		List<PayDto> allPayments = payService.getAllPay();
		request.setAttribute("allPayments", allPayments);
		
		return "/WEB-INF/pages/pay/pay-list.jsp";
	}
	
	public String createPay(HttpServletRequest request) {
		Integer id = HttpUtils.getIntParam("cardId", request);
		
		CardDto card = cardService.getCardById(id);
		
		request.setAttribute("card", card);
		
		return "/WEB-INF/pages/pay/pay-details.jsp";
	}
	
	public String savePay(HttpServletRequest request) {
		Integer id = HttpUtils.getIntParam("id", request);
		Double price = HttpUtils.getDoubleParam("price", request);
		Integer idAccount = HttpUtils.getIntParam("idAccount", request);
		String description = request.getParameter("description");
		
		PayDto pay = new PayDto();
		pay.setId(id);
		pay.setDate(new Date());
		pay.setPrice(price);
		pay.setIdAccount(idAccount);
		pay.setDescription(description);
		
		try {
			payService.savePay(pay);
		} catch (NegativeBalanceException e) {
			List<AccountDto> allAccounts = accountService.getAllAccounts();
			request.setAttribute("allAccounts", allAccounts);
			
			List<String> validationErrors = new ArrayList<>();
			validationErrors.add("pay.balance.negative");
			request.setAttribute("validationErrors", validationErrors);
			return "/WEB-INF/pages/pay/new-pay.jsp";
		} catch (NegativeAmountException e) {
			List<AccountDto> allAccounts = accountService.getAllAccounts();
			request.setAttribute("allAccounts", allAccounts);
			
			List<String> validationErrors = new ArrayList<>();
			validationErrors.add("pay.amount.negative");
			request.setAttribute("validationErrors", validationErrors);
			return "/WEB-INF/pages/pay/new-pay.jsp";
		}
		
		return "redirect:payments-list";
	}
	
	public String newPay(HttpServletRequest request) {
		List<AccountDto> allAccounts = accountService.getAllAccounts();
		request.setAttribute("allAccounts", allAccounts);
		
		return "/WEB-INF/pages/pay/new-pay.jsp";
	}
	
	public String deletePay(HttpServletRequest request) {
		Integer id = HttpUtils.getIntParam("payId", request);
		payService.deletePay(id);
		return "redirect:payments-list";
	}

}
