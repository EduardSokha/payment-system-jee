package by.htp.eduard.ps.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import by.htp.eduard.ps.dao.AuthenticationDao;
import by.htp.eduard.ps.dao.entities.Authentication;
import by.htp.eduard.ps.dao.entities.User;
import by.htp.eduard.ps.dao.exceptions.DaoException;
import by.htp.eduard.ps.dao.mysql.db.ConnectionPool;

public class AuthenticationDaoImpl implements AuthenticationDao{
	
	private final static Logger logger = Logger.getLogger(AuthenticationDaoImpl.class);
	
	private final ConnectionPool cp = ConnectionPool.getInstance();

	@Override
	public User signIn(Authentication authentication) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sq1 = "SELECT `id`, `login`, `password`, `name`, `surname`, `address`, `role_idrole`, `series_number_passport`, `identification_number_passport`, `codeword`, `phone_number`, `residence_registr_data_passport` FROM `users` WHERE `login` = '" + authentication.getLogin() + "' and `password` = '" + authentication.getPassword() + "'";

		try {
			con = cp.getConnection();
			ps = con.prepareStatement(sq1);
			rs = ps.executeQuery(sq1);

			if (rs.next()) {
				User user = createUser(rs);
				return user;
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
	public User forgetPassword(Authentication authentication) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sq1 = "SELECT `id`, `login`, `password`, `name`, `surname`, `address`, `role_idrole`, `series_number_passport`, `identification_number_passport`, `codeword`, `phone_number`, `residence_registr_data_passport` FROM `users` WHERE `series_number_passport` = '" + authentication.getPassportSeries() + "' and `identification_number_passport` = '" + authentication.getPassportId() + "' and `codeword` = '" + authentication.getCodeWord() + "'";

		try {
			con = cp.getConnection();
			ps = con.prepareStatement(sq1);
			rs = ps.executeQuery(sq1);

			if (rs.next()) {
				User user = createUser(rs);
				return user;
			}
		} catch (SQLException e) {
			logger.debug("SQLException DAO", e);
			throw new DaoException(e);
		} finally {
			cp.releaseDbResourses(con, ps, rs);
		}
		
		return null;
	}
	
	private User createUser(ResultSet rs) throws SQLException {
		User user = new User();

		user.setId(rs.getInt("id"));
		user.setLogin(rs.getString("login"));
		user.setPassword(rs.getString("password"));
		user.setName(rs.getString("name"));
		user.setSurname(rs.getString("surname"));
		user.setAddress(rs.getString("address"));
		user.setRoleId(rs.getInt("role_idrole"));
		user.setPassportSeries(rs.getString("series_number_passport"));
		user.setPassportId(rs.getString("identification_number_passport"));
		user.setCodeWord(rs.getString("codeword"));
		user.setPhoneNumber(rs.getString("phone_number"));
		user.setResidenceRegistr(rs.getString("residence_registr_data_passport"));
		return user;
	}
}
