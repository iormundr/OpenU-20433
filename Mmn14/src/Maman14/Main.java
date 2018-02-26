package Maman14;
import java.util.Scanner;
import Maman14.MinHeap.Node;

/* 
 * Author: Iormundr
 * ID: 
 */
public class Main {
	public static void main(String[] args) {
		LinkedList[] lists = readLists();
		SortingAndMergning(lists);
	}

	//getting the list of numbers from the input, including k numbers of lists and the values themselves
	//assuming the values are entered correctly in a descending order
	private static LinkedList[] readLists() {
		Scanner inputScanner = new Scanner(System.in);
		System.out.println("How many arrays you want to sort?: ");
		int k = Integer.parseInt(inputScanner.nextLine());
		LinkedList[] lists = new LinkedList[k];
		System.out.println("Please enter the values for the list\nTo Seperate between lists press enter: \n");
		for (int i = 0; i < k; i++) {
			LinkedList list = new LinkedList();
			String input = inputScanner.nextLine();
			for (String number : input.split(" ")) {
				list.NewNode(Integer.parseInt(number));
			}
			lists[i] = list;
		}
		inputScanner.close();
		return lists;
	}

	//fetching and printing the top/root from the heap and handles the next value
	private static void MinNext(LinkedList[] lists, MinHeap minHeap) {
		Node min = minHeap.fetchMin();
		System.out.print(min.data + " "); 
		int listNum = min.listNum;
		if (lists[listNum].isEmpty()) {
			minHeap.reduceSizeByOne();
		} else {
			Integer nextData = lists[listNum].getHead();
			minHeap.insertToRoot(new Node(nextData, listNum));
		}
	}

	//creation of a min heap 
	private static MinHeap createMinHeap(LinkedList[] lists) {
		Node[] array = new Node[lists.length];
		for (int i = 0; i < lists.length; i++) {
			array[i] = new Node(lists[i].getHead(), i);
		}
		MinHeap minHeap = new MinHeap(array);
		return minHeap;
	}

	//Merging the input lists 
	private static void SortingAndMergning(LinkedList[] lists) {
		MinHeap minHeap = createMinHeap(lists);
		while (!minHeap.isEmpty()) {
			MinNext(lists, minHeap);
		}
	}

}
