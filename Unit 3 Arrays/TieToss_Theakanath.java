//Sony Theakanath
//September 1, 2012

import java.util.Random;

/*
	This program generates 20 rolls of random dies. It then checks
	for the longest run of the die and puts brackets around them.
*/

public class TieToss_Theakanath
{
	public static void main(String[]args)
	{
		//Generate random numbers
		Random rand = new Random();
		String numbers = "";
		int[] randomdietosses = new int[21];
		for(int x = 0; x < 20; x++)
		{
			randomdietosses[x] = rand.nextInt(6)+1;
			numbers+= randomdietosses[x];
		}
		
		//Spacing original
		String spacedoriginal = "";
		for(int x = 0; x < 20; x++)
			spacedoriginal += numbers.substring(x, x+1) + " ";
		
		//Finding longest run
		boolean isthemax = false;
		int currentcount = 0, highestcount = 0, highestend = 0;
		for (int y = 0; y < 20; y++)
		{
			if (randomdietosses[y] == randomdietosses[y+1])
			{
				currentcount++;
				isthemax = (currentcount >= highestcount)?true:false;
				highestcount = (currentcount >= highestcount)?currentcount+1:highestcount;
			} else if (randomdietosses[y] != randomdietosses[y+1]) {
				isthemax = (isthemax == true)?false:true;
				highestend = (isthemax == true)?y;
				currentcount = 0;
			}
		}
		
		//Getting the start position
		int higheststart = highestend - highestcount+1;
		numbers = numbers.substring(0, higheststart) + "(" + numbers.substring(higheststart, highestend+1)
						+ ")" + numbers.substring(highestend+1, 20);
		
		//Printing
		String spacedfinished = "";
		for(int x = 0; x < 22; x++)
			spacedfinished += numbers.substring(x, x+1) + " ";
		
		//Final Printing
		System.out.println("Original: " + spacedoriginal);
		System.out.println("Modified: " + spacedfinished);
	}
}