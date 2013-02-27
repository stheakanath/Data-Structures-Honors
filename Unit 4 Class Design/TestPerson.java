//Sony Theakanath
//Data Structures


import java.util.Scanner;

/**
	TestPerson tests 
*/

public class TestPerson
{
	public static void main(String[]args)
	{
		//Creating People
		Person person1 = createPerson();
		Person person2 = createPerson();
		
		//Displaying name and Id
		System.out.println("\n" + person1.getName() + "'s id: " + person1.getId());
		System.out.println(person2.getName() + "'s id: " + person2.getId()); 
		
		//Display total number of persons
		System.out.println("The total amount of people are " + person2.getId());
		
		//Test Marry
		System.out.println("Marrying " + person2.getName() + "'s name with " + person1.getName() + "'s surname...");
		person2.marry(person1);
		System.out.println("New Name: " + person2.getName());
	}
	
	public static Person createPerson()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("What's the last name?");
		String lastname = scan.nextLine();
		System.out.println("What's the first name?");
		String firstname = scan.nextLine();
		Person theperson = new Person(firstname, lastname);
		return theperson;
	}
}

package org.bcp.Theakanath;

class Person
{
	private String firstname;
	private String lastname;
	private String name;
	private static int numpersons = 1;
	private int id;
	
	public Person(String setfirstname, String setlastname)
	{
		firstname = setfirstname;
		lastname = setlastname;
		id = numpersons;
		numpersons++;
	}
	public void changeName(String newname)
	{
		name = newname;
	}
	
	public void changeid(int newid)
	{
		id = newid;
	}
	
	public String getName()
	{
		return firstname +  " " + lastname;
	}
	
	public String getLastName()
	{
		return lastname;
	}
	
	public String getFirstName()
	{
		return firstname;
	}
	
	public int getId()
	{
		return id;
	}
	
	public int getPeople()
	{
		return numpersons;
	}
	
	public void marry(Person spouse)
	{
		lastname = spouse.getLastName();
	}
}