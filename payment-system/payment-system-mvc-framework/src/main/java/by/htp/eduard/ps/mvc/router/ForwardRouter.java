package by.htp.eduard.ps.mvc.router;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.eduard.ps.mvc.model.ModelAndView;

public class ForwardRouter implements Rourter {

	private HttpServletRequest request;

	private HttpServletResponse response;

	public ForwardRouter(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
	}

	@Override
	public void route(ModelAndView modelAndView) throws ServletException, IOException {
		String viewName = modelAndView.getViewName();
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewName);
		dispatcher.forward(request, response);
	}
}
