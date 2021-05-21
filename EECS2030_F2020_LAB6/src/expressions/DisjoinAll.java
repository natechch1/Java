package expressions;

public class DisjoinAll extends ExpressionCollector{
	public void evaluate() {
		boolean result = false;
		for (int i = 0; i < expressions.size(); i++) {
			expressions.get(i).evaluate();
			result = (boolean) expressions.get(i).getValue() || result;
		}
		this.value = result;
	}
}
