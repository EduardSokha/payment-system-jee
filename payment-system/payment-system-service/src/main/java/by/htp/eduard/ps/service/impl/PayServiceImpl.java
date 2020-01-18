package by.htp.eduard.ps.service.impl;

import java.util.List;

import by.htp.eduard.ps.dao.AccountDao;
import by.htp.eduard.ps.dao.PayDao;
import by.htp.eduard.ps.dao.entities.Account;
import by.htp.eduard.ps.dao.entities.Pay;
import by.htp.eduard.ps.dao.mysql.provider.DaoProvider;
import by.htp.eduard.ps.service.EntityDtoConverter;
import by.htp.eduard.ps.service.PayService;
import by.htp.eduard.ps.service.ServiceProvider;
import by.htp.eduard.ps.service.dto.PayDto;
import by.htp.eduard.ps.service.exceptions.NegativeBalanceException;

public class PayServiceImpl implements PayService {
	
	private final PayDao payDao;
	private final AccountDao accountDao;
	private EntityDtoConverter converter;

	public PayServiceImpl() {
		payDao = DaoProvider.getInstance().getPayDao();
		accountDao = DaoProvider.getInstance().getAccountDao();
		converter = ServiceProvider.getInstance().getEntityDtoConverter();
	}

	@Override
	public List<PayDto> getAllPay() {
		List<Pay> allPay = payDao.getAllPay();
		List<PayDto> dtoList = converter.convertToDtoList(allPay, PayDto.class);
		return dtoList;
	}

	@Override
	public PayDto getPayById(Integer id) {
		Pay pay = payDao.getPayById(id);
		PayDto payDto = converter.convertToDto(pay, PayDto.class);
		return payDto;
	}

	@Override
	public PayDto savePay(PayDto payDto) throws NegativeBalanceException {
		Pay pay = converter.convertToEntity(payDto, Pay.class);
		Account account = accountDao.getAccountById(pay.getIdAccount());
		Double newBalance = account.getBalance() - pay.getPrice();
		if(newBalance<0) {
			throw new NegativeBalanceException();
		}
		
		account.setBalance(newBalance);
		accountDao.updateBalanceAccount(account);
		
		pay = payDao.savePay(pay);
		PayDto dto = converter.convertToDto(pay, PayDto.class);
		return dto;
	}

	@Override
	public void deletePay(Integer id) {
		payDao.deletePay(id);
	}

}
