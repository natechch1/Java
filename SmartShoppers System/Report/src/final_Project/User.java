package final_Project;

public class User {
	public String type;
	public String name;
	public String password;
	public String email;
	public String address;
	public String preferenceStore;
	
	public User(String type, String name , String password , String address , String email, String preferenceStore) {
		super();
		this.type = type;
		this.name = name;
		this.password = password;
		this.address = address;
		this.email = email;
		this.preferenceStore = preferenceStore;
	}
	
	public User(){
		super();
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
	
	public String getAdress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPreferenceStore() {
		return preferenceStore;
	}

	public void setPreferenceStore(String preferenceStore) {
		this.preferenceStore = preferenceStore;
	}
	
	@Override
	public String toString() {
		return "User [type="  + type + ", name=" + name + ", password=" + password + ", address=" + address + ", email=" + email + ", PreferenceStore=" + preferenceStore + "]";
	}
	
}
