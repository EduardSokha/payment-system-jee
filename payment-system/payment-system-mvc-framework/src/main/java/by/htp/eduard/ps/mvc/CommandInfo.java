package by.htp.eduard.ps.mvc;

public class CommandInfo {
	private final String commandClassName;
	private final Object command;
	private final CommandUrls urls;
	
	public CommandInfo(String commandClassName) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		this.commandClassName = commandClassName;
		Class<?> commandClass = Class.forName(commandClassName);
		command = commandClass.newInstance();
		urls = new CommandUrls();
	}
	
	public void addCommandUrl(UrlInfo info) {
		urls.addUrl(info);
	}
	
	public UrlInfo getUrlInfoForUrl(String url) {
		return urls.getUrlInfoForUrl(url);
	}

	public Object getCommand() {
		return command;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commandClassName == null) ? 0 : commandClassName.hashCode());
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
		CommandInfo other = (CommandInfo) obj;
		if (commandClassName == null) {
			if (other.commandClassName != null)
				return false;
		} else if (!commandClassName.equals(other.commandClassName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CommandInfo [commandClassName=" + commandClassName + ", urls=" + urls + "]";
	}
}
