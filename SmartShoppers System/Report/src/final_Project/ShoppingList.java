package final_Project;

public class ShoppingList {
	public String customerName;
	public String storeAddress;
	public String itemName;
	public int quantity;
	public double price;
	public String size;
	public String mapX;
	public String mapY;
	
	public ShoppingList(String customerName, String storeAddress, String itemName, int quantity, double price, String size, String mapX, String mapY) {
		super();
		this.customerName = customerName;
		this.storeAddress = storeAddress;
		this.itemName = itemName;
		this.quantity = quantity;
		this.price = price;
		this.size = size;
		this.mapX = mapX;
		this.mapY = mapY;
	}
	
	public ShoppingList(){
		super();
	}
	
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}
	
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
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
	
	@Override
	public String toString() {
		return "ShoppingList [customerName="  + customerName + ", storeAddress=" + storeAddress + ", ItemName=" + itemName + ", Quantity=" + quantity + ", Price=" + price + ", Size=" + size + ", MapX=" + mapX + ", MapY=" + mapY + "]";
	}
}
