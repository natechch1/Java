package Test;

import static org.junit.Assert.*;

import java.util.Iterator;

import final_Project.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserTest {
	User user;

	@Before
	public void setUp() throws Exception {
		user = new User("Customer", "HongceChen", "123", "Toronto", "hongcechen@gmail.com","");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void newUserTest() {
		try {
			String userName = "askdfhasifhwqioerjq";
			Boolean result =  Account.checkIsNewUser(userName);
			assertEquals(true, result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void createAccountTest() {
		MaintainUser maintain = new MaintainUser();
		user = new User("Customer", "HongceChen", "123", "Toronto", "hongcechen@gmail.com","");
		try {
			maintain.load();
			Account.createAccount(user);
			for(User u : maintain.users) {
				if (u.equals(user)) 
					assertEquals(u, user);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void loginTest() {
		Boolean result = true;
		try {
			result = Account.login("nate", "123");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(true, result);
	}
	
	@Test
	public void changeInfoTest() {
		user = new User("Customer", "HongceChen", "123", "Toronto", "hongcechen@gmail.com","");
		MaintainUser maintain = new MaintainUser();
		User newUser  = new User("Customer", "123", "123", "123", "123@123","123");
		try {
			Account.createAccount(user);
			Account.changeInfo(user, newUser);
			maintain.load();
			for(User u : maintain.users) {
				if (u.equals(newUser)) 
					assertEquals(u, newUser);
			}
			Account.deleteAccount(user);
			Account.deleteAccount(newUser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void deleteTest() {
		MaintainUser maintain = new MaintainUser();
		Boolean result = true;
		User user1 = new User("Administrator", "omen", "123", "Toronto", "omen@gmail.com","");
		try {
			Account.createAccount(user1);
			Account.deleteAccount(user1);
			Account.deleteAccount(user);
			maintain.load();
			for(User u : maintain.users) {
				if (u.equals(user)) result = false;
				if (u.equals(user1)) result = false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(true, result);
		
	}
	
	@Test
	public void storeTest() {
		user = new User("Administrator", "omen", "123", "Toronto", "omen@gmail.com","");
		MaintainStore maintain = new MaintainStore();
		Boolean result = true;
		Store store = new Store("25 Greenview ave", "nate", "12:30", "23:00");
		Store newStore = new Store("25 Greenview ave", "nate", "7:30", "23:00");
		try {
			Account.createAccount(user);
			
			result = Administrator.checkIsNewManager("asdfasdfqwerqwefasdff");
			Administrator.addStore(store);
			if (Manager.getStoreAdress("nate").equals("25 Greenview ave")) 
				result = true;
			if (Manager.getStoreAdress("sadfsfvxcvaetqwertqetqt").equals("Sorry you can't manage any store.")) {
				result = true;
			}
			
			Administrator.updateStore(store, newStore);
			maintain.load();
			for(Store s : maintain.stores) {
				if (s.equals(newStore)) {
					result = true;
				}
			}
			Administrator.deleteStore("25 Greenview ave");
			result = Administrator.checkIsNewStore("25 Greenview ave");
			Account.deleteAccount(user);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(true, result);
		
	}
	
	@Test
	public void itemTest() {
		MaintainItem maintain = new MaintainItem();
		Store store = new Store("25 green", "nate", "12:30", "23:00");
		Item item = new Item("25 green", "123", "coke", "Large", "drink", 2, 3.00, "Yes", "2", "3", "This is drink");
		Item newItem = new Item("25 green", "123", "coke", "Large", "drink", 2, 3.00, "Yes", "2", "3", "This is drink");
		Boolean result = true;
		
		try {
			result = Administrator.checkIsNewManager("adasdfasfweqrrq");
			Administrator.addStore(store);
			Administrator.addItem(item);
			Administrator.updateItem(newItem);
			maintain.load();
			for(Item i : maintain.items) {
				if (i.equals(newItem)) {
					result = true;
				}
			}
			String map = Account.getMap("25 green");
			assertEquals(map, "Item :  coke,  Size : Large,  Catagory : drink,  Coordinate[x,y] : [ 2, 3].\n");
			Administrator.deleteStore("25 green");
			Administrator.deleteItem("coke", "25 green", "Large");
			result = Administrator.checkIsNewItem("coke", "25 green", "Large");
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(true, result);
		
	}
	
	@Test
	public void customerTest() {
		MaintainShoppingList maintain = new MaintainShoppingList();
		ShoppingList sl1 = new ShoppingList("nate", "finch", "fish", 2, 2, "Large", "2", "3");
		ShoppingList sl2 = new ShoppingList("nate", "finch", "chip", 2, 2, "Large", "2", "3");
		Boolean result = true;
		
		try {
			result = Customer.checkIsNewList(sl1);
			result = Customer.checkIsNewList(sl2);
			Customer.addShoppingList(sl1);
			Customer.addShoppingList(sl2);
			Customer.updateQuantity(sl1);
			Customer.updateQuantity(sl2);
			maintain.load();
			for(ShoppingList s : maintain.shoppingLists) {
				if (!s.equals(sl1) || !s.equals(sl2) ) {
					result = true;
				}
			}
			Customer.getTotalPrice("nate", "finch");
			Customer.deleteShoppingList("fish", "Large", "finch", "nate");
			Customer.deleteShoppingList("chip", "Large", "finch", "nate");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(true, result);
		
	}

}
