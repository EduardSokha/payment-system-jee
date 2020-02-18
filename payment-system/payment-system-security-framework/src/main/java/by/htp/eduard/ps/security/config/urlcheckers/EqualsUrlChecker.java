package by.htp.eduard.ps.security.config.urlcheckers;

public class EqualsUrlChecker implements UrlChecker {

	@Override
	public boolean checkUrl(String webUrl, String configUrl) {
		return webUrl.equals(configUrl);
	}

}
