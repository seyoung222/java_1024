package day19;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;

import lombok.Data;

public class BookManagerMainSeyoung {
	
	public static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		/* 도서 관리 프로그램을 작성하세요.
		 * 도서 정보
		 *  - 도서명, 저자(여러명, 엮은이, 옮긴이 다 포함), 가격, 출판사, 분류, ISBN
		 * 기능
		 *  - 도서 추가
		 *   - ISBN으로 정렬
		 *  - 도서 조회 (람다식 써보기)
		 *   - 도서명으로 조회
		 *   - 저자로 조회
		 *   - 출판사로 조회
		 *   - 분류로 조회
		 * */
		ArrayList<Book> list = new ArrayList<Book>();
		int menu=0;
		do {
			printMenu();
			menu=scan.nextInt();
			runMenu(list,menu);
		}while(menu!=3);
	}
	private static void runMenu(ArrayList<Book>list, int menu) {
		switch(menu) {
		case 1:
			insertBook(list,inputBook());
			break;
		case 2:
			int subMenu=0;
			printSubMenu();
			subMenu=scan.nextInt();
			scan.nextLine();
			runSubMenu(list,subMenu);
			break;
		case 3:
			System.out.println("프로그램 종료");
			break;
		default: System.out.println("잘못된 메뉴입니다.");
		}
	}
	
	public static void printBook(ArrayList<Book>list, Predicate<Book>p) {
		for(Book tmp : list) {
			if(p.test(tmp)) {
				System.out.println(tmp);
			}
		}
	}
	private static void runSubMenu(ArrayList<Book> list, int subMenu) {
		switch(subMenu) {
		case 0: //전체조회
			for(Book tmp:list) {
				System.out.println(tmp);
			}
			break;
		case 1: //도서명으로 조회
			System.out.print("▷ 검색할 도서명> ");
			String name =scan.nextLine();
			printBook(list, s->s.getName().equals(name));
			break;
		case 2: //저자로 조회 (입력한 저자를 포함하고 있는 책들을 출력)
			System.out.print("▷ 검색할 저자> ");
			String author = scan.nextLine();
			printBook(list, s->s.getAuthor().contains(author));
			break;
		case 3: //출판사로 조회
			System.out.print("▷ 검색할 출판사> ");
			String publisher = scan.nextLine();
			printBook(list, s->s.getPublisher().equals(publisher));
			break;
		case 4: //분류로 조회
			System.out.print("▷ 검색할 분류> ");
			String classification = scan.nextLine();
			printBook(list, s->s.getClassification().equals(classification));
			break;
		default: System.out.println("잘못된 메뉴입니다.");
		}
	}
	private static void printSubMenu() {
		System.out.println("=======서브메뉴=======");
		System.out.println("0. 전체 조회");
		System.out.println("1. 도서명으로 조회");
		System.out.println("2. 저자로 조회");
		System.out.println("3. 출판사로 조회");
		System.out.println("4. 분류별 조회");
		System.out.print("메뉴 선택> ");
	}
	private static void insertBook(ArrayList<Book>list,Book book) {
		int index=0;
		for(Book tmp : list) {
			if(tmp.getIsbn()<book.getIsbn()) {
				index++;
			}
		}
		list.add(index,book);
		System.out.println("도서가 추가되었습니다.");
	}
	private static Book inputBook() {
		scan.nextLine();
		System.out.print("도서명> ");
		String name = scan.nextLine();
		System.out.print("저자(여러 명일 경우 ,로 구분)> ");
		String author = scan.nextLine();
		System.out.print("가격> ");
		int price = scan.nextInt();
		scan.nextLine();
		System.out.print("출판사> ");
		String publisher = scan.nextLine();
		System.out.print("분류> ");
		String classification = scan.nextLine();
		System.out.print("ISBN> ");
		int isbn = scan.nextInt();
		return new Book(name, publisher, classification, price, isbn, author);		
	}
	private static void printMenu() {
		System.out.println("========메뉴========");
		System.out.println("1. 도서 추가");
		System.out.println("2. 도서 조회");
		System.out.println("3. 종료");
		System.out.print("메뉴 선택> ");
	}
}

@Data
class Book{
	private String name, publisher, classification;
	private int price, isbn;
	private ArrayList<String> author;
	public Book(String name, String publisher, String classification, int price, int isbn, String author) {
		this.name = name;
		this.publisher = publisher;
		this.classification = classification;
		this.price = price;
		this.isbn = isbn;
		this.author = saveAuthor(author);
	}
	//입력받은 저자 나열을 분리해서 리스트에 저장하는 메소드
	public ArrayList<String> saveAuthor(String author) {
		String[] a=author.split(",");
		ArrayList<String> authorList = new ArrayList<String>();
		for(int i=0; i<a.length; i++) {
			authorList.add(a[i]);
		}
		return authorList;
	}
	@Override
	public String toString() {
		return "이름: " + name + " | 출판사: " + publisher + " | 분류: " + classification +
				" | 가격: "+ price + " | ISBN: " + isbn + " | 저자: " + author;
	}
}
