//Sony Theakanath

/*
	Sorry i didnt turn in this assignment. totally forgot about it.
*/

import java.util.*;

public class EasyProblem_Theakanath
{
	public static void main(String[]args)
	{
		char[] alphabet = {'A', 'B', 'C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		char[] inputtedletters = new char[5];
		char[] newletter = new char[5];
		Scanner scan = new Scanner(System.in);
		
		//Input
		for(int x = 0; x < 5; x++)
			inputtedletters[x] = scan.nextLine().charAt(0);
			
		//Formatting
		for(int x = 0; x < 5; x++)
		{
			int index = findIndex(inputtedletters[x])+1; //Not correctindex!
			
			//Checking
			if(index > 0 && index <=5)
			{
				newletter[x] = alphabet[(index*2)-1];
			} else if (index > 5 && index <=10) {
				int divide = (index%3)*5;
				newletter[x] = alphabet[divide-1];
			} else if (index > 10 && index <=15) {
				int correct = wraparound(((index-(index%4))/4)*8);
				newletter[x] = alphabet[correct-1];
			} else if (index > 15 && index <=20) {
				String number = index + "";
				int firstindex = Integer.parseInt(number.substring(0,1));
				int secondindex = Integer.parseInt(number.substring(1,2));
				int correct = wraparound((firstindex+secondindex)*10);
				newletter[x] = alphabet[correct-1];
			} else if (index > 20 && index <=26) {
				int correct = wraparound((largestfactor(index)*12)-1);
				newletter[x] = alphabet[correct];
			} else {
				newletter[x] = '#';
			}
		}
		
		for(int x = 0; x < 5; x++)
			System.out.println(newletter[x]);
	}
	
	public static int findIndex(char input)
	{
		char[] alphabet = {'A', 'B', 'C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		for (int x = 0; x < 26; x++)
			if(alphabet[x] == input)
				return x;
		return -1;
	}
	
	public static int wraparound(int input)
	{
		int temp = input;
		while(!(temp < 26))
		{
			temp= temp - 26;
		}
		return temp;
	}
	
	public static int largestfactor(int limit)
	{
		ArrayList<Integer> factors = new ArrayList<Integer>();
		for (int i=1; i<=limit; i++)
		{
			for (int j=limit; j>=1; j--)
			{
				if (j *i == limit)
				{
					factors.add(i);
					factors.add(j);
				}
			}
		}
		Collections.sort(factors);
		if(factors.get(factors.size()-1) == limit)
			factors.remove(factors.size()-1);
		if(factors.get(factors.size()-1) == limit)
			factors.remove(factors.size()-1);
			
		return factors.get(factors.size()-1);
	}

}