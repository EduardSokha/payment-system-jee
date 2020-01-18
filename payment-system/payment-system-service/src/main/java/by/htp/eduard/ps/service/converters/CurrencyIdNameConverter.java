package by.htp.eduard.ps.service.converters;

import org.dozer.DozerConverter;

import by.htp.eduard.ps.dao.CurrencyDao;
import by.htp.eduard.ps.dao.entities.Currency;
import by.htp.eduard.ps.dao.mysql.provider.DaoProvider;

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
