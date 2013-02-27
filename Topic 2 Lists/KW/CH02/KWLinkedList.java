/*<listing chapter="2" section="8">*/
package KW.CH02;

import java.util.AbstractSequentialList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Class KWLinkedList implements a double linked list and
 * a ListIterator.
 * @author Koffman & Wolfgang
 **/
class KWLinkedList<E> extends AbstractSequentialList<E> {
    // Data Fields

    /** A reference to the head of the list. */
    private Node<E> head = null;
    /** A reference to the end of the list. */
    private Node<E> tail = null;
    /** The size of the list. */
    private int size = 0;

    //Methods
    /*<exercise chapter="2" section="8" type="programming" number="4">*/
    /**
     * Insert an object at the beginning of the list.
     * @param item - the item to be added
     */
    public void addFirst(E item) {

    }

    /**
     * Insert an object at the end of the list.
     * @param item - the item to be added
     */
    public void addLast(E item) {

    }

    /**
     * Get the first element in the list.
     * @return The first element in the list.
     */
    public E getFirst() {

    }

    /**
     * Get the last element in the list.
     * @return The last element in the list.
     */
    public E getLast() {

    }
    /*</exercise>*/

    /*<exercise chapter="2" section="8" type="programming" number="3">*/
    /**
     * Return an Iterator to the list
     * @return an Itertor to the list
     */
    public Iterator<E> iterator() {

    }

    /**
     * Return a ListIterator to the list
     * @return a ListItertor to the list
     */
    public ListIterator<E> listIterator() {


    }

    /** Return a ListIterator that begins at index
     * @param index - The position the iteration is to begin
     * @return a ListIterator that begins at index
     */
    public ListIterator<E> listIterator(int index) {

    }

    /**
     * Return a ListIterator that begins at the same
     * place as an existing ListIterator
     * @param iter - The other ListIterator
     * @return a ListIterator that is a copy of iter
    */
    public ListIterator <E> listIterator(ListIterator <E> iter) {


    }
    /*</exercise>*/
    /**
     * Add an item at the specified index.
     * @param index The index at which the object is to be
     *        inserted
     * @param obj The object to be inserted
     * @throws IndexOutOfBoundsException if the index is out
     *         of range (i < 0 || i > size())
     */
    @Override
    public void add(int index, E obj) {
        listIterator(index).add(obj);
    }

    /**
     * Get the element at position index.
     * @param index Position of item to be retrieved
     * @return The item at index
     */
    @Override
    public E get(int index) {
        return listIterator(index).next();
    }

    /**
     * Return the size of the list
     * @return the size of the list
     */
    @Override
    public int size() {
        return size;
    }

    // Inner Classes
    /** 
     * A Node is the building block for a double-linked list.
     */
    private static class Node<E> {

        /** The data value. */
        private E data;
        /** The link to the next node. */
        private Node<E> next = null;
        /** The link to the previous node. */
        private Node<E> prev = null;

        /**
         * Construct a node with the given data value.
         * @param dataItem The data value
         */
        private Node(E dataItem) {
            data = dataItem;
        }
    } //end class Node

    /** Inner class to implement the ListIterator interface. */
    private class KWListIter implements ListIterator<E> {

        /** A reference to the next item. */
        private Node<E> nextItem;
        /** A reference to the last item returned. */
        private Node<E> lastItemReturned;
        /** The index of the current item. */
        private int index = 0;

        /**
         * Construct a KWListIter that will reference the ith item.
         * @param i The index of the item to be referenced
         */
        public KWListIter(int i) {
            // Validate i parameter.
            if (i < 0 || i > size) {
                throw new IndexOutOfBoundsException(
                        "Invalid index " + i);
            }
            lastItemReturned = null; // No item returned yet.
            // Special case of last item.
            if (i == size) {
                index = size;
                nextItem = null;
            } else { // Start at the beginning
                nextItem = head;
                for (index = 0; index < i; index++) {
                    nextItem = nextItem.next;
                }
            }
        }

        /**
         * Construct a KWListIter that is a copy of another KWListIter
         * @param other The other KWListIter
         */
        public KWListIter(KWListIter other) {
            KWListIter itr = new KWListIter(0);
            itr.index = other.index;
            itr.lastItemReturned = other.lastItemReturned;
            itr.nextItem = other.nextItem;
        }

        /**
         * Indicate whether movement forward is defined.
         * @return true if call to next will not throw an exception
         */
        @Override
        public boolean hasNext() {
            return nextItem != null;
        }

        /** Move the iterator forward and return the next item.
        @return The next item in the list
        @throws NoSuchElementException if there is no such object
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastItemReturned = nextItem;
            nextItem = nextItem.next;
            index++;
            return lastItemReturned.data;
        }

        /**
         * Indicate whether movement backward is defined.
         * @return true if call to previous will not throw an exception
         */
        @Override
        public boolean hasPrevious() {
            return (nextItem == null && size != 0)
                    || nextItem.prev != null;
        }

        /**
         * Return the index of the next item to be returned by next
         * @return the index of the next item to be returned by next
         */
        @Override
        public int nextIndex() {
            return index;
        }

        /**
         * Return the index of the next item to be returned by previous
         * @return the index of the next item to be returned by previous
         */
        @Override
        public int previousIndex() {
            return index - 1;
        }

        /**
         * Move the iterator backward and return the previous item.
         * @return The previous item in the list
         * @throws NoSuchElementException if there is no such object
         */
        @Override
        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            if (nextItem == null) { // Iterator past the last element
                nextItem = tail;
            } else {
                nextItem = nextItem.prev;
            }
            lastItemReturned = nextItem;
            index--;
            return lastItemReturned.data;
        }

