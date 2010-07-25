package scomp;

import static org.kohsuke.args4j.ExampleMode.ALL;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

/*
 * This is the command line interpreter for scomp
 * 
 * @author js94065 (creation 2010-07-25)
 */

public class Compiler {
	
	@Option(name="-o", usage="Write output to file.")
    private String out;

	@Option(name="-target", usage="Target stage is one of scan, parse, inter, " +
			"or assembly.")
    private String target;
	
	@Option(name="-debug", usage="Print debugging information.")
	private Boolean debug;
	
	// receives other command line parameters than options
    @Argument
    private List<String> arguments = new ArrayList<String>();
    
    public static void main(String[] args) throws IOException {
    	(new scomp.Compiler()).doMain(args);
    }
    
    public void doMain(String[] args) throws IOException {
        CmdLineParser parser = new CmdLineParser(this);
        
        // if you have a wider console, you could increase the value;
        // here 80 is also the default
        parser.setUsageWidth(80);

        try {
            // parse the arguments.
            parser.parseArgument(args);

            // you can parse additional arguments if you want.
            // parser.parseArgument("more","args");

            // after parsing arguments, you should check
            // if enough arguments are given.
            if( arguments.isEmpty() )
                throw new CmdLineException("No argument is given");

        } catch( CmdLineException e ) {
            // if there's a problem in the command line,
            // you'll get this exception. this will report
            // an error message.
            System.err.println(e.getMessage());
            System.err.println("java Compiler [options...] inputFile");
            // print the list of available options
            parser.printUsage(System.err);
            System.err.println();

            // print option sample. This is useful some time
            System.err.println("  Example: java Compiler "+parser.printExample(ALL)+ " inputFile");

            return;
        }

        // this will redirect the output to the specified output
        // System.out.println(out);

        if (out!=null) {
        	System.out.println("Output file will be " + out);
        }
            
        if (target!=null) {
	    	if (target.equals("scan")) {
	        	// todo: run scan code
	        	System.out.println("scan stage");
	        } 
	        else if (target.equals("parse")) {
	        	// todo: run parse code
	        	System.out.println("parse stage");
	        }
	        else if (target.equals("inter")) {
	        	// todo: run inter code
	        	System.out.println("inter stage");
	        }
	        else if (target.equals("assembly")) {
	        	// todo: run assembly code
	        	System.out.println("assembly stage");
	        }
	        else {
	        	System.out.println("Invalid target stage name given.  Stage should " +
	        			"be one of scan, parse, inter, or assembly.");
	        }
        }
                
        if (debug) {
        	System.out.println("Debug enabled.");
    		// do debugging stuff
    	}
        
        // access non-option arguments
        if (arguments.size() != 1) {
        	System.out.println("You must have exactly one input file.");
        } else {
        	System.out.println("Input file was " + arguments.get(0));
        }
    }

}
