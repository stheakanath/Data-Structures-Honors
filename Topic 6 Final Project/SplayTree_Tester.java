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
	
	public SplayTree() {
		rootNode = null;
		size = 0;
	}
	
	/**
		Rotates the SplayTree left on the Node node.
		
		@param node Node to be rotated on
	*/
	public void rotateLeft( Node node ) {
		Node rChild = node.getRight();
		node.setRight(rChild.getLeft());
		if ( rChild.getLeft() != null )
			rChild.getLeft().setParent(node);
		rChild.setParent(node.getParent());
		if ( node.getParent() == null )
			rootNode = rChild;
		else if ( node == node.getParent().getLeft() )
			node.getParent().setLeft(rChild);
		else
			node.getParent().setRight(rChild);
		rChild.setLeft(node);
		node.setParent(rChild);
	}
	
	/**
		Rotates the SplayTree right on the Node node.
		
		@param node Node to be rotated on
	*/
	public void rotateRight( Node node ) {
		Node lChild = node.getLeft();
		node.setLeft(lChild.getRight());
		
		if ( lChild.getRight() != null )
			lChild.getRight().setParent(node);
		
		lChild.setParent(node.getParent());
		
		if ( node.getParent() == null )
			rootNode = lChild;
		else if ( node == node.getParent().getLeft() )
			node.getParent().setLeft(lChild);
		else
			node.getParent().setRight(lChild);
		
		lChild.setRight(node);
		node.setParent(lChild);
	}
	
	/**
		Makes the Node node the rootNode of the SplayTree.
		
		@param node Node to be splayed
	*/
	public void splay( Node node ) {
		while ( node.getParent() != null ) {
			if ( node.getParent().getParent() == null ) {
				if ( node.getParent().getLeft() == node )
					rotateRight(node.getParent());
				else
					rotateLeft(node.getParent());
			}
			else if ( node.getParent().getLeft() == node && node.getParent().getParent().getLeft() == node.getParent() ) {
				rotateRight(node.getParent().getParent());
				rotateRight(node.getParent());
			}
			else if ( node.getParent().getRight() == node && node.getParent().getParent().getRight() == node.getParent() ) {
				rotateLeft(node.getParent().getParent());
				rotateLeft(node.getParent());
			}
			else if ( node.getParent().getLeft() == node && node.getParent().getParent().getRight() == node.getParent() ) {
				rotateRight(node.getParent());
				rotateLeft(node.getParent());
			}
			else {
				rotateLeft(node.getParent());
				rotateRight(node.getParent());
			}
		}
	}
	
	/**
		Replaces Node n1 with Node n2.
		
		@param n1 Node to be replaced
		@param n2 Node replacing n1
	*/
	public void replace( Node n1, Node n2 ) {
		if ( n1 == rootNode )
			rootNode = n2;
		else if ( n1 == n1.getParent().getLeft() )
			n1.getParent().setLeft(n2);
		else
			n1.getParent().setRight(n2);
		
		if ( n2 != null )
			n2.setParent(n1.getParent());
	}
	
	/**
		Gets the largest node in the subtree.
		
		@param node Root of the subtree
		@return Largest node in the subtree
	*/
	public Node getSubtreeMaximum( Node node ) {
		while ( node.getRight() != null )
			node = node.getRight();
		return node;
	}
	
	/**
		Gets the smallest node in the subtree.
		
		@param node Root of the subtree
		@return Smallest node in the subtree
	*/
	public Node getSubtreeMinimum( Node node ) {
		while ( node.getLeft() != null ) 
			node = node.getLeft();
		return node;
	}
	
	/**
		Inserts a node into the SplayTree, then splays around that node.
		
		@param comp Comparable value of new node being added
	*/
	public void insert ( Comparable comp ) {
		if ( find(comp) != null ) // Ensures no duplicate nodes
			return;
		Node node = rootNode;
		Node parent = null;
		
		while ( node != null ) {
			parent = node;
			if ( node.getValue().compareTo(comp) > 0 )
				node = node.getRight();
			else
				node = node.getLeft();
		}
		
		node = new Node(comp, parent);
		
		if ( parent == null )
			rootNode = node;
		else if ( parent.getValue().compareTo(node.getValue()) < 0 )
			parent.setRight(node);
		else
			parent.setLeft(node);
		
		splay(node);
		size++;
	}
	
	/**
		Finds the node with the value comp.
		
		@param comp Comparable being searched for
		@return node of comp if found, null otherwise
	*/
	public Node find( Comparable comp ) {
		Node node = rootNode;
		while ( node != null ) {
			if ( node.getValue().compareTo(comp) > 0 )
				node = node.getRight();
			else if ( node.getValue().compareTo(comp) < 0 )
				node = node.getLeft();
			else
				return node;
		}
		return null;
	}
	
	/**
		Removes the node with the value comp.
		
		@param comp Comparable being removed
	*/
	public void remove( Comparable comp ) {
		Node node = find(comp);
		if ( node == null )
			return;
		else if ( size == 1 ) {
			rootNode = null;
			size--;
			return;
		}
		splay(node);
		
		if ( node.getLeft() == null )
			replace(node, node.getRight());
		else if ( node.getRight() == null )
			replace(node, node.getLeft());
		else {
			Node tempNode = getSubtreeMinimum(node.getRight());
			if ( tempNode.getParent() != node ) {
				replace(tempNode, tempNode.getRight());
				tempNode.setRight(node.getRight());
				tempNode.getRight().setParent(tempNode);
			}
			replace(node, tempNode);
			tempNode.setLeft(node.getLeft());
			tempNode.getLeft().setParent(tempNode);
		}
		
		size--;
		if ( size == 1 )
			rootNode = node;
	}
	
	/**
		Getter Methods
	*/
	public int getSize() { return size; }
	public boolean isEmpty() { return rootNode == null; }
	public Node getRoot() { return rootNode; }
	public Comparable getMinimum() { return getSubtreeMinimum(rootNode).getValue(); }
	public Comparable getMaximum() { return getSubtreeMaximum(rootNode).getValue(); }
}

/**
	Node Class
*/
class Node {
	// private data held in each node
	private Comparable value;
	private Node left;
	private Node right;
	private Node parent;
	
	/**
		Basic Constructor
		
		@param initvalue The initial value held in the Node.
	*/
	public Node(Comparable initValue) {
		value = initValue;
		parent = null;
		left = null;
		right = null;
	}
	
	/**
		Overloaded Constructor method to set parent node.
		
		@param initvalue The initial value held in the Node.
		@param initParent The parent node of this Node.
	*/
	public Node(Comparable initValue, Node initParent) {
		value = initValue;
		parent = initParent;
		left = null;
		right = null;
	}
	
	/**
		Overloaded Constructor method for all data.
		
		@param initvalue The initial value held in the Node.
		@param initParent The parent node of this Node.
		@param initLeft The left child node of this Node.
		@param initRight The right child node of this Node.
	*/
	public Node(Comparable initValue, Node initParent, Node initLeft, Node initRight) {
		value = initValue;
		parent = initParent;
		left = initLeft;
		right = initRight;
	}
	
	//Basic getter methods.
	public Comparable getValue() { return value; }
	public Node getLeft() { return left; }
	public Node getRight() { return right; }
	public Node getParent() { return parent; }
	
	// Basic mutator methods.
	public void setValue(Comparable theNewValue) { value = theNewValue; }
	public void setLeft(Node theNewLeft) { left = theNewLeft; }
	public void setRight(Node theNewRight) { right = theNewRight; }
	public void setParent(Node theNewParent) { parent = theNewParent; }
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