package expressions;

public class Subtraction extends Expression{

	Subtraction(int left, int right) {
		super(left, right);
	}
	public void evaluate() {
		this.value = this.left - this.right;
	}
}
