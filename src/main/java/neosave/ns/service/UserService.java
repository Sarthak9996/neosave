package neosave.ns.service;

import java.util.HashMap;

import neosave.ns.models.User;

public interface UserService {

	User fetchUserById(String id);

	HashMap<Boolean, String> createUser(String name, String email, String addressPincode);
}
