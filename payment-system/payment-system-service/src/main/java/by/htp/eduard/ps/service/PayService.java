package by.htp.eduard.ps.service;

import java.util.List;

import by.htp.eduard.ps.service.dto.PayDto;
import by.htp.eduard.ps.service.exceptions.NegativeBalanceException;

public interface PayService {
	
	List<PayDto> getAllPay();
	List<PayDto> getPayByIdAccount(Integer id);
	List<PayDto> getPayByIdUser(Integer id);
	PayDto getPayById(Integer id);
	PayDto savePay(PayDto payDto) throws NegativeBalanceException;
	void deletePay(Integer id);

}
