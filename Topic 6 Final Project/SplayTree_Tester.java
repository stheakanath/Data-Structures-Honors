// Kuriakose Sony Theakanath
// Debnil Sur
// James Garbagnati

import java.util.*;

public class SplayTree_Tester {
	public static void main(String[] args) {
		SplayTree myNameIsSonyAndICantProgram = new SplayTree(); // We won't be using this, I just felt it was necessary.
		SplayTree splay = new SplayTree();
		
		// Randomly creates a SplayTree, printing out the Tree after each insert
		int rand = (int) (Math.random() * 25 + .5);
		for (int i = 0; i < rand; i++) {
			int temp = (int) (Math.random() * 100 + .5);
			System.out.println("Attempting to add " + temp + " to the SplayTree:\nNew SplayTree:");
			splay.insert(temp);
			print(splay.getRoot());
		}
		
		// Removes all of the data by the root
		while ( splay.getSize() > 0 ) {
			if ( splay.getRoot() != null ) {
				System.out.println("Attempting to remove " + splay.getRoot().getValue() + " from the tree.\nNew SplayTree:");
				splay.remove(splay.getRoot().getValue());
				print(splay.getRoot());
			}
			else
				break;
		}
	}
	
	public static void print(Node node) {
		BTreePrinter printer = new BTreePrinter();
		if ( node != null )
			BTreePrinter.printNode(node);
	}
}

/**
	SplayTree
*/
class SplayTree {
	private Node rootNode;
	private int size;
	private static Node head = new Node(null);
	
	public SplayTree() {
		rootNode = null;
		size = 0;
	}
	
	/**
		Makes the Node node the rootNode of the SplayTree.
		
		@param node Node to be splayed
	*/
	public void splay( Comparable comp ) {
		Node left = head;
		Node right = head;
		Node top = rootNode;
		Node temp;
		
		head.setLeft( null );
		head.setRight( null );
		
		while( true ) {
			if ( comp.compareTo( top.getValue() ) < 0 ) { 
				if ( top.getLeft() == null ) // exit
					break;
				
				if ( comp.compareTo( top.getLeft().getValue() ) < 0 ) { // Rotate right
					temp = top.getLeft();
					top.setLeft( temp.getRight() );
					temp.setRight( top );
					top = temp;
					
					if ( top.getLeft() == null ) // exit
						break;
				}
				right.setLeft( top ); // Links the right
				right = top;
				top = top.getLeft();
			}
			else if ( comp.compareTo( top.getValue() ) > 0 ) {
				if ( top.getRight() == null ) // exit
					break;
				
				if ( comp.compareTo( top.getRight().getValue() ) > 0 ) { // Rotate left
					temp = top.getRight();
					top.setRight( temp.getLeft() );
					temp.setLeft( top );
					top = temp;
					
					if ( top.getRight() == null ) // exit
						break;
				}
				left.setRight( top ); // Links the left
				left = top;
				top = top.getRight();
			}
			else
				break; // exit
		}
		left.setRight( top.getLeft() );
		right.setLeft( top.getRight() );
		top.setLeft( head.getRight() );
		top.setRight( head.getLeft() );
		rootNode = top;
	}
	
	/**
		Gets the largest item in the tree.
		
		@return Largest item in the tree
	*/
	public Comparable getMax() {
		Node node = rootNode;
		if ( node == null )
			return null;
		
		while( node.getRight() != null )
			node = node.getRight();
		
		splay( node.getValue() );
		
		return node.getValue();
	}
	
	/**
		Gets the smallest item in the tree.
		
		@return Smallest item in the tree
	*/
	public Comparable getMin() {
		Node node = rootNode;
		if ( node == null )
			return null;
		
		while( node.getLeft() != null )
			node = node.getLeft();
		
		splay( node.getValue() );
		
		return node.getValue();
	}
	
