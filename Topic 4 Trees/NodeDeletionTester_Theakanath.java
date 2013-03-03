//Sony Theakanath
//Data Structures
//Tree-Asg3: Node deletion

/**
   Tester class, it tests the BSTree by creating an array 
   and then it adds it into the array. If it works then it 
   prints all of the numbers in inorder order.
   
   Assessment: This program works perfectly, adding the array
   without the order and then after makes it ordered, thereby 
   making my program functional and working. It is tested well
   as I have used an array and showed both the before and after
   effects. Initally I had some problems, but I fixed the code
   in order to tmake it work. 
*/
public class NodeDeletionTester_Theakanath
{
   public static void main(String[] args) {
      String[] numbers = {"D", "H", "C", "A", "F", "E", "G", "B"};
      BSTree tree = new BSTree("J");
      System.out.println("Previous Order");
      for(int x = 0; x < numbers.length; x++) {
         System.out.print(numbers[x] + " ");
         tree.insert(numbers[x]);
      }
      System.out.println("\nPrinting in order before insertion");
      tree.print();
      tree.removeNode("D");
      System.out.println("\nPrinting in order after removal of D");
      tree.print();
   }
}

/**
   Takes the TreeNode class and a binary Tree consisting of two
   leafs. This BSTree class consists of a root TreeNode, which 
   starts the tree, insert, and print, which are explained later. 
*/ 
class BSTree
{
   public TreeNode root;
   
   /**
      Default constructor, it creates a root node. 
   */
   public BSTree(Comparable initvalue) {
      root = new TreeNode(initvalue);
   }
   
   /**
      Insert is the same as the function below, just 
      starts from the root so that it can sort properly. 
   */
   public void insert(Comparable comp) {
      insert(root, comp);
   }
   
   /**
      Insert is one of the main methods in this class. It takes two
      parameters, the first being a TreeNode and the second being 
      the object you want to insert, Comparable. This is a recursive
      method. The insert method sorts the Comparable object depending
      on the values and the code checks whether it should move left or 
      right. Movement of code is explained below. 
   */
   public TreeNode insert(TreeNode lookingat, Comparable comp) {
      if(lookingat == null) //When it reaches the end of the tree, create that node
         lookingat = new TreeNode(comp);
      else if (comp.compareTo(lookingat.getValue()) > 0) //If object is larger, move on right.
         lookingat.setRight(insert(lookingat.getRight(), comp));
      else if (comp.compareTo(lookingat.getValue()) < 0) //If object is less, move on left.
         lookingat.setLeft(insert(lookingat.getLeft(), comp));
      return lookingat;
   }
   
   /**
      Gets the minimum within the node tree you are looking at.
      If no value then null is returned.
   */
   public Comparable minimum(TreeNode lookingat) {
      if(lookingat != null) {
         while (lookingat.getLeft() != null)
            lookingat = lookingat.getLeft();
         return (Comparable)lookingat.getValue();
      }
      return null;
   }
   
   /**
      Yay for simple. It removes it based on a value that 
      you give.
   */
   public void removeNode(Comparable comp) {
      removeNode(root, comp);
   }
   
   /**
      Handles the removal of a Node. It checks the tree for the minimum
      value and moves the tree around to fix it. 
   */
   public TreeNode removeNode(TreeNode lookingat, Comparable comp) {
      if(lookingat != null) {
         if(comp.compareTo(lookingat.getValue()) < 0) 
            lookingat.setLeft(removeNode(lookingat.getLeft(), comp));
         else if(comp.compareTo(lookingat.getValue()) > 0) 
            lookingat.setRight(removeNode(lookingat.getRight(), comp));
         else if(lookingat.getLeft() == null)
            lookingat = lookingat.getRight(); 
         else if(lookingat.getRight() == null)
            lookingat = lookingat.getLeft();
         else {
            lookingat.setValue(minimum(lookingat.getRight()));
            lookingat.setRight(removeNode(lookingat.getRight(), (Comparable)lookingat.getValue()));  
         }
      }
      return lookingat;
   }

   /**
      Print starts the print from the root, just created to comply with
      assignment details.
   */
   public void print() {
      print(root);
   }
   
   /**
      Print takes the current tree and prints out the tree in inorder 
      traversal. This code can be leveraged to print out different
      orders, just need to change the order of the three functions below. 
   */
   public void print(TreeNode leaf) {
      if(!(leaf == null)) {
         print(leaf.getLeft()); //Move left
         System.out.print(leaf.getValue() + " "); //Then print if reached
         print(leaf.getRight()); //Move on right
      }
   }
}

/**
   TreeNode class, given class. Models as a node 
   inside a tree.
*/
class TreeNode
{
   private Object value;
   private TreeNode left;
   private TreeNode right;
  
   public TreeNode(Object initValue)
   { value = initValue; left = null; right = null; }

   public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
   { value = initValue; left = initLeft; right = initRight; }

   public Object getValue() { return value; }
   public TreeNode getLeft() { return left; }
   public TreeNode getRight() { return right; }

   public void setValue(Object theNewValue) { value = theNewValue; }
   public void setLeft(TreeNode theNewLeft) { left = theNewLeft; }
   public void setRight(TreeNode theNewRight) { right = theNewRight; }
}

