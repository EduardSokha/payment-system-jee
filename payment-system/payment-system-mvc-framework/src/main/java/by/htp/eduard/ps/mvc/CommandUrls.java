package by.htp.eduard.ps.mvc;

import java.util.HashSet;
import java.util.Set;

public class CommandUrls {
	private Set<UrlInfo> urls;

	public CommandUrls() {
		super();
		urls = new HashSet<UrlInfo>();
	}
	
	public void addUrl(UrlInfo info) {
		urls.add(info);
	}
	
	public UrlInfo getUrlInfoForUrl(String url) {
		for (UrlInfo urlInfo : urls) {
			if(urlInfo.isSameFor(url)) {
				return urlInfo;
			}
		}
		
		return null;
	}

	@Override
	public String toString() {
		return "CommandUrls [urls=" + urls + "]";
	}
}
