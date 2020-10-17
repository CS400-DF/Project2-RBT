// --== CS400 File Header Information ==--
// Name: Zihan Wang
// Email: zwang2262@wisc.edu
// Team: DF
// TA: Yelun
// Lecturer: Gary
// Notes to Grader: N/A

import java.util.LinkedList;

/**
 * Binary Search Tree implementation with a Node inner class for representing the nodes within a
 * binary search tree. You can use this class' insert method to build a binary search tree, and its
 * toString method to display the level order (breadth first) traversal of values in that tree.
 */
public class RedBlackTree<T extends Comparable<T>> {

  /**
   * This class represents a node holding a single value within a binary tree the parent, left, and
   * right child references are always be maintained.
   */
  protected static class Node<T> {
    public T data;
    public Node<T> parent; // null for root node
    public Node<T> leftChild;
    public Node<T> rightChild;
    public boolean isBlack = false; // new nodes are red by default

    public Node(T data) {
      this.data = data;
    }

    /**
     * @return true when this node has a parent and is the left child of that parent, otherwise
     *         return false
     */
    public boolean isLeftChild() {
      return parent != null && parent.leftChild == this;
    }

    /**
     * This method performs a level order traversal of the tree rooted at the current node. The
     * string representations of each data value within this tree are assembled into a comma
     * separated string within brackets (similar to many implementations of java.util.Collection).
     * 
     * @return string containing the values of this tree in level order
     */
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

  /**
   * Performs a naive insertion into a binary search tree: adding the input data value to a new node
   * in a leaf position within the tree. After this insertion, no attempt is made to restructure or
   * balance the tree. This tree will not hold null references, nor duplicate data values.
   * 
   * @param data to be added into this binary search tree
   * @throws NullPointerException     when the provided data argument is null
   * @throws IllegalArgumentException when the tree already contains data
   */
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

  /**
   * Recursive helper method to find the subtree with a null reference in the position that the
   * newNode should be inserted, and then extend this tree by the newNode in that position.
   * 
   * @param newNode is the new node that is being added to this tree
   * @param subtree is the reference to a node within this tree which the newNode should be inserted
   *                as a descenedent beneath
   * @throws IllegalArgumentException when the newNode and subtree contain equal data references (as
   *                                  defined by Comparable.compareTo())
   */
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

  /**
   * This method performs a level order traversal of the tree. The string representations of each
   * data value within this tree are assembled into a comma separated string within brackets
   * (similar to many implementations of java.util.Collection, like java.util.ArrayList, LinkedList,
   * etc).
   * 
   * @return string containing the values of this tree in level order
   */
  @Override
  public String toString() {
    return root.toString();
  }

  /**
   * Performs the rotation operation on the provided nodes within this BST. When the provided child
   * is a leftChild of the provided parent, this method will perform a right rotation (sometimes
   * called a left-right rotation). When the provided child is a rightChild of the provided parent,
   * this method will perform a left rotation (sometimes called a right-left rotation). When the
   * provided nodes are not related in one of these ways, this method will throw an
   * IllegalArgumentException.
   * 
   * @param child  is the node being rotated from child to parent position (between these two node
   *               arguments)
   * @param parent is the node being rotated from parent to child position (between these two node
   *               arguments)
   * @throws IllegalArgumentException when the provided child and parent node references are not
   *                                  initially (pre-rotation) related that way
   */
  private void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException {

    // if the parent is not the root
    if (parent.parent != null) {
      // parent is left child
      if (parent.isLeftChild()) {
        
        // if child is the left child of parent, perform a right rotation
        if (parent != null && child.isLeftChild()) {
          parent.leftChild = child.rightChild;
          child.rightChild = parent;
          child.parent = parent.parent;
          child.parent.leftChild = child;
          parent.parent = child;
          
          // if child is the right child of parent, perform a left rotation
        } else if (parent != null && !child.isLeftChild()) {
          parent.rightChild = child.leftChild;
          child.leftChild = parent;
          child.parent = parent.parent;
          child.parent.leftChild = child;
          parent.parent = child;
          // if parent and child are not initially related, throw exception
        } else
          throw new IllegalArgumentException();

        // parent is right child
      } else {
        // if child is the left child of parent, perform a right rotation
        if (parent != null && child.isLeftChild()) {
          parent.leftChild = child.rightChild;
          child.rightChild = parent;
          child.parent = parent.parent;
          child.parent.rightChild = child;
          parent.parent = child;
          // if child is the right child of parent, perform a left rotation
        } else if (parent != null && !child.isLeftChild()) {
          parent.rightChild = child.leftChild;
          child.leftChild = parent;
          child.parent = parent.parent;
          child.parent.rightChild = child;
          parent.parent = child;
          // if parent and child are not initially related, throw exception
        } else
          throw new IllegalArgumentException();
      }
    }
    // if the parent is the root
    else {
      // if child is the left child of parent, perform a right rotation
      if (parent != null && child.isLeftChild()) {
        parent.leftChild = child.rightChild;
        child.rightChild = parent;
        child.parent = parent.parent;
        parent.parent = child;
      }
      // if child is the right child of parent, perform a left rotation
      else if (parent != null && !child.isLeftChild()) {
        parent.rightChild = child.leftChild;
        child.leftChild = parent;
        child.parent = parent.parent;
        parent.parent = child;
      }
      // if parent and child are not initially related, throw exception
      else {
        throw new IllegalArgumentException();
      }
      // change root reference
      this.root = child;
      child.parent = null;
    }
  }


  /**
   * This method may be called recursively The job of this enforceRBTreePropertiesAfterInsert method
   * is to resolve red child under red parent red black tree property violations that are introduced
   * by inserting new nodes into a red black tree. While doing so, all other red black tree
   * properties must also be preserved.
   * 
   * @param newNode - new red child
   */
  private void enforceRBTreePropertiesAfterInsert(Node<T> newNode) {
    Node<T> parent = newNode.parent;
    Node<T> grandParent = null;
    if (parent != null)
      grandParent = parent.parent;
    Node<T> parentSibling = null;
    if (grandParent != null) {
      if (parent.isLeftChild())
        parentSibling = grandParent.rightChild;
      else
        parentSibling = grandParent.leftChild;
    }

    // watch out conditions when inserting red nodes under red nodes
    if (parent != null && !parent.isBlack) {
      // case 1: parent sibling node is red, recolors
      if (parentSibling != null && !parentSibling.isBlack) {
        parent.isBlack = true;
        parentSibling.isBlack = true;
        if (grandParent != null) {
          grandParent.isBlack = false;
          if (grandParent.parent != null && grandParent.parent.isBlack == false)
            enforceRBTreePropertiesAfterInsert(grandParent);
        }
      }

      // parent sibling is black
      else if (parentSibling == null || parentSibling.isBlack) {
        // check if is case 3: parent sibling is black and on the same side as the new node
        if ((newNode.isLeftChild() && !parent.isLeftChild())
            || (!newNode.isLeftChild() && parent.isLeftChild())) {
          // rotate the two red nodes
          rotate(newNode, parent);
          newNode = parent;
        }
        // case 2: parent sibling is black and on the opposite side as the new node
        rotate(newNode.parent, newNode.parent.parent);
        newNode.parent.isBlack = true;


        if (newNode.isLeftChild()) {
           if (newNode.parent.rightChild != null)
          newNode.parent.rightChild.isBlack = false;
        } else {
           if (newNode.parent.leftChild != null)
          newNode.parent.leftChild.isBlack = false;

        }

      }

    }

  }


}

