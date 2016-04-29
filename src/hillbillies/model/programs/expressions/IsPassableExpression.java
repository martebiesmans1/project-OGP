package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.Unit;

public class IsPassableExpression extends UnaryExpression {

	public IsPassableExpression(CubePositionExpression e) {
		super(e);
	}
	
	@Override
	public Boolean evaluate(Unit unit) {
		return (((Cube) getExpressionEvaluate(unit)).isPassableIn(unit.getWorld()));
	}
	
	@Override
	public String toString(){
		return getExpression().toString()+"isPassable";
	}

}