package Maman14;

public class LinkedList {
	private class Node {

		int data;
		Node next;

		public Node(int data) {
			this.data = data;
			next = null;
		}
	}

	//empty constractor 
	public LinkedList() {}

	// Checks if the list is empty
	public boolean isEmpty(){
		return head == null;
	}

	//Adding a new node to the end of the list 
	public void NewNode(int data) {
		Node newNode = new Node(data);
		if (isEmpty()) {
			head = newNode;
			tail = newNode;
		} else {
			tail.next = newNode;
			tail = newNode;
		}
	}

	//Extracting the top of the list and setting the next link 
	public int getHead() {
		if (head == null) {
			throw new IndexOutOfBoundsException("Empty List");
		}
		Node oldHead = head;
		head = oldHead.next;
		if (head == null){
			tail = null;
		}
		return oldHead.data;
	}

	//Head of the list
	private Node head;

	//Last node or tail of list
	private Node tail;
}
