package day20;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.function.Predicate;

public class BookManagerMain2 {
//BookManagerMain를 메소드를 이용해 정리한 버전임
	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		int menu =-1;
		
		ArrayList<Book> list = new ArrayList<Book>();
		do {
			printMenu();
			menu = scan.nextInt();
			runMenu(menu, list);
		}while(menu!=3);
		scan.close();
	}

	public static void printMenu() {
		System.out.println("========메뉴========");
		System.out.println("1. 도서 추가");
		System.out.println("2. 도서 조회");
		System.out.println("3. 종료");
		System.out.print("메뉴 선택> ");		
	}
	public static void runMenu(int menu, ArrayList<Book>list) {
		switch(menu) {
		case 1:
			Book book = createBook();
			if(insertBook(list,book))
				System.out.println("도서 추가가 완료되었습니다.");
			else 
				System.out.println("이미 등록된 isbn 번호 입니다.");
			break;
		case 2:
			printSearchMenu();
			int subMenu = scan.nextInt();
			System.out.println("====================");
			runPrintMenu(subMenu, list);
			break;
		case 3:
			System.out.println("프로그램 종료");
			break;
		default:
			System.out.println("잘못된 메뉴입니다.");
		}
	}
	public static Book createBook() {
		scan.nextLine();
		System.out.print("도서명 : ");
		String title = scan.nextLine();
		System.out.print("저자 : ");
		String author = scan.nextLine();
		System.out.print("가격 : ");
		int price = scan.nextInt();
		scan.nextLine();
		System.out.print("출판사 : ");
		String publisher = scan.nextLine();
		System.out.print("장르 : ");
		String genre = scan.nextLine();
		System.out.print("ISBN : ");
		String isbn = scan.next();
		
		return new Book(title, author, publisher, genre, isbn, price);
	}
	public static boolean insertBook(ArrayList<Book>list,Book book) {
		if(list.contains(book)) {
			return false;
		}
		list.add(book);//add는 boolean값을 가짐. 리스트는 무조건 추가에 성공하므로 true 반환
		Collections.sort(list,(o1,o2)->o1.getIsbn().compareTo(o2.getIsbn()));
		return true;
		
	}
	public static void printSearchMenu() {
		System.out.println("=======메뉴 조회=======");
		System.out.println("1. 도서명");
		System.out.println("2. 저자");
		System.out.println("3. 출판사");
		System.out.println("4. 장르");
		System.out.println("5. 취소");
		System.out.print("조회 방법 선택 : ");
	}
	public static void runPrintMenu(int subMenu, ArrayList<Book>list) {
		String title, author, publisher, genre;
		int count = -1;
		scan.nextLine();
		switch(subMenu) {
		case 1: //도서명으로 조회
			System.out.print("도서명");
			title= scan.nextLine();
			printBookList(list,b->b.getTitle().contains(title.trim()));
			break;
		case 2: //저자로 조회
			System.out.print("저자");
			author = scan.nextLine();
			printBookList(list,b->b.getAuthor().contains(author.trim()));
			break;
		case 3: //출판사로 조회
			System.out.print("출판사");
			publisher = scan.nextLine();
			printBookList(list,b->b.getPublisher().contains(publisher.trim()));
			break;
		case 4: //분류로 조회
			System.out.print("장르");
			genre = scan.nextLine();
			printBookList(list,b->b.getGenre().contains(genre.trim()));
			break;
		case 5: //취소
			System.out.println("조회를 취소했습니다.");
			break;
		default: System.out.println("메뉴가 잘못되었습니다.");
		}
	}
	public static void printBookList(ArrayList<Book>list, Predicate<Book>p) {
		int count=0;
		for(Book tmp : list) {
			if(p.test(tmp)) {
				System.out.println(tmp);
				count++;
			}
		}
		if(count==0) {
			System.out.println("검색 결과가 없습니다.");
		}
	}
}
