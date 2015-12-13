package cz.kuasta.binaryTree;

import java.util.Stack;

public class Node<T> {
	public int id;
	public Stack<T> value;
	
	protected Node<T> left, right, parent;
	
	public Node(int id, Stack<T> value){
		this.id = id;
		this.value = value;
		this.left=null;
		this.right=null;
		this.parent=null;
	}
	
	public Node(int id, Stack<T> value, Node<T> left, Node<T> right){
		this.id = id;
		this.value = value;
		this.left = left;
		this.right = right;
		this.parent = null;
	}
	
	public Node(int id, Stack<T> value, Node<T> left, Node<T> right, Node<T> parent){
		this.id = id;
		this.value = value;
		this.left = left;
		this.right = right;
		this.parent = parent;
	}
	
	public void setParent(Node<T> parent) {
		this.parent = parent;
	}
	
	public Node<T> getParent(){
		return parent;
	}
	
	public Node<T> getLeft() {
		return left;
	}
	public void setLeft(Node<T> left) {
		this.left = left;
	}
	public Node<T> getRight() {
		return right;
	}
	public void setRight(Node<T> right) {
		this.right = right;
	}
	
	protected void clear() {
		this.id=-39; //change to whatever a non-existent id value should be, i just made this up
		this.value=null;
		this.right=null;
		this.left=null;
		this.parent=null;
	}
	

	protected Node<T> minValue() {
        Node<T> localMin=null;
		if (this.getLeft() == null)
              localMin=this;
        else
              this.getLeft().minValue();
		return localMin;
	}
	
	protected boolean hasLeft() {
		return this.getLeft()!=null;
	}

	protected boolean hasRight() {
		return this.getRight()!=null;
	}

	protected boolean isLeaf() {
		return !this.hasLeft() && !this.hasRight();	
	}
}
