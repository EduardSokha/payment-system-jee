package by.htp.eduard.ps.utils;

import java.sql.Date;

/**
 * This class contains common static methods for manipulation with Date objects.
 * @author Eduard
 * 
 * @see java.util.Date
 */
public class DateUtils {
	
	public static Date convertDateToSql(java.util.Date date) {
		long time = date.getTime();
		Date sqlDate = new Date(time);
		return sqlDate;
	}

}
