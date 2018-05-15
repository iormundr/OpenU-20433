package Maman18;

/* Doubly linked list implementation. Every node/link contains data, previous, next nodes.
 * data type is of Type D */
public class DoublyLinkedList<D> {

	public class Node {
		D data;
		Node prev;
		Node next;
		public Node(D data) {
			this.data = data;
			prev = null;
			next = null;
		}

		public D getData() {
			return data;
		}
	}
	Node head;
	
	/* creates an empty list */
	public DoublyLinkedList() {
		head = null;
	}
	
	/* Deleting the node from the list */
	public void delete(Node node) {
		if (node == head) {
			if (node.next != null) {
				node.next.prev = null;
				head = node.next;
			} else {
				head = null;
			}
		} else {
			if (node.next != null) {
				node.next.prev = node.prev;
			}
			if (node.prev != null) {
				node.prev.next = node.next;
			}
		}
	}

	/* Returning the head of the list */ 
	public Node getHead() {
		return head;
	}

	/* Is the list empty? */
	public boolean isEmpty() {
		return head == null;
	}
	
	/* Inserting a new element to the list and returning the node. */
	public Node insert(D data) {
		Node newNode = new Node(data);
		if (!isEmpty()) {
			head.prev = newNode;
			newNode.next = head;
		}
		head = newNode;
		return newNode;
	}

}
