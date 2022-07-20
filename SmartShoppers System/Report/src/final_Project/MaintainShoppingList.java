package final_Project;

import java.io.FileWriter;
import java.util.ArrayList;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class MaintainShoppingList {
	// maintain store
	public ArrayList<ShoppingList> shoppingLists = new ArrayList<ShoppingList>();
	String path = Path.shoppingList;
	
	public void load() throws Exception{
		CsvReader reader = new CsvReader(path); 
		reader.readHeaders();
		
		while(reader.readRecord()){ 
			ShoppingList shoppingList = new ShoppingList();
			//name,id,email,password
			shoppingList.setCustomerName(reader.get("customerName"));
			shoppingList.setStoreAddress(reader.get("storeAddress"));
			shoppingList.setItemName(reader.get("itemName"));
			shoppingList.setQuantity(Integer.parseInt(reader.get("quantity")));
			shoppingList.setPrice(Double.parseDouble(reader.get("price")));
			shoppingList.setSize(reader.get("size"));
			shoppingList.setMapX(reader.get("mapX"));
			shoppingList.setMapY(reader.get("mapY"));
			shoppingLists.add(shoppingList);
		}
	}
	
	public void update() throws Exception{
		try {		
				CsvWriter csvOutput = new CsvWriter(new FileWriter(path, false), ',');
				//name,id,email,password
				csvOutput.write("customerName");
				csvOutput.write("storeAddress");
				csvOutput.write("itemName");
				csvOutput.write("quantity");
				csvOutput.write("price");
				csvOutput.write("size");
				csvOutput.write("mapX");
				csvOutput.write("mapY");
				csvOutput.endRecord();

				// else assume that the file already has the correct header line
				// write out a few records
				for(ShoppingList s: shoppingLists){
					csvOutput.write(s.getCustomerName());
					csvOutput.write(s.getStoreAddress());
					csvOutput.write(s.getItemName());
					csvOutput.write(String.valueOf(s.getQuantity()));
					csvOutput.write(String.valueOf(s.getPrice()));
					csvOutput.write(s.getSize());
					csvOutput.write(s.getMapX());
					csvOutput.write(s.getMapY());
					csvOutput.endRecord();
				}
				csvOutput.close();
			
			}catch (Exception e) {
				e.printStackTrace();
			}
	}
}
