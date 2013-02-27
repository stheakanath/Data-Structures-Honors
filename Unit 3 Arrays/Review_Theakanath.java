//Sony Theakanath
//DS

import java.util.Random;

/**
	These are just review exercises. Nothing special to 
	javadoc.
*/

public class Review_Theakanath
{
	public static void main(String[]args)
	{
		//R8
		int[] r8array = new int[10];
		int[] r8array2 = new int[10];
		Random rand = new Random();
		System.out.println("R8 Assignment:\n\nArray 1");
		for(int x = 0; x < 10; x++)
		{
			r8array[x] = rand.nextInt(100)+1;
			System.out.print(r8array[x]+ " ");
		}
		System.out.print("\nArray 2:\n");
		for(int x = 0; x < 10; x++)
		{
			int test = rand.nextInt(100)+1;
			for(int y = 0; y < x; y++)
			{
				if(test == r8array2[y])
				{
					test = rand.nextInt(100)+1;
					y--;
				}
				r8array2[x] = test;
			}
			System.out.print(r8array2[x] + " ");
		}
		
		/*
		
		Review Exercise 12 - In comment to avoid writing more code
		
		//a
		for (int x = 0; x < values.length; x++)
			sum+= values[x];
		//b
		for (int x = 0; x < values.length; x++)
		{
			if (values[x] == target)
				return true;
		}
		
		//c
		int i = 0;
		for (int x = 0; x < values.length; x++)
		{
			values[i] = 2 * values[x];
			i++;
		}
		*/
		
		/**
			R13 to R18 is in Comments
			
			---R13
			If you have an empty arraylist it will crash. Other
			than that it is fine.
						
			---R14
			The loop will still go through everything even though it already
			found the item
			
			---R15
			It's going to copy the second element to the rest of the array
			
			---R16
			It will have an index error if you just increase it by one.
			
			---R18
			a True
			b False
			c False
			d False
			e False (if you keep track of it)
			f False
			g True
			h True
			
			
		*/
	}
}