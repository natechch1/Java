package final_Project;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class MaintainUser {
	public ArrayList<User> users = new ArrayList<User>();
	String path = Path.user;
	
	public void load() throws Exception{
		CsvReader reader = new CsvReader(path); 
		reader.readHeaders();
		
		while(reader.readRecord()){ 
			User user = new User();
			//name,id,email,password
			user.setType(reader.get("type"));
			user.setName(reader.get("name"));
			user.setPassword(reader.get("password"));
			user.setAddress(reader.get("address"));
			user.setEmail(reader.get("email"));
			user.setPreferenceStore(reader.get("preferenceStore"));
			users.add(user);
		}
	}
	
	public void update() throws Exception{
		try {		
				CsvWriter csvOutput = new CsvWriter(new FileWriter(path, false), ',');
				//name,id,email,password
				csvOutput.write("type");
				csvOutput.write("name");
				csvOutput.write("password");
				csvOutput.write("address");
		    	csvOutput.write("email");
		    	csvOutput.write("preferenceStore");
				csvOutput.endRecord();

				// else assume that the file already has the correct header line
				// write out a few records
				for(User u: users){
					csvOutput.write(u.getType());
					csvOutput.write(u.getName());
					csvOutput.write(u.getPassword());
					csvOutput.write(u.getAdress());
					csvOutput.write(u.getEmail());
					csvOutput.write(u.getPreferenceStore());
					csvOutput.endRecord();
				}
				csvOutput.close();
			
			}catch (Exception e) {
				e.printStackTrace();
			}
	}

}
