package by.htp.eduard.ps.mvc.router;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectRouter implements Rourter {
	private HttpServletResponse response;
	private HttpServletRequest request;

	public RedirectRouter(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
	}


	@Override
	public void route(String viewName) throws ServletException, IOException{
		viewName = viewName.replace("redirect:", "");
		
		ServletContext context = request.getServletContext();
		String contextPath = context.getContextPath();
		
		if(!viewName.startsWith(contextPath)) {
			if(!viewName.startsWith("/")) {
				viewName = "/" + viewName;
			}
			viewName = contextPath + viewName;
		}
		
		response.sendRedirect(viewName);
	}

}
