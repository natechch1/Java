package final_Project;

import java.util.Iterator;

public class Manager extends Account {
	public static String getStoreAdress(String managerName) {
		MaintainStore maintain = new MaintainStore();
		
		try {
			maintain.load();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Store s : maintain.stores) {
			if (s.managerName.equals(managerName)) 
				return s.address;
		}
		return "Sorry you can't manage any store.";
	}
}
