package by.htp.eduard.ps.security.config.urlcheckers;

public class ContainsUrlChecker implements UrlChecker {

	@Override
	public boolean checkUrl(String webUrl, String configUrl) {
		return webUrl.contains(configUrl);
	}

}
