package by.htp.eduard.ps.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.htp.eduard.ps.dao.CurrencyDao;
import by.htp.eduard.ps.dao.entities.Currency;
import by.htp.eduard.ps.dao.exceptions.DaoException;
import by.htp.eduard.ps.jdbc.mysql.ConnectionPool;

public class CurrencyDaoImpl implements CurrencyDao {

	private final static Logger logger = Logger.getLogger(CurrencyDaoImpl.class);
	
	private final ConnectionPool cp = ConnectionPool.getInstance();
	
	@Override
	public List<Currency> getAllCurrencies() {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<Currency> currencies = new ArrayList<Currency>();
		String sq1 = InsertsToSQL.CURRENCY_DAO_IMPL_GET_ALL_CURRENCIES;
//		String sq1 = "SELECT `id`, `name_currency` FROM `currency`";

		try {
			con = cp.getConnection();
			ps = con.prepareStatement(sq1);
			rs = ps.executeQuery(sq1);

			while (rs.next()) {
				Currency currency = createCurrency(rs);
				currencies.add(currency);
			}
		} catch (SQLException e) {
			logger.debug("SQLException DAO", e);
			throw new DaoException(e);
		} finally {
			cp.releaseDbResourses(con, ps, rs);
		}

		return currencies;
	}

	@Override
	public Currency getNameCurrencyById(Integer id) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sq1 = InsertsToSQL.CURRENCY_DAO_IMPL_GET_NAME_CURRENCY_BY_ID + id;
//		String sq1 = "SELECT `id`, `name_currency` FROM `currency` WHERE `id` = " + id;

		try {
			con = cp.getConnection();
			ps = con.prepareStatement(sq1);
			rs = ps.executeQuery(sq1);

			if (rs.next()) {
				Currency currency = createCurrency(rs);
				return currency;
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
	public Currency saveCurrency(Currency currency) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sq1 = InsertsToSQL.CURRENCY_DAO_IMPL_SAVE_CURRENCY;
//		String sq1 = "INSERT INTO `currency` (`name_currency`) VALUES (?)";

		try {
			con = cp.getConnection();
			ps = con.prepareStatement(sq1, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, currency.getName());
			ps.executeUpdate();
			
			ResultSet generatedKeys = ps.getGeneratedKeys();
			if(generatedKeys.next()) {
				Integer id = generatedKeys.getInt(1);
				return getNameCurrencyById(id);
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
	public Currency updateNameCurrency(Currency currency) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sq1 = InsertsToSQL.CURRENCY_DAO_IMPL_UPDATE_NAME_CURRENCY;
//		String sq1 = "UPDATE `currency` SET `name_currency` = ? WHERE `id` = ?";

		try {
			con = cp.getConnection();
			ps = con.prepareStatement(sq1);
			ps.setString(1, currency.getName());
			ps.setInt(2, currency.getId());
			ps.executeUpdate();
			
			return getNameCurrencyById(currency.getId());
		} catch (SQLException e) {
			logger.debug("SQLException DAO", e);
			throw new DaoException(e);
		} finally {
			cp.releaseDbResourses(con, ps, rs);
		}
	}

	@Override
	public void deleteCurrency(Integer id) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sq1 = InsertsToSQL.CURRENCY_DAO_IMPL_DELETE_CURRENCY;
//		String sq1 = "DELETE FROM `currency` WHERE `id` = ?";
		
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
	
	private Currency createCurrency(ResultSet rs) throws SQLException {
		Currency currency = new Currency();

		currency.setId(rs.getInt("id"));
		currency.setName(rs.getString("name_currency"));
		return currency;
	}
}
