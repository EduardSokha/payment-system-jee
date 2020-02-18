package by.htp.eduard.ps.mvc;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

public class ExecutableCommand {
	private final CommandInfo commandInfo;
	private final UrlInfo urlInfo;
	
	public ExecutableCommand(CommandInfo commandInfo, UrlInfo urlInfo) {
		this.commandInfo = commandInfo;
		this.urlInfo = urlInfo;
	}
	
	public Object getCommand() {
		return commandInfo.getCommand();
	}
	
	public Method getExecutableMethod() throws NoSuchMethodException, SecurityException {
		String methodName = urlInfo.getMethod();
		Class<?> commandClass = getCommand().getClass();
		Method executableMethod = commandClass.getDeclaredMethod(methodName, HttpServletRequest.class);
		return executableMethod;
	}
}
