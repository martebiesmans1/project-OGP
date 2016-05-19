package hillbillies.model.programs.statements;

import hillbillies.model.Task;
import hillbillies.model.programs.expressions.Expression;
import hillbillies.model.Counter;

public class PrintStatement extends Statement {
	
	private PrintStatement(Expression<?> expression, boolean hasBeenFullyExecuted) {
		super(hasBeenFullyExecuted);
		this.expression = expression;
	}

	public PrintStatement(Expression<?> expression) {
		this(expression, false);
	}
	
	public Expression<?> getExpression() {
		return expression;
	}
	
	private final Expression<?> expression;

	@Override
	public void execute(Task task, Counter counter) {
		counter.increment();
		System.out.println(expression.evaluate(task).toString());
		super.SetHasFullyExecutedToTrue();
	}

	@Override
	public boolean canExecute(Task task, Counter counter) {
		counter.increment();
		if (counter.getCount() > 1000 || hasBeenFullyExecuted()) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isWellFormed() {
		return true;
	}

	@Override
	public boolean containsActionStatement() {
		return false;
	}

	@Override
	public PrintStatement clone() {
		return new PrintStatement(getExpression(), hasBeenFullyExecuted());
	}

	
//	public PrintStatement(Expression<?> value) {
//		this.message = value;
//	}
//	
//	public final Expression<?> getMessage() {
//		return this.message;
//	}
//	
//	final Expression<?> message;
//	
//	@Override
//	public void execute() {
//		System.out.println(this.getMessage().evaluate(getTask()).toString());
//		this.setCompleted(true);
//	}
//
//	@Override
//	public boolean isMutable() {
//		return true;
//	}
	
}
