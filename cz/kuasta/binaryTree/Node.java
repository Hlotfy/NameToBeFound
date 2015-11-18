package cz.kuasta.binaryTree;

import java.util.Stack;

public class Node<T> {
	public int id;
	public Stack<T> value;
	
	protected Node<T> left, right;
	
	public Node(int id, Stack<T> value){
		this.id = id;
		this.value = value;
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
}
