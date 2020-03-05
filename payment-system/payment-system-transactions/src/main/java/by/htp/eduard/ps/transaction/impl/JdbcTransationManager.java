package by.htp.eduard.ps.transaction.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import by.htp.eduard.ps.jdbc.mysql.ConnectionPool;
import by.htp.eduard.ps.transaction.TransactionManeger;

public class JdbcTransationManager implements TransactionManeger{
	
	private static final TransactionManeger transactionManeger = new JdbcTransationManager();
	
	private Map<Long, Connection> transactionMap = new HashMap<>();
	
	private final static Logger logger = Logger.getLogger(JdbcTransationManager.class);

	public static TransactionManeger getTransactionmaneger() {
		return transactionManeger;
	}

	@Override
	public void beginTransaction() {
		Thread currentThread = Thread.currentThread();
		long threadId = currentThread.getId();
		if(transactionMap.get(threadId) != null) {
			throw new RuntimeException("Transaction has been already opened!");
		}
		
		Connection connection = ConnectionPool.getInstance().getConnection();
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			throw new RuntimeException("Some problem accured during begin transaction!", e);
		}
		transactionMap.put(threadId, connection);
	}

	@Override
	public void comitTransaction() {
		long threadId = getCurrentThreadId();
		Connection connection = transactionMap.get(threadId);
		if(connection == null) {
			throw new RuntimeException("Transaction has not been opened!");
		} 
		
		try {
			connection.commit();
		} catch (SQLException e) {
			throw new RuntimeException("Some problem accured during comit transaction!", e);
		} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					logger.error("Cannot close connection", e);
				} finally {
					transactionMap.remove(threadId);
				}
		}
	}

	@Override
	public void rollBackTransaction() {
		long threadId = getCurrentThreadId();
		Connection connection = transactionMap.get(threadId);
		if(connection == null) {
			throw new RuntimeException("Transaction has not been opened!");
		} 
		
		try {
			connection.rollback();
		} catch (SQLException e) {
			throw new RuntimeException("Some problem accured during rollBack transaction!", e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				logger.error("Cannot close connection", e);
			} finally {
				transactionMap.remove(threadId);
			}
		}
	}

	@Override
	public Connection getConnection() {
		long threadId = getCurrentThreadId();
		Connection connection = transactionMap.get(threadId);
		if(connection == null) {
			throw new RuntimeException("Transaction has not been opened!");
		} 
		return connection;
	}
	
	private long getCurrentThreadId() {
		Thread currentThread = Thread.currentThread();
		return currentThread.getId();
	}
}
