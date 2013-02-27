//Sony Theakanath
//Data Structures

/**
	Written Assignments:
	
	R14.3: b, d, c, a, h, i, j, e
	R14.5: 10, 50
	R14.7: O(nlog(n)), O(log(n)),	O(n log(n)), O(n2	log(n)),	O(sqrt(n)),	O(nsqrt(n)), O(nn), O(n3),	O(2n), O(n),
	
	Implementation	of	P14.6	below
*/

public class BookSortsAssn_Theakanath
{
	public static void main(String[]args)
	{
		int[]	array	= {3,4,1,24,1,2,321,12,12,4,5,4,2};
		System.out.println("Array before: ");
		for(int x :	array)
			System.out.print(x +	" ");
		array	= measurePerformance(array);
		System.out.println("\nArray after: ");
		for(int x :	array)
			System.out.print(x +	" ");
	}
	
	/**
		measurePerformance measures the performance of the	insertion Sort
		algorithm. 
	*/
	public static int[] measurePerformance(int[]	array)
	{
		//Starting measure
		long start = System.nanoTime();
		int[]	sortedarray	= insertionSort(array);
		long end	= System.nanoTime();
		long totalelapsed	= end-start;
		System.out.println("\n\nThe total time, in nanoseconds, to sort the array using insertionSort is "	+ totalelapsed);
		return sortedarray;
	}
	
	/**
		Classic insertion	sort algorithm.
	*/
	public static int[] insertionSort(int[] array)
	{
		for (int	i = 1; i	< array.length; i++)
		{
			int j	= i;
			int firstelement = array[i];
			while	((j >	0)	&&	(array[j-1]	> firstelement))
			{
				array[j]	= array[j-1];
				j--;
			}
			array[j]	= firstelement;
		}
		return array;
	}
}