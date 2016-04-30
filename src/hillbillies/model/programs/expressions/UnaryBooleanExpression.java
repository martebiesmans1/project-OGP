package hillbillies.model.programs.expressions;

import hillbillies.model.Unit;

public abstract class UnaryBooleanExpression extends BooleanExpression {

	protected UnaryBooleanExpression(Expression e) {
		expression = e;
	}
	
	public Expression getExpression() {
		return expression;
	}
	
	public Object getExpressionEvaluate(Unit unit) {
		return getExpression().evaluate(unit);
	}
	
	private final Expression expression;

}