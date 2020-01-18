package by.htp.eduard.ps.service;

import java.util.List;

import by.htp.eduard.ps.service.dto.PaymentSystemDto;

public interface PaymentSystemService {
	
	List<PaymentSystemDto> getAllPaymentSystems();
	PaymentSystemDto getPaymentSystemById(Integer id);
	PaymentSystemDto savePaymentSystem(PaymentSystemDto paymentSystemDto);
	void deletePaymentSystem(Integer id);
	
}
