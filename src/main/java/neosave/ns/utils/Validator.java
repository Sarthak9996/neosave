package neosave.ns.utils;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import neosave.ns.dao.impl.ValidatorDaoImpl;

@Component
public class Validator {

	@Autowired
	ValidatorDaoImpl validatorDao;

	public HashMap<Boolean, String> validateEmail(String email) {
		HashMap<Boolean, String> result = new HashMap<Boolean, String>();
		if (isEmailValid(email)) {
			if (isEmailAlreadyPresent(email)) {
				result.put(Boolean.TRUE, "Successful");
			}
			if (!isEmailAlreadyPresent(email)) {
				result.put(Boolean.FALSE, "Email Already Present");
			}
		}
		if (!isEmailValid(email)) {
			result.put(Boolean.FALSE, "Invalid Email");
		}
		return result;

	}

	public Boolean isEmailValid(String email) {
		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public Boolean isEmailAlreadyPresent(String email) {
		return validatorDao.isEmailPresent(email);
	}
}
