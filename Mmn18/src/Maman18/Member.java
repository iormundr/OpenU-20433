package Maman18;

public class Member {
	/* Member's last name and ID */
	private String lastName;
	private String idNumber;

	/* Member's books) */
	private DoublyLinkedList<Book> books = new DoublyLinkedList<Book>();

	/* Number of books that the member is borowing */
	private int numOfBorrowedBooks = 0;

	/* Link Node with the corresponding books */
	private DoublyLinkedList<String>.Node booksCountersNode;

	/* Creates a new member with lastname and ID */
	public Member(String lastName, String idNumber) {
		this.lastName = lastName;
		this.idNumber = idNumber;
	}

	public void setBooksCountersNode(DoublyLinkedList<String>.Node booksCountersNode) {
		this.booksCountersNode = booksCountersNode;
	}
	
	public DoublyLinkedList<Book> getBooks() {
		return books;
	}

	public int getNumberOfBorrowedBooks() {
		return numOfBorrowedBooks;
	}

	public DoublyLinkedList<String>.Node getBooksCountersNode() {
		return booksCountersNode;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void borrowBook(Book book) {
		books.insert(book);
		numOfBorrowedBooks++;
	}

	public void returnBook(String code) {
		DoublyLinkedList<Book>.Node node = books.getHead();
		while (node != null) {
			if (node.getData().getCode().equals(code)) {
				books.delete(node);
				numOfBorrowedBooks--;
				return;
			}
			node = node.next;
		}
	}

	public String getIdNumber() {
		return idNumber;
	}
}
