package by.htp.eduard.ps.security.config;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import by.htp.eduard.ps.utils.http.HttpUtils;

public class SecurityConfig {

	private static final SecurityConfig config = new SecurityConfig();
	private String loginPage;
	private String succsessLoginUrl;

	private SecurityConfig() {
	}

	public void init(ServletContext context) throws ParserConfigurationException, SAXException, IOException,
			ClassNotFoundException, InstantiationException, IllegalAccessException {

		File mvcCommandsFile = HttpUtils.getFileFromWebInf("/WEB-INF/security-config.xml", context);

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		Document document = builder.parse(mvcCommandsFile);

		Element securityConfig = document.getDocumentElement();

		loginPage = getTagAttribute(securityConfig, "login-page", "page");
		
		succsessLoginUrl = getTagAttribute(securityConfig, "success-login-url", "home");
	}

	public static SecurityConfig getConfig() {
		return config;
	}

	public String getLoginPage() {
		return loginPage;
	}

	public String getSuccsessLoginUrl() {
		return succsessLoginUrl;
	}
	
	private String getTagAttribute(Element parentTag, String tagName, String attrName) {
		NodeList tags = parentTag.getElementsByTagName(tagName);
		Element tag = (Element) tags.item(0);
		String attrValue = tag.getAttribute(attrName);
		
		return attrValue;
	}
}
