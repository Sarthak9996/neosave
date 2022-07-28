package neosave.ns.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import neosave.ns.dao.ValidatorDao;
import neosave.ns.utils.DBUtils;

@Component
public class ValidatorDaoImpl implements ValidatorDao {

	@Autowired
	DBUtils db;

	@Override
	public Boolean isEmailPresent(String email) {
		String sql = "select * from Users where email = '" + email + "';";
		List<Map<String, Object>> result = db.dbUtils().queryForList(sql);
		if (result.size() == 1) {
			return false;
		}
		return true;
	}

}
