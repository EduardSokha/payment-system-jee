package by.htp.eduard.ps.dao.mysql.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

public class ConnectionPool {

	private static final ConnectionPool instance = new ConnectionPool();

	private BasicDataSource dataSource;

	final static Logger logger = Logger.getLogger(ConnectionPool.class);

	public ConnectionPool() {
		DBResourceManager db = DBResourceManager.getInstance();

		dataSource = new BasicDataSource();
		dataSource.setDriverClassName(db.getValue(DBParameters.DB_DRIVER));
		dataSource.setUsername(db.getValue(DBParameters.DB_USER));
		dataSource.setPassword(db.getValue(DBParameters.DB_PASSWORD));
		dataSource.setUrl(db.getValue(DBParameters.DB_URL));
	}

	public Connection getConnection(){
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException("Some problem accured during opening new connection!", e);
		}
	}

	public void releaseDbResourses(Connection con, Statement statement) {
		releaseStatement(statement);
		releaseConnection(con);
	}

	public void releaseDbResourses(Connection con, Statement statement, ResultSet resultSet) {
		releaseResultSet(resultSet);
		releaseStatement(statement);
		releaseConnection(con);
	}

	public void releaseConnection(Connection con) {
		try {
			if (con != null) {
				con.setAutoCommit(true);
				con.close();
			}
		} catch (SQLException e) {
			logger.error("Cannot close connection", e);
		}
	}

	public void releaseStatement(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				logger.error("Cannot cloe Statement", e);
			}
		}
	}

	public void releaseResultSet(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				logger.error("cannot close ResultSet", e);
			}
		}
	}

	public static ConnectionPool getInstance() {
		return instance;
	}
}
