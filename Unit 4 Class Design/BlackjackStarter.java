//Sony Theakanath
//Data Structures

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;

/**
	The starter starts the game. Nothing more to add.
*/

public class BlackjackStarter
{
	public static void main(String[]args)
	{
		Scanner scan = new Scanner(System.in);
		String answertogame;
		do {
			System.out.println("How many players do you have?");
			String answer = scan.nextLine();
			String[] players = new String[Integer.parseInt(answer)];
			for(int x = 0; x < players.length; x++)
			{
				System.out.println("Player " + (x+1) + "'s name?");
				players[x] = scan.nextLine();
			}
			Blackjack j = new Blackjack(players);
			System.out.println("-------------------------------\n\nPlay Again? (y or n)");
			answertogame = scan.nextLine();
		} while (answertogame.equals("y"));

	}
}

/**
	CardType determines the kind of card it is, ace or others and 
	it gives the cardvalue.
*/

class CardType
{
	private int[] values = {11, 10, 10, 10, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
	private String[] cardnames = {"Ace", "K", "Q", "J", "10", "9", "8","7", "6", "5", "4", "3", "2", "Ace"};
	
	private int currentcard;
	private String currentname;
	
	/**
		Constructor
	*/
	public CardType(int randomNumber)
	{
		currentcard = values[randomNumber];
		currentname = cardnames[randomNumber];
	}
	
	/**
		Returns the value of the card
	*/
	public int returnValue()
	{
		return currentcard;
	}
	
	/**
		Returns the name of the card
	*/
	public String returnName()
	{
		return currentname;
	}
}

/**
	Card handles the actual card. In real life it resembles 
	the actual card that you would hold. It has the kind of set
	it belongs to, and it handles all of the other values.
*/

class Card
{
	private CardType faceCard;
	private String cardtype;
	private int cardvalue;
	private String cardname;
		
	/**
		Constructor
	*/
	public Card(int randomnumber, String cardtype)
	{
		faceCard = new CardType(randomnumber);
		this.cardtype = cardtype;
		this.cardvalue = faceCard.returnValue();
		this.cardname = faceCard.returnName();
	}
	
	/**
		Make the Ace a value 1 if user requests so.
	*/
	public void changeToValueOne() { cardvalue = 1; }
	
	/**
		Returns the value of the card.
	*/
	public int getValue() { return cardvalue; }
	
	/**
		Returns the name of the card.
	*/
	public String returnCardName() { return cardname; }
	
	/**
		toString returns everything.
	*/
	public String toString() { return (returnCardName() + " of " + cardtype + " valued at " + cardvalue); }
}

/**
	Deck resembles the whole deck and it handles all of the cards.
*/
class Deck
{
	private ArrayList<Card> deck = new ArrayList<Card>();
	private String[] cardtypes = {"Clover", "Diamonds", "Hearts", "Spades"};
	
	/**
		Creates a new deck
	*/
	public Deck()
	{
		for(int x = 0; x < 4; x++)
			for(int y = 0; y < 13; y++)
				deck.add(new Card(y, cardtypes[x]));
		this.shuffle();
	}
	
	/**
		Shuffle shuffles the deck
	*/
	public void shuffle() { Collections.shuffle(deck); }
	
	/**
		takeTop takes the top card of the deck and removes it
		when taken.
	*/
	public Card takeTop()
	{
		Card thetopcard = deck.get(0);
		deck.remove(0);
		return thetopcard;
	}
}

/**
	Player resembles the player, it handles the hand he has, and the
	value of the cards he has.
*/

class Player
{
	public String name;
	public int valueofhand;
	public boolean bust = false;
	public ArrayList<Card> hand = new ArrayList<Card>();
	
	/**
		Constructor
	*/
	public Player(String name) { this.name = name; }
	
	/**
		Draws the first card so everyone can see.
	*/
	public void drawVisibleCard(Deck deckofcards)
	{
		Scanner scan = new Scanner(System.in);
		hand.add(deckofcards.takeTop());
		String answer;
		if(hand.get(hand.size()-1).returnCardName().equals("Ace"))
		{
			System.out.println("Do you want the Ace to be valued at 1 or 11? (1 or 11)");
			answer = scan.nextLine();
			if(answer.equals("1"))
				hand.get(hand.size()-1).changeToValueOne();
		}
		valueofhand = getValueofHand();
		System.out.println(name + " drew " + hand.get((hand.size()-1)).toString());
	}
	
