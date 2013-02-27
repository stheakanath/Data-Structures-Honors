//Sony Theakanath
//Inherits-Asg1: In Class Lab: Design Patterns

package org.bcp.Theakanath;

/**
	Student Test tests the students created
*/

public class StudentTest_Theakanath
{
	private static StudentTest_Theakanath singleton = new StudentTest_Theakanath();
	
	public static void main(String [] args)
	{
		run();
	}
	
	/**
		Run creates the students and prints them out. Afterwards
		it tests them for equality.
	*/
	public static void run()
	{
		Student sony = new Student("Sony", "Theakanath", 2014);
		Student richard = new Student("Richard", "Liu", 2016);
		Student bryce = new Student("Bryce", "Pauken", 2014);
		ChildlessStudent debnil = new ChildlessStudent("Debnil", "Sur", 2013);
		Student[] students = {sony, richard, bryce, debnil};
		
		//Printing out
		System.out.println("Printing out the students and their ids");
		for(int x = 0; x < students.length; x++)
			System.out.println(students[x].toString());
			
		System.out.println("\nTesting for equality between students (Bryce id now 20141 [same as Sony])");
		
		System.out.println("Richard equals Bryce?: " + richard.equals(bryce));
		bryce.changeid(20141);
		System.out.println("Sony equals Bryce?: " + sony.equals(bryce));
	}
}