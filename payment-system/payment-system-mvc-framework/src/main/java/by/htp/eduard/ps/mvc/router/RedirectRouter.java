package by.htp.eduard.ps.mvc.router;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

public class RedirectRouter implements Rourter {
	private HttpServletResponse response;
	

	public RedirectRouter(HttpServletResponse response) {
		super();
		this.response = response;
	}


	@Override
	public void route(String viewName) throws ServletException, IOException{
		viewName = viewName.replace("redirect:", "");
		response.sendRedirect(viewName);
	}

}
