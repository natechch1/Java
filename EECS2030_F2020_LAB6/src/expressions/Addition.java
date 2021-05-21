package expressions;

public class Addition extends Expression{

	Addition(int left, int right) {
		super(left, right);
	}
	public void evaluate() {
		this.value = this.left + this.right;
	}
}