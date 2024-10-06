package project4;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * This file is implementing the BST data strucutre.
 * Where all of the methods and iterators listed on the Jadoocs are
 * implemented.
 * 
 * @author Henry Yuan
 *
 */
public class BST<E extends Comparable<E>> {
    protected Node root;
    protected int heightOfTree = 0;

    protected class Node {
        E data;
        int height;

        Node left;
        Node right;

        public Node(E d) {
            data = d;
            left = null;
            right = null;
            height = 0;
        }

        public E getData() {
            return data;
        }

        /**
         * @param data
         */
        public void setData(E data) {
            this.data = data;
        }

        /**
         * @return
         */
        public Node getLeft() {
            return left;
        }

        /**
         * @param left
         */
        public void setLeft(Node left) {
            this.left = left;
        }

        /**
         * @return
         */
        public Node getRight() {
            return right;
        }

        /**
         * @param right
         */
        public void setRight(Node right) {
            this.right = right;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

    }

    int size = 0;

    /**
     * Constructs a new, empty tree, sorted according to the natural ordering of its elements
     */
    public BST() {

    }

    /**
     * @param collection
     * Constructs a new tree containing the elements in the specified collection, sorted according to the natural ordering of its elements
     */
    public BST(E[] collection) {
        for (int i = 0; i < collection.length; i++) {
            this.add(collection[i]);
        }
    }

    /**
     * @param e - element to be added to this set
     * @return {@code} true if this set did not already contain the specified element
     * Adds the specified element to this set if it is not already present. More 
     * formally, adds the specified element e to this tree if the set contains 
     * no element e2 such that Objects.equals(e, e2). If this set already contains 
     * the element, the call leaves the set unchanged and returns false.
     */
    public boolean add(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        if (root == null) {// create the first node
            root = new Node(e);
            size++;
            return true;
        }
        Node current = root;
        int height = 0;
        while (current != null) {
            if (current.getData().compareTo(e) > 0) { // add in the left subtree
                if (current.left == null) {
                    current.left = new Node(e);
                    size++;
                    height++;
                    current.left.setHeight(height);
                    if (height > heightOfTree) {
                        heightOfTree = height;
                    }
                    height = 0;
                    return true;
                } else {
                    current = current.left;
                    height++;
                }
            } else if (current.data.compareTo(e) < 0) {// add in the right subtree
                if (current.right == null) {
                    current.right = new Node(e);
                    size++;
                    height++;
                    current.right.setHeight(height);
                    if (height > heightOfTree) {
                        heightOfTree = height;
                    }
                    height = 0;
                    return true;
                } else {
                    current = current.right;
                    height++;
                }
            } else { // duplicate
                return false;
            }
        }
        // we should never get to this line
        return false;
    }

    /**
     * @param e - the value to match
     * @return the least element greater than or equal to e, or null if there is no such element
     * Returns the least element in this tree greater than or equal to the given element, or null if there is no such element.
     */
    public E ceiling(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        return ceilHelper(root, e);
    }

    /**
     * @param current
     * @param input
     * @return the ceiling of the element given
     */
    private E ceilHelper(Node current, E input) {
        if (current == null) {
            return null;
        }

        int compare = input.compareTo(current.data);
        if (compare == 0) {
            return current.data;
        }
        if (compare < 0) {
            E out = ceilHelper(current.left, input);
            if (out != null) {
                return out;
            } else
                return current.data;
        }
        return ceilHelper(current.right, input);

    }

    /**
     * Removes all of the elements from this set. The set will be empty after this call returns.
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * @param o - object to be checked for containment in this set
     * @return true if this set contains the specified element
     */
    public boolean contains(Object o) {

        return containsHelper(root, o);
    }

