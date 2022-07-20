package final_Project;

import java.io.FileWriter;
import java.util.ArrayList;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class MaintainStore {
	// maintain store
	public ArrayList<Store> stores = new ArrayList<Store>();
	String path = Path.store;
	
	public void load() throws Exception{
		CsvReader reader = new CsvReader(path); 
		reader.readHeaders();
		
		while(reader.readRecord()){ 
			Store store = new Store();
			//name,id,email,password
			store.setAddress(reader.get("address"));
			store.setManagerName(reader.get("manager"));
			store.setOpenTime(reader.get("openTime"));
			store.setCloseTime(reader.get("closeTime"));
			stores.add(store);
		}
	}
	
	public void update() throws Exception{
		try {		
				CsvWriter csvOutput = new CsvWriter(new FileWriter(path, false), ',');
				//name,id,email,password
				csvOutput.write("address");
				csvOutput.write("manager");
				csvOutput.write("openTime");
				csvOutput.write("closeTime");
				csvOutput.endRecord();

				// else assume that the file already has the correct header line
				// write out a few records
				for(Store s: stores){
					csvOutput.write(s.getAddress());
					csvOutput.write(s.getManagerName());
					csvOutput.write(s.getOpenTime());
					csvOutput.write(s.getCloseTime());
					csvOutput.endRecord();
				}
				csvOutput.close();
			
			}catch (Exception e) {
				e.printStackTrace();
			}
	}
}
