//Sony Theakanath
//Data Structures

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

/**
	The starter class starts the game from the Blackjack class.
	It loops so that the player can play again.
*/

public class BlackjackStarter_Theakanath
{
	public static void main(String[]args)
	{
		//Keeping track of players, scores, games
		ArrayList<Player> players = new ArrayList<Player>();
		
		
	}
}

class Blackjack
{
	private String[] cards = {"Ace", "K", "Q", "J", "10", "9", "8","7", "6", "5", "4", "3", "2"};
	private int dealerValue;
	
	public Blackjack(ArrayList players)
	{
		//Setting up Computer
		
		
		for(int x = 0; x < player.size(); x++)
		{
			
		}
	}
	
	public void dealerSetUp()
	{
		Random r = new Random();
		String card = cards[r.nextInt(13)];
		System.out.println("Dealer drew " + card);
		dealerValue+= this.convertCard(card);
		String card = cards[r.nextInt(13)];
		dealerValue+= this.convertCard(card);
		if(dealerValue <= 17)
		
	}
	
	public void playersTurn(Player person)
	{
		System.out.println("It is your turn " + person.getName());
		System.out.println("Your current value of cards " + person.numofpoints);
		System.out.println("Drawing your card....");
		randomCard(person);
		
	}
	
	public void drawAgain(Player person)
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Do you want to drawagain? (Y or N)");
		String answer = scan.nextLine();
		while(answer.equals("Y") || person.numofpoints > 21)
		{
			randomCard(person);
			System.out.println("Do you want to drawagain?");
			answer = scan.nextLine();
		}
		if(person.numofpoints > 21)
		{
			System.out.println("Player bust! Value at " + person.umofpoints);
			person.lost = true;
		}
	}	
	
	/**
		Draws a random card from the deck and adds it to the player value.
		If it is an Ace it asks the player if he wants it to be a 1 or a
		11, then adds to his value in his class.
	*/	
	public void randomCard(Player person)
	{
		String iface;
		int integervalueofcard;
		Random r = new Random();
		Scanner scan = new Scanner(System.in);
		String card = cards[r.nextInt(13)];
		if(card.equals("Ace"))
		{
			System.out.println("Do you want your Ace to be valued at 11 or 1? (11 for 11, anything else for 1)");
			iface = scan.nextLine();
			if(iface.equals("11"))
				integervalueofcard = 11;
			else
				integervalueofcard = 1;
		} else {
			integervalueofcard = convertCard(card, false);
		}
		System.out.println(person.getName() + " drew " + card + " valued at " + integervalueofcard + ".");
		person.numofpoints += integervalueofcard;
	}
	
	private void firstDraw(Person player)
	{
		Random r = new Random();
		Scanner scan = new Scanner(System.in);
		String card = cards[r.nextInt(13)];
	}
	
	/**
		Converts the String representation of the card to a number
		so that it can add to the player's value.
	*/
	private int convertCard(String card, boolean thereisace)
	{
			if(!thereisace)
		{
			if(card.equals("K") || card.equals("Q") || card.equals("J"))
				return 10;
			else
				return Integer.parseInt(card)
	}
}

class Player
{
	private String name;
	private int numberofgames;
	private int gameswon;
	
	//Game Tracking
	public int numofpoints;
	public boolean lost;
	
	
	public Player(String name) { this.name = name; }
	
	public String getName() { return name; }
	
	public int getNumOfGames() { return numberofgames; }
	
	public int getGamesWon() { return gameswon; }  
}