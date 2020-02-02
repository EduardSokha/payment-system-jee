package by.htp.eduard.ps.mvc;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import by.htp.eduard.ps.utils.http.HttpUtils;

public class CommandsProvider {

	private static final CommandsProvider instance = new CommandsProvider();
	private final Map<String, CommandInfo> allCommands;
	private final Set<String> staticContentUrls;

	private CommandsProvider() {
		super();
		allCommands = new HashMap<String, CommandInfo>();
		staticContentUrls = new HashSet<String>();
	}

	public void init(ServletContext context) throws ParserConfigurationException, SAXException, IOException,
			ClassNotFoundException, InstantiationException, IllegalAccessException {
		
		File mvcCommandsFile = HttpUtils.getFileFromWebInf("/WEB-INF/mvc-commands.xml", context);

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		Document document = builder.parse(mvcCommandsFile);

		Element mvcCommands = document.getDocumentElement();

		NodeList staticUrlTags = mvcCommands.getElementsByTagName("static-content");

		for (int i = 0; i < staticUrlTags.getLength(); i++) {
			Element staticTag = (Element) staticUrlTags.item(i);
			String staticUrl = staticTag.getAttribute("url");
			staticContentUrls.add(staticUrl);
		}

		NodeList commandTags = mvcCommands.getElementsByTagName("command");

		for (int i = 0; i < commandTags.getLength(); i++) {

			Element commandTag = (Element) commandTags.item(i);
			String classAttribute = commandTag.getAttribute("command-class");
			CommandInfo command = new CommandInfo(classAttribute);

			NodeList commandUrls = commandTag.getElementsByTagName("command-url");

			for (int j = 0; j < commandUrls.getLength(); j++) {

				Element commandUrlTag = (Element) commandUrls.item(j);
				String urlAttribute = commandUrlTag.getAttribute("url");
				String methodAttribute = commandUrlTag.getAttribute("method");
				UrlInfo url = new UrlInfo(urlAttribute, methodAttribute);
				command.addCommandUrl(url);
			}

			allCommands.put(classAttribute, command);
		}
	}

	public static CommandsProvider getInstance() {
		return instance;
	}
	
	public void addCommand(String commandClass, CommandInfo command) {
		allCommands.put(commandClass, command);
	}

	public ExecutableCommand getCommandForUrl(String url) {
		Collection<CommandInfo> values = allCommands.values();
		for (CommandInfo commandInfo : values) {
			UrlInfo urlInfo = commandInfo.getUrlInfoForUrl(url);
			if (urlInfo != null) {
				ExecutableCommand command = new ExecutableCommand(commandInfo, urlInfo);
				return command;
			}
		}
		throw new RuntimeException("Cannot find command for url " + url);
	}
	
	public boolean isStaticUrl(String url) {
		for (String staticUrl : staticContentUrls) {
			if(url.contains(staticUrl)) {
				return true;
			}
		}
		
		return false;		
	}

}
