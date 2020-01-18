package by.htp.eduard.ps.mvc;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.eduard.ps.mvc.router.Rourter;
import by.htp.eduard.ps.mvc.router.RouterFactory;
import by.htp.eduard.ps.mvc.staticcontent.StaticContentProvider;

public class DispatcherServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final CommandsProvider provider = CommandsProvider.getInstance();
	
	public DispatcherServlet() {
		super();
	}

	@Override
	public void init() throws ServletException {
		ServletContext context = this.getServletContext();
		try {
			provider.init(context);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doService(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doService(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
	
	private void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String requestURI = request.getRequestURI();
		ServletContext servletContext = request.getServletContext();
		String contextPath = servletContext.getContextPath();
		String commandName = requestURI.replace(contextPath, "");
		if(provider.isStaticUrl(commandName)) {
			StaticContentProvider staticProvider = new StaticContentProvider();
			staticProvider.provideStaticContent(request, response, commandName);
			return;
		}
		
		ExecutableCommand executableCommand = provider.getCommandForUrl(commandName);
		Method executableMethod = executableCommand.getExecutableMethod();
		Object command = executableCommand.getCommand();
		Object result = executableMethod.invoke(command, request);
		
		
		String viewName = (String)result;
		Rourter router = RouterFactory.getRouter(viewName, request, response);
		router.route(viewName);
	}

}
