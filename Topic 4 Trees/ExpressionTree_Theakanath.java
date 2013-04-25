//Sony Theakanath
//Data Structures
//BS Tree: Project

import java.io.*;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;
import java.util.EmptyStackException;
import java.util.LinkedList;

/**
   ExpressionTree tests out the expression. You can test out the program
   by editing the variable exp. 
   
   Limitations: Must not have whitespace and must be single digits. 
   
   Reflection: This assignment took a while to program. Everything works
   except for the fact that this cannot handle whitespace and single digits.
   In addition to that, I deleted several of the methods in BSUtilites because
   they weren't needed for the Expression Tree. This small program, though
   sounding simple, needed several helper methods in order to handle
   everything. I thought this was a fun assignment, especially when the program
   starts drawing out the tree. 
*/
public class ExpressionTree_Theakanath
{
   public static void main(String[]args) {
      TreeUtilities expressionmaker = new TreeUtilities();
      String exp = "5+2/4+2*5-2"; //Edit Expression here
      TreeNode expression = expressionmaker.createExpressionTree(exp);
      System.out.println("Expression: " + exp);
      TreeDisplay display = new TreeDisplay();
      System.out.println("Result: "+ expressionmaker.eval(expression));
      display.displayTree(expression);
   }   
}

/**
   A container for useful static methods that operate on TreeNode objects.
*/
class TreeUtilities
{
	/**
      precondition:  t is non-empty
	   postcondition: returns the value in the leftmost node of t.
	*/
   public static Object leftmost(TreeNode t) {
      while(t.getLeft() != null)
         t = t.getLeft();
      return t.getValue();
	}

	/**
      precondition:  t is non-empty
      postcondition: returns the value in the rightmost node of t.
	*/
   public static Object rightmost(TreeNode t) {
      while(t.getRight() != null)
         t = t.getRight();
      return t.getValue();
	}

	/**
      postcondition: returns the maximum depth of t, where an empty tree
	                  has depth 0, a tree with one node has depth 1, etc
	*/
   public static int maxDepth(TreeNode t) {
      if(t == null) {
         return 0;
      } else {
         int left = maxDepth(t.getLeft());
         int right = maxDepth(t.getRight());
         if(left > right)
            return left+1;
         else
            return right+1;
      }
	}
   
	/**
      Precondition:  expTree is an expression tree consisting of Integer objects
	                  joined by "+" and "*" operators
	   postcondition: returns the value of the expression tree
	*/
   public static int eval(TreeNode expTree) {
      int left = 0;
      int right = 0;
      int total = 0;
      if (expTree.getLeft() != null)
		   left = eval(expTree.getLeft());
      if (expTree.getRight() != null)
         right = eval(expTree.getRight());
      if (expTree.getLeft() == null && expTree.getRight() == null) {
         String value = (String)expTree.getValue();
         int convertvalue = Integer.parseInt(value);
         return convertvalue;
      } else {
         String value = (String)expTree.getValue();
         char operator = (value.trim()).charAt(0);
         if (operator == '+')
            total = left + right;
         else if (operator == '-') 
            total = left - right;
         else if (operator == '*')
            total = left * right;
         else if (operator == '/') 
            total = left / right;  
         return total;
      }
   }

	/**
      Precondition:  exp represents an arithmetic expression,
	                  consisting of "+", "*", paretheses and numbers
	   Postcondition: returns an expression tree to represent this arithmetic expression
	*/
   public static TreeNode createExpressionTree(String checker) {
      String converted = convert(checker);
      Stack numsandops = new Stack();
	   for (int x = 0; x < converted.length(); x++) {
	      char current = converted.charAt(x); 
         if (operator(current)) {
            TreeNode right = (TreeNode)numsandops.top();
            numsandops.pop();
            TreeNode left = (TreeNode)numsandops.top();
            numsandops.pop();
            String value = "";
            value += current;
            TreeNode nextvalue = new TreeNode(value, left, right);
            numsandops.push(nextvalue);
         } else {
            String value = new String();
            value += current;
            TreeNode nextvalue = new TreeNode(value, null, null);
            numsandops.push(nextvalue);
         }
      }
      TreeNode top = (TreeNode)numsandops.top();
      return top;
	}
   
   /**
      Checks if item is an operator
   */
   private static boolean operator(char item) {
	   if (item == '+' || item== '-' || item == '*' || item == '/')
         return true;
      return false;
   }
   
   /**
      Converts an inorder expression to postfix. 
   */
   private static String convert(String expression) {
      Stack operators = new Stack();
      String result = "";
	   for (int x = 0; x < expression.length(); x++) {
	      char currentchar = expression.charAt(x);
         if (currentchar >= '0' && currentchar <= '9')
            result += currentchar;
         else {
		      while (!operators.isEmpty() && comparePrecedence(currentchar, operators) <= 0) {
               result += operators.top();
               operators.pop();
		      }
            operators.push(currentchar);
         }
	   }
      while(!operators.isEmpty()) {
         result += operators.top();
         operators.pop();
      }
      return result;
   }
    
   /**
      Compares the precendence of operators so that it knows
      where to add to the tree. 
   */
   private static int comparePrecedence(char operator, Stack stack) {
      char compareto = ((Character)stack.top()).charValue();
      int operatorprec = 0;
      int comparetoprec = 0;
      if (operator == '*' || operator == '/')
         operatorprec = 1;
      if (compareto == '*' || compareto == '/')
         comparetoprec = 1;
      return operatorprec - comparetoprec;
    }
}

/**
   A graphical component for displaying the contents of a binary tree.
   Usage:
   TreeDisplay display = new TreeDisplay();
   display.displayTree(someTree);
   display.visit(someNode);
*/
class TreeDisplay extends JComponent
{
	private static final int ARC_PAD = 2;
	private TreeNode root = null;
	private TreeNode visiting = null;
	private Set visited = new HashSet();
	private int delay = 500;

