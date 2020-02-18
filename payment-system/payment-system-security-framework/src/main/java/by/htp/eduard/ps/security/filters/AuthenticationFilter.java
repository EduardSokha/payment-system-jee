package by.htp.eduard.ps.security.filters;

import java.io.IOException;
import java.util.Set;

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
import by.htp.eduard.ps.security.config.PermitAllUrl;
import by.htp.eduard.ps.security.config.SecurityConfig;
import by.htp.eduard.ps.security.config.urlcheckers.UrlChecker;
import by.htp.eduard.ps.utils.http.HttpUtils;

public class AuthenticationFilter extends HttpFilter {

	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		ServletContext context = this.getServletContext();
		SecurityConfig config = SecurityConfig.getConfig();
		try {
			config.init(context);
		} catch (Exception e1) {
			throw new ServletException();
		}
		
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
		
		Set<PermitAllUrl> allUrl = SecurityConfig.getConfig().getPermitAllUrls();
		
		for (PermitAllUrl permitAllUrl : allUrl) {
			String urlForFiltr = permitAllUrl.getUrl();
			UrlChecker urlChecker = permitAllUrl.getUrlChecker();
			
			if(urlChecker.checkUrl(url, urlForFiltr)) {
				chain.doFilter(req, res);
				System.out.println("succsess for = " + urlForFiltr);
				return;
			}
			
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
