package final_Project;

import java.util.ArrayList;
import java.util.UUID;

public class Item {
	public String storeAddress;
	public String itemID;
	public String itemName;
	public String itemSize;
	public String category;
	public int quantity;
	public double price;
	public String discount;
	public String mapX;
	public String mapY;
	public String description;
	
	public Item(String storeAddress, String itemID, String itemName, String itemSize, String category, int quantity, double price, String discount, String mapX, String mapY, String description) {
		super();
		this.storeAddress = storeAddress;
		this.itemID = itemID;
		this.itemName = itemName;
		this.itemSize = itemSize;
		this.category = category;
		this.quantity = quantity;
		this.price = price;
		this.discount = discount;
		this.mapX = mapX;
		this.mapY = mapY;
		this.description = description;
	}
	
	public Item(){
		super();
	}
	
	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAdress) {
		this.storeAddress = storeAdress;
	}
	
	public String getItemID() {
		return itemID;
	}

	public void setItemID(String itemID) {
		this.itemID = itemID;
	}
	
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public String getItemSize() {
		return itemSize;
	}

	public void setItemSize(String itemSize) {
		this.itemSize = itemSize;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void updateQuantity(int up) {
		this.quantity += up;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}
	
	public String getMapX() {
		return mapX;
	}

	public void setMapX(String mapX) {
		this.mapX = mapX;
	}
	
	public String getMapY() {
		return mapY;
	}

	public void setMapY(String mapY) {
		this.mapY = mapY;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "Item [StoreAddress="  + storeAddress + ", ItemID=" + itemID + ", itemName=" + itemName + ", itemSize=" + itemSize + ", category=" + category + ", quantity=" + quantity + ", price=" + price + ", Discount=" + discount + ", MapX=" + mapX + ", MapY=" + mapY + ", Description=" + description + "]";
	}
}
