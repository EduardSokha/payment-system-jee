package by.htp.eduard.service.converters;

import org.dozer.DozerConverter;

import by.htp.eduard.dao.PaymentSystemDao;
import by.htp.eduard.dao.mysql.provider.DaoProvider;
import by.htp.eduard.entities.PaymentSystem;

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
