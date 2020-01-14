package by.htp.eduard.service.impl;

import java.util.List;

import by.htp.eduard.dao.PaymentSystemDao;
import by.htp.eduard.dao.mysql.provider.DaoProvider;
import by.htp.eduard.entities.PaymentSystem;
import by.htp.eduard.service.PaymentSystemService;

public class PaymentSystemServiceImpl implements PaymentSystemService {
	
	private final PaymentSystemDao paymentSystemDao;
	
	

	public PaymentSystemServiceImpl() {
		paymentSystemDao = DaoProvider.getInstance().getPaymentSystemDao();
	}
	
	@Override
	public List<PaymentSystem> getAllPaymentSystems() {
		return paymentSystemDao.getAllPaymentSystems();
	}

	@Override
	public PaymentSystem savePaymentSystem(PaymentSystem paymentSystem) {
		if(paymentSystem.getId() == null) {
			return paymentSystemDao.savePaymentSystem(paymentSystem);
		}
		
		return paymentSystemDao.updatePaymentSystem(paymentSystem);
	}

	@Override
	public PaymentSystem getPaymentSystemById(Integer id) {
		return paymentSystemDao.getPaymentSystemById(id);
	}

	@Override
	public void deletePaymentSystem(Integer id) {
		paymentSystemDao.deletePaymentSystem(id);
	}

}
