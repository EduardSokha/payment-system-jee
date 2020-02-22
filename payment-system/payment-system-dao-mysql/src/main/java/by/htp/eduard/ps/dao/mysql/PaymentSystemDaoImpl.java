package by.htp.eduard.ps.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.htp.eduard.ps.dao.PaymentSystemDao;
import by.htp.eduard.ps.dao.entities.PaymentSystem;
import by.htp.eduard.ps.dao.exceptions.DaoException;
import by.htp.eduard.ps.jdbc.mysql.ConnectionPool;

public class PaymentSystemDaoImpl implements PaymentSystemDao {

	private final static Logger logger = Logger.getLogger(PaymentSystemDaoImpl.class);
	
	private final ConnectionPool cp = ConnectionPool.getInstance();

	@Override
	public List<PaymentSystem> getAllPaymentSystems() {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<PaymentSystem> paymentSystems = new ArrayList<PaymentSystem>();
		String sq1 = InsertsToSQL.PAYMENT_SYSTEM_DAO_IMPL_GET_ALL_PAYMENT_SYSTEMS;
//		String sq1 = "SELECT `id`, `type_paym_syst_card` FROM `payment_system_card`";

		try {
			con = cp.getConnection();
			ps = con.prepareStatement(sq1);
			rs = ps.executeQuery(sq1);

			while (rs.next()) {
				PaymentSystem paymentSystem = createPaymentSystem(rs);
				paymentSystems.add(paymentSystem);
			}
		} catch (SQLException e) {
			logger.debug("SQLException DAO", e);
			throw new DaoException(e);
		} finally {
			cp.releaseDbResourses(con, ps, rs);
		}

		return paymentSystems;
	}
	
	@Override
	public PaymentSystem getPaymentSystemById(Integer id) {
		
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		//String sq1 = "SELECT `id`, `type_paym_syst_card` FROM `payment_system_card` WHERE `id` = ?";
		String sq1 = InsertsToSQL.PAYMENT_SYSTEM_DAO_IMPL_GET_PAYMENT_SYSTEM_BY_ID + id;
//		String sq1 = "SELECT `id`, `type_paym_syst_card` FROM `payment_system_card` WHERE `id` = " + id;

		try {
			con = cp.getConnection();
			ps = con.prepareStatement(sq1);
			//ps.setInt(1, id);
			rs = ps.executeQuery(sq1);

			if (rs.next()) {
				PaymentSystem paymentSystem = createPaymentSystem(rs);
				return paymentSystem;
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
	public PaymentSystem savePaymentSystem(PaymentSystem paymentSystem) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sq1 = InsertsToSQL.PAYMENT_SYSTEM_DAO_IMPL_SAVE_PAYMENT_SYSTEM;
//		String sq1 = "INSERT INTO `payment_system_card` (`type_paym_syst_card`) VALUES (?)";

		try {
			con = cp.getConnection();
			ps = con.prepareStatement(sq1, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, paymentSystem.getName());
			ps.executeUpdate();
			
			ResultSet generatedKeys = ps.getGeneratedKeys();
			if(generatedKeys.next()) {
				Integer id = generatedKeys.getInt(1);
				return getPaymentSystemById(id);
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
	public PaymentSystem updatePaymentSystem(PaymentSystem paymentSystem) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sq1 = InsertsToSQL.PAYMENT_SYSTEM_DAO_IMPL_UPDATE_PAYMENT_SYSTEM;
//		String sq1 = "UPDATE `payment_system_card` SET `type_paym_syst_card` = ? WHERE `id` = ?";

		try {
			con = cp.getConnection();
			ps = con.prepareStatement(sq1);
			ps.setString(1, paymentSystem.getName());
			ps.setInt(2, paymentSystem.getId());
			ps.executeUpdate();
			
			return getPaymentSystemById(paymentSystem.getId());
		} catch (SQLException e) {
			logger.debug("SQLException DAO", e);
			throw new DaoException(e);
		} finally {
			cp.releaseDbResourses(con, ps, rs);
		}
	}

	@Override
	public void deletePaymentSystem(Integer id) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sq1 = InsertsToSQL.PAYMENT_SYSTEM_DAO_IMPL_DELETE_PAYMENT_SYSTEM;
//		String sq1 = "DELETE FROM `payment_system_card` WHERE `id` = ?";

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

	private PaymentSystem createPaymentSystem(ResultSet rs) throws SQLException {
		PaymentSystem paymentSystem = new PaymentSystem();

		paymentSystem.setId(rs.getInt("id"));
		paymentSystem.setName(rs.getString("type_paym_syst_card"));
		return paymentSystem;
	}
}
