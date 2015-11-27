package cz.kuasta.binaryTree;

import java.util.Stack;

import cz.kuasta.items.Item;

/**
 * Binary tree class adapted to the usage of inventory handling.<p>
 * 
 * Contains node mapping for instances with same id and their amounts.<br>
 * @since 1.2
 * @version 1.1
 * @author Martin Vostal
*/
public class BinaryTree<T extends Item> {
	private Node<T> root;
	private int nodes;
		
	public BinaryTree(){
	
	}
	
	/**
	 * Returns boolean value of true/false if the tree contains an instance with given value.
	 * @param id - id value to look for
	 * @return true/false
	*/
	public boolean contains(int id){
		int result = containsSize(id);
				
		if(result>0)
			return true;
		else
			return false;
	}
	/**
	 * Counts the number of instances with the same id in the tree.
	 * @param id - id value to look for
	 * @return result - number of instances found
	*/
	public int instanceCounter(int id){
		int result = containsSize(id);
		return result;
	}
	/**
	 * Returns an ArrayList<T> which contains node data.
	 * @param id - id value to look for
	 * @return result - node value
	*/
	public Stack<T> getValue(int id){
		return lookUp(root, id).value;	
	}
	/***/
	public void setValue(int id, Stack<T> value){
		lookUp(root, id).value = value;
	}
	/**
	 * Returns an instance from a node.
	 * @param id - id value to look for
	 * @param arrayId - arrayId of the instance
	 * @return result - instance of node value 
	*/
	public T getInstance(int id, int stackId){
		return lookUp(root, id).value.get(stackId);
	}
	/**
	 * Inserts a new Node<T> into the tree.
	 * @param node - node for insertion
	*/
	public void addNode(Node<T> node){
		if(root == null)
			root = node;
		else
			insert(root, node);
	}
	/***/
	/*public void removeNode(int id){
		remove(root, id);
	}*/
	/**
	 * Inserts an instance into a Node<T>.
	 * @param instance - instance for insertion
	*/
	public void addInstance(T instance){
		if(contains(instance.id) == false){
			Stack<T> tmp = new Stack<T>();
			tmp.add(instance);
			
			Node<T> node = new Node<T>(instance.id, tmp);
			
			addNode(node);
		}else{
			lookUp(root, instance.id).value.push(instance);
		}
	}
	/**
	 * Returns the total amount from instances
	 * @param instance - instance for insertion
	*/
	public int getTotalInstancedAmount(int id){
		int counter = 0;
		
		if(contains(id) == true){
			for(T t : lookUp(root, id).value){
				counter =+ t.getAmount();
			}
		}
		
		return counter;
	}
	/*public void inOrder(){
		inOrder(root);
	}*/
	/*private void instanceAmountCheck(ArrayList<T> instances){
		int size = instances.size();
		for(int i = 0;i < size;i++){
			if(instances.get(i).getAmount() <= 0){
				instances.remove(i);
				i--;
				size--;
			}
		}
	}*/
	
	/***/
	private Node<T> insert(Node<T> node, Node<T> add){
		if (node == null){
			node = add;
        } else if(node.id == add.id) {
        	node.value.addAll(add.value);
        } else if(add.id < node.id) {
            node.setLeft(insert(node.getLeft(), add));
        } else {
            node.setRight(insert(node.getRight(), add));
        }
		return node;
	}
	
	/***/
	private int containsSize(int id){
		Node<T> currentNode = root;
		
		while(currentNode != null){
			if(currentNode.id == id)
				return currentNode.value.size();
			else if(id < currentNode.id)
				currentNode = currentNode.left;
			else
				currentNode = currentNode.right;
		}
		
		return 0;
	}
	/***/
	private Node<T> lookUp(Node<T> node, int id){
		if (node == null){ 
		    return null; 
		}else{
			if(node.id == id){
				return node; 
			}else{
				if(id < node.id)
					return lookUp(node.getLeft(), id); 
				else
					return lookUp(node.getRight(), id); 
			} 
		}	
	}
	
	/*private Node<T> remove(Node<T> node, int id){
        if( node==null )
            return null;
        if( id < node.id )
            node.left = remove(node.left, id);
        else if( id > node.id )
        	node.right = remove(node.right, id);
        else if( node.left != null && node.right != null ){
            node.value = findMin(node.right).value;
            node.right = remove( node.right, node.id );
        }
        else
            node = ( node.left != null ) ? node.left : node.right;
        return node;
    }*/
	@SuppressWarnings("unused")
	private Node<T> findMin(Node<T> node){
        if( node == null )
            return null;
        else if( node.left == null )
            return node;
        return findMin( node.left );
    }
	
	/*public void inOrder(Node<T> node) {
		if (node != null) {
			inOrder(node.getLeft());
			System.out.println(node.value);
			inOrder(node.getRight());
		}
	}*/
	
	public int size() {
		return nodes;
	}
}
