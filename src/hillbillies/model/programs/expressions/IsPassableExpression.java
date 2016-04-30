package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.Unit;
import hillbillies.model.programs.type.BooleanType;
import hillbillies.model.programs.type.CubeType;

public class IsPassableExpression extends BooleanExpression {

	public IsPassableExpression(CubeExpression e) {
		expression = e;
	}
	
	public CubeExpression getExpression() {
		return expression;
	}
	
	public CubeType getExpressionEvaluate(Unit unit) {
		return getExpression().evaluate(unit);
	}
	
	private final CubeExpression expression;
	
	@Override
	public BooleanType evaluate(Unit unit) {
		return new BooleanType(((Cube) getExpressionEvaluate(unit).getValue()).isPassableIn(unit.getWorld()));
	}
	
	@Override
	public String toString(){
		return getExpression().toString()+"isPassable";
	}

}
