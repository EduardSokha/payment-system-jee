package by.htp.eduard.ps.webadmin.commands;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import by.htp.eduard.ps.mvc.model.ModelAndView;
import by.htp.eduard.ps.service.CurrencyService;
import by.htp.eduard.ps.service.ServiceProvider;
import by.htp.eduard.ps.service.dto.CurrencyDto;
import by.htp.eduard.ps.utils.http.HttpUtils;

public class CurrencyCommand {
	
	private final CurrencyService currencyService;

	public CurrencyCommand() {
		currencyService = ServiceProvider.getInstance().getCurrencyService();
	}
	
	public ModelAndView showAllCurrencies(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("/WEB-INF/pages/currencies/currencies-list.jsp");
		List<CurrencyDto> allCurrencies = currencyService.getAllCurrencies();
		modelAndView.addViewData("allCurrencies", allCurrencies);
		return modelAndView;
	}
	
	public ModelAndView addCurrency(HttpServletRequest request) {		
		return new ModelAndView("/WEB-INF/pages/currencies/currency-details.jsp");
	}
	
	public ModelAndView saveCurrency(HttpServletRequest request) {		
		Set<String> validationErrors = new HashSet<>();
		Integer id = HttpUtils.getIntParam("id", request);
		String name = request.getParameter("newCurrency");
		if(StringUtils.isBlank(name)) {
			validationErrors.add("name.currency.empty");
		}
		
		if(!validationErrors.isEmpty()) {
			ModelAndView modelAndView = new ModelAndView("/WEB-INF/pages/currencies/currency-details.jsp");
			modelAndView.addAllValidationError(validationErrors);
			CurrencyDto currency = currencyService.getNameCurrencyById(id);
			modelAndView.addViewData("currency", currency);
			return modelAndView;
		}
		
		ModelAndView modelAndView = new ModelAndView("redirect:currency-list");
		
		CurrencyDto currency = new CurrencyDto();
		currency.setId(id);
		currency.setName(name);
		
		currencyService.saveCurrency(currency);
		
		return modelAndView;
	}

	public ModelAndView editCurrency(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("/WEB-INF/pages/currencies/currency-details.jsp");
		Integer id = HttpUtils.getIntParam("currencyId", request);
		CurrencyDto currency = currencyService.getNameCurrencyById(id);
		modelAndView.addViewData("currency", currency);
		return modelAndView;
	}
	
	public ModelAndView deleteCurrency(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("redirect:currency-list");
		Integer id = HttpUtils.getIntParam("currencyId", request);
		currencyService.deleteCurrency(id);
		return modelAndView;
	}
}
