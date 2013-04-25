//Sony Theakanath
//Heaps [of Food] but Deleting Them Now. Oh Noes!
//Data Structures H

/**
   Heap Tester tests the heap out. Whoop Whoop!
   
   The program tests perfectly. I used the data as shown below. 
   It first prints the heap via the levels and then I set a loop
   to remove all of the elements. If the loop is successful then
   it should print the elements from max to least and as seen
   by the output it works. I did not test an empty heap, but it gets
   handled when there are no elements left in the heap underneath the
   loop.
*/
public class HeapDeletionTester_Theakanath
{
   public static void main(String[]args) {
      Heap heap = new Heap(15);
      int[] numbers = {65, 43, 35, 22, 41, 10, 17, 16, 14, 26, 38, 4, 6, 7};
      for(int x : numbers)
         heap.add(x);
      heap.print();
      System.out.println("\n");
      while(!heap.isEmpty())
         System.out.print(heap.removeRoot() + " ");
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
      Removes the root, moves the values so that the heap makes
      sense and then returns the root for you. Returns null if empty.
   */
   public Comparable removeRoot() {
      if(isEmpty())
         return null;
      else {
         Comparable toberemoved = data[0].getData();
         data[0] = data[--currentsize];
         int largervalue;
         int theindex = 0;
         Node top = data[theindex];
         while(theindex < currentsize/2) {
            if(theindex*2+2 < currentsize && data[theindex*2+1].getData().compareTo(data[theindex*2+2].getData()) < 0)
               largervalue = theindex*2+2;
            else
               largervalue = theindex*2+1;
            if(top.getData().compareTo(data[largervalue].getData()) >= 0)
               break;
            data[theindex] = data[largervalue];
            theindex = largervalue;
         }
         data[theindex] = top;
         return toberemoved;
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
   
   /**
      Checks if the heap is empty.
   */
   public boolean isEmpty() {
      if(currentsize == 0)
         return true;
      return false;
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