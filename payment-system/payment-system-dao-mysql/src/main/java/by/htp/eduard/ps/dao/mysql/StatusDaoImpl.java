package by.htp.eduard.ps.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.htp.eduard.ps.dao.StatusDao;
import by.htp.eduard.ps.dao.entities.Status;
import by.htp.eduard.ps.dao.exceptions.DaoException;
import by.htp.eduard.ps.jdbc.mysql.ConnectionPool;

public class StatusDaoImpl implements StatusDao {

	private final static Logger logger = Logger.getLogger(StatusDaoImpl.class);

	private final ConnectionPool cp = ConnectionPool.getInstance();

	@Override
	public List<Status> getAllStatus() {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<Status> stat = new ArrayList<>();
		String sq1 = InsertsToSQL.STATUS_DAO_IMPL_GET_ALL_STATUS;
//		String sq1 = "SELECT `id`, `name_status` FROM `status`";

		try {
			con = cp.getConnection();
			ps = con.prepareStatement(sq1);
			rs = ps.executeQuery(sq1);

			while (rs.next()) {
				Status status = createStatus(rs);
				stat.add(status);
			}
		} catch (SQLException e) {
			logger.debug("SQLException DAO", e);
			throw new DaoException(e);
		} finally {
			cp.releaseDbResourses(con, ps, rs);
		}

		return stat;
	}

	@Override
	public Status getNameStatusById(Integer id) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sq1 = InsertsToSQL.STATUS_DAO_IMPL_GET_NAME_STATUS_BY_ID + id;
//		String sq1 = "SELECT `id`, `name_status` FROM `status` WHERE `id` = " + id;

		try {
			con = cp.getConnection();
			ps = con.prepareStatement(sq1);
			rs = ps.executeQuery(sq1);

			if (rs.next()) {
				Status status = createStatus(rs);
				return status;
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
	public Status saveStatus(Status status) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sq1 = InsertsToSQL.STATUS_DAO_IMPL_SAVE_STATUS;
//		String sq1 = "INSERT INTO `status` (`name_status`) VALUES (?)";

		try {
			con = cp.getConnection();
			ps = con.prepareStatement(sq1, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, status.getName());
			ps.executeUpdate();
			
			ResultSet generatedKeys = ps.getGeneratedKeys();
			if(generatedKeys.next()) {
				Integer id = generatedKeys.getInt(1);
				return getNameStatusById(id);
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
	public Status updateNameStatus(Status status) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sq1 = InsertsToSQL.STATUS_DAO_IMPL_UPDATE_NAME_STATUS;
//		String sq1 = "UPDATE `status` SET `name_status` = ? WHERE `id` = ?";

		try {
			con = cp.getConnection();
			ps = con.prepareStatement(sq1);
			ps.setString(1, status.getName());
			ps.setInt(2, status.getId());
			ps.executeUpdate();
			
			return getNameStatusById(status.getId());
		} catch (SQLException e) {
			logger.debug("SQLException DAO", e);
			throw new DaoException(e);
		} finally {
			cp.releaseDbResourses(con, ps, rs);
		}
	}

	@Override
	public void deleteStatus(Integer id) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sq1 = InsertsToSQL.STATUS_DAO_IMPL_DELETE_STATUS;
//		String sq1 = "DELETE FROM `status` WHERE `id` = ?";
		
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
	
	private Status createStatus(ResultSet rs) throws SQLException {
		Status status = new Status();

		status.setId(rs.getInt("id"));
		status.setName(rs.getString("name_status"));
		return status;
	}
}
