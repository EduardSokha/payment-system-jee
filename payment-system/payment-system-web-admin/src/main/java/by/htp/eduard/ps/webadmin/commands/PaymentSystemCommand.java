package by.htp.eduard.ps.webadmin.commands;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import by.htp.eduard.ps.mvc.model.ModelAndView;
import by.htp.eduard.ps.service.PaymentSystemService;
import by.htp.eduard.ps.service.ServiceProvider;
import by.htp.eduard.ps.service.dto.PaymentSystemDto;
import by.htp.eduard.ps.utils.http.HttpUtils;

public class PaymentSystemCommand {
	
private final PaymentSystemService paymentSystemService;
	
	public PaymentSystemCommand() {
		paymentSystemService = ServiceProvider.getInstance().getPaymentSystemService();
	}
	
	public ModelAndView showAllPaymentSystems(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("/WEB-INF/pages/payment-systems/payment-system-list.jsp");
		List<PaymentSystemDto> allPaymentSystems = paymentSystemService.getAllPaymentSystems();
		modelAndView.addViewData("allPaymentSystems", allPaymentSystems);
		
		return modelAndView;
	}
	
	public ModelAndView addPaymentSystem(HttpServletRequest request) {		
		return new ModelAndView("/WEB-INF/pages/payment-systems/payment-system-details.jsp");
	}
	
	public ModelAndView savePaymentSystem(HttpServletRequest request) {
		Set<String> validationErrors = new HashSet<>();
		Integer id = HttpUtils.getIntParam("id", request);
		String name = request.getParameter("newNamePaySyst");
		
		if(StringUtils.isBlank(name)) {
			validationErrors.add("name.paymentSystem.empty");
		}
		
		if(!validationErrors.isEmpty()) {
			ModelAndView modelAndView = new ModelAndView("/WEB-INF/pages/payment-systems/payment-system-details.jsp");
			modelAndView.addAllValidationError(validationErrors);
			
			PaymentSystemDto paymentSystem = paymentSystemService.getPaymentSystemById(id);
			modelAndView.addViewData("paymentSystem", paymentSystem);
			
			return modelAndView;
		}
		
		PaymentSystemDto paymentSystem = new PaymentSystemDto();
		paymentSystem.setId(id);
		paymentSystem.setName(name);
		
		paymentSystemService.savePaymentSystem(paymentSystem);
		ModelAndView modelAndView = new ModelAndView("redirect:payment-system-list");
		
		return modelAndView;
	}
	
	public ModelAndView editPaymentSystem(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("/WEB-INF/pages/payment-systems/payment-system-details.jsp");
		Integer id = HttpUtils.getIntParam("systemId", request);
		PaymentSystemDto paymentSystem = paymentSystemService.getPaymentSystemById(id);
		modelAndView.addViewData("paymentSystem", paymentSystem);
		
		return modelAndView;
	}
	
	public ModelAndView deletePaymentSystem(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("redirect:payment-system-list");
		Integer id = HttpUtils.getIntParam("systemId", request);
		paymentSystemService.deletePaymentSystem(id);
		
		return modelAndView;
	}
}
