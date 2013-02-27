//Sony Theakanath
//Data Structures
//Tree-Asg2: Reference Implementation

/**
   Tester class, it tests the BSTree by creating an array 
   and then it adds it into the array. If it works then it 
   prints all of the numbers in inorder order.
   
   Assessment: This program works perfectly, adding the array
   without the order and then after makes it ordered, thereby 
   making my program functional and working. It is tested well
   as I have used an array and showed both the before and after
   effects. 
*/
public class BSTreeTester_Theakanath
{
   public static void main(String[] args) {
      int[] numbers = {0, 2, 3, -1, 5, 10, 1, 4};
      BSTree tree = new BSTree(12);
      System.out.println("Previous Order");
      for(int x = 0; x < numbers.length; x++) {
         System.out.print(numbers[x] + " ");
         tree.insert(numbers[x]);
      }
      System.out.println("\nPrinting in order");
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
      else 
         ;
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

