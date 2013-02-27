//Sony Theakanath
//August 23, 2012
//Advanced Comp Sci: Data Structures
//Intro-Asg3: File Reading and Flesch Calculation

import java.io.*;

/**
	This program reads a text file on a directory and computes the
	Flesch Readability Index for it. Based on the index it gives the
	reading level of the file.
*/

public class Flesh_Theakanath
{
	public static void main (String []args) throws IOException
	{
		//I'm a Mac so my directory will be different
		File testFile = new File("/Users/Sony/Desktop/test.txt");
		
		System.out.println("I will compute the Flesch Readability Index for your article.");
			//	System.out.println((getContents(testFile)));
		int index = (int)computeIndex(getContents(testFile));

		System.out.println("The Flesch RI for your file is : " + index);
		System.out.println("The reading level for your file is : " + readabilityLevel(index));
	}
	
	/**
		Taken from a tutorial, this method reads the File from a directory
		and converts it to a String file, which is what it returns.
	*/
	public static String getContents(File aFile)
	{
		StringBuilder contents = new StringBuilder();
		try
		{
      	BufferedReader input =  new BufferedReader(new FileReader(aFile));
      	try
			{
				String line = null;
				while ((line = input.readLine()) != null)
				{
				contents.append(line);
				contents.append(System.getProperty("line.separator"));
				}
      	}
      	finally
			{
				input.close();
      	}
    	}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
		return contents.toString();
	}
	
	/**
		countSyllabes counts the number of syllables in a word. If a
		word ends with a 'e' it checks whether it has more than 1 syllable
		and doesn't count that.
	*/
	public static int countSyllables(String word)
	{
		int totalsyllables = 0;
		boolean atcurrentvowel = false;
		for(int x = 0; x < word.length(); x++)
		{		
			if	(containsThatCharacter(word.charAt(x), true) && (atcurrentvowel == false))
			{
				atcurrentvowel = true;
				totalsyllables++;
			} else if (containsThatCharacter(word.charAt(x), true) && (atcurrentvowel == true)) {
				atcurrentvowel = true;
			} else {
				atcurrentvowel = false;
			}
		}
		
		/*if ((word.length() >= 1) && (word.charAt(word.length()-1) == 'e') && totalsyllables != 1)
			totalsyllables--;
		*/
		return totalsyllables;
	}
	
	/**
		containsThatCharacter checks whether the word contains that specific
		character. The second parameter switches between sentence counting
		capabilities and vowel checking.
		
		This method is used in countSyllables and countSentences.
	*/
	public static boolean containsThatCharacter(char letter, boolean forVowel)
	{
		char[] vowels = {'a', 'e', 'i', 'o', 'u', 'y'};
		char[] sentenceends = {'.', ':', ';', '?', '!'};
		
		if(forVowel)
		{
			for(int x = 0; x < vowels.length; x++)
			{
				if(letter == vowels[x])
					return true;
			}
		} else {
			for(int x = 0; x < sentenceends.length; x++)
			{
				if(letter == sentenceends[x])
					return true;
			}
		}
		return false;
	}
	
	/**
		replaceEnding replaces the endings of the wall of text with a blank
		so that countSyllables can be more easily handled.
	*/
	public static String replaceEnding(String text)
	{
		text.replace(".", " ");
		text.replace(";", " ");
		text.replace("?", " ");
		text.replace("!", " ");
		text.replace(":", " ");
		return text;
	}
	
	/**
		computeIndex computes the index of the text made by the file
		and returns it.
	*/
	public static double computeIndex(String text)
	{
		int numberofsyllables = 0;
		int numberofwords = 0;
		int numberofsentences = 0;
		
		
		//Counting sentences
		for(int x = 0; x < text.length(); x++)
		{
			if(containsThatCharacter(text.charAt(x), false))
				numberofsentences++;
		}
		
		//Counting words
		String cleanedup = replaceEnding(text);
		String[] splitwords = cleanedup.split(" ");
		numberofwords = splitwords.length;
		
		//Counting syllables
		for(int y = 0; y < splitwords.length; y++)
		{
			numberofsyllables += countSyllables(splitwords[y]);
		}
		
		//Calculating index
		double temp = 84.6*numberofsyllables/numberofwords;
		double temp2 = 1.015*numberofwords/numberofsentences;
		System.out.println(numberofsyllables + " " + numberofwords + " " + numberofsentences);
		return 206.835-temp-temp2;
	}
	
	/**
		Takes the index and returns the level of reading
	*/
	public static String readabilityLevel(int index)
	{
		if (index >= 91 && index <= 100)
			return "5th grader";
		else if (index >= 81 && index <= 90)
			return "6th grader";
		else if (index >= 71 && index <= 80)
			return "7th grader";
		else if (index >= 66 && index <= 70)
			return "8th grader";
		else if (index >= 61 && index <= 65)
			return "9th grader";
		else if (index >= 51 && index <= 60)
			return "High School Student";
		else if (index >= 31 && index <= 50)
			return "College Student";
		else if (index >= 0 && index <= 30)
			return "College graduate";
		else if (index < 0)
			return "Law school graduate";
		else
			return "Young reader";
	}
}