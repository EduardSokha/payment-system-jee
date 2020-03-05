package by.htp.eduard.ps.webadmin.commands;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import by.htp.eduard.ps.mvc.model.ModelAndView;
import by.htp.eduard.ps.service.NameCardService;
import by.htp.eduard.ps.service.ServiceProvider;
import by.htp.eduard.ps.service.dto.NameCardDto;
import by.htp.eduard.ps.utils.http.HttpUtils;

public class NameCardCommand {

	private final NameCardService nameCardService;

	public NameCardCommand() {
		nameCardService = ServiceProvider.getInstance().getNameCardService();
	}
	
	public ModelAndView showTradeNamesCards(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("/WEB-INF/pages/trade-names-cards/trade-names-cards-list.jsp");
		List<NameCardDto> allTradeNamesCards = nameCardService.getAllNamesCard();
		modelAndView.addViewData("allTradeNamesCards", allTradeNamesCards);
		return modelAndView;
	}
	
	public ModelAndView addTradeNameCards(HttpServletRequest request) {	
		return new ModelAndView("/WEB-INF/pages/trade-names-cards/trade-names-cards-details.jsp");
	}
	
	public ModelAndView saveTradeNameCards(HttpServletRequest request) {
		Set<String> validationErrors = new HashSet<>();
		Integer id = HttpUtils.getIntParam("id", request);
		String name = request.getParameter("newTradeName");
		
		if(StringUtils.isBlank(name)) {
			validationErrors.add("tradeName.empty");
		}
		
		if(!validationErrors.isEmpty()) {
			ModelAndView modelAndView = new ModelAndView("/WEB-INF/pages/trade-names-cards/trade-names-cards-details.jsp");
			modelAndView.addAllValidationError(validationErrors);
			NameCardDto nameCard = nameCardService.getNameCardById(id);
			modelAndView.addViewData("nameCard", nameCard);
			return modelAndView;
		}
		
		ModelAndView modelAndView = new ModelAndView("redirect:trade-names-list");
		NameCardDto nameCard = new NameCardDto();
		nameCard.setId(id);
		nameCard.setName(name);
		
		nameCardService.saveNameCard(nameCard);
		
		return modelAndView;
	}

	public ModelAndView editTradeNameCards(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("/WEB-INF/pages/trade-names-cards/trade-names-cards-details.jsp");
		Integer id = HttpUtils.getIntParam("nameId", request);
		NameCardDto nameCard = nameCardService.getNameCardById(id);
		modelAndView.addViewData("nameCard", nameCard);
		return modelAndView;
	}
	
	public ModelAndView deleteTradeNameCards(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("redirect:trade-names-list");
		Integer id = HttpUtils.getIntParam("nameId", request);
		nameCardService.deleteNameCard(id);
		return modelAndView;
	}
}
