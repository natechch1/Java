package expressions;

public class AddAll extends ExpressionCollector{
	public void evaluate() {
		int output = 0;
		for (int i = 0; i < expressions.size(); i++) {
			expressions.get(i).evaluate();
			output += (int) expressions.get(i).getValue();
		}
		this.value = output;
	}
}
