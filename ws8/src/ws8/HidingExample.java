package ws8;

public class HidingExample {

	public static void main(String[] args) {
	Q4 obj1 = new Q4();
	obj1.printCount();
	Q5 obj2 = new Q5();
	obj2.printCount();
	Q6 obj3 = new Q6();
	obj3.printCount();

	}

}
class Q4{
	static int counter = 0;
	public Q4 () {
		counter++;
	}
	static void printCount() {
		System.out.println(counter);
	}
}
class Q5 extends Q4{
	static int counter = 0;
	public Q5() {
		counter++;
	}
	static void printCount() {
		System.out.println(counter);
	}
}
class Q6 extends Q4{
	public Q6() {
		counter++;
	}
	static void printCount() {
		System.out.println(counter);
	}
}