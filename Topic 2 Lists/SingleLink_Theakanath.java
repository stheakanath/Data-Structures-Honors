//Sony Theakanath
//Data Strucutres


import java.util.NoSuchElementException;
import java.util.ListIterator; 

/**
   Having a lot of fun with SingleLinkedLists! maybe.
   This tests out the LinkedList class.
*/

public class SingleLink_Theakanath {
   public static void main(String[]args) {
      LinkedList list = new LinkedList();
      list.addFirst("Sony");
      list.addFirst("k");
      list.addFirst("l");
      list.addFirst("dsf");
      list.addFirst("safds");
      list.addFirst("math");
      list.addFirst("sleeeeeep");
      System.out.println("Original List: ");
      list.printList();
      System.out.println("\nRemoving 3rd element");
      list.remove(3);
      list.printList();
      System.out.println("\nRemoving first occurrence of k");
      list.remove("k");
      list.printList();
      System.out.println("\nAdding fun to 2nd index");
      list.add(2, "fun");
      list.printList();
   }
}



/**
  A linked list is a sequence of nodes with efficient
  element insertion and removal. This class 
  contains a subset of the methods of the standard
  java.util.LinkedList class.
*/
class LinkedList {

	private Node first;
   
	private class Node 	{ 
		public Object data;
		public Node next;
	}
	
	/**
	 * Constructs an empty linked list.
	 */
	public LinkedList() {
		first = null;
	}
	
	/**
	 * Returns the first element in the linked list.
	 * @return the first element in the linked list
	 */
	public Object getFirst() {
		if (first == null)
			throw new NoSuchElementException();
		return first.data;
	}
	
	/**
	 * Removes the first element in the linked list.
	 * @return the removed element
	 */
	public Object removeFirst() {
		if (first == null)
			throw new NoSuchElementException();
		Object element = first.data;
		first = first.next;
		return element;
	}

	/**
	 * Adds an element to the front of the linked list.
	 * @param element the element to add
	 */
	public void addFirst(Object element)  {
		Node newNode = new Node();
		newNode.data = element;
		newNode.next = first;
		first = newNode;
	}
	
   /**
      Gets size of linkedlist
   */
   public int size() {
      int size = 0;
      Node current = first;
      while (current.next != null)
      {
        size++;
        current = current.next;
      }
      return size+1;
   }
   
   /**
      Removes Node at index
   */
   public boolean remove(int index) {
      if(!(index > size() || index < 0)) {
         Node current = first;
         if(index == 0)
            first = first.next;
         else {
            int currindex = 0;
            for(int x = 0; x < index-1; x++)
               current = current.next;
            current.next = current.next.next;
         }
         return true;
      } else {
         return false;
      }
   }
   
   /**
      Removes first occurrence of object and returns
      if successful.
   */
   public boolean remove(Object object) {
      boolean found = false;
      for(Node x = first; x != null; x = x.next)
      {
         if(x.next!= null)
            if(x.next.data.equals(object))
            {
               x.next = x.next.next;
               found = true;
            }
      }
      return found;
   }
   
   /**
      Adds object at specified index.
   */
   public void add(int index, Object i) {
      if(!(index > size() || index < 0)) {
         Node item = new Node();
         item.data = i;
         Node current = first;
         if(index == 0)
         {
            Node backup = first;
            first = item;
            first.next = backup;
         }   
         else {
            int currindex = 0;
            for(int x = 0; x < index-1; x++)
               current = current.next;
            Node backup = current.next;
            current.next = item;
            item.next = backup;
         }
      }
   }
   
   /**
      Prints the list.
   */
   public void printList() {
      Node current = first;
      System.out.print(current.data + " ");
      while(current.next != null)
      {
         current = current.next;
         System.out.print(current.data + " ");
      }
   }
}