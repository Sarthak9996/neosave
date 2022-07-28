package neosave.ns.service.impl;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import neosave.ns.dao.impl.UserDaoImpl;
import neosave.ns.models.User;
import neosave.ns.service.UserService;
import neosave.ns.utils.URLUtils;
import neosave.ns.utils.Validator;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	UserDaoImpl userDao;

	@Autowired
	URLUtils urlUtils;

	@Autowired
	Validator validator;

	@Override
	public User fetchUserById(String id) {
		User user = new User();
		user = userDao.fetchUserById(id);
//		if (user != null) {
//			return user;
//		}
		if (user.getAddressPincode() == null && user.getEmail() == null && user.getName() == null) {
			return null;
		}
		return user;
	}

	@Override
	public HashMap<Boolean, String> createUser(String name, String email, String addressPincode) {
		HashMap<Boolean, String> result = new HashMap<Boolean, String>();

		String state = fetchState(addressPincode);

		HashMap<Boolean, String> validateEmail = validator.validateEmail(email);
		if (validateEmail.containsKey(Boolean.TRUE)) {
			Integer userId = userDao.createUser(name, email, addressPincode, state);
			if (userId != -1) {
				result.put(Boolean.TRUE, userId.toString());
			} else if (userId == -1) {
				result.put(Boolean.FALSE, "Failed to create user!!");
			}
		} else {
			return validateEmail;
		}
		return result;
	}

	public String fetchState(String pinCode) {
		String state = "NA";
		String url = "https://api.postalpincode.in/pincode/" + pinCode;
		try {
			String sendGET = urlUtils.sendGET(url);
			sendGET = sendGET.substring(1, sendGET.length() - 1);
			JSONObject myObject = new JSONObject(sendGET);
			Object status = myObject.get("Status");
			if (("Success").equalsIgnoreCase(status.toString())) {
				JSONArray jsonArray = myObject.getJSONArray("PostOffice");
				JSONObject obj = jsonArray.getJSONObject(0);
				state = obj.get("State").toString();
			}
		} catch (IOException e) {
			System.err.println("Error sending get request to the url : " + url);
			return state;
		}
		return state;
	}

}
