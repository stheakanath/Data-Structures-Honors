//Sony Theakanath
//Data Structures

package org.bcp.Theakanath;
import java.util.Scanner;

/**
	TestPerson tests the Person class. 
*/

public class TestPerson_Theakanath
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
	
	/**
		Creates a new person based on user input.
		Preconditions: none
	*/
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

/**
	Person creates a person with a first name, last name and it
	keeps track of the number of persons based on the program.
*/

class Person
{
	private String firstname;
	private String lastname;
	private String name;
	private static int numpersons = 1;
	private int id;
	
	/**
		Default constructor
		Preconditons: There is a first and last name
	*/
	public Person(String setfirstname, String setlastname)
	{
		firstname = setfirstname;
		lastname = setlastname;
		id = numpersons;
		numpersons++;
	}
	
	/**
		Changes the persons fullname
		Preconditions: It's a full name
	*/
	public void changeName(String newname)
	{
		name = newname;
	}
	
	/**
		Changes the persons lastname
		Preconditions: It's a last name
	*/
	public void changeLastName(String newlast)
	{
		lastname = newlast;
	}
	
	/**
		Changes the persons first name
		Preconditions: It's a first name
	*/
	public void changeFirstName(String newfirst)
	{
		firstname = newfirst;
	}
	
	/**
		Changes the person's id
		Preconditions: there is an id, parameter
	*/
	public void changeid(int newid)
	{
		id = newid;
	}
	
	/**
		returns the person's first and last name together.
		Preconditions: none
	*/
	public String getName()
	{
		return firstname +  " " + lastname;
	}
	
	/**
		Returns the person's last name
		Preconditions: none
	*/
	public String getLastName()
	{
		return lastname;
	}
	
	/**
		Returns the person's first name
		Preconditions: none
	*/
	public String getFirstName()
	{
		return firstname;
	}
	
	/**
		Returns the person's id
		Preconditions: None
	*/
	public int getId()
	{
		return id;
	}
	
	/**
		Returns the number of people
		Preconditions: none
	*/
	public int getPeople()
	{
		return numpersons;
	}
	
	/**
		Class invariant, this combines the last name with the persons lastname.
		Preconditions: spouse has name
	*/
	public void marry(Person spouse)
	{
		lastname = spouse.getLastName();
	}
}