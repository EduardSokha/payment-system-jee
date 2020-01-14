package by.htp.eduard.service.impl;

import java.util.List;

import by.htp.eduard.dao.CurrencyDao;
import by.htp.eduard.dao.mysql.provider.DaoProvider;
import by.htp.eduard.dto.CurrencyDto;
import by.htp.eduard.entities.Currency;
import by.htp.eduard.service.CurrencyService;
import by.htp.eduard.service.EntityDtoConverter;
import by.htp.eduard.service.ServiceProvider;

public class CurrencyServiceImpl implements CurrencyService {

	private final CurrencyDao currencyDao;
	
	private EntityDtoConverter converter;
	
	public CurrencyServiceImpl() {
		currencyDao = DaoProvider.getInstance().getCurrencyDao();
		converter = ServiceProvider.getInstance().getEntityDtoConverter();
	}

	@Override
	public List<CurrencyDto> getAllCurrencies() {
		List<Currency> allCurrencies = currencyDao.getAllCurrencies();
		List<CurrencyDto> dtoList = converter.convertToDtoList(allCurrencies, CurrencyDto.class);
		return dtoList;
	}

	@Override
	public CurrencyDto getNameCurrencyById(Integer id) {
		Currency currency = currencyDao.getNameCurrencyById(id);
		CurrencyDto dto = converter.convertToDto(currency, CurrencyDto.class);
		return dto;
	}

	@Override
	public CurrencyDto saveCurrency(CurrencyDto currencyDto) {
		Currency currency = converter.convertToEntity(currencyDto, Currency.class);
		if(currency.getId() == null) {
			currency = currencyDao.saveCurrency(currency);
			CurrencyDto dto = converter.convertToDto(currency, CurrencyDto.class);
			return dto;
		}
		
		currency = currencyDao.updateNameCurrency(currency);
		CurrencyDto dto = converter.convertToDto(currency, CurrencyDto.class);
		return dto;
	}

	@Override
	public void deleteCurrency(Integer id) {
		currencyDao.deleteCurrency(id);
	}
}
