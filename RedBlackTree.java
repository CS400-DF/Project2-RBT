// --== CS400 File Header Information ==--
// Name: <Haoxuan Wei>
// Email: <hwei72@wisc.edu>
// Team: <DF>
// Role: <Front End>
// TA: <Yelun>
// Lecturer: <Gary Dahl>
// Notes to Grader: <optional extra notes>
import java.awt.*;
import java.util.LinkedList;

public class RedBlackTree<T extends Comparable<T>> {
  protected static class Node<T> {
    public Color color;
    public T data;
    public Node<T> parent; // null for root node
    public Node<T> leftChild;
    public Node<T> rightChild;
    public boolean isBlack = false; // new nodes are red by default
    public Node(T data) {
      this.data = data;
    }
    public boolean isLeftChild() {
      return parent != null && parent.leftChild == this;
    }
    @Override
    public String toString() { // display subtree in order traversal
      String output = "[";
      LinkedList<Node<T>> q = new LinkedList<>();
      q.add(this);
      while (!q.isEmpty()) {
        Node<T> next = q.removeFirst();
        if (next.leftChild != null)
          q.add(next.leftChild);
        if (next.rightChild != null)
          q.add(next.rightChild);
        output += next.data.toString();
        if (!q.isEmpty())
          output += ", ";
      }
      return output + "]";
    }
  }

  protected Node<T> root; // reference to root node of tree, null when empty
  public void insert(T data) throws NullPointerException, IllegalArgumentException {
    // null references cannot be stored within this tree
    if (data == null)
      throw new NullPointerException("This RedBlackTree cannot store null references.");

    Node<T> newNode = new Node<>(data);
    if (root == null) {
      root = newNode;
    } // add first node to an empty tree
    else
      insertHelper(newNode, root); // recursively insert into subtree
    this.root.isBlack = true; // root node is always black
  }
  private void insertHelper(Node<T> newNode, Node<T> subtree) {
    int compare = newNode.data.compareTo(subtree.data);
    // do not allow duplicate values to be stored within this tree
    if (compare == 0)
      throw new IllegalArgumentException("This RedBlackTree already contains that value.");

    // store newNode within left subtree of subtree
    else if (compare < 0) {
      if (subtree.leftChild == null) { // left subtree empty, add here
        subtree.leftChild = newNode;
        newNode.parent = subtree;
        // otherwise continue recursive search for location to insert
      } else
        insertHelper(newNode, subtree.leftChild);
    }
    // store newNode within the right subtree of subtree
    else {
      if (subtree.rightChild == null) { // right subtree empty, add here
        subtree.rightChild = newNode;
        newNode.parent = subtree;
        // otherwise continue recursive search for location to insert
      } else
        insertHelper(newNode, subtree.rightChild);
    }
    // call enforceRBTreePropertiesAfterInsert method after adding a new red node to the tree
    enforceRBTreePropertiesAfterInsert(newNode);
  }
  @Override
  public String toString() {
    return root.toString();
  }
  public void rotate(Node<T> child, Node<T> parent)
          throws IllegalArgumentException {
      if (child.isLeftChild() == true) // right rotation with the child on the left
      {
          parent.leftChild = child.rightChild;
          child.parent = parent.parent;
          if (parent.parent == null) //if parent node is the root node
          {
              root = child; //set child node as root node
          } else if (parent.isLeftChild()) //if the parent node is the left node of the grandparent node
          {
              parent.parent.leftChild = child;
          } else // if it is the right node
          {
              parent.parent.rightChild = child;
          }
          child.rightChild = parent;
          parent.parent = child;
      } else //left rotation
      {
          parent.rightChild = child.leftChild; //in general it is opposite as the right rotation
          child.parent = parent.parent;
          if (parent.parent == null) {
              root = child;
          } else if (parent.isLeftChild()) {
              parent.parent.leftChild = child;
          } else {
              parent.parent.rightChild = child;
          }
          child.leftChild = parent;
          parent.parent = child;
      }
  }
    public boolean isBlack(Node n) //check if the new node is black
    {
        return colorOf(n) == Color.black;
    }
    public boolean isRed(Node n) //check if the new node is Red
    {
        return colorOf(n) == Color.red;
    }
    public Color colorOf(Node n) //return color of a certain node
    {
        return n.color;
    }
    public void changeColor(Node n,Color c) //change certain node's color
    {
        n.color= c;
    }
    public Node sibling(Node n)//return the siblin of a specific node
    {
        // if n is a null node or its parent is a null node then return null, otherwise
        // if n is its parent's leftchild then return its parent's rightchild, if it does
        // not exist just return  itself.
        if (n == n.parent.leftChild)
        {
            return n.parent.rightChild;
        }
        else {return n.parent.leftChild;}
    }
    public Node grandParent(Node n)
    {
        return n.parent.parent;
    }


  private void enforceRBTreePropertiesAfterInsert(Node n)
  {
      if (n != null && n!=root && isRed(n.parent) && isRed(n))
      {
          //case 1 passed
          if (isRed(sibling(n.parent))) // if the sibling of parent of Node n is a red node (case 1)
          {
              changeColor(n.parent, Color.black);
              changeColor(sibling(n.parent),Color.black);
              changeColor(grandParent(n),Color.red);
              enforceRBTreePropertiesAfterInsert(grandParent(n));
          }
          //case 2
          else if (isBlack(sibling(n.parent)) && !sibling(n.parent).isLeftChild() && n.isLeftChild()) //if sib is black node and it is the rightchild and n is the leftchild
          {
              changeColor(grandParent(n),Color.red);
              changeColor(n.parent,Color.black);
              rotate(n.parent,grandParent(n));
          }
          else if (isBlack(sibling(n.parent)) && sibling(n.parent).isLeftChild() && !n.isLeftChild())
          {
              changeColor(grandParent(n),Color.red);
              changeColor(sibling(n.parent),Color.black);
              rotate(n.parent,grandParent(n));
          }
          //case 3
          else if (isBlack(sibling(n.parent)) && sibling(n.parent).isLeftChild() && n.isLeftChild()) //if both of them are on the same side
          {
              rotate(n,n.parent);
              enforceRBTreePropertiesAfterInsert(n.parent);
          }
          else if (isBlack(sibling(n.parent)) && !sibling(n.parent).isLeftChild() && !n.isLeftChild()) //if both of them are on the same side
          {
              rotate(n,n.parent);
              enforceRBTreePropertiesAfterInsert(n.parent);
          }
      }
  }
}

