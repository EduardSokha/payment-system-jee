package by.htp.eduard.ps.service.converters;

import org.dozer.DozerConverter;

import by.htp.eduard.ps.dao.AccountDao;
import by.htp.eduard.ps.dao.entities.Account;
import by.htp.eduard.ps.dao.mysql.provider.DaoProvider;

public class AccountBalanceConverter extends DozerConverter<Integer, String> {
	
	private final AccountDao accountDao;

	public AccountBalanceConverter() {
		super(Integer.class, String.class);
		accountDao = DaoProvider.getInstance().getAccountDao();		
	}

	@Override
	public String convertTo(Integer source, String destination) {
		Account account = accountDao.getAccountById(source);
		return account.getBalance()+"";
	}

	@Override
	public Integer convertFrom(String source, Integer destination) {
		throw new UnsupportedOperationException();
	}

}
