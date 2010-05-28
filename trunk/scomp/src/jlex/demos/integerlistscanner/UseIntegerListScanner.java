package jlex.demos.integerlistscanner;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * @author js94065 (creation 2010-05-28)
 *
 */

public class UseIntegerListScanner {
    /**
     * @param arguments
     * <br>Unused
     */
    public static final void main(final String[] arguments) {
        try {
            System.out.println("Scanning correct input");
            
            final Yylex lexer =  new Yylex(new ByteArrayInputStream("1 2 3 4 5".getBytes()));
            Yytoken x = lexer.yylex();
            ArrayList<Integer> res = new ArrayList<Integer>();

            while (x != null) {
                res.add(x.getIntValue());
                x = lexer.yylex();
            }
            System.out.println(res);
        } catch (final IOException exception) {
            exception.printStackTrace();
        }
        
    }
}
