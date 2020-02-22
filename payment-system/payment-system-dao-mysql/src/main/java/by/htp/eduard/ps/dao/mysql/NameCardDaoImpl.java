package by.htp.eduard.ps.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.htp.eduard.ps.dao.NameCardDao;
import by.htp.eduard.ps.dao.entities.NameCard;
import by.htp.eduard.ps.dao.exceptions.DaoException;
import by.htp.eduard.ps.jdbc.mysql.ConnectionPool;

public class NameCardDaoImpl implements NameCardDao {
	
	private final static Logger logger = Logger.getLogger(NameCardDaoImpl.class);
	
	private final ConnectionPool cp = ConnectionPool.getInstance();

	@Override
	public List<NameCard> getAllNamesCard() {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<NameCard> namesCard = new ArrayList<NameCard>();
		String sq1 = InsertsToSQL.NAME_CARD_DAO_IMPL_GET_ALL_NAMES_CARD;
//		String sq1 = "SELECT `id`, `name_card` FROM `name_card`";

		try {
			con = cp.getConnection();
			ps = con.prepareStatement(sq1);
			rs = ps.executeQuery(sq1);

			while (rs.next()) {
				NameCard nameCard = createNameCard(rs);
				namesCard.add(nameCard);
			}
		} catch (SQLException e) {
			logger.debug("SQLException DAO", e);
			throw new DaoException(e);
		} finally {
			cp.releaseDbResourses(con, ps, rs);
		}

		return namesCard;
	}

	@Override
	public NameCard getNameCardById(Integer id) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sq1 = InsertsToSQL.NAME_CARD_DAO_IMPL_GET_NAME_CARD_BY_ID + id;
//		String sq1 = "SELECT `id`, `name_card` FROM `name_card` WHERE `id` = " + id;


		try {
			con = cp.getConnection();
			ps = con.prepareStatement(sq1);
			rs = ps.executeQuery(sq1);

			if (rs.next()) {
				NameCard nameCard = createNameCard(rs);
				return nameCard;
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
	public NameCard saveNameCard(NameCard nameCard) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sq1 = InsertsToSQL.NAME_CARD_DAO_IMPL_SAVE_NAME_CARD;
//		String sq1 = "INSERT INTO `name_card` (`name_card`) VALUES (?)";
		
		try {
			con = cp.getConnection();
			ps = con.prepareStatement(sq1, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, nameCard.getName());
			ps.executeUpdate();
			
			ResultSet generatedKeys = ps.getGeneratedKeys();
			if(generatedKeys.next()) {
				Integer id = generatedKeys.getInt(1);
				return getNameCardById(id);
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
	public NameCard updateNameCard(NameCard nameCard) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sq1 = InsertsToSQL.NAME_CARD_DAO_IMPL_UPDATE_NAME_CARD;
//		String sq1 = "UPDATE `name_card` SET `name_card` = ? WHERE `id` = ?";

		try {
			con = cp.getConnection();
			ps = con.prepareStatement(sq1);
			ps.setString(1, nameCard.getName());
			ps.setInt(2, nameCard.getId());
			ps.executeUpdate();
			
			return getNameCardById(nameCard.getId());
		} catch (SQLException e) {
			logger.debug("SQLException DAO", e);
			throw new DaoException(e);
		} finally {
			cp.releaseDbResourses(con, ps, rs);
		}
	}

	@Override
	public void deleteNameCard(Integer id) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sq1 = InsertsToSQL.NAME_CARD_DAO_IMPL_DELETE_NAME_CARD;
//		String sq1 = "DELETE FROM `name_card` WHERE `id` = ?";
		
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
	
	private NameCard createNameCard(ResultSet rs) throws SQLException {
		NameCard nameCard = new NameCard();

		nameCard.setId(rs.getInt("id"));
		nameCard.setName(rs.getString("name_card"));
		return nameCard;
	}
}
