package scomp;
import static scomp.Tools.*;
import java.util.List;

/**
 * The Decaf method name.
 * 
 * @author Wilson (creation 2010-07-19)
 */

public class MethodName extends MethodCall{
	
	private final String methodName;
	private final List<AbstractExpression> abstractExpressions;
	
	public MethodName(final String methodName, List<AbstractExpression> abstractExpressions) {
		this.methodName = methodName;
		this.abstractExpressions = emptyIfNull(abstractExpressions);
	}
	
	public final String getMethodName() {
		return this.methodName;
	}
	
	public final List<AbstractExpression> getExpressions() {
		return this.abstractExpressions;
	}
}
