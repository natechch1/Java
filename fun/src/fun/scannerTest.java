package fun;
import java.util.Scanner; // import the Scanner class 

public class scannerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		   Scanner userObj = new Scanner(System.in);
		   String userName;
		   int userAge;
		   System.out.println("Enter user name:");
		   userName = userObj.nextLine();
		   System.out.println("Enter user age:");
		   userAge = userObj.nextInt();
		    System.out.println("Username is: " + userName + "\n" + "User age is: " + userAge);        
		  }
		
	}


