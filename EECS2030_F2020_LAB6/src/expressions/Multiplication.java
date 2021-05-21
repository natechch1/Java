package expressions;

public class Multiplication extends Expression{

	Multiplication(int left, int right) {
		super(left, right);
	}
	public void evaluate() {
		this.value = this.left * this.right;
	}
}
