//Sony Theakanath
//Data Structures

/**
	ArrayListTester tests MyArrayList with several values.
*/

public class ArrayListTester_Theakanath
{
	public static void main(String[]args)
	{
		MyArrayList t = new MyArrayList(100000);
		System.out.println(t.size());
		for(int i = 0; i < 100; i++ )
		  t.add(i);
		System.out.println("size after adding 100 elements: " + t.size());
		t.add(20);
		t.set(20,10);
		t.add(40);
		System.out.println("size after adding 2 more elements: " + t.size());
		System.out.println("\nentire list:");
		for(int i = 0; i < t.size(); i++ )
		{
		  System.out.print(t.get(i) + "\t");
		  if( i%5 == 4 ) System.out.println();
		}
		
		// Does this work....?
		System.out.println("\n\nRetrieving element at index: " + t.size());
		System.out.println( t.get(t.size()) );
	}
}

/**
	MyArrayList replicates ArrayList (somewhat) and has all of the basic
	functionalities of ArrayList.
*/

class MyArrayList
{
	private Object[] array; //Array, which stores the array
	public int largestindex; //Largestindex which has the largest index of the array (of the largest filled values)
	
	/**
		Default constructor, sets array to have 1000 objects. 
	*/
	public MyArrayList()
	{
		array = new Object[1000];
		largestindex = 0;
	}
	
	/**
		Custom constructor, sets array to be a specified amount
	*/
	public MyArrayList(int lengthofarray)
	{
		array = new Object[lengthofarray];
		largestindex = 0;
	}
	
	/**
		findEndIndex finds the largest non-null index in the ArrayList, going backwards.
	*/
	public int findEndIndex()
	{
		int lastsize = -1;
		for(int x = 0; x < array.length; x++)
		{
			if(array[x] == null)
			{
				lastsize = x;
				break;
			}
		}
		return lastsize;
	}
	
	/**
		add adds an Object to the array. It throws
		an exception if the value is off the index.
	*/
	public void add(Object toadd)
	{
		int endindex = findEndIndex();
		if(endindex == -1)
			throw new IndexOutOfBoundsException();
		else
		{
			array[endindex] = toadd;
			largestindex = endindex;
		}
	}
	
	/**
		Sets the object at a specified index. Gives an exception if 
		it is higher than the length
	*/
	public void set(int indextoaddat, Object toadd)
	{
		if(findEndIndex() >= array.length || indextoaddat < 0)
			throw new IndexOutOfBoundsException();
		else
		{
			array[indextoaddat] = toadd;
			largestindex = findEndIndex();
		}
	}
	
	/**
		Returns the Object at the requested index. If the requested index is
		higher than the highest modified value in the array it gives an
		exception. 
	*/
	public Object get(int indextoget)
	{
		if(indextoget >= findEndIndex() || indextoget < 0)
			throw new IndexOutOfBoundsException();
		else
			return array[indextoget];
	}
	
	/**
		Size returns the size of the array.
	*/
	public int size()
	{
		int lastsize = -1;
		for(int x = array.length-1; x > -1; x--)
		{
			if(array[x] != null)
			{
				lastsize = x;
				break;
			}
		}
		if(lastsize == -1)
			return 0;
		else
			return lastsize+1;
	}
}

