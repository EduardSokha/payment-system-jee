package by.htp.eduard.ps.web.commands;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.htp.eduard.ps.mvc.model.ModelAndView;
import by.htp.eduard.ps.service.CardService;
import by.htp.eduard.ps.service.PayService;
import by.htp.eduard.ps.service.ServiceProvider;
import by.htp.eduard.ps.service.dto.CardDto;
import by.htp.eduard.ps.service.dto.PayDto;
import by.htp.eduard.ps.service.dto.UserDto;
import by.htp.eduard.ps.service.exceptions.NegativeAmountException;
import by.htp.eduard.ps.service.exceptions.NegativeBalanceException;
import by.htp.eduard.ps.utils.http.HttpUtils;

public class PayCommands {
	
	private final PayService payService;
	private final CardService cardService;	

	public PayCommands() {
		payService = ServiceProvider.getInstance().getPayService();
		cardService = ServiceProvider.getInstance().getCardService();
	}
	
	public ModelAndView showAllPayments(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("/WEB-INF/pages/pay/pay-list.jsp");
		
		HttpSession session = request.getSession();	
		UserDto userDto = (UserDto)session.getAttribute("authentication");
		
		List<PayDto> allPayments = payService.getPayByIdUser(userDto.getId());
		modelAndView.addViewData("allPayments", allPayments);
		
		return modelAndView;
	}
	
	public ModelAndView createPay(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("/WEB-INF/pages/pay/pay-details.jsp");
		Integer id = HttpUtils.getIntParam("cardId", request);
		
		CardDto card = cardService.getCardById(id);
		
		modelAndView.addViewData("card", card);
		
		return modelAndView;
	}
	
	public ModelAndView savePay(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("redirect:payments-list");
		
		Double price = HttpUtils.getDoubleParam("price", request);
		Integer idAccount = HttpUtils.getIntParam("idAccount", request);
		String description = request.getParameter("description");
		
		PayDto pay = new PayDto();
		pay.setDate(new Date());
		pay.setPrice(price);
		pay.setIdAccount(idAccount);
		pay.setDescription(description);
		
		try {
			payService.savePay(pay);
		} catch (NegativeBalanceException e) {
			e.printStackTrace();
		} catch (NegativeAmountException e) {
			e.printStackTrace();
		}
		
		return modelAndView;
	}
}