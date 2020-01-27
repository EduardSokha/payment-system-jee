package by.htp.eduard.ps.security.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.eduard.ps.utils.http.HttpUtils;

public class AuthenticationFilter extends HttpFilter {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)	throws IOException, ServletException {
		String url = HttpUtils.getPageContext(req);
		
		if(url.contains("/static-content")) {
			chain.doFilter(req, res);
			return;
		}
		
		if(url.equals("/logout")) {
			chain.doFilter(req, res);
			return;
		}
		
		if(url.equals("/sign-in")) {
			chain.doFilter(req, res);
			return;
		}
		
		if(url.equals("/identity")) {
			chain.doFilter(req, res);
			return;
		}
		
		HttpSession session = req.getSession();
		Object authentication = session.getAttribute("authentication");
		if(authentication != null) {
			chain.doFilter(req, res);
			return;
		}
		
		ServletContext context = req.getServletContext();
		String contextPath = context.getContextPath();
		String signInUrl = contextPath + "/sign-in";
		res.sendRedirect(signInUrl);
		
	}
}
