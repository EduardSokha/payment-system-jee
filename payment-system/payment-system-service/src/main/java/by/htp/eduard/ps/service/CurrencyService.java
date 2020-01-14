package by.htp.eduard.ps.service;

import java.util.List;

import by.htp.eduard.ps.service.dto.CurrencyDto;

public interface CurrencyService {
	List<CurrencyDto> getAllCurrencies();
	CurrencyDto getNameCurrencyById(Integer id);
	CurrencyDto saveCurrency(CurrencyDto currencyDto);
	void deleteCurrency(Integer id);
}
