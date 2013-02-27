//Sony Theakanath
//Data Structures -  Sorts-Asg1: Duo Core Work Merger

/**
	This class creates 2 arrays and combines them together
	sorting them from least to greatest.
*/

public class MergeTester_Theakanath
{
	public static void main(String[]args)
	{
		int[] firstarray = genArray();
		int[] secondarray = genArray();
		System.out.println("First array:");
		for(int x : firstarray)
			System.out.print(x + ", ");
		System.out.println("\nSecond array:");
		for(int x : secondarray)
			System.out.print(x + ", ");	
		System.out.println("Now sorting and combining the two arrays together.");
		int[] sorted = merge(firstarray, secondarray);
		System.out.println("Sorted and Combined array:");
		for(int x : sorted)
			System.out.print(x + ", ");
	}
	
	/**
		GenArray generates a random array from the size 900 to 1100
		and populates it with numbers between 1 to 10000.
	*/
	public static int[] genArray()
	{
		final int maxarraysize = 1100;
		final int minarraysize = 900;
		final int maxnumber = 10000;
		int sizeofarray = minarraysize + (int)(Math.random() * ((maxarraysize - minarraysize) + 1));
		int[] createdarray = new int[sizeofarray];
		for(int x = 0; x < createdarray.length; x++)
			createdarray[x] = 1 + (int)(Math.random() * maxnumber);
		createdarray = selectionSort(createdarray);
		return createdarray;
	}
	
	/**
		SelectionSort is a sorting algorithm. It sorts the array from
		least to greatest and returns it. This is used when creating a
		random array.
	*/
	public static int[] selectionSort(int[] arr)
	{
		int i, j, minIndex, tmp;
		int n = arr.length;
		for (i = 0; i < n - 1; i++)
		{
			minIndex = i;
			for (j = i + 1; j < n; j++)
				if (arr[j] < arr[minIndex])
					minIndex = j;
				if (minIndex != i)
				{
					tmp = arr[i];
					arr[i] = arr[minIndex];
					arr[minIndex] = tmp;
				}
		}
		return arr;
	}
	
	/**
		Merge combines two arrays together. It returns the combined 
		array sorted from least to greatest.
	*/
	public static int[] merge(int[] array1, int[] array2)
	{
		int[] combinedarray = new int[array1.length + array2.length];
		int x = 0, y = 0, z = 0;
		while (x < array1.length && y < array2.length)
			if (array1[x] < array2[y])       
				combinedarray[z++] = array1[x++];
			else        
				combinedarray[z++] = array2[y++];
		while (x < array1.length)  
			combinedarray[z++] = array1[x++];
		while (y < array2.length)    
			combinedarray[z++] = array2[y++];
		return combinedarray;
	}
}