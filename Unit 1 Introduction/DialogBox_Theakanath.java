//Sony Theakanath
//Assignment 1

import javax.swing.JOptionPane;

/**
	This program asks the user for his name and an item to do.
	It then tells it can't do that.
	
	j
*/

public class DialogBox_Theakanath
{
	public static void main (String []args)
	{
		String name = JOptionPane.showInputDialog("What is your name?");
		String item = JOptionPane.showInputDialog("What would you like me to do?");
		JOptionPane.showMessageDialog(null, "I'm sorry, " + name + ". I'm afraid I can't do that.");
		System.exit(0);
	}
}