package final_Project;

import java.io.FileWriter;
import java.util.ArrayList;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class MaintainItem {
	// maintain store
	public ArrayList<Item> items = new ArrayList<Item>();
	String path = Path.item;
	
	public void load() throws Exception{
		CsvReader reader = new CsvReader(path); 
		reader.readHeaders();
		
		while(reader.readRecord()){ 
			Item item = new Item();
			//name,id,email,password
			item.setStoreAddress(reader.get("storeAddress"));
			item.setItemID(reader.get("itemID"));
			item.setItemName(reader.get("itemName"));
			item.setItemSize(reader.get("itemSize"));
			item.setCategory(reader.get("category"));
			item.setQuantity(Integer.parseInt(reader.get("quantity")));
			item.setPrice(Double.parseDouble(reader.get("price")));
			item.setDiscount(reader.get("discount"));
			item.setMapX(reader.get("mapX"));
			item.setMapY(reader.get("mapY"));
			item.setDescription(reader.get("description"));
			items.add(item);
		}
	}
	
	public void update() throws Exception{
		try {		
				CsvWriter csvOutput = new CsvWriter(new FileWriter(path, false), ',');
				//name,id,email,password
				csvOutput.write("storeAddress");
				csvOutput.write("itemID");
				csvOutput.write("itemName");
				csvOutput.write("itemSize");
				csvOutput.write("category");
				csvOutput.write("quantity");
				csvOutput.write("price");
				csvOutput.write("discount");
				csvOutput.write("mapX");
				csvOutput.write("mapY");
				csvOutput.write("description");
				csvOutput.endRecord();

				// else assume that the file already has the correct header line
				// write out a few records
				for(Item i : items){
					csvOutput.write(i.getStoreAddress());
					csvOutput.write(i.getItemID());
					csvOutput.write(i.getItemName());
					csvOutput.write(i.getItemSize());
					csvOutput.write(i.getCategory());
					csvOutput.write(String.valueOf(i.getQuantity()));
					csvOutput.write(String.valueOf(i.getPrice()));
					csvOutput.write(i.getDiscount());
					csvOutput.write(i.getMapX());
					csvOutput.write(i.getMapY());
					csvOutput.write(i.getDescription());
					csvOutput.endRecord();
				}
				csvOutput.close();
			
			}catch (Exception e) {
				e.printStackTrace();
			}
	}
}
