//Sony Theakanath
//Heaps [of food]
//Data Structures H

/**
   Heap Tester tests the heap out. Whoop!
*/
public class HeapTester_Theakanath
{
   public static void main(String[]args) {
      Heap heap = new Heap(15);
      int[] numbers = {65, 43, 35, 22, 41, 10, 17, 16, 14, 26, 38, 4, 6, 7};
      for(int x : numbers)
         heap.add(x);
      heap.print();
   }
}

/**
   A type of data structure, heaps is a tree that manages
   data.
*/
class Heap
{
   private Node[] data;
   private int datasize;
   private int currentsize;
   
   /**
      Default constructor, sets all of the private
      instance variables.
   */
   public Heap(int sizeofheap) {
      data = new Node[sizeofheap];
      currentsize = 0;
      datasize = sizeofheap;
   }
   
   /**
      Adds a comparable object to the array of data also checks
      for the data and moves it up using the max_heap property.
   */
   public void add(Comparable object) {
      if(currentsize != datasize) {
         Node newdata = new Node(object);
         data[currentsize] = newdata;
         
         //Checking sure for max-Heap property
         int newindex = currentsize++;
         int parentindex = (newindex-1)/2;
         Node bottom = data[newindex];
         while(newindex > 0 && data[parentindex].getData().compareTo(bottom.getData()) < 0) {
            data[newindex] = data[parentindex];
            newindex = parentindex;
            parentindex = (parentindex--)/2;
         }
         data[newindex] = bottom;
      }
   }
   
   /**
      Prints out the heap by levels.
   */
   public void print() {
      int index = 0;
      int mindex = 0;
      while(index+1 != datasize) {
         System.out.print(data[index].getData() + " ");
         int mersenne = (int)Math.pow(2, mindex+1)-1;
         if(index+1 == mersenne) {
            System.out.print("\n");
            mindex++;  
         }
         index++;
      }
   }
}

/**
   Node is also a type of data structure. This is for use for the heaps.
*/
class Node
{
   private Comparable data;
   public Node(Comparable dat) { data = dat; }
   public Comparable getData() { return data; }
   public void setData(Comparable dat) { data = dat; } 
}