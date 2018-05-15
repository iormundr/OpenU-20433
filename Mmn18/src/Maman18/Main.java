package Maman18;

/* 
 * Author: 
 * ID: 
 */

import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Library library = new Library();
		System.out.println("Welcome to Library Managment.\nBelow is the command legend:\nAdd User: + NAME ID\nRemove User: - NAME ID");
		System.out.println("Add Book to User: NAME ID BOOK_ID +\nRemove Book from User: NAME ID BOOK_ID -");
		System.out.println("Query by ID: ? ID");
		System.out.println("Query by BOOK_ID: ? BOOK_ID");
		System.out.println("Query by the most consuming book user: ? !\n");
		System.out.println("To Exit 'exit'\n");
		Scanner inputScanner = new Scanner(System.in);
		String input = inputScanner.nextLine();
		while (!input.equals("exit")) {
			library.ProcessIn(input);
			input = inputScanner.nextLine();
		}
		inputScanner.close();
	}
}
