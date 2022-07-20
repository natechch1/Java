package final_Project;

public class Customer extends Account {
	
	// for administrator add new store and check if already exist this store
		public static Boolean checkIsNewList(ShoppingList s) throws Exception{
			MaintainShoppingList maintain = new MaintainShoppingList();
			
			try {
				maintain.load();
				
				for (int i = 0; i < maintain.shoppingLists.size(); i++) {
					if(maintain.shoppingLists.get(i).itemName.equals(s.itemName) && maintain.shoppingLists.get(i).size.equals(s.size) && maintain.shoppingLists.get(i).storeAddress.equals(s.storeAddress) && maintain.shoppingLists.get(i).customerName.equals(s.customerName)) 
						return false;
				}
				maintain.update();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return true;
		}
		
	public static void addShoppingList(ShoppingList s) throws Exception{
		MaintainShoppingList maintain = new MaintainShoppingList();
		
		try {
			maintain.load();
			maintain.shoppingLists.add(s);
			maintain.update();
			
			for(ShoppingList sl : maintain.shoppingLists){
				System.out.println(sl.toString());
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static void updateQuantity(ShoppingList sl) throws Exception{
		MaintainShoppingList maintain = new MaintainShoppingList();
		
		try {
			maintain.load();
			
			for (int i = 0; i < maintain.shoppingLists.size(); i++) {
				if(maintain.shoppingLists.get(i).itemName.equals(sl.itemName) && maintain.shoppingLists.get(i).size.equals(sl.size) && maintain.shoppingLists.get(i).storeAddress.equals(sl.storeAddress) && maintain.shoppingLists.get(i).customerName.equals(sl.customerName)) {
					maintain.shoppingLists.get(i).setCustomerName(sl.customerName);
					maintain.shoppingLists.get(i).setStoreAddress(sl.storeAddress);
					maintain.shoppingLists.get(i).setItemName(sl.itemName);
					maintain.shoppingLists.get(i).setQuantity(sl.quantity);
					maintain.shoppingLists.get(i).setPrice(sl.price);
					maintain.shoppingLists.get(i).setSize(sl.size);
					maintain.shoppingLists.get(i).setMapX(sl.mapX);
					maintain.shoppingLists.get(i).setMapY(sl.mapY);
					break;
				}
			}
			
			maintain.update();
			
			for(ShoppingList s: maintain.shoppingLists){
				System.out.println(s.toString());
			}
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static void deleteShoppingList(String itemName, String size , String storeAddress, String customerName) throws Exception{
		MaintainShoppingList maintain = new MaintainShoppingList();
		
		try {
			maintain.load();
			System.out.println(" Here : " + itemName +", " + size + ", " + storeAddress + ", " + customerName);
			for (int i = 0; i < maintain.shoppingLists.size(); i++) {
				if(maintain.shoppingLists.get(i).itemName.equals(itemName) && maintain.shoppingLists.get(i).size.equals(size) && maintain.shoppingLists.get(i).storeAddress.equals(storeAddress) && maintain.shoppingLists.get(i).customerName.equals(customerName)) {
					maintain.shoppingLists.get(i).setCustomerName(null);
					maintain.shoppingLists.get(i).setStoreAddress(null);
					maintain.shoppingLists.get(i).setItemName(null);
					maintain.shoppingLists.get(i).setQuantity(0);
					maintain.shoppingLists.get(i).setPrice(0);
					maintain.shoppingLists.get(i).setSize(null);
					maintain.shoppingLists.get(i).setMapX(null);
					maintain.shoppingLists.get(i).setMapY(null);
					System.out.println("We are going side");
					maintain.shoppingLists.remove(i);
				}
			}
			maintain.update();
			
			for(ShoppingList s: maintain.shoppingLists){
				System.out.println(s.toString());
			}
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static double getTotalPrice(String name, String storeAddress) throws Exception{
		MaintainShoppingList maintain = new MaintainShoppingList();
		double total= 0;
		
		try {
			maintain.load();
			for (int i = 0; i < maintain.shoppingLists.size(); i++) {
				if (maintain.shoppingLists.get(i).customerName.equals(name) && maintain.shoppingLists.get(i).storeAddress.equals(storeAddress)) 
					total += maintain.shoppingLists.get(i).price * maintain.shoppingLists.get(i).quantity;
			}
			 	
			
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return total;
	}
	
}
