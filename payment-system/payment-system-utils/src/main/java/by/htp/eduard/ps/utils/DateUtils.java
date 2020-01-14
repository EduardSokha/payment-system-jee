package by.htp.eduard.ps.utils;

import java.sql.Date;

public class DateUtils {
	
	public static Date convertDateToSql(java.util.Date date) {
		long time = date.getTime();
		Date sqlDate = new Date(time);
		return sqlDate;
	}

}
