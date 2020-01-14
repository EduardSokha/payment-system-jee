package by.htp.eduard.service.converters;

import org.dozer.DozerConverter;

import by.htp.eduard.dao.CurrencyDao;
import by.htp.eduard.dao.mysql.provider.DaoProvider;
import by.htp.eduard.entities.Currency;

public class CurrencyIdNameConverter extends DozerConverter<Integer, String> {

	private final CurrencyDao currencyDao;
	
	public CurrencyIdNameConverter() {
		super(Integer.class, String.class);
		currencyDao = DaoProvider.getInstance().getCurrencyDao();
	}

	@Override
	public String convertTo(Integer source, String destination) {
		Currency currency = currencyDao.getNameCurrencyById(source);
		return currency.getName();
	}

	@Override
	public Integer convertFrom(String source, Integer destination) {
		throw new UnsupportedOperationException();
	}
}
