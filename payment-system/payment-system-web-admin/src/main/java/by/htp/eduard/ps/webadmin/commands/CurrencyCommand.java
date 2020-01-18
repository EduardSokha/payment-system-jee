package by.htp.eduard.ps.webadmin.commands;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.htp.eduard.ps.service.CurrencyService;
import by.htp.eduard.ps.service.ServiceProvider;
import by.htp.eduard.ps.service.dto.CurrencyDto;
import by.htp.eduard.ps.utils.http.HttpUtils;

public class CurrencyCommand {
	
	private final CurrencyService currencyService;

	public CurrencyCommand() {
		currencyService = ServiceProvider.getInstance().getCurrencyService();
	}
	
	public String showAllCurrencies(HttpServletRequest request) {
		List<CurrencyDto> allCurrencies = currencyService.getAllCurrencies();
		request.setAttribute("allCurrencies", allCurrencies);
		
		return "/WEB-INF/pages/currencies/currencies-list.jsp";
	}
	
	public String addCurrency(HttpServletRequest request) {		
		return "/WEB-INF/pages/currencies/currency-details.jsp";
	}
	
	public String saveCurrency(HttpServletRequest request) {
		Integer id = HttpUtils.getIntParam("id", request);
		String name = request.getParameter("newCurrency");
		
		CurrencyDto currency = new CurrencyDto();
		currency.setId(id);
		currency.setCurrencyName(name);
		
		currencyService.saveCurrency(currency);
		
		return "redirect:currency-list";
	}

	public String editCurrency(HttpServletRequest request) {
		Integer id = HttpUtils.getIntParam("currencyId", request);
		CurrencyDto currency = currencyService.getNameCurrencyById(id);
		request.setAttribute("currency", currency);
		return "/WEB-INF/pages/currencies/currency-details.jsp";
	}
	
	public String deleteCurrency(HttpServletRequest request) {
		Integer id = HttpUtils.getIntParam("currencyId", request);
		currencyService.deleteCurrency(id);
		return "redirect:currency-list";
	}
}
