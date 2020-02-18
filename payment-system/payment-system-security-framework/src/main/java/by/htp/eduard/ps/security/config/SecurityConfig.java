package by.htp.eduard.ps.security.config;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import by.htp.eduard.ps.security.config.urlcheckers.UrlChecker;
import by.htp.eduard.ps.security.config.urlcheckers.UrlCheckerFactory;
import by.htp.eduard.ps.utils.http.HttpUtils;

public class SecurityConfig {

	private static final SecurityConfig config = new SecurityConfig();
	private String loginPage;
	private String succsessLoginUrl;
	private Set<PermitAllUrl> permitAllUrls;

	private SecurityConfig() {
	}

	public void init(ServletContext context) throws ParserConfigurationException, SAXException, IOException,
			ClassNotFoundException, InstantiationException, IllegalAccessException {
		
		permitAllUrls = new HashSet<>();

		File mvcCommandsFile = HttpUtils.getFileFromWebInf("/WEB-INF/security-config.xml", context);

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		Document document = builder.parse(mvcCommandsFile);

		Element securityConfig = document.getDocumentElement();

		loginPage = getTagAttribute(securityConfig, "login-page", "page");
		
		succsessLoginUrl = getTagAttribute(securityConfig, "success-login-url", "home");
		
		
		
		NodeList tagPermitAllUrls = securityConfig.getElementsByTagName("permit-all-urls");
		
		Element tagPermitAllUrl = (Element) tagPermitAllUrls.item(0);

		NodeList allTagsPermitAllUrl = tagPermitAllUrl.getElementsByTagName("permit-all-url");

		for (int i = 0; i < allTagsPermitAllUrl.getLength(); i++) {

			Element permitAllUrl = (Element) allTagsPermitAllUrl.item(i);
			String attribute = permitAllUrl.getAttribute("process-strategy");
			String url = permitAllUrl.getTextContent();
			UrlChecker urlChecker = UrlCheckerFactory.getInstance().getUrlChecker(attribute);
			PermitAllUrl permit = new PermitAllUrl(url, urlChecker);
			permitAllUrls.add(permit);
		}
		
		System.out.println(permitAllUrls);

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

	public Set<PermitAllUrl> getPermitAllUrls() {
		return permitAllUrls;
	}

	private String getTagAttribute(Element parentTag, String tagName, String attrName) {
		NodeList tags = parentTag.getElementsByTagName(tagName);
		Element tag = (Element) tags.item(0);
		String attrValue = tag.getAttribute(attrName);
		
		return attrValue;
	}
}
