package by.htp.eduard.ps.jdbc.mysql;

import java.util.ResourceBundle;

public class DBResourceManager {

	private final static DBResourceManager instance = new DBResourceManager();

	private final static String DB_PROPERTIES_FILE = "db";

	private ResourceBundle bundle = ResourceBundle.getBundle(DB_PROPERTIES_FILE);

	public static DBResourceManager getInstance() {
		return instance;
	}

	public String getValue(String key) {
		return bundle.getString(key);
	}
}
