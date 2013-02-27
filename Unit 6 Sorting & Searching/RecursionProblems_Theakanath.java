//Sony Theakanath
//Data Structures

/**
	R13.1 - a) Recursion is the process of repeating items in a self
			  similar way
			  b) Iteration is the act of repeating a process with the intent
			  of reaching a specific goal.
			  c) Infinite Recursion is the state where a program goes in an
			  infinite loop, with no ending sequence or term to make it stop.
			  d) Recursive helper methods are used when you want to hide the 
			  implementation for a solution.
			  
	R13.2 - Set lowest to be first value of array and index to 0.
			  Then call same method Check if lowestsetvalue is less than 
			  the index+1 set parameter.
			  
	P13.6:
	public int getMaximum(int[] values, int first, int last)
	{
		if(first == values.length)
			return last;
		else
		{
			if(values[first] > last)
				last = values[first];
			getMaximum(values, first+1, last);
		}
	}
	
	P13.7:
	public int getSum(int[] values, int sum, int index)
	{
		if(index == values.length)
			return sum;
		else
			return getSum(values, sum+=values[index], index+1);
	} 
	
	P13.8:
		Preconditions
			aShape: is a polygon
			getAreaofTriangle(Point[] points) returns area of triangle with those points.
			cutTriangle1(Point[] points) retuns a cut triangle.
			cutTriangle2(Point[] points) continies from the top method and it returns a
			cut polygon that is left over from the cut triangle. 

	public class computeArea(Point[] points)
	{
		if(points.length == 3)
			return getAreaofTriangle(points);
		else
		{
			Point[] triangle = cutTriangle1(points);
			Point[] polygon = cutTriangle2(points);
			return getAreaofTriangle(triangle) + getAreaofTriangle(polygon);
		}
	}
*/

public class RecursionProblems_Theakanath
{
	public static void main(String[]args) { System.out.println("Answers above"); }
}