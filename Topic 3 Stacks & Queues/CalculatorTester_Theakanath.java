//Sony Theakanath
//Data Structures
//Stack Calculator

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Scanner;

/**
   Tester class, goes in an infinite loop and asks the user if he/she wants to
   put in a value and then evaluate it.
*/

public class CalculatorTester_Theakanath 
{
   public static void main(String[]args) {
      Scanner scan = new Scanner(System.in);
      Calculator calc = new Calculator();
      String yesorno;
      System.out.println("Welcome to the Stack Calculator!, try this out by typing in an expression");
      System.out.println("An example would be: '3 + 4 * 2 / ( 1 - 5 ) ^ 2 ^ 3' which evaluates to 3");
      do {
         System.out.println("Expression (Don't worry about spaces): ");
         String input = scan.nextLine();
         System.out.println("That evaluates to: " + calc.cleanandeval(input));
         System.out.println("Evaluate another one? (y or n)");
         yesorno = scan.nextLine();
      } while (yesorno.equals("y"));
   }
}

/**
   The calculator class takes in a string of characters and it calculates 
   the amount using several functions. This is extensively documented, so look in for
   details. In order to calculate the values, the Calculator class implements the 
   Shunting Yard Algorithm, which takes in an array of values and converts them to reverse
   polish notation. Afterwhich the calculate function handles the RPN and converts it to
   a normal integer.
   
   Functions: - Handles addition, substraction, multiplication, division and exponents.
              - Handles parenthesis
              - Handles spaces and no spaces input
              
   Possible Shortcomings: - Make code cleaner, it looks sooooo ugly. Yuck.
                          - Uhhh, GUI maybe? 
                          - Only works with integers, no doubles for now.
                          - Doesn't handle weird input, intentionally to break the program.
                          
   The Awesomeness: - Possibly the longest javadoc I made, does this get triple points?
*/
class Calculator 
{
   /**
      Just cleans up the input and calls all of the other functions.
      Mainly the handler of spaces and no spaces.
   */
   public int cleanandeval(String input) {
      String clean = input.replace(" ", "");
      String[] tokens = clean.split("");
      String[] polish = makePolish(tokens);
      return evaluate(polish);
   }
   
   /**
      Hmmm I wonder what this does. Looking at the first line it seems like
      it takes the returned RPN and then evaluates it. That's what it does!
   */
   public int evaluate(String[] polishnotation) {
      Stack<String> stack = new Stack<String>();
      for(String token : polishnotation) //Going through everything
         if(!isoperator(token))
            stack.push(token);
         else {
            int number2 = Integer.parseInt((String)stack.pop());
            int number1 = Integer.parseInt((String)stack.pop());
            int combine;
            if(token.equals("+")) 
               combine = number1 + number2;
            else if(token.equals("-"))
               combine = number1 - number2;
            else if(token.equals("*"))
               combine = number1 * number2;
            else if(token.equals("/"))
               combine = number1 / number2;
            else
               combine = (int)Math.pow(number1, number2);
            stack.push(String.valueOf(combine));
         }
      return Integer.parseInt((String)stack.pop());
   }
   
   /**
      makepolish(String[]) is the bride of this wedding, it handles the mumbo jumbo stuffs.
      As explained above, this uses the Shunting Yard Algorithm and outputs the RPN.
      (How this works is explained below, this was a pain to make)
   */
   public String[] makePolish(String[] input) {
      ArrayList<String> polish = new ArrayList<String>(); //This is the final RPN
      Stack<String> inqueue = new Stack<String>(); 
      
      for(String token : input) {
         if(isoperator(token)) { //Checking operator
            while(!inqueue.isEmpty() && isoperator(inqueue.peek())) {  //Making sure above
               if(cmpPrecendence(token, (String)inqueue.peek()) <= 0)
                  polish.add((String)inqueue.pop());      
               break;
            }
            inqueue.push(token);
         } else if(token.equals("(")) { //Parenthesis Check
            inqueue.push(token);
         } else if (token.equals(")")) {
            while(!inqueue.isEmpty() && !inqueue.peek().equals("("))
               polish.add((String)inqueue.pop());
            inqueue.pop();
         } else //Just numbers
            polish.add(token);
      }
      
      while(!inqueue.isEmpty())
         polish.add((String)inqueue.pop());
      String[] output = new String[polish.size()];
      return polish.toArray(output);
   }
   
   /**
      Checks both operators and then returns the difference of both.
   */ 
   private int cmpPrecendence(String operator, String operator2) {
      return getPrecendenceValue(operator) - getPrecendenceValue(operator2);
   }
   
   /**
      Returns the precendence values. Based on the values given by the wiki page
      for SYA.
   */
   private int getPrecendenceValue(String operator) {
      if(operator.equals("+"))
         return 2;
      else if(operator.equals("-"))
         return 2;
      else if(operator.equals("*"))
         return 3;
      else if(operator.equals("/"))
         return 3;
      else if(operator.equals("^"))
         return 4;
      else //Just return a random value lols
         return 0;  
   }
   
   /**
      Is it an operator or not?
      The world may never know.
   */
   private boolean isoperator(Object input) {
      if(input.equals("+") || input.equals("/") || input.equals("-") || input.equals("*") || input.equals("^"))
         return true;
      return false;
   }
}

/**
   Basically creating a Stack, nothing more to explain.
*/
class Stack<String>
{
   private LinkedList stack;
   
   /**
      Default Constructor
   */
   public Stack() {
      stack = new LinkedList();
   }
   
   /**
      Checks if stack is empty.
   */
   public boolean isEmpty() {
      if(stack.size() == 0)
         return true;
      return false;
   }
   
   /**
      Adds object to stack
   */
   public Object push(String o) {
      stack.add(0, o);
      return o;
   }
   
   /**
      Takes object out of stack. Like Pez!
   */
   public Object pop() {
      if(isEmpty())
         throw new EmptyStackException();
      else {
         Object object = stack.getFirst();
         stack.remove(0);
         return object;
      }
   }
   
   /**
      Looks at the first element in the stack.
   */
   public Object peek() {
      if(isEmpty())
         throw new EmptyStackException();
      else {
         Object object = stack.getFirst();
         return object;
      }
   }
}