package neosave.ns.dao;

import neosave.ns.models.User;

public interface UserDao {

	User fetchUserById(String id);

	Integer createUser(String name, String email, String addressPincode, String state);
}
