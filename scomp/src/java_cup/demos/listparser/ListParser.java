
//----------------------------------------------------
// The following code was generated by CUP v0.10k
// Thu Jun 03 20:30:17 CEST 2010
//----------------------------------------------------

package java_cup.demos.listparser;

import java.util.*;
import java_cup.runtime.*;

/** CUP v0.10k generated parser.
  * @version Thu Jun 03 20:30:17 CEST 2010
  */
public class ListParser extends java_cup.runtime.lr_parser {

  /** Default constructor. */
  public ListParser() {super();}

  /** Constructor which sets the default scanner. */
  public ListParser(java_cup.runtime.Scanner s) {super(s);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\007\000\002\003\005\000\002\002\004\000\002\003" +
    "\004\000\002\004\004\000\002\004\003\000\002\005\003" +
    "\000\002\005\003" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\013\000\004\004\004\001\002\000\010\004\004\005" +
    "\012\006\011\001\002\000\004\002\006\001\002\000\004" +
    "\002\000\001\002\000\010\004\ufffd\005\ufffd\006\ufffd\001" +
    "\002\000\010\004\ufffb\005\ufffb\006\ufffb\001\002\000\010" +
    "\004\ufffc\005\ufffc\006\ufffc\001\002\000\012\002\uffff\004" +
    "\uffff\005\uffff\006\uffff\001\002\000\010\004\004\005\015" +
    "\006\011\001\002\000\010\004\ufffe\005\ufffe\006\ufffe\001" +
    "\002\000\012\002\001\004\001\005\001\006\001\001\002" +
    "" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\013\000\004\003\004\001\001\000\010\003\007\004" +
    "\012\005\006\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\006\003\007\005\013\001\001\000\002" +
    "\001\001\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$ListParser$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$ListParser$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$ListParser$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 1;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}

}

/** Cup generated class to encapsulate user supplied action code.*/
class CUP$ListParser$actions {
  private final ListParser parser;

  /** Constructor */
  CUP$ListParser$actions(ListParser parser) {
    this.parser = parser;
  }

