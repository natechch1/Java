package expressions;

public class TimesAll extends ExpressionCollector{
	public void evaluate() {
		int output = 1;
		for (int i = 0; i < expressions.size(); i++) {
			expressions.get(i).evaluate();
			output *= (int) expressions.get(i).getValue();
		}
		this.value = output;
	}
}
