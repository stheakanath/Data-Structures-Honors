//Sony Theakanath
//Data Structures

/**
	ArrayListTester tests MyArrayList with several values.
*/

public class MyArrayListTester_Theak
{
	public static void main(String[]args)
	{
		MyArrayList<Integer> t = new MyArrayList<Integer>( );
		System.out.println(t.size()); 
		for(int i = 0; i<100000; i++ ) 
			t.add(i); 
		System.out.println(t.size()); 
		t.remove(2); 
		t.remove(20000); 
		t.set(20,10); 
		t.add(40,20); 
		System.out.println(t.size()); 
		for(int i = 0; i<42; i++ ) 
			System.out.println(t.get(i));
	}
}

/**
	MyArrayList replicates ArrayList and has all of the basic
	functionalities of ArrayList.
*/

class MyArrayList<SomeType>
{	
	private SomeType[] arraylist;
	private int sizeofarray;
	
	/**
		Default constructor, sets empty array 
	*/
	public MyArrayList()
	{
		sizeofarray = 0;
		createadditional(10);
	}
		
	/**
		Size returns the size of the array.
	*/
	public int size()
	{
		return sizeofarray;
	}
	
	/**
		add adds an Object to the array.
	*/
	public void add(SomeType toadd)
	{
		add(size(), toadd);
	}
	
	/**
		Overloaded add where the user can set where the object
		index will be.
	*/
	public void add(int index, SomeType toadd)
	{
		if(arraylist.length == size())
			createadditional(size()*2+1);
		for(int x = sizeofarray; x > index; x--)
			arraylist[x] = arraylist[x-1];
		arraylist[index] = toadd;
        sizeofarray++;  
	}
	
	/**
		Removes an index from the arraylist and lowers the index.
	*/
	
	public void remove(int index)
	{
		SomeType removeditem = arraylist[index];
		for(int i = index; i < size() - 1; i++)
			arraylist[i] = arraylist[i+1];
		sizeofarray--;
	}
	
	/**
		Searches for the first occurence of the given argument,
		testing for equality using the equals method.
	*/
	public int indexOf(SomeType obj)
	{
		for(int x = 0; x < sizeofarray; x++)
			if(arraylist[x].equals(obj))
				return x;
		return -1;
	} 
	
	/**
		Replaces the element at the specified position in this list
		with the specified element.
	*/
	public void set(int indextoaddat, SomeType toadd)
	{
		if(indextoaddat >= sizeofarray || indextoaddat < 0)
			throw new IndexOutOfBoundsException();
		else
			arraylist[indextoaddat] = toadd;
	}
	
	/**
		Returns true if this list contains the specified element.
	*/
	public boolean contains(SomeType obj)
	{
		for(int x = 0; x < sizeofarray; x++)
			if(arraylist[x].equals(obj))
				return true;
		return false;
	}

	/**
		Returns the Object at the requested index. If the requested index is
		higher than the highest modified value in the array it gives an
		exception. 
	*/
	public Object get(int indextoget)
	{
		if(indextoget >= sizeofarray || indextoget < 0)
			throw new IndexOutOfBoundsException();
		else
			return arraylist[indextoget];
	}
	
	/**
		Creates the new array and resizes everything
	*/
	private void createadditional(int finallength)
	{
		if(finallength < sizeofarray )
			return;
		SomeType[] previous = arraylist;
		arraylist = (SomeType [])new Object[finallength];
		for(int x = 0; x < size(); x++)
			arraylist[x] = previous[x];
	}
}