	/**
		Inserts a node into the SplayTree, then splays around that node.
		
		@param comp Comparable value of new node being added
	*/
	public void insert ( Comparable comp ) {
		Node node = new Node( comp );
		if ( rootNode == null && size == 0 ) {
			rootNode = node;
			return;
		}
		
		splay(comp);
		
		int temp = comp.compareTo( rootNode.getValue() );
		if ( temp == 0 ) // Double checks for duplicates
			return;
		
		if ( temp < 0 ) {
			node.setLeft( rootNode.getLeft() );
			node.setRight( rootNode );
			rootNode.setLeft( null );
		}
		else {
			node.setRight( rootNode.getRight() );
			node.setLeft( rootNode );
			rootNode.setRight( null );
		}
		rootNode = node;
		size++;
	}
	
	/**
		Finds the item with the value comp.
		
		@param comp Comparable being searched for
		@return item if found, null otherwise
	*/
	public Comparable find( Comparable comp ) {
		if ( rootNode == null ) // Empty
			return null;
		
		splay( comp );
		
		if ( rootNode.getValue().compareTo( comp ) != 0 ) // Not found
			return null;
		
		return rootNode.getValue();
	}
	
	/**
		Removes the node with the value comp.
		
		@param comp Comparable being removed
	*/
	public void remove( Comparable comp ) {
		splay( comp );
		
		if ( comp.compareTo( rootNode.getValue() ) != 0 ) // Node not found
			return;
		
		if ( rootNode.getLeft() == null ) 
			rootNode = rootNode.getRight();
		else {
			Node node = rootNode.getRight();
			rootNode = rootNode.getLeft();
			splay( comp );
			rootNode.setRight( node );
		}
		
		size--;
	}
	
	/**
		Getter Methods
	*/
	public int getSize() { return size; }
	public boolean isEmpty() { return rootNode == null; }
	public Node getRoot() { return rootNode; }
}

/**
	Node Class
*/
class Node {
	// private data held in each node
	private Comparable value;
	private Node left;
	private Node right;
	
	/**
		Basic Constructor
		
		@param initvalue The initial value held in the Node.
	*/
	public Node(Comparable initValue) {
		value = initValue;
		left = null;
		right = null;
	}
	
	/**
		Overloaded Constructor method for all data.
		
		@param initvalue The initial value held in the Node.
		@param initLeft The left child node of this Node.
		@param initRight The right child node of this Node.
	*/
	public Node(Comparable initValue, Node initLeft, Node initRight) {
		value = initValue;
		left = initLeft;
		right = initRight;
	}
	
	//Basic getter methods.
	public Comparable getValue() { return value; }
	public Node getLeft() { return left; }
	public Node getRight() { return right; }
	
	// Basic mutator methods.
	public void setValue(Comparable theNewValue) { value = theNewValue; }
	public void setLeft(Node theNewLeft) { left = theNewLeft; }
	public void setRight(Node theNewRight) { right = theNewRight; }
}

/**
	Code found online: http://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram
	Prints out the Tree
*/
class BTreePrinter {

    public static <T extends Comparable<?>> void printNode(Node root) {
        int maxLevel = BTreePrinter.maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static <T extends Comparable<?>> void printNodeInternal(List<Node> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        BTreePrinter.printWhitespaces(firstSpaces);

        List<Node> newNodes = new ArrayList<Node>();
        for (Node node : nodes) {
            if (node != null) {
                System.out.print(node.getValue());
                newNodes.add(node.getLeft());
                newNodes.add(node.getRight());
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            BTreePrinter.printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                BTreePrinter.printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).getLeft() != null)
                    System.out.print("/");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(i + i - 1);

                if (nodes.get(j).getRight() != null)
                    System.out.print("\\");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static <T extends Comparable<?>> int maxLevel(Node node) {
        if (node == null)
            return 0;

        return Math.max(BTreePrinter.maxLevel(node.getLeft()), BTreePrinter.maxLevel(node.getRight())) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }

}