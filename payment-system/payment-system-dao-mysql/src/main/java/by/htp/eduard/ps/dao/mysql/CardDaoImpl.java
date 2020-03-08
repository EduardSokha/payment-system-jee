package by.htp.eduard.ps.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.htp.eduard.ps.dao.CardDao;
import by.htp.eduard.ps.dao.entities.Card;
import by.htp.eduard.ps.dao.exceptions.DaoException;
import by.htp.eduard.ps.jdbc.mysql.ConnectionPool;
import by.htp.eduard.ps.utils.DateUtils;

public class CardDaoImpl implements CardDao {

	private final static Logger logger = Logger.getLogger(CardDaoImpl.class);

	private final ConnectionPool cp = ConnectionPool.getInstance();

	@Override
	public List<Card> getAllCards() {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<Card> cards = new ArrayList<Card>();
		String sq1 = InsertsToSQL.CARD_DAO_IMPL_GET_ALL_CARDS;

		try {
			con = cp.getConnection();
			ps = con.prepareStatement(sq1);
			rs = ps.executeQuery(sq1);

			while (rs.next()) {
				Card card = createCard(rs);
				cards.add(card);
			}
		} catch (SQLException e) {
			logger.debug("SQLException DAO", e);
			throw new DaoException(e);
		} finally {
			cp.releaseDbResourses(con, ps, rs);
		}

		return cards;
	}
	
	@Override
	public Card getCardByIdAccount(Integer id) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sq1 = InsertsToSQL.CARD_DAO_IMPL_GET_CARD_BY_ID_ACCOUNT + id;

		try {
			con = cp.getConnection();
			ps = con.prepareStatement(sq1);
			rs = ps.executeQuery(sq1);

			if (rs.next()) {
				Card card = createCard(rs);
				return card;
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
	public Card getCardById(Integer id) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sq1 = InsertsToSQL.CARD_DAO_IMPL_GET_CARD_BY_ID + id;

		try {
			con = cp.getConnection();
			ps = con.prepareStatement(sq1);
			rs = ps.executeQuery(sq1);

			if (rs.next()) {
				Card card = createCard(rs);
				return card;
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
	public Card saveCard(Card card) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sq1 = InsertsToSQL.CARD_DAO_IMPL_SAVE_CARD;

		try {
			con = cp.getConnection();
			ps = con.prepareStatement(sq1, Statement.RETURN_GENERATED_KEYS);
			ps.setDate(1, DateUtils.convertDateToSql(card.getDate()));
			ps.setInt(2, card.getIdAccount());
			ps.setInt(3, card.getIdPaymentSystem());
			ps.setInt(4, card.getIdTradeNameCard());
			ps.executeUpdate();
			
			ResultSet generatedKeys = ps.getGeneratedKeys();
			if(generatedKeys.next()) {
				Integer id = generatedKeys.getInt(1);
				return getCardById(id);
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
	public void deleteCard(Integer id) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sq1 = InsertsToSQL.CARD_DAO_IMPL_DELETE_CARD;
		
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
	
	private Card createCard(ResultSet rs) throws SQLException {
		Card card = new Card();

		card.setId(rs.getInt("id"));
		card.setDate(rs.getDate("creation_date"));
		card.setIdAccount(rs.getInt("account_idaccount"));
		card.setIdPaymentSystem(rs.getInt("payment_system_card_idpayment_system_card"));
		card.setIdTradeNameCard(rs.getInt("name_card_idname_card"));
		
		return card;
	}
}
