package by.htp.eduard.ps.dao;

import java.util.List;

import by.htp.eduard.ps.dao.entities.PaymentSystem;

public interface PaymentSystemDao {
	
	List<PaymentSystem> getAllPaymentSystems();
	
	PaymentSystem getPaymentSystemById(Integer id);
	
	PaymentSystem savePaymentSystem(PaymentSystem paymentSystem);
	
	PaymentSystem updatePaymentSystem(PaymentSystem paymentSystem);
	
	void deletePaymentSystem(Integer id);
	

}
