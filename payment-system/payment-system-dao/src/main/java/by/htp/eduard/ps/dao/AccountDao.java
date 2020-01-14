package by.htp.eduard.ps.dao;

import java.util.List;

import by.htp.eduard.ps.dao.entities.Account;

public interface AccountDao {
	
	List<Account> getAllAccounts();
	List<Account> getAccountByIdUser(Integer id);
	Account getAccountById(Integer id);
	Account saveAccount(Account account);
	Account updateAccount(Account account);
	Account lockUnlockAccount(Account account);
	Account updateBalanceAccount(Account account);
	void deleteAccount(Integer id);

}
