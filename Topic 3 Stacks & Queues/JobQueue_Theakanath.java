//Sony Theakanath
//Data Structures

import java.util.ArrayList;
import java.util.Random;

/**
   Tester class.
*/
public class JobQueue_Theakanath
{
   public static void main(String [] args) {
      Random rand = new Random();
      ArrayList<int[]> jobs = new ArrayList<int[]>();   
      for(int x = 0; x < 100; x++) {
         int[] arr = {x, rand.nextInt(51)}; //Order, number of pages
         jobs.add(arr);
      }
      repeatprint(1, jobs);
      repeatprint(2, jobs);
      repeatprint(3, jobs);
   }
   
   /**
      Just to cut down the code. Does the same thing based on the printers
   */
   public static void repeatprint(int numofprinters, ArrayList<int[]> jobs) {
      PrintQueue q = new PrintQueue(numofprinters);
      System.out.println("This prints the whole job list. {orderarrived, numofpages}");
      System.out.println("Based on " + numofprinters + " printers.");
      for(int x = 0; x < jobs.size(); x++)
         System.out.print("{" + jobs.get(x)[0] + " " + jobs.get(x)[1] + "}, ");
      ArrayList<int[]> newsort = q.sortjobs(jobs);
      System.out.println();
      for(int x = 0; x < newsort.size(); x++)
         System.out.print("{" + newsort.get(x)[0] + " " + newsort.get(x)[1] + "}, ");
      System.out.println("\n" + q.calculatetime() + " minutes.\n\n");
   }
}

/**
   PrintQueue simulates a printing queue. It uses queues, ArrayLists and 
   sorting algorithms in order to check each print job.
*/
class PrintQueue
{
   private int numofprinters;
   private BasicQueue list;
   
   /**
      Determines how many printers are there.
   */
   public PrintQueue(int numofprinters) {
      this.numofprinters = numofprinters;
      list = new BasicQueue();
   }
   
   /**
      Sorts the jobs based on the number of pages in each job.
      Uses selectionsort in order to sort the pages in the queue.
   */
   public ArrayList<int[]> sortjobs(ArrayList<int[]> prevjobs) {
      ArrayList<int[]> sortedjobs = this.selectionsort(prevjobs);
      for(int x = 0; x < sortedjobs.size(); x++)
         list.add(sortedjobs.get(x));
      return sortedjobs;
   }
   
   /**
      Calculates the time to print all of the jobs.
   */
   public double calculatetime() {
      BasicQueue copied = list;
      double total = 0;
      for(int x = 0; x < copied.size(); x++) {
         int[] takenarray = (int[])copied.remove();
         total+= (double)takenarray[1]/(10*numofprinters);
      }
      return total;
   }
   
   /**
      Selection sort algorithm, pretty self explanatory other than
      the fact that it checks the num of pages in order to sort.
      Sorts from least to greatest.
   */
   private ArrayList<int[]> selectionsort(ArrayList<int[]> data){
      int lenD = data.size();
      int j = 0;
      int[] tmp;
      for(int i = 0; i < lenD; i++){
         j = i;
         for(int k = i; k < lenD; k++)
            if(data.get(j)[1] > data.get(k)[1])
               j = k;
         tmp = data.get(i);
         data.set(i, data.get(j));
         data.set(j, tmp);
      }
      return data;
   }
}

/**
   BasicQueue creates a queue based upon the ArrayList.
*/
class BasicQueue 
{
   private static final int DEFAULT_SIZE = 100;
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