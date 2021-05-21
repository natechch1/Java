package ws9;

public class Q2 {
	

		interface A{
			int var = 0;
			int getVar();
		}
		interface B{
			int var = 10;
			int getVar();
		}
		class C implements A, B{
			public int getVar() {
				return B.var;
			}
		}	
		
	}


