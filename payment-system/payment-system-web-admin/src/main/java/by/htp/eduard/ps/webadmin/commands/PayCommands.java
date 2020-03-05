package by.htp.eduard.ps.webadmin.commands;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import by.htp.eduard.ps.mvc.model.ModelAndView;
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
	
	public ModelAndView showAllPayments(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("/WEB-INF/pages/pay/pay-list.jsp");
		List<PayDto> allPayments = payService.getAllPay();
		modelAndView.addViewData("allPayments", allPayments);
		
		return modelAndView;
	}
	
	public ModelAndView createPay(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("/WEB-INF/pages/pay/pay-details.jsp");
		Integer id = HttpUtils.getIntParam("cardId", request);
		CardDto card = cardService.getCardById(id);
		modelAndView.addViewData("card", card);
		
		return modelAndView;
	}
	
	public ModelAndView savePay(HttpServletRequest request) {
		Set<String> validationErrors = new HashSet<>();
		Integer id = HttpUtils.getIntParam("id", request);
		Double price = HttpUtils.getDoubleParam("price", request);
		if(price == null) {
			validationErrors.add("amount.empty");
		}
		
		Integer idAccount = HttpUtils.getIntParam("idAccount", request);
		String description = request.getParameter("description");
		
		if(!validationErrors.isEmpty()) {
			ModelAndView modelAndView = new ModelAndView("/WEB-INF/pages/pay/new-pay.jsp");
			modelAndView.addAllValidationError(validationErrors);
			List<AccountDto> allAccounts = accountService.getAllAccounts();
			modelAndView.addViewData("allAccounts", allAccounts);
			return modelAndView;
		}
		
		PayDto pay = new PayDto();
		pay.setId(id);
		pay.setDate(new Date());
		pay.setPrice(price);
		pay.setIdAccount(idAccount);
		pay.setDescription(description);
		
		try {
			payService.savePay(pay);
		} catch (NegativeBalanceException e) {
			ModelAndView modelAndView = new ModelAndView("/WEB-INF/pages/pay/new-pay.jsp");
			List<AccountDto> allAccounts = accountService.getAllAccounts();
			modelAndView.addViewData("allAccounts", allAccounts);
			
			validationErrors.add("pay.balance.negative");
			modelAndView.addAllValidationError(validationErrors);
			
			return modelAndView;
		} catch (NegativeAmountException e) {
			ModelAndView modelAndView = new ModelAndView("/WEB-INF/pages/pay/new-pay.jsp");
			List<AccountDto> allAccounts = accountService.getAllAccounts();
			modelAndView.addViewData("allAccounts", allAccounts);
			
			validationErrors.add("pay.amount.negative");
			modelAndView.addAllValidationError(validationErrors);
			
			return modelAndView;
		}
		
		ModelAndView modelAndView = new ModelAndView("redirect:payments-list");
		
		return modelAndView;
	}
	
	public ModelAndView newPay(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("/WEB-INF/pages/pay/new-pay.jsp");
		List<AccountDto> allAccounts = accountService.getAllAccounts();
		modelAndView.addViewData("allAccounts", allAccounts);
		
		return modelAndView;
	}
	
	public ModelAndView deletePay(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("redirect:payments-list");
		Integer id = HttpUtils.getIntParam("payId", request);
		payService.deletePay(id);
		
		return modelAndView;
	}
}