	/**
		Returns the value of the players hand
	*/
	public int getValueofHand()
	{
		int totalsize = 0;
		for(int x = 0; x < hand.size(); x++)
			totalsize+= hand.get(x).getValue();
		return totalsize;
	}
}

/**
	Human extends the functionality of the player except it
	adds hit so that the player can add more cards if he wishes.
*/

class Human extends Player
{
	/**
		Constructor
	*/
	public Human(String name, Deck deckofcards)
	{
		super(name);
		super.drawVisibleCard(deckofcards);
	}
	
	/**
		Hit replicates the real blackjack game, he can hit until
		he goes bust.
	*/
	public void hit(Deck deckofcards)
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Do you want to hit? (y or n)");
		System.out.println("Your current hand value is " + getValueofHand());
		String answer = scan.nextLine();
		while(answer.equals("y") && !bust)
		{
			drawVisibleCard(deckofcards);
			System.out.println("Total value: " + getValueofHand());
			if(getValueofHand() > 21)
			{
				bust = true;
				System.out.println("You are bust!");
			} else {
				System.out.println("Do you want to hit again? (y or n)");
				answer = scan.nextLine();
			}
		}
	}
}

/**
	Computer extends the functionality of the Player exxcept 
	it hits automatically for itself and it draws the hidden cards
	for the players to bet themselves and to not cheat.
*/

class Computer extends Player
{
	/**
		Constructor
	*/
	public Computer(Deck deckofcards)
	{
		super("Computer");
		super.drawVisibleCard(deckofcards);
		configureComputerHand(deckofcards);
	}
	
	/**
		Draws a hidden card, and adds it to his deck.
	*/	
	public void drawHiddenCard(Deck deckofcards)
	{
		hand.add(deckofcards.takeTop());
		valueofhand = getValueofHand();
		System.out.println("Computer now drew its hidden card");
	}
	
	/**
		Keeps hitting until it hits a soft 17 or below 16, or else
		it doesn't bet.
	*/
	public void configureComputerHand(Deck deckofcards)
	{
		while(getValueofHand() <= 17)
			drawHiddenCard(deckofcards);
	}
}

/**
	This class allows the ArrayList to be sorted so that the
	winner can be determined easily.
*/

class HandComparator implements Comparator<Player>
{
	public int compare(Player player1, Player player2)
	{
		return player1.getValueofHand() - player2.getValueofHand();
	}
}

/**
	The blackjack class starts the game and adds everything
	together.
*/
class Blackjack
{
	private ArrayList<Human> players = new ArrayList<Human>();
	private Deck thedeck;
	
	/**
		Starts the game and finishes the game
	*/
	public Blackjack(String[] players)
	{
		thedeck = new Deck();
		System.out.println("Autodrawing everyones first card and setting up dealer...\n");
		System.out.println("Computer's turn. -----------");
		Computer computer = new Computer(thedeck);
		autoDraw(players, thedeck);
		playerHit(players, thedeck);
		ArrayList<Player> everyone = new ArrayList<Player>();
		for(Player player : this.players)
			everyone.add(player);
		everyone.add(computer);
		Collections.sort(everyone, new HandComparator());
		ArrayList<Player> bustpeople = addBustList(everyone);
		System.out.println("The winners are (in order from first to last):");
		for(int x = everyone.size()-1; x >= 0; x--)
			System.out.println(everyone.get(x).name + " at hand value " +  everyone.get(x).getValueofHand());
		if(bustpeople.size() > 0)
		{
			System.out.println("\nBUST PEOPLE:");
			for(int x = bustpeople.size()-1; x >= 0; x--)
				System.out.println(bustpeople.get(x).name + " at hand value " +  bustpeople.get(x).getValueofHand());
		}
	}
	
	/**
		Autostarts all the players
	*/
	public void autoDraw(String[] players, Deck thedeck)
	{
		for(int x = 0; x < players.length; x++)
		{
			System.out.println("\nAuto-" + players[x] + "'s turn. -----------");
			this.players.add(new Human(players[x], thedeck));
		}
	}
	
	/**
		Autostarts the player hits
	*/
	public void playerHit(String[] players, Deck thedeck)
	{
		System.out.println("\n\nTime to hit your cards!");
		for(int x = 0; x < this.players.size(); x++)
		{
			System.out.println("\n" + players[x] + "'s turn. -----------");
			this.players.get(x).hit(thedeck);
		}	
	}
	
	/**
		Adds the bustlist and returns it
	*/
	public ArrayList<Player> addBustList(ArrayList<Player> everyone)
	{
		ArrayList<Player> bustpeople = new ArrayList<Player>();
		for(int x = 0; x < everyone.size(); x++)
			if(everyone.get(x).getValueofHand() > 21)
			{
				bustpeople.add(everyone.get(x));
				everyone.remove(x);
				x--;
			}
		return bustpeople;
	}
}