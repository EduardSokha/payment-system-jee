package by.htp.eduard.ps.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.htp.eduard.ps.dao.PayDao;
import by.htp.eduard.ps.dao.entities.Pay;
import by.htp.eduard.ps.dao.exceptions.DaoException;
import by.htp.eduard.ps.jdbc.mysql.ConnectionPool;
import by.htp.eduard.ps.utils.DateUtils;

public class PayDaoImpl implements PayDao{
	
	private final static Logger logger = Logger.getLogger(PayDaoImpl.class);
	
	private final ConnectionPool cp = ConnectionPool.getInstance();

	@Override
	public List<Pay> getAllPay() {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<Pay> payments = new ArrayList<Pay>();
		String sq1 = InsertsToSQL.PAY_DAO_IMPL_GET_ALL_PAY;

		try {
			con = cp.getConnection();
			ps = con.prepareStatement(sq1);
			rs = ps.executeQuery(sq1);

			while (rs.next()) {
				Pay pay = createPay(rs);
				payments.add(pay);
			}
		} catch (SQLException e) {
			logger.debug("SQLException DAO", e);
			throw new DaoException(e);
		} finally {
			cp.releaseDbResourses(con, ps, rs);
		}

		return payments;
	}

	@Override
	public List<Pay> getPayByIdAccount(Integer id) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<Pay> payments = new ArrayList<Pay>();
		String sq1 = InsertsToSQL.PAY_DAO_IMPL_GET_PAY_BY_ID_ACCOUNT + id;

		try {
			con = cp.getConnection();
			ps = con.prepareStatement(sq1);
			rs = ps.executeQuery(sq1);

			while (rs.next()) {
				Pay pay = createPay(rs);
				payments.add(pay);
			}
		} catch (SQLException e) {
			logger.debug("SQLException DAO", e);
			throw new DaoException(e);
		} finally {
			cp.releaseDbResourses(con, ps, rs);
		}

		return payments;
	}

	@Override
	public Pay getPayById(Integer id) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sq1 = InsertsToSQL.PAY_DAO_IMPL_GET_PAY_BY_ID + id;

		try {
			con = cp.getConnection();
			ps = con.prepareStatement(sq1);
			rs = ps.executeQuery(sq1);

			if (rs.next()) {
				Pay pay = createPay(rs);
				return pay;
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
	public Pay savePay(Pay pay) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sq1 = InsertsToSQL.PAY_DAO_IMPL_SAVE_PAY;
		
		try {
			con = cp.getConnection();
			ps = con.prepareStatement(sq1, Statement.RETURN_GENERATED_KEYS);
			ps.setDate(1, DateUtils.convertDateToSql(pay.getDate()));
			ps.setDouble(2, pay.getPrice());
			ps.setInt(3, pay.getIdAccount());
			ps.setString(4, pay.getDescription());
			ps.executeUpdate();
			
			ResultSet generatedKeys = ps.getGeneratedKeys();
			if(generatedKeys.next()) {
				Integer id = generatedKeys.getInt(1);
				return getPayById(id);
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
	public void deletePay(Integer id) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sq1 = InsertsToSQL.PAY_DAO_IMPL_DELETE_PAY;
		
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
	
	private Pay createPay(ResultSet rs) throws SQLException {
		Pay pay = new Pay();

		pay.setId(rs.getInt("id"));
		pay.setDate(rs.getDate("date"));
		pay.setPrice(rs.getDouble("price"));
		pay.setIdAccount(rs.getInt("idaccount"));
		pay.setDescription(rs.getString("description"));

		return pay;
	}
}
