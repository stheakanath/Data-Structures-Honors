//Sony Theakanath
//Data Structures

import java.util.ArrayList;

/**
	Test tests all of the classes and prints the greeting out
*/
public class DifferentTeachers_Theakanath
{
	public static void main(String[]args)
	{
		ArrayList<Person> people = new ArrayList<Person>();
		people.add(new BCPStudent("Sony","Theakanath"));
		people.add(new BCPTeacher("Brad","Lindemann"));
		people.add(new SFStudent("Cockroach","Liu"));
		people.add(new SFTeacher("Bruce","Penikin"));
		System.out.println("\n");
		
		/**
			The super statement gets called first then the Student class
			gets called second.
		*/
		for(Person guy : people)
			guy.greet();
	}
}

/**
	SFTeacher resembles person but it prints out the class name (Without hardcoded 
	values) and sets up the greet method.
*/
class	SFTeacher extends Person
{
	private static int numpersons = 1;
	
	public SFTeacher(String setfirstname, String setlastname)
	{
		super(setfirstname, setlastname);
		System.out.println(this.getClass().getCanonicalName() + " born.");
		numpersons++;
		super.addClass();
	}
	
	public void greet()
	{
		String greeting = "Hi, I am " + getName() + " and I am a terrible " + this.getClass().getCanonicalName();
		super.setGreeting(greeting);
		System.out.println(getGreeting());
	}
	
	public int specificCount() { return numpersons-1; }
}

/**
	SFStudent resembles person but it prints out the class name (Without hardcoded 
	values) and sets up the greet method.
*/
class	SFStudent extends Person
{
	private static int numpersons = 1;
	
	public SFStudent(String setfirstname, String setlastname)
	{
		super(setfirstname, setlastname);
		System.out.println(this.getClass().getCanonicalName() + " born.");
		numpersons++;
		super.addClass();
	}
	
	public void greet()
	{
		String greeting = "Hi, I am " + getName() + " and I am a terrible " + this.getClass().getCanonicalName();
		super.setGreeting(greeting);
		System.out.println(getGreeting());
	}
	
	public int specificCount() { return numpersons-1; }
}

/**
	BCPTeacher resembles person but it prints out the class name (Without hardcoded 
	values) and sets up the greet method.
*/
class BCPTeacher extends Person
{
	private static int numpersons = 1;
	
	public BCPTeacher(String setfirstname, String setlastname)
	{
		super(setfirstname, setlastname);
		System.out.println(this.getClass().getCanonicalName() + " born.");
		numpersons++;
		super.addClass();
	}
	
	public void greet()
	{
		String greeting = "Hi, I am " + getName() + " and I am an awesome " + this.getClass().getCanonicalName();
		super.setGreeting(greeting);
		System.out.println(getGreeting());
	}
	
	public int specificCount() { return numpersons-1; }
}

/**
	BCPStudent resembles person but it prints out the class name (Without hardcoded 
	values) and sets up the greet method.
*/
class BCPStudent extends Person
{
	private static int numpersons = 1;
	
	public BCPStudent(String setfirstname, String setlastname)
	{
		super(setfirstname, setlastname);
		System.out.println(this.getClass().getCanonicalName() + " born.");
		numpersons++;
		super.addClass();
	}
	
	public void greet()
	{
		String greeting = "Hi, I am " + getName() + " and I am an awesome " + this.getClass().getCanonicalName();
		super.setGreeting(greeting);
		System.out.println(getGreeting());
	}
	
	public int specificCount() { return numpersons-1; }
}


/**
	Person creates a person with a first name, last name and it
	keeps track of the number of persons based on the program.
	
	Taken from last program and not modified.
*/
abstract class Person
{
	private String firstname, greeting, lastname, name;
	private static int numpersons = 1, familycount = 1;
	private int id;
	
	/**
		Default constructor
		Preconditons: There is a first and last name
	*/
	public Person(String setfirstname, String setlastname)
	{
		System.out.println("Person born");
		firstname = setfirstname;
		lastname = setlastname;
		id = numpersons;
		numpersons++;
		familycount++;
	}
	
	/**
		greet is an abstract method which can be edited to do anything
	*/
	abstract void greet();
	
	/**
		Changes the persons fullname
		Preconditions: It's a full name
	*/
	public void changeName(String newname) { name = newname; }
	
	/**
		Changes the persons lastname
		Preconditions: It's a last name
	*/
	public void changeLastName(String newlast) { lastname = newlast; }
	
	/**
		Changes the persons first name
		Preconditions: It's a first name
	*/
	public void changeFirstName(String newfirst) { firstname = newfirst; }
	
	/**
		Changes the person's id
		Preconditions: there is an id, parameter
	*/
	public void changeid(int newid) { id = newid; }
	
	/**
		returns the person's first and last name together.
		Preconditions: none
	*/
	public String getName() { return firstname +  " " + lastname; }
	
	/**
		Returns the person's last name
		Preconditions: none
	*/
	public String getLastName() { return lastname; }
	
	/**
		Returns the person's first name
		Preconditions: none
	*/
	public String getFirstName() { return firstname; }
	
	/**
		Returns the person's id
		Preconditions: None
	*/
	public int getId() { return id; }
	
	/**
		Returns the number of people
		Preconditions: none
	*/
	public int getPeople() { return numpersons-1; }
	
	/**
		Returns the number of classes created.
		Preconditions: none
	*/
	public int familyCount() {	return familycount-1; }
	
	/**
		Returns the greeting
	*/
	public String getGreeting() {	return greeting; }
	
	/**
		Sets the greeting
	*/
	public void setGreeting(String greeting) { this.greeting = greeting; }
	
	/**
		Adds one more to family class so that it can keep track
		the number of classes created.
	*/
	public void addClass() { familycount++; }
	
	/**
		Class invariant, this combines the last name with the persons lastname.
		Preconditions: spouse has name
	*/
	public void marry(Person spouse) { lastname = spouse.getLastName(); }
	
	/**
		Compares two people, teachers come before students.
	*/
	public int compareTo(Person o)
	{
		String otherclass = o.getClass().getCanonicalName();
		String thisclass = this.getClass().getCanonicalName();
		if(otherclass.equals(thisclass))
			return o.getName().compareTo(this.getName());
		else if((otherclass.equals("BCPTeacher") || otherclass.equals("SFTeacher")) &&
				  (!thisclass.equals("BCPTeacher") || !thisclass.equals("SFTeacher")))
			return -1;
		else if((!otherclass.equals("BCPTeacher") || !otherclass.equals("SFTeacher")) &&
				  (thisclass.equals("BCPTeacher") || thisclass.equals("SFTeacher")))
			return 1;
		else
			return o.getName().compareTo(this.getName());
	}
}