package by.htp.eduard.service.impl;

import java.util.List;

import by.htp.eduard.dao.AccountDao;
import by.htp.eduard.dao.mysql.provider.DaoProvider;
import by.htp.eduard.dto.AccountDto;
import by.htp.eduard.entities.Account;
import by.htp.eduard.service.AccountService;
import by.htp.eduard.service.EntityDtoConverter;
import by.htp.eduard.service.ServiceProvider;

public class AccountServiceImpl implements AccountService {
	
	private final AccountDao accountDao;
	
	private EntityDtoConverter converter;

	public AccountServiceImpl() {
		accountDao = DaoProvider.getInstance().getAccountDao();
		converter = ServiceProvider.getInstance().getEntityDtoConverter();
	}

	@Override
	public List<AccountDto> getAllAccounts() {
		List<Account> allAccounts = accountDao.getAllAccounts();
		List<AccountDto> allDtos = converter.convertToDtoList(allAccounts, AccountDto.class);
		return allDtos;
	}
	
	@Override
	public List<AccountDto> getAccountByIdUser(Integer id) {
		List<Account> allAccounts = accountDao.getAccountByIdUser(id);
		List<AccountDto> allDtos = converter.convertToDtoList(allAccounts, AccountDto.class);
		return allDtos;
	}

	@Override
	public AccountDto getAccountById(Integer id) {
		Account accountById = accountDao.getAccountById(id);
		AccountDto dto = converter.convertToDto(accountById, AccountDto.class);
		return dto;
	}

	@Override
	public AccountDto saveAccount(AccountDto accountDto) {
		Account account = converter.convertToEntity(accountDto, Account.class);
		
		if(account.getId() == null) {
			account = accountDao.saveAccount(account);
			AccountDto dto = converter.convertToDto(account, AccountDto.class);
			return dto;
		}
		
		account= accountDao.updateAccount(account);
		AccountDto dto = converter.convertToDto(account, AccountDto.class);
		return dto;
	}

	@Override
	public AccountDto lockUnlockAccount(AccountDto accountDto) {
		Account account = converter.convertToEntity(accountDto, Account.class);
		account = accountDao.lockUnlockAccount(account);
		AccountDto dto = converter.convertToDto(account, AccountDto.class);
		return dto;
	}
	
	@Override
	public AccountDto updateBalanceAccount(AccountDto accountDto) {
		Account account = converter.convertToEntity(accountDto, Account.class);
		account = accountDao.updateBalanceAccount(account);
		AccountDto dto = converter.convertToDto(account, AccountDto.class);
		return dto;
	}

	@Override
	public void deleteAccount(Integer id) {
		accountDao.deleteAccount(id);
	}
}
