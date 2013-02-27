//Sony Theakanath
//Data Structures

import info.gridworld.actor.*;
import info.gridworld.grid.*;
import java.util.ArrayList;

/**
	
*/

interface Robot extends Bug
{
	
	public Location positon() { }
}

class RandomRobot extends Robot
{

	/** Default constructor */
	public RandomRobot()
	{
		
	}

	public void act()
	{
		Grid<Actor> gr = getGrid();
	}	
}

public class RobotTester_Theakanath
{
	public static ActorWorld world = new ActorWorld(new BoundedGrid(9, 9));
	public static RandomRobot robot = new RandomRobot();
   
	public static void main(String[] args)
	{
		//world.add(new Location(9, 0), robot);
		setUpCourt();
		world.show();
	}
  
	/**
		This sets up the maze. The order starts from the bottom then
		to the top.
	*/
	public static void setUpCourt()
	{
		//Line 8
		world.add(new Location(8,0), new Rock());
		world.add(new Location(8,1), new Rock());
		world.add(new Location(8,2), new Rock());
		world.add(new Location(8,3), new Rock());
		world.add(new Location(8,4), new Rock());
		world.add(new Location(8,5), new Rock());
		world.add(new Location(8,6), new Rock());
		world.add(new Location(8,8), new Rock());
		
		//Line 7
		world.add(new Location(7,0), new Rock());
		world.add(new Location(7,6), new Rock());
		world.add(new Location(7,8), new Rock());
		
		//Line 6
		world.add(new Location(6,0), new Rock());
		world.add(new Location(6,1), new Rock());
		world.add(new Location(6,2), new Rock());
		world.add(new Location(6,4), new Rock());
		world.add(new Location(6,6), new Rock());
		world.add(new Location(6,8), new Rock());
		
		//Line 5
		world.add(new Location(5,0), new Rock());
		world.add(new Location(5,4), new Rock());
		world.add(new Location(5,8), new Rock());
		
		//Line 4
		world.add(new Location(4,0), new Rock());
		world.add(new Location(4,2), new Rock());
		world.add(new Location(4,4), new Rock());
		world.add(new Location(4,5), new Rock());
		world.add(new Location(4,6), new Rock());
		world.add(new Location(4,8), new Rock());
		
		//Line 3
		world.add(new Location(3,0), new Rock());
		world.add(new Location(3,2), new Rock());
		world.add(new Location(3,4), new Rock());
		world.add(new Location(3,8), new Rock());
		
		//Line 2
		world.add(new Location(2,0), new Rock());
		world.add(new Location(2,2), new Rock());
		world.add(new Location(2,3), new Rock());
		world.add(new Location(2,4), new Rock());
		world.add(new Location(2,5), new Rock());
		world.add(new Location(2,6), new Rock());
		world.add(new Location(2,8), new Rock());
		
		//Line 1
		world.add(new Location(1,0), new Rock());
		world.add(new Location(1,6), new Rock());
		world.add(new Location(1,8), new Rock());
		
		//Line 0
		world.add(new Location(0,0), new Rock());
		world.add(new Location(0,7), new Rock());
		world.add(new Location(0,2), new Rock());
		world.add(new Location(0,3), new Rock());
		world.add(new Location(0,4), new Rock());
		world.add(new Location(0,5), new Rock());
		world.add(new Location(0,6), new Rock());
		world.add(new Location(0,8), new Rock());
	}
}