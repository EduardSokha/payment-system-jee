package by.htp.eduard.ps.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.htp.eduard.ps.dao.UserDao;
import by.htp.eduard.ps.dao.entities.ListAccountsAndCards;
import by.htp.eduard.ps.dao.entities.User;
import by.htp.eduard.ps.dao.exceptions.DaoException;
import by.htp.eduard.ps.dao.mysql.db.ConnectionPool;

public class UserDaoImpl implements UserDao {

	private final static Logger logger = Logger.getLogger(UserDaoImpl.class);
	
	private final ConnectionPool cp = ConnectionPool.getInstance();

	@Override
	public List<User> getAllUsers() {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<User> users = new ArrayList<User>();
		String sq1 = "SELECT `id`, `login`, `password`, `name`, `surname`, `address`, `role_idrole`, "
		          + "`series_number_passport`, `identification_number_passport`, `codeword`, "
		          + "`phone_number`, `residence_registr_data_passport` "
		          + "FROM `users`";

		try {
			con = cp.getConnection();
			ps = con.prepareStatement(sq1);
			rs = ps.executeQuery(sq1);

			while (rs.next()) {
				User user = new User();

				user.setId(rs.getInt("id"));
				user.setLogin(rs.getString("login"));
				user.setName(rs.getString("name"));
				user.setSurname(rs.getString("surname"));
				user.setAddress(rs.getString("address"));
				user.setRoleId(rs.getInt("role_idrole"));
				user.setPassportSeries(rs.getString("series_number_passport"));
				user.setPassportId(rs.getString("identification_number_passport"));
				user.setCodeWord(rs.getString("codeword"));
				user.setPhoneNumber(rs.getString("phone_number"));
				user.setResidenceRegistr(rs.getString("residence_registr_data_passport"));

				users.add(user);
			}
		} catch (SQLException e) {
			logger.debug("SQLException DAO", e);
			throw new DaoException(e);
		} finally {
			cp.releaseDbResourses(con, ps, rs);
		}

		return users;
	}

	@Override
	public List<ListAccountsAndCards> getAccountsAndCards() {
		
		Connection con = null;
		ResultSet rs = null;
		Statement st = null;
		List<ListAccountsAndCards> accaunts = new ArrayList<ListAccountsAndCards>();
		String sq1 = "SELECT account.idaccount, account.balance, account.status, account.creation_date, currency.name_currency, "
				+ "card.idcard, type_paym_syst_card, name_card.name_card FROM users_account "
				+ "INNER JOIN account ON users_account.account_idaccount = account.idaccount "
				+ "INNER JOIN currency ON account.currency_idcurrency = currency.idcurrency "
				+ "INNER JOIN card ON  account.idaccount = card.account_idaccount "
				+ "INNER JOIN payment_system_card ON card.payment_system_card_idpayment_system_card = idpayment_system_card "
				+ "INNER JOIN name_card ON  card.name_card_idname_card = name_card.idname_card ORDER BY card.idcard";
		
		try {

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(sq1);

			while (rs.next()) {
				ListAccountsAndCards getAccount = new ListAccountsAndCards();
				getAccount.setIdAccount(rs.getInt(1));
				getAccount.setBalance(rs.getInt(2));
				getAccount.setStatusAccount(rs.getString(3));
				getAccount.setCreationDateAccount(rs.getString(4));
				getAccount.setCurrency(rs.getString(5));
				getAccount.setIdCard(rs.getInt(6));
				getAccount.setTypePaySyst(rs.getString(7));
				getAccount.setNameCard(rs.getString(8));

				accaunts.add(getAccount);

			}

		} catch (SQLException e) {
			logger.debug("SQLException DAO", e);
			throw new DaoException(e);
		} finally {
			cp.releaseDbResourses(con, st, rs);
		}

		return accaunts;
	}

	@Override
	public User getUserById(Integer id) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sq1 = "SELECT `id`, `login`, `password`, `name`, `surname`, `address`, `role_idrole`, `series_number_passport`, `identification_number_passport`, `codeword`, `phone_number`, `residence_registr_data_passport` FROM `users` WHERE `id` = " + id;

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
	public User saveUser(User user) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sq1 = "INSERT INTO users(login, password, name, surname, address, role_idrole, series_number_passport, identification_number_passport, codeword, phone_number, residence_registr_data_passport) VALUES(?,?,?,?,?,?,?,?,?,?,?)";

		try {
			con = cp.getConnection();
			ps = con.prepareStatement(sq1, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getLogin());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setString(4, user.getSurname());
			ps.setString(5, user.getAddress());
			ps.setInt(6, user.getRoleId());
			ps.setString(7, user.getPassportSeries());
			ps.setString(8, user.getPassportId());
			ps.setString(9, user.getCodeWord());
			ps.setString(10, user.getPhoneNumber());
			ps.setString(11, user.getResidenceRegistr());
			ps.executeUpdate();
			
			ResultSet generatedKeys = ps.getGeneratedKeys();
			if(generatedKeys.next()) {
				Integer id = generatedKeys.getInt(1);
				return getUserById(id);
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
	public User updateUser(User user) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sq1 = "UPDATE `users` SET `login` = ?, `password` = ?, `name` = ?, `surname` = ?, `address` = ?, `role_idrole` = ?, `series_number_passport` = ?, `identification_number_passport` = ?, `codeword` = ?, `phone_number` = ?, `residence_registr_data_passport` = ? WHERE `id` = ?";

		try {
			con = cp.getConnection();
			ps = con.prepareStatement(sq1);
//			ps.setInt(1, user.getId());
			ps.setString(1, user.getLogin());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setString(4, user.getSurname());
			ps.setString(5, user.getAddress());
			ps.setInt(6, user.getRoleId());
			ps.setString(7, user.getPassportSeries());
			ps.setString(8, user.getPassportId());
			ps.setString(9, user.getCodeWord());
			ps.setString(10, user.getPhoneNumber());
			ps.setString(11, user.getResidenceRegistr());
			ps.setInt(12, user.getId());
			ps.executeUpdate();
			
			return getUserById(user.getId());
		} catch (SQLException e) {
			logger.debug("SQLException DAO", e);
			throw new DaoException(e);
		} finally {
			cp.releaseDbResourses(con, ps, rs);
		}
	}
	
	@Override
	public void deleteUser(Integer id) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sq1 = "DELETE FROM `users` WHERE `id` = ?";
		
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
