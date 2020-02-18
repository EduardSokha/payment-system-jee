package by.htp.eduard.ps.mvc;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.eduard.ps.mvc.model.ModelAndView;
import by.htp.eduard.ps.mvc.router.Rourter;
import by.htp.eduard.ps.mvc.router.RouterFactory;
import by.htp.eduard.ps.mvc.staticcontent.StaticContentProvider;
import by.htp.eduard.ps.utils.http.HttpUtils;

public class DispatcherServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final CommandsProvider provider = CommandsProvider.getInstance();
	
	public DispatcherServlet() {
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
		
		String commandName = HttpUtils.getPageContext(request);
		if(provider.isStaticUrl(commandName)) {
			StaticContentProvider staticProvider = new StaticContentProvider();
			staticProvider.provideStaticContent(request, response, commandName);
			return;
		}
		
		ExecutableCommand executableCommand = provider.getCommandForUrl(commandName);
		Method executableMethod = executableCommand.getExecutableMethod();
		Object command = executableCommand.getCommand();
		Object result = executableMethod.invoke(command, request);
		
		if(result.getClass() == String.class) {
			
			ModelAndView modelAndView = new ModelAndView(result.toString());
			
			Rourter router = RouterFactory.getRouter(modelAndView, request, response);
			router.route(modelAndView);
			
			return;
			
		}
		
		
		ModelAndView modelAndView = (ModelAndView)result;
		
		HttpSession session = request.getSession();
		ModelAndView redirectModelAndView = (ModelAndView)session.getAttribute("redirectModelAndView");
		
		if(redirectModelAndView != null) {
			modelAndView.addAllValidationError(redirectModelAndView.getValidationErrors());
			modelAndView.addAllViewData(redirectModelAndView.getViewData());
			
			session.removeAttribute("redirectModelAndView");
		}
		
		Set<String> validationErrors = modelAndView.getValidationErrors();
		request.setAttribute("validationErrors", validationErrors);
		
		Map<String, Object> viewData = modelAndView.getViewData();
		Set<String> dataKeys = viewData.keySet();
		for (String key : dataKeys) {
			request.setAttribute(key, viewData.get(key));
		}
		
		Rourter router = RouterFactory.getRouter(modelAndView, request, response);
		router.route(modelAndView);
	}

}
