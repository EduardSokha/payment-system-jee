package by.htp.eduard.ps.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;

import by.htp.eduard.ps.dao.AccountDao;
import by.htp.eduard.ps.dao.entities.Account;
import by.htp.eduard.ps.dao.exceptions.DaoException;
import by.htp.eduard.ps.jdbc.mysql.ConnectionPool;
import by.htp.eduard.ps.transaction.TransactionManeger;
import by.htp.eduard.ps.utils.DateUtils;

public class AccountDaoImpl implements AccountDao {
	
	private final static Logger logger = Logger.getLogger(AccountDaoImpl.class);

	private final ConnectionPool cp = ConnectionPool.getInstance();

	@Override
	public List<Account> getAllAccounts() {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<Account> accounts = new ArrayList<Account>();
		String sq1 = InsertsToSQL.ACCOUNT_DAO_IMPL_GET_ALL_ACCOUNTS;

		try {
			con = TransactionManeger.getTransactionManeger().getConnection();
			ps = con.prepareStatement(sq1);
			rs = ps.executeQuery(sq1);

			while (rs.next()) {
				Account account = createAccount(rs);
				accounts.add(account);
			}
		} catch (SQLException e) {
			logger.debug("SQLException DAO", e);
			throw new DaoException(e);
		}

		return accounts;
	}
	
	@Override
	public List<Account> getAccountByIdUser(Integer id) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<Account> accounts = new ArrayList<Account>();
		String sq1 = InsertsToSQL.ACCOUNT_DAO_IMPL_GET_ACCOUNT_BY_ID_USER + id;

		try {
			con = cp.getConnection();
			ps = con.prepareStatement(sq1);
			rs = ps.executeQuery(sq1);

			while (rs.next()) {
				Account account = createAccount(rs);
				accounts.add(account);
			}
		} catch (SQLException e) {
			logger.debug("SQLException DAO", e);
			throw new DaoException(e);
		} finally {
			cp.releaseDbResourses(con, ps, rs);
		}

		return accounts;
	}

	@Override
	public Account getAccountById(Integer id) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sq1 = InsertsToSQL.ACCOUNT_DAO_IMPL_GET_ACCOUNT_BY_ID + id;

		try {
			con = cp.getConnection();
			ps = con.prepareStatement(sq1);
			rs = ps.executeQuery(sq1);

			if (rs.next()) {
				Account account = createAccount(rs);
				return account;
			}
		} catch (SQLException e) {
			logger.debug("SQLException DAO", e);
			throw new DaoException(e);
		} finally {
			cp.releaseDbResourses(con, ps, rs);
		}
		
		return null;
	}

	@Override
	public Account saveAccount(Account account) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sq1 = InsertsToSQL.ACCOUNT_DAO_IMPL_SAVE_ACCOUNT;
		
		try {
			con = cp.getConnection();
			ps = con.prepareStatement(sq1, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, UUID.randomUUID().toString());
			ps.setDouble(2, account.getBalance());
			ps.setDate(3, DateUtils.convertDateToSql(account.getDate()));
			ps.setInt(4, account.getIdUser());
			ps.setInt(5, account.getIdStatus());
			ps.setInt(6, account.getIdCurrency());
			
			ps.executeUpdate();
			
			ResultSet generatedKeys = ps.getGeneratedKeys();
			if(generatedKeys.next()) {
				Integer id = generatedKeys.getInt(1);
				return getAccountById(id);
			}
		} catch (SQLException e) {
			logger.debug("SQLException DAO", e);
			throw new DaoException(e);
		} finally {
			cp.releaseDbResourses(con, ps, rs);
		}

		return null;
	}

	@Override
	public Account updateAccount(Account account) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sq1 = InsertsToSQL.ACCOUNT_DAO_IMPL_UPDATE_ACCOUNT;

		try {
			con = cp.getConnection();
			ps = con.prepareStatement(sq1);
			ps.setDouble(1, account.getBalance());
			ps.setInt(2, account.getIdStatus());
			ps.setInt(3, account.getId());
			ps.executeUpdate();
			
			return getAccountById(account.getId());
		} catch (SQLException e) {
			logger.debug("SQLException DAO", e);
			throw new DaoException(e);
		} finally {
			cp.releaseDbResourses(con, ps, rs);
		}
	}

	@Override
	public Account lockUnlockAccount(Account account) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sq1 = InsertsToSQL.ACCOUNT_DAO_IMPL_LOCK_UNLOCK_ACCOUNT;

		try {
			con = cp.getConnection();
			ps = con.prepareStatement(sq1);
			ps.setInt(1, account.getIdStatus());
			ps.setInt(2, account.getId());
			ps.executeUpdate();
			
			return getAccountById(account.getId());
		} catch (SQLException e) {
			logger.debug("SQLException DAO", e);
			throw new DaoException(e);
		} finally {
			cp.releaseDbResourses(con, ps, rs);
		}
	}

	@Override
	public Account updateBalanceAccount(Account account) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sq1 = InsertsToSQL.ACCOUNT_DAO_IMPL_UPDATE_BALANCE_ACCOUNT;

		try {
			con = cp.getConnection();
			ps = con.prepareStatement(sq1);
			ps.setDouble(1, account.getBalance());
			ps.setInt(2, account.getId());
			ps.executeUpdate();
			
			return getAccountById(account.getId());
		} catch (SQLException e) {
			logger.debug("SQLException DAO", e);
			throw new DaoException(e);
		} finally {
			cp.releaseDbResourses(con, ps, rs);
		}
	}

	@Override
	public void deleteAccount(Integer id) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sq1 = InsertsToSQL.ACCOUNT_DAO_IMPL_DELETE_ACCOUNT;

		try {
			con = cp.getConnection();
			ps = con.prepareStatement(sq1);
			ps.setInt(1, id);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			logger.debug("SQLException DAO", e);
			throw new DaoException(e);
		} finally {
			cp.releaseDbResourses(con, ps, rs);
		}
	}
	
	private Account createAccount(ResultSet rs) throws SQLException {
		Account account = new Account();
		
		account.setId(rs.getInt("id"));
		account.setNumber(rs.getString("number"));
		account.setBalance(rs.getDouble("balance"));
		account.setDate(rs.getDate("creation_date"));
		account.setIdUser(rs.getInt("iduser"));
		account.setIdStatus(rs.getInt("idstatus"));
		account.setIdCurrency(rs.getInt("currency_idcurrency"));
		
		return account;
	}

}
