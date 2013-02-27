//Sony Theakanath
//Data Structures

import java.util.EmptyStackException;
import java.util.LinkedList;

/**
   BinaryConverter converts a decimal to a binary. Contains two methods,
   printBinary which converts an integer to a binary and main which tests
   out this method.
*/

public class BinaryConverter_Theakanath
{
   /**
      printBinary converts a decimal integer to its binary equivalent (and displays it to standard output).
      preconditon: decimal > 0
   */
   public static void printBinary(int decimal) {
      Stack<Integer> stack = new Stack<Integer>();
      
      while(decimal != 0) {
         int remainder = decimal%2;
         stack.push(remainder);
         decimal = decimal/2;
      }
      
      while(!stack.isEmpty()) {
         System.out.print(stack.pop());
      }
   }
       
   public static void main(String[] a) {
      int i = 10;
      printBinary(10);
      printBinary(9);
      printBinary(8);
   }             
}

/**
   Basically creating a Stack, nothing more to explain.
*/
class Stack<E>
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
   public Object push(Object o) {
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