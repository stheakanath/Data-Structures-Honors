//Sony Theakanath
//Adv:CS Data Structures
//Fall Final Project

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
 
/**
   Testing class no javadoc. Tests the user ben and matches it with
   the first person since it is the closest correlation. Prints out the
   books it recommended.
   
*/
public class Tester_Theakanath
{
   public static void main(String[] args)
   {
      Database base = new Database();
      Recommendation system = new Recommendation(base);
      String[] books = system.recommendBooks("Ben");
      for(String b : books)
         System.out.println(b);
   }
}

/**
   Recommendation takes the Database library. It's main functions are to 
   recommend books, compute the algorithm in order to recommend the books and 
   to recommend the matching books.
   
   Further explained below.
*/
class Recommendation
{
   private Database database;

   /**
      Constructor. It pairs the database with the class.
   */
   public Recommendation(Database database)
   {
      this.database = database;
   }
   
   /**
      recommendBooks takes the parameter. The parameter asks which
      person are we getting the recommendation for. It first locates the username in 
      the database. The second phase computes the algorithm:
      
         The algorithm compares all of the rating arrays and checks whether the two people
         have the same ratings. If they do then it adds 2 points to their total value. If 
         the ratings differ by 2 then it adds 1 to the total. The algorithm puts all of the 
         ratings in a single array for the next phase.
      
      The next phase (3) takes the correlation array and then it gets the index of the highest
      correlation from the algorithm from the array. 
      Phase 4 then takes this index found and then calls getSimilarBooks (explained below)
      and returns the books returned by the method. 
   */
   public String[] recommendBooks(String username)
   {
      //Phase 1
      int index = -1;
      for(int x = 0; x < database.users.size(); x++)
         if(database.users.get(x).equals(username))
            index = x;
      
      ArrayList<Integer> correlations = new ArrayList<Integer>();
      int similarity = 0;
      
      //Phase 2
      for(int x = 0; x < database.ratings.size(); x++) {
         similarity = 0;
         for(int y = 0; y < database.ratings.get(x).length; y++) {
            if(database.ratings.get(index)[y] == database.ratings.get(x)[y] && database.ratings.get(x)[y]!= 0)
               similarity+= 2;
            else if(Math.abs(Math.abs(database.ratings.get(index)[y])- Math.abs(database.ratings.get(x)[y])) == 2)
               similarity+= 1;
         }
         correlations.add(similarity);
      }
      
      //Phase 3
      int similarityindex = correlations.get(index);
      correlations.remove(index);
      int match = Collections.max(correlations);
      int indexofmatch = -1;
      for(int x = 0; x < correlations.size(); x++)
         if(correlations.get(x) == match)
         {
            indexofmatch = x;
            break;
         }
         
      //Phase 4
      String[] listofbooks = getSimilarBooks(index, indexofmatch);
      return listofbooks;
   }
   
   /**
      getSimilarBooks gets the index of the person that we are getting the recommendation
      of the books for and the second parameter is the index of the person that has the 
      highest correlation with the first parameter. 
      
      The method takes both of these two parameters and compares the ratings, making sure 
      they aren't less than 0 and they both didn't rate these. If it matches that then it 
      adds the book into a array that gets returned by the method. 
   */
   public String[] getSimilarBooks(int indexoforiginal, int indexofmatch)
   {
      int[] originalbooks = database.ratings.get(indexoforiginal);
      int[] matchedbooks = database.ratings.get(indexofmatch);
      ArrayList<Integer> indexesofunread = new ArrayList<Integer>();
      for(int x = 0; x < database.ratings.get(indexoforiginal).length; x++) {
         if(database.ratings.get(indexoforiginal)[x] == 0 && database.ratings.get(indexofmatch)[x] != 0)
             indexesofunread.add(x);
      }
      
      String[] books = new String[indexesofunread.size()]; 
      for(int x = 0; x < indexesofunread.size(); x++)
         books[x] = database.books.get(indexesofunread.get(x));
         
      return books;
   }
}

/**
   Database reads all of the text files that contains all of the databases. It is later 
   extensively used in the Recommendation class.
*/
class	Database	
{
   public ArrayList<String> books = new ArrayList<String>();
   public ArrayList<String> users = new ArrayList<String>();
   public ArrayList<int[]> ratings = new ArrayList<int[]>();
	
   /**
      Default Constructor, this calls readFile so that it can store it into the
      instance variables.
   */
   public Database()
	{
      readFile("books.txt", 0);
      readFile("ratings.txt", 1);
	}
   
   /**
      readFile takes the nameofthefile to find and the modifyarray value.
      If modifyarray is 0 then the program knows to modify the books array. If it is 1
      then it modifies users and ratings. It reads the files, parses them and then stores
      it into the arrays. 
   */
   private void readFile(String nameoffile, int modifyarray)
   {      
      ArrayList<String> temp = new ArrayList<String>();
      File inFile = new File(nameoffile);
		Scanner input = null;
		try {
			input	= new	Scanner(inFile);
		} catch (FileNotFoundException fnfE) {
			System.out.println("File not found");
		} catch (Exception e) {
         System.out.println("Other error");
      }
      while(input.hasNextLine()) {
         String nextLine = input.nextLine();
         temp.add(nextLine);
      }
      
      if(modifyarray == 0) { //books
         books = temp;
      } else if (modifyarray == 1) { //users and ratings 
         for(int x = 0; x < temp.size(); x++) {
            if(x%2 == 0)
               users.add(temp.get(x));
            else {
               String[] numbers = temp.get(x).split(" ");
               int[] numbersparsed = new int[numbers.length];
               for(int y = 0; y < numbers.length; y++)
                  numbersparsed[y] = Integer.parseInt(numbers[y]);
               ratings.add(numbersparsed);
            }
         }
      } else {
         System.out.println("Invalid inputter");
      }  
   }
}