package by.htp.eduard.ps.security.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.eduard.ps.mvc.CommandInfo;
import by.htp.eduard.ps.mvc.CommandsProvider;
import by.htp.eduard.ps.mvc.UrlInfo;
import by.htp.eduard.ps.utils.http.HttpUtils;

public class AuthenticationFilter extends HttpFilter {

	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
			UrlInfo signInInfo = new UrlInfo("/sign-in", "signIn");
			UrlInfo authenticationInfo = new UrlInfo("/auth", "authentication");
			UrlInfo logOutInfo = new UrlInfo("/logout", "logout");
			
			CommandInfo securityInfo;
			
			try {
				securityInfo = new CommandInfo("by.htp.eduard.ps.security.commands.AuthenticationCommand");
			} catch (Exception e) {
				throw new ServletException(e);
			} 
			
			securityInfo.addCommandUrl(signInInfo);
			securityInfo.addCommandUrl(authenticationInfo);
			securityInfo.addCommandUrl(logOutInfo);
			
			CommandsProvider commandsProvider = CommandsProvider.getInstance();
			commandsProvider.addCommand("by.htp.eduard.ps.security.commands.AuthenticationCommand", securityInfo);
	}

	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		String url = HttpUtils.getPageContext(req);

		if (url.contains("/static-content")) {
			chain.doFilter(req, res);
			return;
		}

		if (url.equals("/logout")) {
			chain.doFilter(req, res);
			return;
		}

		if (url.equals("/sign-in")) {
			chain.doFilter(req, res);
			return;
		}

		if (url.equals("/auth")) {
			chain.doFilter(req, res);
			return;
		}

		HttpSession session = req.getSession();
		Object authentication = session.getAttribute("authentication");
		if (authentication != null) {
			chain.doFilter(req, res);
			return;
		}
		
		session.setAttribute("user-required-url", url);

		ServletContext context = req.getServletContext();
		String contextPath = context.getContextPath();
		String signInUrl = contextPath + "/sign-in";
		res.sendRedirect(signInUrl);

	}
}
