package implementation;

import java.util.Comparator;
import java.util.TreeSet;

public class BinarySearchTree<K, V> {
	/*
	 * You may not modify the Node class and may not add any instance nor static
	 * variables to the BinarySearchTree class.
	 */
	private class Node {
		private K key;
		private V value;
		private Node left, right;

		private Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

	private Node root;
	private int treeSize, maxEntries;
	private Comparator<K> comparator;

	public BinarySearchTree(Comparator<K> comparator, int maxEntries) {
		if(comparator == null || maxEntries < 1) {
			throw new IllegalArgumentException("Error");
		}
		this.comparator = comparator;
		this.maxEntries = maxEntries;
		treeSize = 0;
	}

	public void adding(Node node, K key, V value) {
		Node addNode = new Node(key,value);
		Node curr = node;
		Node parent;

		if(comparator.compare(key, curr.key) < 0) {
			
			parent = curr;

			curr = curr.left;

			if(curr == null) {

				parent.left = addNode;

				treeSize++;

			}else if(curr.key == addNode.key) {

				curr.value = addNode.value;

			} else {

				adding(node.left, key, value);

			}
		}else if(comparator.compare(key, curr.key) > 0){
			parent = curr;

			curr = curr.right;

			if(curr == null) {

				parent.right = addNode;

				treeSize++;

			}else if(curr.key == addNode.key) {

				curr.value = addNode.value;

			} else {
				adding(node.right, key, value);
			}

		}


	}

	public BinarySearchTree<K, V> add(K key, V value) throws TreeIsFullException {
		if(treeSize == maxEntries) {
			throw new TreeIsFullException("Error");
		}
		Node addNode = new Node(key, value);
		if(root == null) {

			root = addNode;

			treeSize++;

		}else if(root == addNode) {

			root.value = addNode.value;

		}else {

			adding(root, key, value);

		}

		return this;
	}
	public void printAscending(Node node) {
		if(node != null) {

			printAscending(node.left);

			System.out.print("{"+ node.key.toString()
			+ ":"+ node.value.toString() + "}");

			printAscending(node.right);
		}
	}

	public String toString() {
		String temp = "";
		if(!isEmpty()) {
			printAscending(root);
		}else {
		return "EMPTY TREE";
		} 
		return temp;
	}

	/* Provided: modify */
	public boolean isEmpty() {
		return root == null;
	}

	/* Provided: modify */
	public int size() {
		return treeSize;
	}

	/* Provided: modify */
	public boolean isFull() {
		return treeSize == maxEntries;
	}
	
	public KeyValuePair<K, V> findMin(Node node) {
		KeyValuePair<K, V> temp = new KeyValuePair<K, V>(node.key, node.value);
		if(node.left == null) {

			return temp;

		}
		return findMax(node.left);
	}

	public KeyValuePair<K, V> getMinimumKeyValue() throws TreeIsEmptyException {
		if(isEmpty()) {
			throw new TreeIsEmptyException("Error");
		}
		return findMin(root);
	}

	public KeyValuePair<K, V> findMax(Node node) {
		KeyValuePair<K, V> temp = new KeyValuePair<K, V>(node.key, node.value);
		if(node.right == null) {

			return temp;

		}
		return findMax(node.right);
	}
	public KeyValuePair<K, V> getMaximumKeyValue() throws TreeIsEmptyException {
		if(isEmpty()) {
			throw new TreeIsEmptyException("Error");
		}
		return findMax(root);
	}

	public KeyValuePair<K, V> findIt(Node node, K key){
		KeyValuePair<K, V> temp = new KeyValuePair<K, V>(node.key, node.value);

		if(node != null) {

			if(comparator.compare(key, node.key) < 0) {

				return findIt(node.left, key);

			}else if(comparator.compare(key, node.key) > 0) {

				return findIt(node.right, key);

			}else if(comparator.compare(key, node.key) == 0) {

				return temp;

			}
		}

		return null;
	}

	public KeyValuePair<K, V> find(K key) {
		return findIt(root, key);
	}

	public BinarySearchTree<K, V> delete(K key) throws TreeIsEmptyException {
	    if (isEmpty()) {
	        throw new TreeIsEmptyException("Tree is empty");
	    }

	    root = delete(root, key);
	    return this;
	}

	private BinarySearchTree<K, V>.Node delete(Node node, K key) {
	    if (node == null) {
	        // The key is not in the tree, return null.
	        return null;
	    }

	    int cmp = ((String) key).compareTo((String) node.key);
	    if (cmp < 0) {
	    	node.left = delete(node.left, key);
	    } else if (cmp > 0) {
	    	node.right = delete(node.right, key);
	    } else {
	        if (node.left == null) {
	            // The node has no left child, so return its right child
	            return node.right;
	        } else if (node.right == null) {
	            // The node has no right child, so return its left child
	            return node.left;
	        } else {
	            // The node has two children
	        	KeyValuePair<K, V> min = findMin(node.right);
	        	node.key = min.getKey();
	        	node.value = min.getValue();
	        	node.right = delete(node.right, min.getKey());
	        }
	    }
	    return node;
	}

}
