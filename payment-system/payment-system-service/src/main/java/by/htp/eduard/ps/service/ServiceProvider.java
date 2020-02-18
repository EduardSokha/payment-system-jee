package by.htp.eduard.ps.service;

import by.htp.eduard.ps.service.impl.AccountServiceImpl;
import by.htp.eduard.ps.service.impl.CardServiceImpl;
import by.htp.eduard.ps.service.impl.CurrencyServiceImpl;
import by.htp.eduard.ps.service.impl.DozerEntityDtoConverter;
import by.htp.eduard.ps.service.impl.NameCardServiceImpl;
import by.htp.eduard.ps.service.impl.PayServiceImpl;
import by.htp.eduard.ps.service.impl.PaymentSystemServiceImpl;
import by.htp.eduard.ps.service.impl.RoleServiceImpl;
import by.htp.eduard.ps.service.impl.StatusServiceImpl;
import by.htp.eduard.ps.service.impl.UserServiceImpl;

public class ServiceProvider {
	
private static final ServiceProvider instance = new ServiceProvider();
	
	private ServiceProvider() {}
	
	private UserService userService = null;
	
	private PaymentSystemService paymentSystemService = null;
	
	private NameCardService nameCardService = null;
	
	private CurrencyService currencyService = null;

	private RoleService roleService = null;

	private CardService cardService = null;

	private AccountService accountService = null;

	private PayService payService = null;
	
	private StatusService statusService = null;
	
	private EntityDtoConverter entityDtoConverter = null;
	
	public static ServiceProvider getInstance() {
		return instance;
	}

	public UserService getUserService() {
		if(userService == null) {
			userService = new UserServiceImpl();
		}
		
		return userService;
	}

	public PaymentSystemService getPaymentSystemService() {
		if(paymentSystemService == null) {
			paymentSystemService = new PaymentSystemServiceImpl();
		}
		
		return paymentSystemService;
	}

	public NameCardService getNameCardService() {
		if(nameCardService == null) {
			nameCardService = new NameCardServiceImpl();
		}
		
		return nameCardService;
	}

	public CurrencyService getCurrencyService() {
		if(currencyService == null) {
			currencyService = new CurrencyServiceImpl();
		}
		
		return currencyService;
	}

	public RoleService getRoleService() {
		if(roleService == null) {
			roleService = new RoleServiceImpl();
		}
		
		return roleService;
	}

	public CardService getCardService() {
		if(cardService == null) {
			cardService = new CardServiceImpl();
		}
		
		return cardService;
	}

	public AccountService getAccountService() {
		if(accountService == null) {
			accountService = new AccountServiceImpl();
		}
		
		return accountService;
	}

	public PayService getPayService() {
		if(payService == null) {
			payService = new PayServiceImpl();
		}
		
		return payService;
	}

	public StatusService getStatusService() {
		if(statusService == null) {
			statusService = new StatusServiceImpl();
		}
		
		return statusService;
	}

	public EntityDtoConverter getEntityDtoConverter() {
		if(entityDtoConverter == null) {
			entityDtoConverter = new DozerEntityDtoConverter();
		}
		
		return entityDtoConverter;
	}
}
