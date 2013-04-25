//Sony Theakanath
//Data Structures

import java.util.Scanner;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.EmptyStackException;

/**
   SymbolTester works perfectly, as long as you give spaces when you 
   enter the input. It handles no variables and when variables are there.
   Other than that I didn't have much trouble with programming this 
   program.
*/
public class SymbolTester_Teakanath {
   public static void main(String[]args) {
      SymbolTable run = new SymbolTable(); 
   }
}

/**
   SymbolTable handles making the inputs and giving the result from the console
   It handles two functions, let and print. Let lets you define a variable and 
   print lets you evaluate a function
*/
class SymbolTable {
   private Scanner scan;
   private Map<String, Integer> symbolTable;
   private Calculator calc;
   
   /**
      Default constructor, it starts the command line and handles all of the
      input. It also creates the variables.
   */
   public SymbolTable() {
      scan = new Scanner(System.in);
      symbolTable = new HashMap<String, Integer>();
      calc = new Calculator();
      System.out.println("Enter commands. Type 'quit' to exit.");
      System.out.println("Commands must have the form (FORMAT VERY IMPORTANT):");
      System.out.println("PRINT <expression>");
      System.out.println("or");
      System.out.println("LET <variable> = <expression>\n");
      String input = scan.nextLine();
      while (!input.equals("quit")) {
         if(input.contains("LET")) {
            String var = input.substring(4, input.indexOf("=")-1);
            int value = calc.cleanandeval(input.substring(input.indexOf("=")+1));
            System.out.println("Letting " + var + " be " + value);
            symbolTable.put(var, value);
         } else if(input.contains("PRINT")) {
            String newstring = convertinput(input);
            boolean works = validinput(newstring);
            if(works) {
               int evaluated = calc.cleanandeval(newstring);
               System.out.println("Evaluated as " + evaluated);
            }
         } else {
            System.out.println("ERROR: Command not recognized");
         }
         input = scan.nextLine();
      }
      System.out.println("Quiting Program");
   }
   
   /**
      Convertinput converts all of the variables in the function into 
      the value it has in the symbolTable. 
   */
   public String convertinput(String orginput) {
      String newinput = orginput;
      for (Map.Entry<String, Integer> e: symbolTable.entrySet()) {
         if(newinput.contains(e.getKey())) {
            String supposedvalue = e.getValue() + "";
            newinput = newinput.replace(e.getKey(), supposedvalue);
         }
      }
      return newinput;
   }
   
   /**
      Validinput checks if the input has variables defined in the 
      symbolTable. If not it returns a false boolean value and then
      does not evaluate the function.
   */
   public boolean validinput(String input) {
      String value = input.substring(6);
      String[] in = value.split(" ");
      ArrayList<String> inputs = new ArrayList<String>(Arrays.asList(in));
   
      for(int x = 0; x < inputs.size(); x++) {
         if(inputs.get(x).equals("+") || inputs.get(x).equals("-") || inputs.get(x).equals("/") || inputs.get(x).equals("*") || inputs.get(x).equals("^")) {
            inputs.remove(x);
         } else if(isNumeric(inputs.get(x))) {
            inputs.remove(x);
         }
      }
      for(int x = 0; x < inputs.size(); x++) {
         for (Map.Entry<String, Integer> e: symbolTable.entrySet()) {
            if(inputs.get(x).equals(e.getKey())) {
              inputs.remove(x);
            }
         }
      }
      if(inputs.size() > 0) {
         System.out.println("ERROR: Variable " + inputs.get(0) + " not found"); 
         return false;
      }
      return true;
   }
   
   /**
      Checks if the string input is numeric. 
   */
   public boolean isNumeric(String inputData) {
      return inputData.matches("[-+]?\\d+(\\.\\d+)?");
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