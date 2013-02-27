//Sony Theakanath
//August 20, 2012
//Intro-Asg2: Types

import java.math.BigInteger;

/**
	This program expiraments with the random datatypes.
*/

public class Types_Theakanath
{
	public static void main (String [] args)
	{
		casting();
		byteadd();
		bigintegercreation();
		lossoffloats();
	}
	
	//Casting expirament
	public static void casting()
	{
		final byte crumb = 1;
		final short richard = 200;
		final long chriscorrea = 400;
		final float boat = 324;
		final char icantthinkofaname = 2;
		
		//Converting to int
		int convertCrumb = (int)crumb;
		int convertRichard = (int)richard;
		int convertChriscorrea = (int)chriscorrea;
		int convertBoat = (int)boat;
		String convertIcantthinkofaname = String.valueOf(icantthinkofaname);
	}
	
	//Adding 1 to bytes
	public static void byteadd()
	{
		//Adding byte. It adds up to 127 but then after when it should be
		//128 it becomes -128 and decreases to -127 and so on.
		byte total = 1;
	 	final byte increment = 1; 
		for(int x = 0; x < 128; x++)
		{
			total+=increment;
		}
	}
	
	//Creating a big integer
	public static void bigintegercreation()
	{
		BigInteger big1 = new BigInteger("1241241213");
		BigInteger big2 = new BigInteger("32412123");
		System.out.println("Addding the BigIntegers together = ");
		System.out.println(big1.add(big2) + "\n-------------");
	}
	
	//Showing the loss of floats
	public static void lossoffloats()
	{	
		double test = 349882.323;
		System.out.println((float)test);
		System.out.println("As you see here, you have a loss of precision when you convert");
		System.out.println("a double into a float."); 
	}
}