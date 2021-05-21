package fun;
import java.util.Random;
public class randomTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random rand = new Random();
		String[] name = {"Nate","Zoe","Keith"};
		for(int i = 0; i < 3; i++) {
			int intRan = rand.nextInt(101);
			System.out.println(name[i] + ": " + intRan);
		}
		
	}

}