	/**
      Creates a frame with a new TreeDisplay component.
	   (constructor returns the TreeDisplay component--not the frame).
   */
   public TreeDisplay() {
		JFrame frame = new JFrame("Tree Display");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(this);
		frame.pack();
		frame.setVisible(true);
		java.util.Timer timer = new java.util.Timer();
		TimerTask task = new TimerTask() {
			public void run() {
				TreeDisplay.this.repaint();
			}
		};
		timer.schedule(task, 0, 1000);
	}

	/**
      Tells the frame the default size of the tree
	*/
   public Dimension getPreferredSize() { return new Dimension(400, 300); }

	/**
      Called whenever the TreeDisplay must be drawn on the screen
   */
   public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		Dimension d = getSize();
		g2.setPaint(Color.white);
		g2.fill(new Rectangle2D.Double(0, 0, d.width, d.height));
      int depth = TreeUtilities.maxDepth(root);
      if (depth == 0)
        	return;
      if (depth == 1)
        	depth = 2;
      FontMetrics font = g2.getFontMetrics();
      int leftPad = font.stringWidth(
	   TreeUtilities.leftmost(root).toString()) / 2;
      int rightPad = font.stringWidth(
		TreeUtilities.rightmost(root).toString()) / 2;
      int textHeight = font.getHeight();
      drawTree(g2, root, leftPad + ARC_PAD, d.width - rightPad - ARC_PAD, textHeight / 2 + ARC_PAD, (d.height - textHeight - 2 * ARC_PAD) / (depth - 1));
   }

	/**
      Draws the tree, starting from the given node, in the region with x values ranging
	   from minX to maxX, with y value beginning at y, and next level at y + yIncr.
   */
   private void drawTree(Graphics2D g2, TreeNode t, int minX, int maxX, int y, int yIncr) {
		if (t == null)
			return;
		int x = (minX + maxX) / 2;
		int nextY = y + yIncr;
		g2.setPaint(Color.black);
		if (t.getLeft() != null) {
			int nextX = (minX + x) / 2;
			g2.draw(new Line2D.Double(x, y, nextX, nextY));
		}
		if (t.getRight() != null) {
			int nextX = (x + maxX) / 2;
			g2.draw(new Line2D.Double(x, y, nextX, nextY));
		}
		FontMetrics font = g2.getFontMetrics();
		String text = t.getValue().toString();
		int textHeight = font.getHeight();
		int textWidth = font.stringWidth(text);
		Rectangle2D.Double box = new Rectangle2D.Double(x - textWidth / 2 - ARC_PAD, y - textHeight / 2 - ARC_PAD, textWidth + 2 * ARC_PAD, textHeight + 2 * ARC_PAD);//, ARC_PAD, ARC_PAD);
		Color c;
		if (t == visiting)
			c = Color.YELLOW;
		else if (visited.contains(t))
			c = Color.ORANGE;
		else
			c = new Color(187, 224, 227);
		g2.setPaint(c);
		g2.fill(box);
		g2.setPaint(Color.black);
		g2.draw(box);
		g2.drawString(text, x - textWidth / 2, y + textHeight / 2);
		drawTree(g2, t.getLeft(), minX, x, nextY, yIncr);
		drawTree(g2, t.getRight(), x, maxX, nextY, yIncr);
	}

	/**
      Tells the component to switch to displaying the given tree
   */
   public void displayTree(TreeNode root) {
		this.root = root;
		repaint();
	}

	/**
      Light up this particular node, indicating we're visiting it.
	*/
   public void visit(TreeNode t) {
		if (visited.contains(t))
			visited = new HashSet();
		visiting = t;
		visited.add(t);
		repaint();
		try {
			Thread.sleep(delay);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
      Change the length of time to pause when visiting a node
	*/
   public void setDelay(int delay) { this.delay = delay; }
}

/**
   TreeNode simulates a node for use in the Binary Tree. It 
   stores a value and a left and right value.
*/
class TreeNode
{
   private Object value;
   private TreeNode left;
   private TreeNode right;
   private char operator;
  
   public TreeNode(Object initValue)
     { value = initValue; left = null; right = null; }
   
   public TreeNode(char operator, Object initValue)
     { value = initValue; left = null; right = null; this.operator = operator; }

   public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
     { value = initValue; left = initLeft; right = initRight; }

   public Object getValue() { return value; }
   public TreeNode getLeft() { return left; }
   public TreeNode getRight() { return right; }

   public void setValue(Object theNewValue) { value = theNewValue; }
   public void setLeft(TreeNode theNewLeft) { left = theNewLeft; }
   public void setRight(TreeNode theNewRight) { right = theNewRight; }
}

/**
   Basically creating a Stack, nothing more to explain.
*/
class Stack<E>
{
   private LinkedList stack;
   
   /** Default Constructor */
   public Stack()
    { stack = new LinkedList(); }
   
   /** Checks if stack is empty. */
   public boolean isEmpty()
    { if(stack.size() == 0) return true; return false; }
   
   /** Adds object to stack */
   public Object push(Object o)
    { stack.add(0, o); return o; }
      
   /** Gets top */
   public Object top() { return stack.getFirst(); }
   
   /** Takes object out of stack. Like Pez! */
   public Object pop() {
      if(isEmpty())
         throw new EmptyStackException();
      else {
         Object object = stack.getFirst();
         stack.remove(0);
         return object;
      }
   }
   
   /** Looks at the first element in the stack. */
   public Object peek() {
      if(isEmpty())
         throw new EmptyStackException();
      else {
         Object object = stack.getFirst();
         return object;
      }
   }
}