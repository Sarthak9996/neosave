package neosave.ns.models;

public class User {

	private String name;
	private String email;
	private String addressPincode;
	private String state;

	public User() {
		super();
	}

	public User(String name, String email, String addressPincode, String state) {
		super();
		this.name = name;
		this.email = email;
		this.addressPincode = addressPincode;
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddressPincode() {
		return addressPincode;
	}

	public void setAddressPincode(String addressPincode) {
		this.addressPincode = addressPincode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
