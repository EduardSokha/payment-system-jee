package by.htp.eduard.ps.service;

import java.util.List;

import by.htp.eduard.entities.PaymentSystem;

public interface PaymentSystemService {
	
	List<PaymentSystem> getAllPaymentSystems();
	PaymentSystem getPaymentSystemById(Integer id);
	PaymentSystem savePaymentSystem(PaymentSystem paymentSystem);
	void deletePaymentSystem(Integer id);
}
