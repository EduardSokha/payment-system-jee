package by.htp.eduard.ps.transaction;

import java.sql.Connection;

import by.htp.eduard.ps.transaction.impl.JdbcTransationManager;

public interface TransactionManeger {
	
	void beginTransaction();
	void comitTransaction();
	void rollBackTransaction();
	Connection getConnection();
	
	static TransactionManeger getTransactionManeger() {
		return JdbcTransationManager.getTransactionmaneger();
	}
}
