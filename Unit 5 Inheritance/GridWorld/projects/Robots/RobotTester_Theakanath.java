//Sony Theakanath
//Data Structures

import info.gridworld.actor.*;
import info.gridworld.grid.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
	Random Robot does what the name states, it is a random robot
	that moves around randomly.
*/
class RandomRobot extends Bug 
{
	private Random rand = new Random();
	
	/**
		Act makes the robot move.
	*/
	public void act()
	{
		if(getLocation().getRow() == 0 && getLocation().getCol() == 1)
		{
			System.err.println("Reached Finish Line!");
			removeSelfFromGrid();
		} else {
			Grid<Actor> gr = getGrid();
			int randomIndex = rand.nextInt(2);
			if(getLocation().getRow() == 9 && getLocation().getCol() == 1)
				javax.swing.JOptionPane.showMessageDialog(null, "Score: " + 3, "GAME OVER!", 0); 
			else if(randomIndex == 0)
			{	
				turn();
				turn();
			} else {
				if(!canMove())
				{
					turn();
					turn();
				} else
					super.move();
			}
		}
	}
}

/**
	RightWallRobot stays on the right side of the wall
	and reaches the end.
*/
class RightWallRobot extends Bug
{
	/**
		Act makes the robot move, and it checks if the robot
		reaches the end. If it has then it ends the maze.
	*/
	public void act()
	{
		try {
			Grid<Actor> gr = getGrid();
			if(gr.get(getLocation().getAdjacentLocation(getDirection())) instanceof Rock)
			{
				turn();
			} else if(gr.get(getLocation().getAdjacentLocation(getDirection()-270)) == null) {
				turn();
				turn();
				move();
			} else
				move();
		} catch (IllegalArgumentException e) {
			System.err.println("Reached Finish Line!");
			removeSelfFromGrid();
		}
	}
}

/**
	Memory robot checks the previous locations it has entered 
	and does not go there if it is a dead end.
*/

class MemoryRobot extends Bug
{
	private ArrayList<Location> donotgo = new ArrayList<Location>();
	
	/**
		Memory robot checks the previous locations and makes sure
		it doesn't go there, if it is a dead end. The act function
		handles all of this.
	*/
	public void act()
	{
		try {
			Grid<Actor> gr = getGrid();
			
			//Checking if dead end starts here
			boolean bad = false;
			if(getLocation().getAdjacentLocation(getDirection()).getRow() > 8 ||
			   getLocation().getAdjacentLocation(getDirection()).getRow() < 0 ||
				getLocation().getAdjacentLocation(getDirection()).getCol() > 8 ||
				getLocation().getAdjacentLocation(getDirection()).getCol() < 0)
			{
				if(!donotgo.contains(getLocation().getAdjacentLocation(getDirection())))
					donotgo.add(getLocation().getAdjacentLocation(getDirection()));
			}
			Location next = getLocation().getAdjacentLocation(getDirection());
			for(Location x : donotgo)
				if (next.equals(x))
					bad = true;
					
			//Regular moves
			if(gr.get(getLocation().getAdjacentLocation(getDirection())) == null && bad != true)
				move();
			else if(gr.get(getLocation().getAdjacentLocation(getDirection())) instanceof Rock)
			{
				donotgo.add(getLocation().getAdjacentLocation(getDirection()));
				turn();
				turn();
			} else if (gr.get(getLocation().getAdjacentLocation(getDirection()-270)) == null) {
				turn();
				turn();
			} else { 
				move();
			}
			bad = false; } catch (IllegalArgumentException e) {
				//Ends the maze
				System.err.println("Reached Finish Line!");
				removeSelfFromGrid();
		}
	}
}

/**
	RobotTester tests all three robots, and creates the maze.
*/
public class RobotTester_Theakanath
{
	public static ActorWorld world = new ActorWorld(new BoundedGrid(9, 9));
   
	public static void main(String[] args)
	{
		//Stating which robot to use
		Scanner scan = new Scanner(System.in);
		System.out.println("What robot do you want to use? m - memoryrobot, r = rightwallrobot, rand = randomrobot");
		String answer = scan.nextLine();
		Bug robot = null;
		if(answer.equals("m"))
			robot = new MemoryRobot();
		else if(answer.equals("r"))
			robot = new RightWallRobot();
		else if(answer.equals("rand"))
			robot = new RandomRobot();
		else {
			System.out.println("Not a valid answer, creating MemoryRobot");
			robot = new MemoryRobot();
		}
		
		//Change the location of the robot here.
		world.add(new Location(8, 7), robot);
		setUpMaze();
		world.show();
	}
  
	/**
		This sets up the maze. The order starts from the bottom then
		to the top.
	*/
	public static void setUpMaze()
	{
		String[] maze = {"******* *", "*     * *", "*** * * *", "*   *   *",
							  "* * *** *", "* * *   *", "* ***** *", "*     * *", 
							  "* *******"};
		for(int x = 0; x < maze.length; x++)
			for(int y = 0; y < maze[x].length(); y++)
				if(maze[x].substring(y,y+1).equals("*"))
					world.add(new Location(Math.abs(x-8), y), new Rock());
	}
}