package cz.kuasta.binaryTree;

import java.util.ArrayList;

public class Node<T> {
	public int id;
	public ArrayList<T> value;
	
	protected Node<T> left, right;
	
	public Node(int id, ArrayList<T> value){
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
