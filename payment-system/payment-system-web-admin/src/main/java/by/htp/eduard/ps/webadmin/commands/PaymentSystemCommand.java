package by.htp.eduard.ps.webadmin.commands;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.htp.eduard.ps.service.PaymentSystemService;
import by.htp.eduard.ps.service.ServiceProvider;
import by.htp.eduard.ps.service.dto.PaymentSystemDto;
import by.htp.eduard.ps.utils.http.HttpUtils;

public class PaymentSystemCommand {
	
private final PaymentSystemService paymentSystemService;
	
	public PaymentSystemCommand() {
		paymentSystemService = ServiceProvider.getInstance().getPaymentSystemService();
	}
	
	public String showAllPaymentSystems(HttpServletRequest request) {
		List<PaymentSystemDto> allPaymentSystems = paymentSystemService.getAllPaymentSystems();
		request.setAttribute("allPaymentSystems", allPaymentSystems);
		
		return "/WEB-INF/pages/payment-systems/payment-system-list.jsp";
	}
	
	public String addPaymentSystem(HttpServletRequest request) {		
		return "/WEB-INF/pages/payment-systems/payment-system-details.jsp";
	}
	
	public String savePaymentSystem(HttpServletRequest request) {
		Integer id = HttpUtils.getIntParam("id", request);
		String name = request.getParameter("newNamePaySyst");
		
		PaymentSystemDto paymentSystem = new PaymentSystemDto();
		paymentSystem.setId(id);
		paymentSystem.setName(name);
		
		paymentSystemService.savePaymentSystem(paymentSystem);
		
		return "redirect:payment-system-list";
	}
	
	public String editPaymentSystem(HttpServletRequest request) {
		Integer id = HttpUtils.getIntParam("systemId", request);
		PaymentSystemDto paymentSystem = paymentSystemService.getPaymentSystemById(id);
		request.setAttribute("paymentSystem", paymentSystem);
		return "/WEB-INF/pages/payment-systems/payment-system-details.jsp";
	}
	
	public String deletePaymentSystem(HttpServletRequest request) {
		Integer id = HttpUtils.getIntParam("systemId", request);
		paymentSystemService.deletePaymentSystem(id);
		return "redirect:payment-system-list";
	}
}
