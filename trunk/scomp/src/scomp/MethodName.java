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
	private final List<Expression> expressions;
	
	public MethodName(final String methodName, List<Expression> expressions) {
		this.methodName = methodName;
		this.expressions = emptyIfNull(expressions);
	}
	
	public final String getMethodName() {
		return this.methodName;
	}
	
	public final List<Expression> getExpressions() {
		return this.expressions;
	}
}
