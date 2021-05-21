import eecs2030.lab1.Worksheet4_1;

public class  x {
	public static class Worksheet4_1 {
		int counter = 0;
		int timer;
		public static void main (String [] args) {
		Worksheet4_1 object1 = new Worksheet4_1();
		object1.setCoutner();
		object1.setTimer(10);
		System.out.println("Counter = " + object1.counter + "\tTimer = " + object1.timer);
		Worksheet4_1 object2 = new Worksheet4_1();
		object2.setCoutner();
		object2.setTimer(20);
		System.out.println("Counter = " + object2.counter + "\tTimer = " + object2.timer);
		object2.resetCounter();
		System.out.println();
		}
		public Worksheet4_1() {
		this.timer = 0;
		}
		public void setCoutner () {
		this.counter++;
		}
		public void resetCounter() {
		this.counter = 0;
		}
		public void setTimer(int second) {
		this.timer = second;
		}
		}
}
