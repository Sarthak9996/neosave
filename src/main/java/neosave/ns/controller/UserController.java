package neosave.ns.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import neosave.ns.models.User;
import neosave.ns.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserServiceImpl userService;

	@GetMapping("/{id}")
	public ResponseEntity<Object> fetchUserById(@PathVariable(value = "id") Integer id) {

		User user = new User();
		user = userService.fetchUserById(id.toString());
		if (user != null) {
			return new ResponseEntity<Object>(user, HttpStatus.OK);
		}
		return new ResponseEntity<Object>("User Not Found", HttpStatus.NOT_FOUND);
	}

	@PostMapping("/create")
	public ResponseEntity<HashMap<String, String>> createUser(@RequestParam(name = "name") String name,
			@RequestParam(name = "email") String email, @RequestParam(name = "addressPincode") String addressPincode) {
		HashMap<String, String> response = new HashMap<String, String>();

		HashMap<Boolean, String> createUser = userService.createUser(name, email, addressPincode);

		if (createUser.containsKey(Boolean.TRUE)) {
			response.put("id", createUser.get(Boolean.TRUE));
			return new ResponseEntity<HashMap<String, String>>(response, HttpStatus.OK);
		} else {
			if (("Email Already Present").equalsIgnoreCase(createUser.get(Boolean.FALSE))) {
				response.put("errorMessage", createUser.get(Boolean.FALSE));
				return new ResponseEntity<HashMap<String, String>>(response, HttpStatus.FOUND);
			} else if (("Invalid Email").equalsIgnoreCase(createUser.get(Boolean.FALSE))) {
				response.put("errorMessage", createUser.get(Boolean.FALSE));
				return new ResponseEntity<HashMap<String, String>>(response, HttpStatus.BAD_REQUEST);
			} else {
				response.put("errorMessage", createUser.get(Boolean.FALSE));
				return new ResponseEntity<HashMap<String, String>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
}
