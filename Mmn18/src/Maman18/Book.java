package Maman18;

public class Book {

	private String code;
	private Member borrower;

	/* creates a new book @param code for book code and borrower id */
	public Book(String code, Member borrower) {
		this.code = code;
		this.borrower = borrower;
	}

	public String getCode() {
		return code;
	}

	public Member getBorrower() {
		return borrower;
	}
}


