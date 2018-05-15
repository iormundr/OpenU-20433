package Maman18;

public class Library {

	/* HashTable which contains all members of the library. */
	private HashTable<Member> members = new HashTable<Member>();

	/* HashTable which contains all books borrowed by a member in the library. */
	private HashTable<Book> books = new HashTable<Book>();

	/* Index 0 will be held for members that don't have any books
	 * once the book is added the index value will grow to 1 and so on.
	 * Every index is of a DoubleLinkedList*/
	private DoublyLinkedList<String>[] booksCounters;

	/* Creating a new librarry*/
	public Library() {
		booksCounters = new DoublyLinkedList[11];
		for (int i = 0; i < 11; i++) {
			booksCounters[i] = new DoublyLinkedList<String>();
		}
	}
	/* Process that Max Borrowers */
	private void ProcessMaxQuerry(String input) {
		System.out.println("Which member or members currently borrowing the maximum number of books?");
		for (int i = 10; i > 0; i--) {
			DoublyLinkedList<String> maxMembers = booksCounters[i];
			if (!maxMembers.isEmpty()) {
				if (i == 1){
					System.out.println("Currently " + i + " book is being borrowed by:");
				} else {
					System.out.println("Currently " + i + " books are being borrowed by:");
				}
				DoublyLinkedList<String>.Node currNode = maxMembers.head;
				while (currNode != null) {
					System.out.println(currNode.data);
					currNode = currNode.next;
				}

				return;
			}
		}
		System.out.println("No members or no one borrowed books");
	}
	/* Processing the input */
	public void ProcessIn(String input) {
		if (isQuery(input)) {
			WorkOnQuery(input);
		} else {
			Process(input);
		}
	}
	/* Process a new input action, a new member/book borrow/member leave or book return*/
	private void Process(String input) {
		if (input.endsWith("+")) {
			ProcessBorrow(input);
		} else if (input.endsWith("-")) {
			ProcessReturn(input);
		} else if (input.startsWith("+")) {
			AddNewMemeber(input);
		} else {
			RemoveMember(input);
		}
	}

	/* Process the query.
	 * Determine on which query is it, for a member/book/max borrowers*/
	private void WorkOnQuery(String input) {
		char thirdChar = input.charAt(2);
		if (thirdChar >= '0' && thirdChar <= '9') {
			CurrentMemberQuery(input);
		} else if (thirdChar >= 'A' && thirdChar <= 'Z') {
			ProcessBookQuery(input);
		} else {
			ProcessMaxQuerry(input);
		}
	}

	/* Query for a member current borrowed books*/
	private void CurrentMemberQuery(String input) {
		String id = input.substring(input.indexOf(' ') + 1);
		Member member = members.find(id);
		System.out.println("Checking how many books Member ID: " + id + " has");
		System.out.println("Member ID: " + id + " borrowed these books:");
		DoublyLinkedList<Book>.Node node = member.getBooks().getHead();
		while (node != null) {
			System.out.println("Book: " + node.getData().getCode());
			node = node.next;
		}	
	}
	/* Checking if the current command is a Query that starts with ? */
	private boolean isQuery(String input) {
		return input.startsWith("?");
	}
	/* Process query for a book which print the member that currently holds it*/
	private void ProcessBookQuery(String input) {
		String id = input.substring(input.indexOf(' ') + 1);
		System.out.println("Which member has the book " + id + "?");
		Book book = books.find(id);
		if (book != null) {
			System.out.println("Book ID: " + id + " is currently borrowed by user: " + book.getBorrower().getLastName() + ", " + book.getBorrower().getIdNumber());
		} else {
			System.out.println("Book ID: " + id + " is not borrowed");
		}
	}
	/* Process a leaving member */
	private void RemoveMember(String input) {
		String[] splitInput = input.split(" ");
		String memberName = splitInput[1];
		String memberId = splitInput[2];
		Member member = members.find(memberId);
		booksCounters[member.getNumberOfBorrowedBooks()].delete(member.getBooksCountersNode());
		DoublyLinkedList<Book>.Node node = member.getBooks().getHead();
		while (node != null) {
			books.remove(node.getData().getCode());
			node = node.next;
		}
		members.remove(memberId);
		System.out.println("Member: " + memberName + ". With ID: " + memberId + ". Has ended his membership");
	}

	/* Handling the borrowing of a book. Adding the member to the hash table.
	 * Checking if he already borrowed the maximum amount of books*/
	private void ProcessBorrow(String input) {
		String[] splitInput = input.split(" ");
		String memberName = splitInput[0];
		String memberId = splitInput[1];
		String bookCode = splitInput[2];
		Member member = members.find(memberId);
		if (member.getNumberOfBorrowedBooks() == 10) {
			System.out.println("Member " + memberId + " already has 10 books, can't borrow more");
			return;
		}
		Book book = new Book(bookCode, member);
		member.borrowBook(book);
		books.insert(bookCode, book);
		DoublyLinkedList<String>.Node newNode = FixCounters(member.getBooksCountersNode(), member.getNumberOfBorrowedBooks() - 1, member.getNumberOfBorrowedBooks());
		member.setBooksCountersNode(newNode);
		System.out.println("Member: " + memberName + " With ID: " + memberId + " borrowed: " + bookCode);
	}

	/* Removes a book from a member */
	private void ProcessReturn(String input) {
		//System.out.println("Action: book return");
		String[] splitInput = input.split(" ");
		String memberName = splitInput[0];
		String memberId = splitInput[1];
		String bookCode = splitInput[2];
		Member member = members.find(memberId);
		member.returnBook(bookCode);
		books.remove(bookCode);
		DoublyLinkedList<String>.Node newNode = FixCounters(member.getBooksCountersNode(), member.getNumberOfBorrowedBooks() + 1, member.getNumberOfBorrowedBooks());
		member.setBooksCountersNode(newNode);
		System.out.println("Member: " + memberName + " With ID: " + memberId + " returned: " + bookCode);
	}

	/* Adding a new member */
	private void AddNewMemeber(String input) {
		String[] splitInput = input.split(" ");
		String memberName = splitInput[1];
		String memberId = splitInput[2];
		Member member = new Member(memberName, memberId);
		members.insert(memberId, member);
		DoublyLinkedList<String>.Node newNode = booksCounters[0].insert(memberName + ", " + memberId);
		member.setBooksCountersNode(newNode);
		System.out.println("New member: " + memberName + "\nID: " + memberId + " was added");
	}

	/* Rearrange the arrays that contain books, remove/add one and return a new node */
	private DoublyLinkedList<String>.Node FixCounters(DoublyLinkedList<String>.Node node, int oldNumOfBooks, int newNumOfBooks) {
		booksCounters[oldNumOfBooks].delete(node);
		return booksCounters[newNumOfBooks].insert(node.data);
	}

}
