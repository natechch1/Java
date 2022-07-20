package final_Project;

import java.util.ArrayList;

public class Store {
	public String address;
	public String managerName;
	public String openTime;
	public String closeTime;
	
	public Store(String address, String managerName, String openTime, String closeTime) {
		super();
		this.address = address;
		this.managerName = managerName;
		this.openTime = openTime;
		this.closeTime = closeTime;
	}
	
	public Store(){
		super();
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	
	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}
	
	public String getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}
	
	
	@Override
	public String toString() {
		return "Store [address="  + address + ", ManagerName=" + managerName + ", Open time=" + openTime + ", Close time=" + closeTime + "]";
	}
}
