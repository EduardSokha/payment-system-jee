package by.htp.eduard.ps.mvc.router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RouterFactory {

	public static Rourter getRouter(String viewName, HttpServletRequest request, HttpServletResponse response) {

		if (viewName.startsWith("redirect:")) {
			return new RedirectRouter(response);
		}
		
		return new ForwardRouter(request, response);
	}

}
