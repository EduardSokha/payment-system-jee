package by.htp.eduard.ps.security.config.urlcheckers;

import java.util.HashMap;
import java.util.Map;

public class UrlCheckerFactory {
	
	private Map<String, UrlChecker> urlChekerMap;
	
	private final static UrlCheckerFactory instance = new UrlCheckerFactory();

	private UrlCheckerFactory() {
		urlChekerMap = new HashMap<>();
		urlChekerMap.put("equals", new EqualsUrlChecker());
		urlChekerMap.put("contains", new ContainsUrlChecker());
	}
	
	public UrlChecker getUrlChecker(String checkStrategy) {
		UrlChecker urlChecker = urlChekerMap.get(checkStrategy);
		if(urlChecker == null) {
			throw new RuntimeException("invalid strategy: " + checkStrategy);
		}
		return urlChecker;
	}

	public static UrlCheckerFactory getInstance() {
		return instance;
	}
}
