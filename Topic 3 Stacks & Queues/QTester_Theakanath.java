//Sony Theakanath
//Data Structures
//StacksQs-Asg3: Q Implementation

/**
   Tester class testing out Queue.
*/

public class QTester_Theakanath
{
   public static void main(String[]args) {
      BasicQueue q = new BasicQueue();
      System.out.println("Q is empty: "+ q.isEmpty());
      System.out.println("Now adding H, E, L, L, O, B, C, P");
      q.add("H");
      q.add("E");
      q.add("L");
      q.add("L");
      q.add("O");
      q.add("B");
      q.add("C");
      q.add("P");

      System.out.println("The top element is: "+ q.peek());
      System.out.println(q.size());
      while (!q.isEmpty()){
       	System.out.println("removing: "+q.remove());
      }
      System.out.println("Is it empty: "+ q.isEmpty());
      System.out.println("Now adding 1");
      q.add("1");
      System.out.println("Is it empty: "+ q.isEmpty());
      System.out.println("Now adding 2, 3, 4, 5");
      q.add("2");
      q.add("3");
      q.add("4");
      q.add("5");
      System.out.println("The top element is: "+ q.peek());
      System.out.println("Removing " + q.remove() );
      System.out.println("Removing "+ q.remove() );
      System.out.println("Now adding 7, 8, 9, 10, 11, 12");
      q.add("7");
      q.add("8");
      q.add("9");
      q.add("10");
      q.add("11");
      q.add("12"); 
      System.out.println("Now adding Last");
      q.add("Last");
      System.out.println("The top element is: "+ q.peek());
      System.out.println("The size is: "+ q.size());

      while (!q.isEmpty()){
       	System.out.println("removing:" +q.remove());
      }
      for(int i = 0; i < 10000; i++ )
         q.add( "" + i );
   }
}

/**
   BasicQueue creates a queue based upon the ArrayList.
*/

class BasicQueue 
{
   private static final int DEFAULT_SIZE = 10;
   private Object[] objects; 
   private int size;
   private int front;
   private int back;
   
   /**
      Constructs a new queue
   */
   public BasicQueue() {
      objects = new Object[DEFAULT_SIZE];
      size = 0;
      front = 0;
      back = -1;
   }
   
   /**
      Checks if queue is empty
   */
   public boolean isEmpty() {
      return size == 0;
   }
   
   /**
      Adds queue to beginning of list
   */
   public boolean add(Object j) {
      if(size == objects.length)
         throw new Error("Q is full");
       back = increase(back);
       objects[back] = j;
       size++;
       return true;
   }
   
   /**
      Removes the head of the array.
   */
   public Object remove() {
      if(isEmpty())
         return null;
      size--;
      Object frontitem = objects[front];
      objects[front] = null;
      front = increase(front);
      return frontitem;
   }
   
   
   /**
      Retrieves the head of the array or returns a null
      if empty.
   */
   public Object peek() {
      if(isEmpty())
         return null;
      else
         return objects[front]; 
   }
   
   /**
      Returns the size of the populated queue.
   */
   public int size() {
      return size;
   }
   
   /**
      Handles situations when the object index is at the end
      of the array or at the beginning
   */
   private int increase(int x) {
      if(++x == objects.length)
         x = 0;
      return x;
   }
}