package by.htp.eduard.ps.webadmin.commands;


import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.htp.eduard.ps.mvc.model.ModelAndView;
import by.htp.eduard.ps.service.AccountService;
import by.htp.eduard.ps.service.CardService;
import by.htp.eduard.ps.service.CurrencyService;
import by.htp.eduard.ps.service.NameCardService;
import by.htp.eduard.ps.service.PaymentSystemService;
import by.htp.eduard.ps.service.ServiceProvider;
import by.htp.eduard.ps.service.UserService;
import by.htp.eduard.ps.service.dto.AccountDto;
import by.htp.eduard.ps.service.dto.CardDto;
import by.htp.eduard.ps.service.dto.CurrencyDto;
import by.htp.eduard.ps.service.dto.NameCardDto;
import by.htp.eduard.ps.service.dto.PaymentSystemDto;
import by.htp.eduard.ps.service.dto.UserDto;
import by.htp.eduard.ps.utils.http.HttpUtils;

public class CardCommands {
	
	private final CardService cardService;
	private final NameCardService nameCardService;
	private final PaymentSystemService paymentSystemService;
	private final AccountService accountService;
	private final UserService userService;
	private final CurrencyService currencyService;

	public CardCommands() {
		cardService = ServiceProvider.getInstance().getCardService();
		nameCardService = ServiceProvider.getInstance().getNameCardService();
		paymentSystemService = ServiceProvider.getInstance().getPaymentSystemService();
		accountService = ServiceProvider.getInstance().getAccountService();
		currencyService = ServiceProvider.getInstance().getCurrencyService();
		userService = ServiceProvider.getInstance().getUserService();
	}
	
	public ModelAndView showAllCards(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("/WEB-INF/pages/cards/cards-list.jsp");
		List<CardDto> cards = cardService.getAllCards();
		modelAndView.addViewData("allCards", cards);
		
		return modelAndView;
	}
	
	public ModelAndView addCard(HttpServletRequest request) {	
		ModelAndView modelAndView = new ModelAndView("/WEB-INF/pages/cards/card-details.jsp");
		List<NameCardDto> allTradeNamesCards = nameCardService.getAllNamesCard();
		modelAndView.addViewData("allTradeNamesCards", allTradeNamesCards);
		
		List<PaymentSystemDto> allPaymentSystems = paymentSystemService.getAllPaymentSystems();
		modelAndView.addViewData("allPaymentSystems", allPaymentSystems);
		
		List<AccountDto> allAccounts = accountService.getAllAccounts();
		modelAndView.addViewData("allAccounts", allAccounts);
		
		return modelAndView;
	}
	
	public ModelAndView saveCard(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("redirect:cards-list");
		Integer idAccount = HttpUtils.getIntParam("idAccount", request);
		Integer idPaymentSystem = HttpUtils.getIntParam("idPaymentSystem", request);
		Integer idTradeNameCard = HttpUtils.getIntParam("idTradeNameCard", request);
		
		CardDto card = new CardDto();
		card.setDate(new Date());	
		card.setIdAccount(idAccount);
		card.setIdPaymentSystem(idPaymentSystem);
		card.setIdTradeNameCard(idTradeNameCard);
		
		cardService.saveCard(card);
		
		return modelAndView;
	}

	public ModelAndView editCard(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("redirect:cards-list");
		Integer id = HttpUtils.getIntParam("cardId", request);
		
		CardDto card = new CardDto();		
		card.setId(id);
		cardService.saveCard(card);
		return modelAndView;
	}
	
	public ModelAndView deleteCard(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("redirect:cards-list");
		Integer id = HttpUtils.getIntParam("cardId", request);
		cardService.deleteCard(id);
		return modelAndView;
	}
	
	public ModelAndView addCardAndAccount(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("/WEB-INF/pages/cards/add-card-account.jsp");
		List<NameCardDto> allTradeNamesCards = nameCardService.getAllNamesCard();
		modelAndView.addViewData("allTradeNamesCards", allTradeNamesCards);
		
		List<PaymentSystemDto> allPaymentSystems = paymentSystemService.getAllPaymentSystems();
		modelAndView.addViewData("allPaymentSystems", allPaymentSystems);
		
		List<AccountDto> allAccounts = accountService.getAllAccounts();
		modelAndView.addViewData("allAccounts", allAccounts);
		
		List<CurrencyDto> allCurrencies = currencyService.getAllCurrencies();
		modelAndView.addViewData("allCurrencies", allCurrencies);
		
		List<UserDto> allUsers = userService.getAllUsers();
		modelAndView.addViewData("allUsers", allUsers);
		
		return modelAndView;
	}
	
	public ModelAndView saveCardAndAccount(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("redirect:cards-list");
		Integer idAccount = HttpUtils.getIntParam("idAccount", request);
		Integer idPaymentSystem = HttpUtils.getIntParam("idPaymentSystem", request);
		Integer idTradeNameCard = HttpUtils.getIntParam("idTradeNameCard", request);
		
		Integer id = HttpUtils.getIntParam("id", request);
		Double balance = HttpUtils.getDoubleParam("balance", request);
		Integer idUser = HttpUtils.getIntParam("idUser", request);
		Integer idStatus = HttpUtils.getIntParam("idStatus", request);
		Integer idCurrency = HttpUtils.getIntParam("idCurrency", request);
		
		CardDto card = new CardDto();
		card.setDate(new Date());	
		card.setIdAccount(idAccount);
		card.setIdPaymentSystem(idPaymentSystem);
		card.setIdTradeNameCard(idTradeNameCard);
		
		AccountDto account = new AccountDto();
		account.setId(id);
		account.setBalance(balance);
		account.setDate(new Date());
		account.setIdUser(idUser);
		account.setIdStatus(idStatus);
		account.setIdCurrency(idCurrency);
		
		cardService.createAccountAndCard(account, card);
		
		return modelAndView;
	}
}
