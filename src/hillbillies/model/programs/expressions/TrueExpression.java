package hillbillies.model.programs.expressions;

import hillbillies.model.Unit;
import hillbillies.model.programs.type.BooleanType;

public class TrueExpression extends BooleanExpression {

	public TrueExpression() {
	}

	@Override
	public BooleanType evaluate(Unit unit) {
		return new BooleanType(true);
	}
	
	@Override
	public String toString() {
		return "true";
	}

}
