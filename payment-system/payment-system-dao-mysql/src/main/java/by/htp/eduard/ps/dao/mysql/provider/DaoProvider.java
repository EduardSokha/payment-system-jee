package by.htp.eduard.ps.dao.mysql.provider;

import by.htp.eduard.ps.dao.AccountDao;
import by.htp.eduard.ps.dao.CardDao;
import by.htp.eduard.ps.dao.CurrencyDao;
import by.htp.eduard.ps.dao.NameCardDao;
import by.htp.eduard.ps.dao.PayDao;
import by.htp.eduard.ps.dao.PaymentSystemDao;
import by.htp.eduard.ps.dao.RoleDao;
import by.htp.eduard.ps.dao.StatusDao;
import by.htp.eduard.ps.dao.UserDao;
import by.htp.eduard.ps.dao.mysql.AccountDaoImpl;
import by.htp.eduard.ps.dao.mysql.CardDaoImpl;
import by.htp.eduard.ps.dao.mysql.CurrencyDaoImpl;
import by.htp.eduard.ps.dao.mysql.NameCardDaoImpl;
import by.htp.eduard.ps.dao.mysql.PayDaoImpl;
import by.htp.eduard.ps.dao.mysql.PaymentSystemDaoImpl;
import by.htp.eduard.ps.dao.mysql.RoleDaoImpl;
import by.htp.eduard.ps.dao.mysql.StatusDaoImpl;
import by.htp.eduard.ps.dao.mysql.UserDaoImpl;

public class DaoProvider {

	private static final DaoProvider instance = new DaoProvider();

	private UserDao userDao = new UserDaoImpl();
	
	private PaymentSystemDao paymentSystemDao = new PaymentSystemDaoImpl();
	
	private NameCardDao nameCardDao = new NameCardDaoImpl();
	
	private CurrencyDao currencyDao = new CurrencyDaoImpl();

	private RoleDao roleDao = new RoleDaoImpl();

	private CardDao cardDao = new CardDaoImpl();

	private AccountDao accountDao = new AccountDaoImpl();
	
	private PayDao payDao = new PayDaoImpl();
	
	private StatusDao statusDao = new StatusDaoImpl();

	private DaoProvider() {
	}

	public static DaoProvider getInstance() {
		return instance;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public PaymentSystemDao getPaymentSystemDao() {
		return paymentSystemDao;
	}

	public NameCardDao getNameCardDao() {
		return nameCardDao;
	}

	public CurrencyDao getCurrencyDao() {
		return currencyDao;
	}

	public RoleDao getRoleDao() {
		return roleDao;
	}

	public CardDao getCardDao() {
		return cardDao;
	}

	public AccountDao getAccountDao() {
		return accountDao;
	}

	public PayDao getPayDao() {
		return payDao;
	}

	public StatusDao getStatusDao() {
		return statusDao;
	}
}