        /**
         * Add a new item between the item that will be returned
         * by next and the item that will be returned by previous.
         * If previous is called after add, the element added is
         * returned.
         * @param obj The item to be inserted
         */
        @Override
        public void add(E obj) {
            if (head == null) { // Add to an empty list.
                head = new Node<E>(obj);
                tail = head;
            } else if (nextItem == head) { // Insert at head.
                // Create a new node.
                Node<E> newNode = new Node<E>(obj);
                // Link it to the nextItem.
                newNode.next = nextItem; // Step 1
                // Link nextItem to the new node.
                nextItem.prev = newNode; // Step 2
                // The new node is now the head.
                head = newNode; // Step 3
            } else if (nextItem == null) { // Insert at tail.
                // Create a new node.
                Node<E> newNode = new Node<E>(obj);
                // Link the tail to the new node.
                tail.next = newNode; // Step 1
                // Link the new node to the tail.
                newNode.prev = tail; // Step 2
                // The new node is the new tail.
                tail = newNode; // Step 3
            } else { // Insert into the middle.
                // Create a new node.
                Node<E> newNode = new Node<E>(obj);
                // Link it to nextItem.prev.
                newNode.prev = nextItem.prev; // Step 1
                nextItem.prev.next = newNode; // Step 2
                // Link it to the nextItem.
                newNode.next = nextItem; // Step 3
                nextItem.prev = newNode; // Step 4
            }
            // Increase size and index and set lastItemReturned.
            size++;
            index++;
            lastItemReturned = null;
        } // End of method add.

        /*<exercise chapter="2" section="8" type="programming" number="1">*/
        /** Remove the last item returned. This can only be
         *  done once per call to next or previous.
         *  @throws IllegalStateException if next or previous
         *  was not called prior to calling this method
         */
        @Override
        public void remove() {
        }
        /*</exercise>*/

        /*<exercise chapter="2" section="8" type="programming" number="2">*/
        /** Replace the last item returned with a new value
         *  @param item The new value
         *  @throws IllegalStateException if next or previous
         *  was not called prior to calling this method
         */
        @Override
        public void set(E item) {
         }
        /*</exercise>*/
    } //end class KWListIter

    /*<exercise chapter="2" section="7" type="programming" number="1">*/
    /**
     * Method to find the index of the first occurence of an item in the list
     * @param target The item being sought
     * @return The index of the first occurence of the tartet item
     *         or -1 if the item is not found.
     */
    @Override
    public int indexOf(Object target) {
         return -1;
    }
    /*</exercise>*/

    /*<exercise chapter="2" section="7" type="programming" number="2">*/
    /**
     * Method to find the index of the last occurence of an item in the list
     * @param target The item being sought
     * @return The index of the last occurence of the tartet item
     *         or -1 if the item is not found.
     */
    @Override
    public int lastIndexOf(Object target) {
         return -1;
    }
    /*</exercise>*/

    /*<exercise chapter="2" section="7" type="programming" number="3">*/
    /**
     * Method to return the index of the minimum item in the list
     * It is assumed that each item in the list implements Comparable
     * @return Index of the minimum item in the list
     *         or -1 if the list is empty
     * @throws ClassCastExcepition if the list elements do not implement Comparable
     */
    public int indexOfMin() {
        return 0;
    }
    /*</exercise>*/

    /*<exercise chapter="2" section="6" type="programming" number="1">*/
    /** Static method to provide solution to exercise */
    public static void exercise_2_6_1() {
        // Create figure 2.20
        Node tom = new Node<String>("Tom");
        Node dick = new Node<String>("Dick");
        Node harry = new Node<String>("Harry");
        Node sam = new Node<String>("Sam");
 


		  Node<String> head = tom;
        Node<String> tail = sam;
        // Insert "Bill" before "Tom"


        // Insert "Sue" before "Sam"
        Node sue = new Node("Sue");

		  System.out.print("Printing out full list starting with ");
		  Node<String> temp = head;
		  while( temp != null ) {
		  		System.out.println( " node: " + temp );
				System.out.println("   data: " + temp.data + " next:" + temp.next + " prev:" + temp.prev);
				temp = temp.next;
		  }

        // Remove "Bill"

        // Remove "Sam"


		  System.out.print("Printing out list after removals ");
		  temp = head;
		  while( temp != null ) {
		  		System.out.println(" node: " + temp );
				System.out.println("   data: " + temp.data + " next:" + temp.next + " prev:" + temp.prev);
				temp = temp.next;
		  }

		  }
    /*</exercise>*/
}
/*</listing>*/


public class KWLinkedListTest_Lastna {
	 public static void main(String[] args) {
	 
	 	KWLinkedList.exercise_2_6_1();
		
	 	KWLinkedList<String> list1 = new KWLinkedList<String>();
		list1.addFirst("F");
		list1.addLast("L");
		list1.addFirst("I");
		list1.add(1," ");
		
		ListIterator<String> iter1 = list1.listIterator();
		iter1.next();
		iter1.next();
		iter1.add("R");
		String result = iter1.previous();
		System.out.println("Just added \"R\" and now calling previous returns " + result );
		
		iter1.remove();
		iter1.next();
		iter1.set("SET");
		
	   ListIterator<String> printIter = list1.listIterator();
		System.out.print("first-");
		while( printIter.hasNext() ) {
			String str = printIter.next();
			System.out.print("--> " + str + " <--");
		}
		System.out.println("--last");
	 }
}