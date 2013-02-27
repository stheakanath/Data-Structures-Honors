//Sony Theakanath
//Data Structures
//StacksQs-Asg2: Basic Stack

import java.util.EmptyStackException;
import java.util.LinkedList;

/**
   Practice working with Stacks. Tester class.
*/

public class StackTester_Theakanath
{
   public static void main(String [] args) 
   {
		Stack<String> dishes = new Stack<String>();
		System.out.println("Is it empty: " + dishes.isEmpty());
		System.out.println("Now pushing H, E, L, L, O");
		dishes.push("H");// 
		dishes.push("E");
		dishes.push("L");
		dishes.push("L");
		dishes.push("O");

		System.out.println("The top element is: " + dishes.peek());

		while (!dishes.isEmpty()){
			System.out.println("Popping: "+dishes.pop());
		}
		System.out.println("Is it empty: " + dishes.isEmpty());
		System.out.println("Now pushing 1");
		dishes.push("1");
		System.out.println("Is it empty: " + dishes.isEmpty());
		System.out.println("Now pushing 2, 3, 4, 5");
		dishes.push("2");
		dishes.push("3");
		dishes.push("4");
		dishes.push("5");
		System.out.println("The top element is: " + dishes.peek());
		System.out.println("Removing " + dishes.pop() );
		System.out.println("Removing "+ dishes.pop() );
		System.out.println("Now pushing Last");
		dishes.push("Last");
		System.out.println("The top element is: " + dishes.peek());

		while (!dishes.isEmpty()){
			System.out.println("Popping: " +dishes.pop());
		}
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