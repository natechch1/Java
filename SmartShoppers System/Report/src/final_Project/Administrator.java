package final_Project;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class Administrator extends Account{
	// for administrator add new store and check if already exist this store
	public static Boolean checkIsNewStore(String address) throws Exception{
		MaintainStore maintain = new MaintainStore();
		
		try {
			maintain.load();
			
			for (int i = 0; i < maintain.stores.size(); i++) {
				if(maintain.stores.get(i).address.equals(address)) 
					return false;
			}
			maintain.update();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return true;
	}
	
	public static Boolean checkIsNewItem(String itemName, String storeAddress, String size) throws Exception{
		MaintainItem maintain = new MaintainItem();
		
		try {
			maintain.load();
			
			for (int i = 0; i < maintain.items.size(); i++) {
				if(maintain.items.get(i).itemName.equals(itemName) && maintain.items.get(i).itemSize.equals(size) && maintain.items.get(i).storeAddress.equals(storeAddress)) 
					return false;
			}
			maintain.update();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return true;
	}
	
	public static Boolean checkIsNewManager(String manager) throws Exception{
		MaintainStore maintain = new MaintainStore();
		
		try {
			maintain.load();
			
			for (int i = 0; i < maintain.stores.size(); i++) {
				if(maintain.stores.get(i).managerName.equals(manager)) 
					return false;
			}
			maintain.update();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return true;
	}
	
	
	// input should be all Store info
	public static void addStore(Store store) throws Exception{
		MaintainStore maintain = new MaintainStore();
		
		
		try {
			maintain.load();
			maintain.stores.add(store);
			maintain.update();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	// input should be all Store info
		public static void addItem(Item item) throws Exception{
			MaintainItem maintain = new MaintainItem();
			
			try {
				maintain.load();
				maintain.items.add(item);
				maintain.update();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	
	public static void updateStore(Store oldStore, Store newStore) throws Exception{
		MaintainStore maintain = new MaintainStore();
		
		try {
			maintain.load();
			
			for (int i = 0; i < maintain.stores.size(); i++) {
				if(maintain.stores.get(i).address.equals(oldStore.address)) {
					maintain.stores.get(i).setAddress(newStore.address);
					maintain.stores.get(i).setManagerName(newStore.managerName);
					maintain.stores.get(i).setOpenTime(newStore.openTime);
					maintain.stores.get(i).setCloseTime(newStore.closeTime);
					break;
				}
			}
			maintain.update();
			
			for(Store s: maintain.stores){
				System.out.println(s.toString());
			}
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static void deleteStore(String address) throws Exception{
		MaintainStore maintain = new MaintainStore();
		
		try {
			maintain.load();
			
			for (int i = 0; i < maintain.stores.size(); i++) {
				if(maintain.stores.get(i).address.equals(address)) {
					maintain.stores.get(i).setAddress(null);
					maintain.stores.get(i).setManagerName(null);
					maintain.stores.get(i).setOpenTime(null);
					maintain.stores.get(i).setCloseTime(null);
					maintain.stores.remove(i);
				}
			}
			maintain.update();
			
			for(Store s: maintain.stores){
				System.out.println(s.toString());
			}
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static void updateItem(Item newItem) throws Exception{
		MaintainItem maintain = new MaintainItem();
		
		try {
			maintain.load();
			
			for (int i = 0; i < maintain.items.size(); i++) {
				if(maintain.items.get(i).itemID.equals(newItem.itemID)) {
					maintain.items.get(i).setItemSize(newItem.itemSize);
					maintain.items.get(i).setCategory(newItem.category);
					maintain.items.get(i).setQuantity(newItem.quantity);
					maintain.items.get(i).setPrice(newItem.price);
					maintain.items.get(i).setDiscount(newItem.discount);
					maintain.items.get(i).setMapX(newItem.mapX);
					maintain.items.get(i).setMapY(newItem.mapY);
					maintain.items.get(i).setDescription(newItem.description);;
					break;
				}
			}

			
			maintain.update();
			
			for(Item i: maintain.items){
				System.out.println(i.toString());
			}
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static void deleteItem(String iName, String storeAddress, String itemSize) throws Exception{
		MaintainItem maintain = new MaintainItem();
		
		try {
			maintain.load();
			
			for (int i = 0; i < maintain.items.size(); i++) {
				if(maintain.items.get(i).itemName.equals(iName) && maintain.items.get(i).storeAddress.equals(storeAddress) && maintain.items.get(i).itemSize.equals(itemSize)) {
					maintain.items.get(i).setItemID(null);
					maintain.items.get(i).setItemName(null);;
					maintain.items.get(i).setItemSize(null);
					maintain.items.get(i).setCategory(null);
					maintain.items.get(i).setQuantity(0);
					maintain.items.get(i).setPrice(0);
					maintain.items.get(i).setDiscount(null);
					maintain.items.get(i).setMapX(null);
					maintain.items.get(i).setMapY(null);
					maintain.items.get(i).setDescription(null);;
					maintain.items.remove(i);
				}
			}
			maintain.update();
			
			for(Item i: maintain.items){
				System.out.println(i.toString());
			}
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
}
