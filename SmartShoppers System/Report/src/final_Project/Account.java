package final_Project;

import java.util.ArrayList;

public class Account extends User{
	public  ArrayList<String> loginAccount = new ArrayList<String>();
	
	
	public static Boolean checkIsNewUser(String userName) throws Exception{
		MaintainUser maintain = new MaintainUser();
		
		
		try {
			maintain.load();
			
			for (int i = 0; i < maintain.users.size(); i++) {
				if(maintain.users.get(i).name.equals(userName)) 
					return false;
			}
			maintain.update();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return true;
	}
	
	// input should be all info of Account and it should be customer and Administrator Account and Administrator Account can just has one.
	public static void createAccount(User newUser) throws Exception{
		MaintainUser maintain = new MaintainUser();
		
		try {
			maintain.load();
			maintain.users.add(newUser);
			maintain.update();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	public static Boolean login(String userName, String pwd) throws Exception{
		MaintainUser maintain = new MaintainUser();
		
		try {
			maintain.load();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for(User u : maintain.users) {
			if (userName.equals(u.name) && pwd.equals(u.password)) {
				return true;
			}
		}
		return false;
	}
	
	public static void changeInfo(User oldUser, User changedUser) throws Exception{
		MaintainUser maintain = new MaintainUser();
		
		try {
			maintain.load();
			
			for (int i = 0; i < maintain.users.size(); i++) {
				if(maintain.users.get(i).name.equals(oldUser.name)) {
	//				maintain.users.remove(i);
	//				maintain.users.add(changedUser);
					maintain.users.get(i).setName(changedUser.name);
					maintain.users.get(i).setPassword(changedUser.password);
					maintain.users.get(i).setAddress(changedUser.address);
					maintain.users.get(i).setEmail(changedUser.email);
					break;
				}
			}

			
			maintain.update();
			
			for(User u: maintain.users){
				System.out.println(u.toString());
			}
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	
	public static void deleteAccount(User user) throws Exception{
		MaintainUser maintain = new MaintainUser();
		
		try {
			maintain.load();
			
			for (int i = 0; i < maintain.users.size(); i++) {
				if(maintain.users.get(i).name.equals(user.name)) {
					maintain.users.get(i).setName(null);
					maintain.users.get(i).setPassword(null);
					maintain.users.get(i).setAddress(null);
					maintain.users.get(i).setEmail(null);
					maintain.users.remove(i);
				}
			}

			
			maintain.update();
			
			for(User u: maintain.users){
				System.out.println(u.toString());
			}
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static String getMap(String storeAddress) throws Exception{
		MaintainItem maintain = new MaintainItem();
		String map = "";
		
		try {
			maintain.load();
			
			for (Item i : maintain.items) {
				if (i.storeAddress.equals(storeAddress)) 
					map += "Item :  " + i.itemName+ ",  Size : " + i.itemSize + ",  Catagory : " + i.category + ",  Coordinate[x,y] : [ " + i.mapX + ", " + i.mapY + "]." + "\n";
				
			}
	
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return map;
	}
	
	
}
