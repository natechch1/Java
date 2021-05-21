package fun;

public class EECS2021count {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String inputNum = "01000000100010100101001100110011";
		
		if(inputNum.length() == 32) {
			 System.out.println("opcode: " + inputNum.substring(25, 32));
			 System.out.println("rd: " + inputNum.substring(20, 25));
			 System.out.println("fun3: " + inputNum.substring(17, 20));
			 System.out.println("rs1: " + inputNum.substring(12, 17));
			 System.out.println("rs2: " + inputNum.substring(7, 12));
			 System.out.println("func7: " + inputNum.substring(0, 7));
		}
		else {
			System.out.println("add 0's number : " + (32 - inputNum.length()));
			System.out.println("not 32  zi ji suan");
		}
	}
}