  /** Method with the actual generated action code. */
  public final java_cup.runtime.Symbol CUP$ListParser$do_action(
    int                        CUP$ListParser$act_num,
    java_cup.runtime.lr_parser CUP$ListParser$parser,
    java.util.Stack            CUP$ListParser$stack,
    int                        CUP$ListParser$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$ListParser$result;

      /* select the action based on the action number */
      switch (CUP$ListParser$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // Element ::= List 
            {
              Object RESULT = null;
		int listleft = ((java_cup.runtime.Symbol)CUP$ListParser$stack.elementAt(CUP$ListParser$top-0)).left;
		int listright = ((java_cup.runtime.Symbol)CUP$ListParser$stack.elementAt(CUP$ListParser$top-0)).right;
		List list = (List)((java_cup.runtime.Symbol) CUP$ListParser$stack.elementAt(CUP$ListParser$top-0)).value;
		 RESULT = list; 
              CUP$ListParser$result = new java_cup.runtime.Symbol(3/*Element*/, ((java_cup.runtime.Symbol)CUP$ListParser$stack.elementAt(CUP$ListParser$top-0)).left, ((java_cup.runtime.Symbol)CUP$ListParser$stack.elementAt(CUP$ListParser$top-0)).right, RESULT);
            }
          return CUP$ListParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // Element ::= IDENTIFIER 
            {
              Object RESULT = null;
		int identifierleft = ((java_cup.runtime.Symbol)CUP$ListParser$stack.elementAt(CUP$ListParser$top-0)).left;
		int identifierright = ((java_cup.runtime.Symbol)CUP$ListParser$stack.elementAt(CUP$ListParser$top-0)).right;
		String identifier = (String)((java_cup.runtime.Symbol) CUP$ListParser$stack.elementAt(CUP$ListParser$top-0)).value;
		 RESULT = identifier; 
              CUP$ListParser$result = new java_cup.runtime.Symbol(3/*Element*/, ((java_cup.runtime.Symbol)CUP$ListParser$stack.elementAt(CUP$ListParser$top-0)).left, ((java_cup.runtime.Symbol)CUP$ListParser$stack.elementAt(CUP$ListParser$top-0)).right, RESULT);
            }
          return CUP$ListParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // Elements ::= Element 
            {
              List RESULT = null;
		int elementleft = ((java_cup.runtime.Symbol)CUP$ListParser$stack.elementAt(CUP$ListParser$top-0)).left;
		int elementright = ((java_cup.runtime.Symbol)CUP$ListParser$stack.elementAt(CUP$ListParser$top-0)).right;
		Object element = (Object)((java_cup.runtime.Symbol) CUP$ListParser$stack.elementAt(CUP$ListParser$top-0)).value;
		 RESULT = new ArrayList<Object>(); RESULT.add(element); 
              CUP$ListParser$result = new java_cup.runtime.Symbol(2/*Elements*/, ((java_cup.runtime.Symbol)CUP$ListParser$stack.elementAt(CUP$ListParser$top-0)).left, ((java_cup.runtime.Symbol)CUP$ListParser$stack.elementAt(CUP$ListParser$top-0)).right, RESULT);
            }
          return CUP$ListParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // Elements ::= Elements Element 
            {
              List RESULT = null;
		int elementsleft = ((java_cup.runtime.Symbol)CUP$ListParser$stack.elementAt(CUP$ListParser$top-1)).left;
		int elementsright = ((java_cup.runtime.Symbol)CUP$ListParser$stack.elementAt(CUP$ListParser$top-1)).right;
		List elements = (List)((java_cup.runtime.Symbol) CUP$ListParser$stack.elementAt(CUP$ListParser$top-1)).value;
		int elementleft = ((java_cup.runtime.Symbol)CUP$ListParser$stack.elementAt(CUP$ListParser$top-0)).left;
		int elementright = ((java_cup.runtime.Symbol)CUP$ListParser$stack.elementAt(CUP$ListParser$top-0)).right;
		Object element = (Object)((java_cup.runtime.Symbol) CUP$ListParser$stack.elementAt(CUP$ListParser$top-0)).value;
		 RESULT = new ArrayList<Object>(elements); RESULT.add(element); 
              CUP$ListParser$result = new java_cup.runtime.Symbol(2/*Elements*/, ((java_cup.runtime.Symbol)CUP$ListParser$stack.elementAt(CUP$ListParser$top-1)).left, ((java_cup.runtime.Symbol)CUP$ListParser$stack.elementAt(CUP$ListParser$top-0)).right, RESULT);
            }
          return CUP$ListParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // List ::= LEFT_PARENTHESIS RIGHT_PARENTHESIS 
            {
              List RESULT = null;
		 RESULT = new ArrayList<Object>(); 
              CUP$ListParser$result = new java_cup.runtime.Symbol(1/*List*/, ((java_cup.runtime.Symbol)CUP$ListParser$stack.elementAt(CUP$ListParser$top-1)).left, ((java_cup.runtime.Symbol)CUP$ListParser$stack.elementAt(CUP$ListParser$top-0)).right, RESULT);
            }
          return CUP$ListParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // $START ::= List EOF 
            {
              Object RESULT = null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$ListParser$stack.elementAt(CUP$ListParser$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$ListParser$stack.elementAt(CUP$ListParser$top-1)).right;
		List start_val = (List)((java_cup.runtime.Symbol) CUP$ListParser$stack.elementAt(CUP$ListParser$top-1)).value;
		RESULT = start_val;
              CUP$ListParser$result = new java_cup.runtime.Symbol(0/*$START*/, ((java_cup.runtime.Symbol)CUP$ListParser$stack.elementAt(CUP$ListParser$top-1)).left, ((java_cup.runtime.Symbol)CUP$ListParser$stack.elementAt(CUP$ListParser$top-0)).right, RESULT);
            }
          /* ACCEPT */
          CUP$ListParser$parser.done_parsing();
          return CUP$ListParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // List ::= LEFT_PARENTHESIS Elements RIGHT_PARENTHESIS 
            {
              List RESULT = null;
		int elementsleft = ((java_cup.runtime.Symbol)CUP$ListParser$stack.elementAt(CUP$ListParser$top-1)).left;
		int elementsright = ((java_cup.runtime.Symbol)CUP$ListParser$stack.elementAt(CUP$ListParser$top-1)).right;
		List elements = (List)((java_cup.runtime.Symbol) CUP$ListParser$stack.elementAt(CUP$ListParser$top-1)).value;
		 RESULT = elements; 
              CUP$ListParser$result = new java_cup.runtime.Symbol(1/*List*/, ((java_cup.runtime.Symbol)CUP$ListParser$stack.elementAt(CUP$ListParser$top-2)).left, ((java_cup.runtime.Symbol)CUP$ListParser$stack.elementAt(CUP$ListParser$top-0)).right, RESULT);
            }
          return CUP$ListParser$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number found in internal parse table");

        }
    }
}
