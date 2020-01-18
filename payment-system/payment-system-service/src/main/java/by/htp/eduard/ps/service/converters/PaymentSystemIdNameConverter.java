package by.htp.eduard.ps.service.converters;

import org.dozer.DozerConverter;

import by.htp.eduard.ps.dao.PaymentSystemDao;
import by.htp.eduard.ps.dao.entities.PaymentSystem;
import by.htp.eduard.ps.dao.mysql.provider.DaoProvider;

public class PaymentSystemIdNameConverter extends DozerConverter<Integer, String> {

	private final PaymentSystemDao paymentSystemDao;
	
	public PaymentSystemIdNameConverter() {
		super(Integer.class, String.class);
		paymentSystemDao = DaoProvider.getInstance().getPaymentSystemDao();
	}

	@Override
	public String convertTo(Integer source, String destination) {
		PaymentSystem paymentSystem = paymentSystemDao.getPaymentSystemById(source);
		return paymentSystem.getName();
	}

	@Override
	public Integer convertFrom(String source, Integer destination) {
		throw new UnsupportedOperationException();
	}

}
