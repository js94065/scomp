
//----------------------------------------------------
// The following code was generated by CUP v0.10k
// Wed Jun 09 12:25:38 CEST 2010
//----------------------------------------------------

package scomp;

import java_cup.runtime.*;

/** CUP v0.10k generated parser.
  * @version Wed Jun 09 12:25:38 CEST 2010
  */
public class DecafParser extends java_cup.runtime.lr_parser {

  /** Default constructor. */
  public DecafParser() {super();}

  /** Constructor which sets the default scanner. */
  public DecafParser(java_cup.runtime.Scanner s) {super(s);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\053\000\002\013\003\000\002\002\004\000\002\014" +
    "\003\000\002\014\006\000\002\015\003\000\002\015\003" +
    "\000\002\015\003\000\002\015\005\000\002\015\004\000" +
    "\002\015\004\000\002\015\005\000\002\021\004\000\002" +
    "\021\002\000\002\020\003\000\002\020\005\000\002\017" +
    "\003\000\002\017\003\000\002\034\003\000\002\034\003" +
    "\000\002\034\003\000\002\034\003\000\002\035\003\000" +
    "\002\035\003\000\002\035\003\000\002\035\003\000\002" +
    "\035\003\000\002\035\003\000\002\035\003\000\002\035" +
    "\003\000\002\036\003\000\002\036\003\000\002\036\003" +
    "\000\002\036\003\000\002\037\003\000\002\037\003\000" +
    "\002\040\003\000\002\040\003\000\002\041\003\000\002" +
    "\041\003\000\002\041\003\000\002\043\003\000\002\044" +
    "\003\000\002\042\003" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\007\000\010\050\005\052\006\053\007\001\002\000" +
    "\004\002\001\001\002\000\004\002\uffdc\001\002\000\004" +
    "\002\uffdb\001\002\000\004\002\uffda\001\002\000\004\002" +
    "\011\001\002\000\004\002\000\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\007\000\006\013\007\041\003\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$DecafParser$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$DecafParser$actions(this);
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
    return action_obj.CUP$DecafParser$do_action(act_num, parser, stack, top);
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
class CUP$DecafParser$actions {
  private final DecafParser parser;

  /** Constructor */
  CUP$DecafParser$actions(DecafParser parser) {
    this.parser = parser;
  }

  /** Method with the actual generated action code. */
  public final java_cup.runtime.Symbol CUP$DecafParser$do_action(
    int                        CUP$DecafParser$act_num,
    java_cup.runtime.lr_parser CUP$DecafParser$parser,
    java.util.Stack            CUP$DecafParser$stack,
    int                        CUP$DecafParser$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$DecafParser$result;

      /* select the action based on the action number */
      switch (CUP$DecafParser$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 42: // BoolLiteral ::= BOOLEAN_LITERAL 
            {
              Object RESULT = null;

              CUP$DecafParser$result = new java_cup.runtime.Symbol(32/*BoolLiteral*/, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).left, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).right, RESULT);
            }
          return CUP$DecafParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 41: // HexLiteral ::= INT_LITERAL 
            {
              Object RESULT = null;

              CUP$DecafParser$result = new java_cup.runtime.Symbol(34/*HexLiteral*/, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).left, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).right, RESULT);
            }
          return CUP$DecafParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 40: // DecimalLiteral ::= INT_LITERAL 
            {
              Object RESULT = null;

              CUP$DecafParser$result = new java_cup.runtime.Symbol(33/*DecimalLiteral*/, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).left, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).right, RESULT);
            }
          return CUP$DecafParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 39: // Literal ::= STRING_LITERAL 
            {
              Object RESULT = null;

              CUP$DecafParser$result = new java_cup.runtime.Symbol(31/*Literal*/, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).left, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).right, RESULT);
            }
          return CUP$DecafParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 38: // Literal ::= CHAR_LITERAL 
            {
              Object RESULT = null;

              CUP$DecafParser$result = new java_cup.runtime.Symbol(31/*Literal*/, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).left, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).right, RESULT);
            }
          return CUP$DecafParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 37: // Literal ::= INT_LITERAL 
            {
              Object RESULT = null;

              CUP$DecafParser$result = new java_cup.runtime.Symbol(31/*Literal*/, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).left, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).right, RESULT);
            }
          return CUP$DecafParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 36: // CondOp ::= OR 
            {
              Object RESULT = null;

              CUP$DecafParser$result = new java_cup.runtime.Symbol(30/*CondOp*/, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).left, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).right, RESULT);
            }
          return CUP$DecafParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 35: // CondOp ::= AND 
            {
              Object RESULT = null;

              CUP$DecafParser$result = new java_cup.runtime.Symbol(30/*CondOp*/, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).left, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).right, RESULT);
            }
          return CUP$DecafParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 34: // EqOp ::= NOT_EQUAL 
            {
              Object RESULT = null;

              CUP$DecafParser$result = new java_cup.runtime.Symbol(29/*EqOp*/, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).left, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).right, RESULT);
            }
          return CUP$DecafParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 33: // EqOp ::= EQUAL 
            {
              Object RESULT = null;

              CUP$DecafParser$result = new java_cup.runtime.Symbol(29/*EqOp*/, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).left, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).right, RESULT);
            }
          return CUP$DecafParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 32: // RelOp ::= GREATER_OR_EQUAL 
            {
              Object RESULT = null;

              CUP$DecafParser$result = new java_cup.runtime.Symbol(28/*RelOp*/, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).left, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).right, RESULT);
            }
          return CUP$DecafParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 31: // RelOp ::= LESSER_OR_EQUAL 
            {
              Object RESULT = null;

              CUP$DecafParser$result = new java_cup.runtime.Symbol(28/*RelOp*/, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).left, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).right, RESULT);
            }
          return CUP$DecafParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 30: // RelOp ::= GREATER 
            {
              Object RESULT = null;

              CUP$DecafParser$result = new java_cup.runtime.Symbol(28/*RelOp*/, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).left, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).right, RESULT);
            }
          return CUP$DecafParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 29: // RelOp ::= LESSER 
            {
              Object RESULT = null;

              CUP$DecafParser$result = new java_cup.runtime.Symbol(28/*RelOp*/, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).left, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).right, RESULT);
            }
          return CUP$DecafParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 28: // ArithOp ::= BITWISE_ROTATE_RIGHT 
            {
              Object RESULT = null;

              CUP$DecafParser$result = new java_cup.runtime.Symbol(27/*ArithOp*/, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).left, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).right, RESULT);
            }
          return CUP$DecafParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 27: // ArithOp ::= ARITHMETIC_SHIFT_RIGHT 
            {
              Object RESULT = null;

              CUP$DecafParser$result = new java_cup.runtime.Symbol(27/*ArithOp*/, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).left, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).right, RESULT);
            }
          return CUP$DecafParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 26: // ArithOp ::= ARITHMETIC_SHIFT_LEFT 
            {
              Object RESULT = null;

              CUP$DecafParser$result = new java_cup.runtime.Symbol(27/*ArithOp*/, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).left, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).right, RESULT);
            }
          return CUP$DecafParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 25: // ArithOp ::= MODULO 
            {
              Object RESULT = null;

              CUP$DecafParser$result = new java_cup.runtime.Symbol(27/*ArithOp*/, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).left, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).right, RESULT);
            }
          return CUP$DecafParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 24: // ArithOp ::= DIVIDE 
            {
              Object RESULT = null;

              CUP$DecafParser$result = new java_cup.runtime.Symbol(27/*ArithOp*/, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).left, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).right, RESULT);
            }
          return CUP$DecafParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 23: // ArithOp ::= TIMES 
            {
              Object RESULT = null;

              CUP$DecafParser$result = new java_cup.runtime.Symbol(27/*ArithOp*/, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).left, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).right, RESULT);
            }
          return CUP$DecafParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 22: // ArithOp ::= MINUS 
            {
              Object RESULT = null;

              CUP$DecafParser$result = new java_cup.runtime.Symbol(27/*ArithOp*/, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).left, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).right, RESULT);
            }
          return CUP$DecafParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 21: // ArithOp ::= PLUS 
            {
              Object RESULT = null;

              CUP$DecafParser$result = new java_cup.runtime.Symbol(27/*ArithOp*/, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).left, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).right, RESULT);
            }
          return CUP$DecafParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 20: // BinOp ::= CondOp 
            {
              Object RESULT = null;

              CUP$DecafParser$result = new java_cup.runtime.Symbol(26/*BinOp*/, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).left, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).right, RESULT);
            }
          return CUP$DecafParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 19: // BinOp ::= EqOp 
            {
              Object RESULT = null;

              CUP$DecafParser$result = new java_cup.runtime.Symbol(26/*BinOp*/, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).left, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).right, RESULT);
            }
          return CUP$DecafParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 18: // BinOp ::= RelOp 
            {
              Object RESULT = null;

              CUP$DecafParser$result = new java_cup.runtime.Symbol(26/*BinOp*/, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).left, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).right, RESULT);
            }
          return CUP$DecafParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // BinOp ::= ArithOp 
            {
              Object RESULT = null;

              CUP$DecafParser$result = new java_cup.runtime.Symbol(26/*BinOp*/, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).left, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).right, RESULT);
            }
          return CUP$DecafParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // CalloutArg ::= STRING_LITERAL 
            {
              Object RESULT = null;

              CUP$DecafParser$result = new java_cup.runtime.Symbol(13/*CalloutArg*/, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).left, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).right, RESULT);
            }
          return CUP$DecafParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // CalloutArg ::= Expr 
            {
              Object RESULT = null;

              CUP$DecafParser$result = new java_cup.runtime.Symbol(13/*CalloutArg*/, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).left, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).right, RESULT);
            }
          return CUP$DecafParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // MoreCalloutArg ::= CalloutArg COMMA MoreCalloutArg 
            {
              Object RESULT = null;

              CUP$DecafParser$result = new java_cup.runtime.Symbol(14/*MoreCalloutArg*/, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-2)).left, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).right, RESULT);
            }
          return CUP$DecafParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // MoreCalloutArg ::= CalloutArg 
            {
              Object RESULT = null;

              CUP$DecafParser$result = new java_cup.runtime.Symbol(14/*MoreCalloutArg*/, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).left, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).right, RESULT);
            }
          return CUP$DecafParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // CallingArgs ::= 
            {
              Object RESULT = null;

              CUP$DecafParser$result = new java_cup.runtime.Symbol(15/*CallingArgs*/, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).right, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).right, RESULT);
            }
          return CUP$DecafParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // CallingArgs ::= COMMA MoreCalloutArg 
            {
              Object RESULT = null;

              CUP$DecafParser$result = new java_cup.runtime.Symbol(15/*CallingArgs*/, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-1)).left, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).right, RESULT);
            }
          return CUP$DecafParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // Expr ::= LEFT_PARENTHESIS Expr RIGHT_PARENTHESIS 
            {
              Object RESULT = null;

              CUP$DecafParser$result = new java_cup.runtime.Symbol(11/*Expr*/, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-2)).left, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).right, RESULT);
            }
          return CUP$DecafParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // Expr ::= NOT Expr 
            {
              Object RESULT = null;

              CUP$DecafParser$result = new java_cup.runtime.Symbol(11/*Expr*/, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-1)).left, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).right, RESULT);
            }
          return CUP$DecafParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // Expr ::= MINUS Expr 
            {
              Object RESULT = null;

              CUP$DecafParser$result = new java_cup.runtime.Symbol(11/*Expr*/, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-1)).left, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).right, RESULT);
            }
          return CUP$DecafParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // Expr ::= Expr BinOp Expr 
            {
              Object RESULT = null;

              CUP$DecafParser$result = new java_cup.runtime.Symbol(11/*Expr*/, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-2)).left, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).right, RESULT);
            }
          return CUP$DecafParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // Expr ::= Literal 
            {
              Object RESULT = null;

              CUP$DecafParser$result = new java_cup.runtime.Symbol(11/*Expr*/, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).left, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).right, RESULT);
            }
          return CUP$DecafParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // Expr ::= MethodCall 
            {
              Object RESULT = null;

              CUP$DecafParser$result = new java_cup.runtime.Symbol(11/*Expr*/, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).left, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).right, RESULT);
            }
          return CUP$DecafParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // Expr ::= Location 
            {
              Object RESULT = null;

              CUP$DecafParser$result = new java_cup.runtime.Symbol(11/*Expr*/, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).left, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).right, RESULT);
            }
          return CUP$DecafParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // Location ::= Literal LEFT_BRACKET Expr RIGHT_BRACKET 
            {
              Object RESULT = null;

              CUP$DecafParser$result = new java_cup.runtime.Symbol(10/*Location*/, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-3)).left, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).right, RESULT);
            }
          return CUP$DecafParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // Location ::= Literal 
            {
              Object RESULT = null;

              CUP$DecafParser$result = new java_cup.runtime.Symbol(10/*Location*/, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).left, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).right, RESULT);
            }
          return CUP$DecafParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // $START ::= MethodName EOF 
            {
              Object RESULT = null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-1)).right;
		Object start_val = (Object)((java_cup.runtime.Symbol) CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-1)).value;
		RESULT = start_val;
              CUP$DecafParser$result = new java_cup.runtime.Symbol(0/*$START*/, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-1)).left, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).right, RESULT);
            }
          /* ACCEPT */
          CUP$DecafParser$parser.done_parsing();
          return CUP$DecafParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // MethodName ::= Literal 
            {
              Object RESULT = null;

              CUP$DecafParser$result = new java_cup.runtime.Symbol(9/*MethodName*/, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).left, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).right, RESULT);
            }
          return CUP$DecafParser$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number found in internal parse table");

        }
    }
}

