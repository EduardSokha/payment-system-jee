package by.htp.eduard.ps.mvc.router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.eduard.ps.mvc.model.ModelAndView;

public class RouterFactory {

	public static Rourter getRouter(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response) {
		String viewName = modelAndView.getViewName();

		if (viewName.startsWith("redirect:")) {
			return new RedirectRouter(request, response);
		}
		
		return new ForwardRouter(request, response);
	}

}
