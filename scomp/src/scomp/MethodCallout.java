package scomp;
import static scomp.Tools.*;
import java.util.List;

/**
 * The Decaf method callout.
 * 
 * @author Wilson (creation 2010-07-19)
 */

public class MethodCallout extends MethodCall{
	
	private final String stringLiteral;
	private final List<CalloutArgument> calloutArguments;
	
	public MethodCallout(final String stringLiteral, final List<CalloutArgument> calloutArguments) {
		this.stringLiteral = stringLiteral;
		this.calloutArguments = emptyIfNull(calloutArguments);
	}
	
	public final String getStringLiteral(){
		return this.stringLiteral;
	}
	
	public final List<CalloutArgument> getCalloutArguments(){
		return this.calloutArguments;
	}
}
