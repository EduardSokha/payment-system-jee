package by.htp.eduard.ps.mvc;

public class UrlInfo {
	private final String url;
	private final String method;

	public UrlInfo(String url, String method) {
		super();
		this.url = url;
		this.method = method;
	}

	public boolean isSameFor(String anotherUrl) {
		return url.equals(anotherUrl);
	}

	public String getUrl() {
		return url;
	}

	public String getMethod() {
		return method;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UrlInfo other = (UrlInfo) obj;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UrlInfo [url=" + url + ", method=" + method + "]";
	}
}