    /**
     * @param current
     * @param o
     * @return boolean of contains
     */
    public boolean containsHelper(Node current, Object o) {
        if (o == null) { // throws NullPointerException if the specified element is null and 
            throw new NullPointerException(); // this set uses natural ordering, or its comparator does not permit null elements
        }
        if (current == null) {
            return false;
        }

        E obj = (E) o; // cast Object to E

        int compare = current.getData().compareTo(obj);

        if (compare < 0) { // if compare < 0 then go into the right subtree
            return containsHelper(current.right, o); 

        } else if (compare > 0) { // if compare > 0 then go into the left subtree
            return containsHelper(current.left, o);
        } else { // if compare == 0 return true;
            return true;
        }
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     * @param obj - object to be compared for equality with this tree
     * Compares the specified object with this tree for equality. 
     * Returns true if the given object is also a tree, the two trees have the same size,
     * and every member of the given tree is contained in this tree.
     */
    public boolean equals(Object obj) {
        BST o = (BST) obj; // cast obj to BST
        if (o.size() == this.size()) { // check if they are the same size
            if (equalsHelper(o.root, root)) { // check if every member is contained in this tree
                return true;
            }
        }
        return false;

    }

    /**
     * @param obj
     * @param current
     * @return boolean of equals
     */
    public boolean equalsHelper(Node obj, Node current) {
        if (obj.data == null || current.data == null) {
            return false;
        }
        int compare = current.getData().compareTo(obj.getData());
        if (compare < 0) { // if compare < 0, then go into the right subtree
            return equalsHelper(current.right, obj);

        } else if (compare > 0) { // if compare < 0, then go into the left subtree
            return equalsHelper(current.left, obj);
        } else {
            return true;
        }
    }

    /**
     * @return the first (lowest) element currently in this tree.
     */
    public E first() {
        if (root == null) { // throws NosuchElementException if set is empty
            throw new NoSuchElementException();
        }
        Node out = firstHelper(root);
        return out.data;
    }

    /**
     * @param current
     * @return Lowest element in the tree
     */
    private Node firstHelper(Node current) {
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    /**
     * @param e - the value to match
     * @return the greatest element in this set less than or equal to the given element,
     * or null if there is no such element.
     */
    public E floor(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        return floorHelper(root, e);
    }

    /**
     * @param current
     * @param input
     * @return the greatest element in this set less than or equal to the given element
     */
    private E floorHelper(Node current, E input) {
        if (current == null) {
            return null;
        }

        int compare = current.data.compareTo(input);
        if (compare == 0) {
            return current.data;
        }
        if (compare < 0) {
            E out = floorHelper(current.right, input);
            if (out != null) {
                return out;
            } else
                return current.data;
        }
        return floorHelper(current.right, input);
    }

    /**
     * @param index - index of the element to return
     * @return the element at the specified position in this tree
     */
    public E get(int index) {
        if (index >= size() || index < 0) { //IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())
            throw new IndexOutOfBoundsException();
        }
        return getHelper(root, index);
    }

    /**
     * @param current
     * @param index
     * @return the element at the specified position in this tree
     */
    public E getHelper(Node current, int index) {
        Iterator<E> iter = new iterator(root); // uses iterator to push/pop off the stack

        E out = iter.next();
        int counter = 0;
        while (iter.hasNext() && counter != index) {
            out = iter.next();
            counter++;
        }
        return out;

    }

    /**
     * @return height of the whole tree
     */
    public int height() {
        return heightOfTree;
    }

    /**
     * @param e - the value to match
     * @return the least element greater than e, or null if there is no such element
     * Returns the least element in this tree strictly greater than the given element, or null if there is no such element.
     */
    public E higher(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        return higherHelper(root, e, root.data);
    }

    /**
     * @param current
     * @param e
     * @param higher a holder for the a higher node
     * @return the least element greater than e, or null if there is no such element 
     */
    public E higherHelper(Node current, E e, E higher) {

        if (current == null) { // if current is null return null
            return null;
        }
        if (current.left == null || current.right == null) { // when reached the bottom of a branch
            return current.data;
        }

        int compare = current.data.compareTo(e);
        if (current.left.data.compareTo(e) == 0) { // if the left node was the same as e then return higher
            return higher;
        }

        if (compare > 0) { // 
            if (higher.compareTo(e) > 0) {
                higher = current.data;
            }
            if (current.data.compareTo(higher) < 0) {
                higher = current.data;
            }
            E out = higherHelper(current.left, e, higher);
            if (out != null) {
                return out;
            } else
                return current.data;
        }
        if (higher.compareTo(current.data) < 0) {
            higher = current.data;
        }
        return higherHelper(current.right, e, higher);
    }

    /**
     * @return true if this set contains no elements
     * Returns true if this set contains no elements.
     */
    public boolean isEmpty() {
        if (root == null) { // if root is null the set contains no elements.
            return true;
        }
        return false;
    }

    /**
     * @return an iterator over the elements in this set in ascending order
     */
    public Iterator<E> iterator() {
        return new iterator(root);
    }

    private class iterator implements Iterator<E> {
        /**
         * creates a stack
         */
        private Stack<Node> stack;

        iterator(Node root) { // set the starting point for the iterator
            stack = new Stack<Node>();
            moveLeft(root);
        }

        private void moveLeft(Node current) { // push the left node onto the stack
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
        }

        public boolean hasNext() { // return the opposite of if the stack is empty
            return !stack.isEmpty();
        }

        public E next() { // pop off the top of the stack then return the element
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Node current = stack.pop();

            if (current.right != null) {
                moveLeft(current.right);
            }
            return current.getData();
        }
    }

    /**
     * @return the last (highest) element currently in this tree
     * Returns the last (highest) element currently in this tree.
     */
    public E last() { 
        if (root == null) {
            throw new NoSuchElementException();
        }
        Node out = lastHelper(root);
        return out.data;
    }

    /**
     * @param current
     * @return Last node in the tree
     */
    private Node lastHelper(Node current) {
        if (current.right == null) {
            return current;
        } else {
            return lastHelper(current);
        }
    }

    /**
     * @param e - the value to match
     * @return the greatest element less than e, or null if there is no such element
     */
    public E lower(E e) {
        if (e == null) { // if e is null throw NullPointerException
            throw new NullPointerException();
        }
        return lowerHelper(root, e);
    }

    /**
     * @param current
     * @param input
     * @return the greatest element less than e, or null if there is no such element
     */
    private E lowerHelper(Node current, E input) {
        if (current == null) {
            return null;
        }

        int compare = current.data.compareTo(input);

        if (compare < 0) { // if compare < 0 call lowerHelper but on the left subtree
            E out = lowerHelper(current.left, input);
            if (out != null) {
                return out;
            } else
                return current.data;
        } // else call lowerHelper on the right subtree
        return lowerHelper(current.right, input);
    }

    /**
     * @return an iterator over the elements in this tree in order of the postorder traversal
     */
    public Iterator<E> postorderIterator() {
        return new postorderIterator(root);
    }

    private class postorderIterator implements Iterator<E> {
        private Stack<Node> stack;

        postorderIterator(Node root) { // set the starting point for the iterator
            stack = new Stack<Node>();
            moveLeft(root); // push left node
        }

        private void moveLeft(Node current) { // move to the left subtree
            while (current != null) {
                current = current.left; 
            }
        }

        public boolean hasNext() { // determine if the stack hasNext
            return !stack.isEmpty();
        }

        public E next() { // pops the top of the stack and returns it
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Node current = stack.pop();

            if (current.right != null) {// move to the right subtree
                stack.push(current); // push the current node last
                moveLeft(current.right); // push right node
            }
            return current.getData();
        }
    }

    /**
     * @return an iterator over the elements in this tree in order of the postorder traversal
     */
    public Iterator<E> preorderIterator() {
        return new preorderIterator(root);
    }

    class preorderIterator implements Iterator<E> {
        private Stack<Node> stack;

        preorderIterator(Node root) { // set the starting point for the iterator
            stack = new Stack<Node>();
            moveLeft(root);
        }

        private void moveLeft(Node current) {
            while (current != null) {
                
                current = current.left;
                 
            }
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Node current = stack.pop();

            if (current.right != null) {
                moveLeft(current.right);
                stack.push(current); // push the current node first
            }
            return current.getData();
        }
    }

    /**
     * @param o - object to be removed from this set, if present
     * @return true if this set contained the specified element
     */
    public boolean remove(Object o) {
        if (!this.contains(o)) {

            return false;
        }
        root = removeHelper(root, o);
        if (this.contains(o)) {

            return false;
        } else {
            size--;
            return true;
        }
    }

    /**
     * @param current
     * @param o
     * @return returns the node that is removed
     */
    public Node removeHelper(Node current, Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
        if (current == null) {
            return current;
        }

        E obj = (E) o;

        int compare = current.getData().compareTo(obj);


        if (compare == 0) {
            if (current.right == null) {
                return current.left;
            }
            if (current.left == null) {
                return current.right;
            }
            Node lowest = firstHelper(root.right);

            current.data = lowest.data;
            current.right = removeHelper(current.right, current.data);
        } else if (compare > 0) {
            current.left = removeHelper(current.left, o);

        } else if (compare < 0) {
            current.right = removeHelper(current.right, o);
        }
        return current;
    }

    /**
     * @return the number of elements in this tree
     */
    public int size() {
        return size;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     * @Override toString in class Object
     * a string representation of this collection
     */
    public String toString() {
        String out = toString(root);

        out = out.substring(0, out.length() - 2);
        return "[" + out + "]";
    }

    /**
     * @param current
     * @return 
     */
    protected String toString(Node current) {
        String out = "";
        if (current == null) {
            return "";
        } else {
            out += current.getData().toString() + ", ";
            out += toString(current.getLeft());
            out += toString(current.getRight());
            return out;
        }
    }

    public String toStringTreeFormat() {
        return "";
    }

}
