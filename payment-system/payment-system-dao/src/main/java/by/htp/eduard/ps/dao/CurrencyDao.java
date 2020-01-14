package by.htp.eduard.ps.dao;

import java.util.List;

import by.htp.eduard.ps.dao.entities.Currency;

public interface CurrencyDao {
	List<Currency> getAllCurrencies();
	Currency getNameCurrencyById(Integer id);
	Currency saveCurrency(Currency currency);
	Currency updateNameCurrency(Currency currency);
	void deleteCurrency(Integer id);
}
