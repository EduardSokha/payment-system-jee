package by.htp.eduard.ps.service;

import java.util.List;

import by.htp.eduard.entities.Pay;
import by.htp.eduard.ps.service.exceptions.NegativeBalanceException;

public interface PayService {
	
	List<Pay> getAllPay();
	Pay getPayById(Integer id);
	Pay savePay(Pay pay) throws NegativeBalanceException;
	void deletePay(Integer id);

}
