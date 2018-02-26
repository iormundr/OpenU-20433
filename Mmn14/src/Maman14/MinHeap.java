package Maman14;

public class MinHeap {

	//Contains the values and the index of each node
	public static class Node {
		int data;
		int listNum;

		public Node(int data, int listNum) {
			this.data = data;
			this.listNum = listNum;
		}
	}

	//The implementation of Heapify algorithm
	private void heapify(int i) {
		int l_left = left(i);
		int r_right = right(i);
		int smallest = i;
		if (l_left <= heapSize && array[l_left].data < array[i].data) {
			smallest = l_left;
		}
		if (r_right <= heapSize && array[r_right].data < array[smallest].data) {
			smallest = r_right;
		}

		if (smallest != i) {
			swap(i, smallest);
			heapify(smallest);
		}
	}

	//returns the left value of the current heap node, part of Heapify algorithm implementation 
	private int left(int i) {
		return i * 2;
	}

	//returns the right value of the current heap node, part of Heapify algorithm implementation 
	private int right(int i) {
		return i * 2 + 1;
	}
	
	//swaping the values, part of Heapify algorithm
	private void swap(int i, int j) {
		Node temp_array = array[i];
		array[i] = array[j];
		array[j] = temp_array;
	}

	//Is heap empty?
	public boolean isEmpty() {
		return heapSize == 0;
	}

	//Fetching the root 
	public Node fetchMin() {
		return array[1];
	}

	//Inserting to the top of the heap
	public void insertToRoot(Node top) {
		array[1] = top;
		heapify(1);
	}

	//Building the min heap 
	private void buildMinHeap() {
		this.heapSize = array.length - 1;
		for (int i = heapSize / 2; i > 0; i--) {
			heapify(i);
		}
	}

	//generates a min heap 
	public MinHeap(Node[] inputarr) {
		this.array = new Node[inputarr.length + 1];
		System.arraycopy(inputarr, 0, this.array, 1, inputarr.length);
		buildMinHeap();
	}

	//Reducing the size of the heap by one
	public void reduceSizeByOne() {
		array[1] = array[heapSize];
		heapSize--;
		heapify(1);
	}

	//array of the nodes
	private Node[] array;
	//Size of the heap
	private int heapSize;


}
