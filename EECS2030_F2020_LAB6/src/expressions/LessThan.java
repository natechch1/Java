package expressions;

public class LessThan extends Expression{

	LessThan(int left, int right) {
		super(left, right);
	}
	public void evaluate() {
		this.value = (this.left < this.right);
	}
}