package by.htp.eduard.ps.security.config;

import by.htp.eduard.ps.security.config.urlcheckers.UrlChecker;

public class PermitAllUrl {
	
	private String url;
	
	private UrlChecker urlChecker;

	public PermitAllUrl(String url, UrlChecker urlChecker) {
		this.url = url;
		this.urlChecker = urlChecker;
	}

	public String getUrl() {
		return url;
	}

	public UrlChecker getUrlChecker() {
		return urlChecker;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		result = prime * result + ((urlChecker == null) ? 0 : urlChecker.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		PermitAllUrl other = (PermitAllUrl) obj;
		if (url == null) {
			if (other.url != null) {
				return false;
			}
		} else if (!url.equals(other.url)) {
			return false;
		}
		if (urlChecker == null) {
			if (other.urlChecker != null) {
				return false;
			}
		} else if (!urlChecker.equals(other.urlChecker)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "PermitAllUrl [url=" + url + ", urlChecker=" + urlChecker + "]";
	}
}
