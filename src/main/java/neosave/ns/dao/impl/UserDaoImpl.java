package neosave.ns.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import neosave.ns.dao.UserDao;
import neosave.ns.models.User;
import neosave.ns.utils.DBUtils;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	DBUtils db;

	@Override
	public User fetchUserById(String id) {
		User user = new User();
		String sql = "select * from Users where id = " + id + " ;";
		List<Map<String, Object>> result = db.dbUtils().queryForList(sql);
		if (result.size() == 1) {
			user.setName(result.get(0).get("name").toString());
			user.setEmail(result.get(0).get("email").toString());
			user.setAddressPincode(result.get(0).get("addresspincode").toString());
			user.setState(result.get(0).get("state").toString());
		}
		return user;
	}

	@Override
	public Integer createUser(String name, String email, String addressPincode, String state) {
		int userId;
		String insertSQL = "INSERT INTO public.users (name, email, addressPinCode, state) VALUES(?, ?, ?, ?);";
		try {
			KeyHolder holder = new GeneratedKeyHolder();
			db.dbUtils().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, name);
					ps.setString(2, email);
					ps.setString(3, addressPincode);
					ps.setString(4, state);
					return ps;
				}
			}, holder);
			userId = Integer.parseInt(holder.getKeys().get("id").toString());
		} catch (Exception e) {
			userId = -1;
		}

		return userId;
	}

}
