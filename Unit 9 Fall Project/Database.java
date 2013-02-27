import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
 
class	Database	
{
   ArrayList<String> books = new ArrayList<String>();
   
   public static void main(String[] args)
   {
      this.Database();
   }
	public Database()
	{
		File inFile	= new	File("books.txt");
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
         books.add(nextLine);
         System.out.println(nextLine);
      }
      

      //
      
	}
}