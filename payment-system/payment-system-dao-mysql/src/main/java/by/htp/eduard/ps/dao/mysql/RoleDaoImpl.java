package by.htp.eduard.ps.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.htp.eduard.ps.dao.RoleDao;
import by.htp.eduard.ps.dao.entities.Role;
import by.htp.eduard.ps.dao.exceptions.DaoException;
import by.htp.eduard.ps.dao.mysql.db.ConnectionPool;

public class RoleDaoImpl implements RoleDao {
	
	private final static Logger logger = Logger.getLogger(RoleDaoImpl.class);
	
	private final ConnectionPool cp = ConnectionPool.getInstance();
	
	@Override
	public List<Role> getAllRoles() {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<Role> roles = new ArrayList<Role>();
		String sq1 = "SELECT `id`, `title` FROM `role`";

		try {
			con = cp.getConnection();
			ps = con.prepareStatement(sq1);
			rs = ps.executeQuery(sq1);

			while (rs.next()) {
				Role role = createRole(rs);
				roles.add(role);
			}
		} catch (SQLException e) {
			logger.debug("SQLException DAO", e);
			throw new DaoException(e);
		} finally {
			cp.releaseDbResourses(con, ps, rs);
		}

		return roles;
	}

	@Override
	public Role getNameRoleById(Integer id) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sq1 = "SELECT `id`, `title` FROM `role` WHERE `id` = " + id;

		try {
			con = cp.getConnection();
			ps = con.prepareStatement(sq1);
			rs = ps.executeQuery(sq1);

			if (rs.next()) {
				Role role = createRole(rs);
				return role;
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
	public Role saveRole(Role role) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sq1 = "INSERT INTO `role` (`title`) VALUES (?)";

		try {
			con = cp.getConnection();
			ps = con.prepareStatement(sq1, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, role.getName());
			ps.executeUpdate();
			
			ResultSet generatedKeys = ps.getGeneratedKeys();
			if(generatedKeys.next()) {
				Integer id = generatedKeys.getInt(1);
				return getNameRoleById(id);
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
	public Role updateNameRole(Role role) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sq1 = "UPDATE `role` SET `title` = ? WHERE `id` = ?";

		try {
			con = cp.getConnection();
			ps = con.prepareStatement(sq1);
			ps.setString(1, role.getName());
			ps.setInt(2, role.getId());
			ps.executeUpdate();
			
			return getNameRoleById(role.getId());
		} catch (SQLException e) {
			logger.debug("SQLException DAO", e);
			throw new DaoException(e);
		} finally {
			cp.releaseDbResourses(con, ps, rs);
		}
	}

	@Override
	public void deleteRole(Integer id) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sq1 = "DELETE FROM `role` WHERE `id` = ?";
		
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
	
	private Role createRole(ResultSet rs) throws SQLException {
		Role role = new Role();

		role.setId(rs.getInt("id"));
		role.setName(rs.getString("title"));
		return role;
	}
}
