package jlex.demos.integerlistscanner;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
            final List<Integer> list = new ArrayList<Integer>();
            Yytoken token = lexer.yylex();
            
            while (token != null) {
                list.add(token.getIntValue());
                token = lexer.yylex();
            }
            
            System.out.println(list);
        } catch (final IOException exception) {
            exception.printStackTrace();
        }
        
    }
}
