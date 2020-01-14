package by.htp.eduard.service.impl;

import java.util.List;

import by.htp.eduard.dao.AccountDao;
import by.htp.eduard.dao.PayDao;
import by.htp.eduard.dao.mysql.provider.DaoProvider;
import by.htp.eduard.entities.Account;
import by.htp.eduard.entities.Pay;
import by.htp.eduard.service.PayService;
import by.htp.eduard.service.exceptions.NegativeBalanceException;

public class PayServiceImpl implements PayService {
	
	private final PayDao payDao;
	private final AccountDao accountDao;

	public PayServiceImpl() {
		payDao = DaoProvider.getInstance().getPayDao();
		accountDao = DaoProvider.getInstance().getAccountDao();
	}

	@Override
	public List<Pay> getAllPay() {
		return payDao.getAllPay();
	}

	@Override
	public Pay getPayById(Integer id) {
		return payDao.getPayById(id);
	}

	@Override
	public Pay savePay(Pay pay) throws NegativeBalanceException {
		Account account = accountDao.getAccountById(pay.getIdAccount());
		Double newBalance = account.getBalance() - pay.getPrice();
		if(newBalance<0) {
			throw new NegativeBalanceException();
		}
		
		account.setBalance(newBalance);
		accountDao.updateBalanceAccount(account);
		return payDao.savePay(pay);
	}

	@Override
	public void deletePay(Integer id) {
		payDao.deletePay(id);
	}

}
