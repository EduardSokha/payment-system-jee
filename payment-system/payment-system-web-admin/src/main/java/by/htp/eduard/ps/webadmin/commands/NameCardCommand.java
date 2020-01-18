package by.htp.eduard.ps.webadmin.commands;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.htp.eduard.ps.service.NameCardService;
import by.htp.eduard.ps.service.ServiceProvider;
import by.htp.eduard.ps.service.dto.NameCardDto;
import by.htp.eduard.ps.utils.http.HttpUtils;

public class NameCardCommand {

	private final NameCardService nameCardService;

	public NameCardCommand() {
		nameCardService = ServiceProvider.getInstance().getNameCardService();
	}
	
	public String showTradeNamesCards(HttpServletRequest request) {
		List<NameCardDto> allTradeNamesCards = nameCardService.getAllNamesCard();
		request.setAttribute("allTradeNamesCards", allTradeNamesCards);
		
		return "/WEB-INF/pages/trade-names-cards/trade-names-cards-list.jsp";
	}
	
	public String addTradeNameCards(HttpServletRequest request) {		
		return "/WEB-INF/pages/trade-names-cards/trade-names-cards-details.jsp";
	}
	
	public String saveTradeNameCards(HttpServletRequest request) {
		Integer id = HttpUtils.getIntParam("id", request);
		String name = request.getParameter("newTradeName");
		
		NameCardDto nameCard = new NameCardDto();
		nameCard.setId(id);
		nameCard.setName(name);
		
		nameCardService.saveNameCard(nameCard);
		
		return "redirect:trade-names-list";
	}

	public String editTradeNameCards(HttpServletRequest request) {
		Integer id = HttpUtils.getIntParam("nameId", request);
		NameCardDto nameCard = nameCardService.getNameCardById(id);
		request.setAttribute("nameCard", nameCard);
		return "/WEB-INF/pages/trade-names-cards/trade-names-cards-details.jsp";
	}
	
	public String deleteTradeNameCards(HttpServletRequest request) {
		Integer id = HttpUtils.getIntParam("nameId", request);
		nameCardService.deleteNameCard(id);
		return "redirect:trade-names-list";
	}
}
