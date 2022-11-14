package day15;

import java.util.ArrayList;
import java.util.Scanner;

public class HomeworkAccount {

	public static void main(String[] args) {
		/* 가계부 프로그램을 작성하세요
		 * 메뉴
		 * 1. 내역 추가
		 * 2. 내역 확인
		 * 3. 내역 수정
		 * 4. 내역 삭제
		 * 5. 종료
		 * 
		 * 1. 내역 추가
		 * 구분: 수입/지출
		 * 분류: 식비/통신비/교통비/생활비/용돈
		 * 내용: 홈플러스/XX약국/00식당
		 * 금액: 5000
		 * 일시: 년, 월, 일
		 * 
		 * 2. 내역 확인
		 * 2-1. 전체 내역 확인
		 * 2-2. 년 내역 확인
		 * 2-3. 월 내역 확인
		 * 2-4. 일 내역 확인
		 * 구분 | 일시 | 분류 | 내용 | 금액 (한줄에 출력)
		 * 
		 * 3. 내역 수정
		 * 년, 월, 일로 검색 후 내역 출력
		 * 번호를 입력
		 * 입력받은 번호에 맞는 내역을 수정 - 구분/일시/분류/내용/금액 순으로 입력받아 수정
		 * 
		 * 4. 내역 삭제
		 * 년, 월, 일로 검색 후 내역 출력
		 * 번호를 입력
		 * 입력받은 번호에 맞는 내역을 삭제
		 * 
		 * 추가 - 가계부를 년, 월, 일로 정렬
		 * 5. 종료
		 * */
		/* 가게부가 필요
		 * 가계부는 내역들로 구성
		 * 가계부 클래스
		 * 내역(항목) 클래스
		 * 가계부는 내열득을 가지고 있다(포함 관계) => 가계부 클래승 내역 객체가 필드
		 * 내역 추가, 확인, 수정, 삭제 기능을 인터페이스로 묶을 수 있다.
		 * */
		ArrayList<Item> list = new ArrayList<Item>();
		Scanner scan = new Scanner(System.in);
		int menu=-1;
		do {
			printMenu();
			menu = scan.nextInt();
			runMenu(menu, list);
			
		}while(menu != 5);
	}

	private static void runMenu(int menu, ArrayList<Item> list) {
		switch(menu) {
		case 1:
			insertItem(list);
			break;
		case 2:
			printItem(list);
			break;
		case 3:
			System.out.println("내역 수정");
			break;
		case 4:
			System.out.println("내역 삭제");
			break;
		case 5:
			System.out.println("프로그램 종료");
			break;
		default:
		}
	}

	private static void printItem(ArrayList<Item> list) {
		for(Item tmp:list) {
			System.out.println(tmp);
		}
	}

	private static void insertItem(ArrayList<Item> list) {
		Scanner scan = new Scanner(System.in);
		System.out.print("구분> ");
		String inAndOut = scan.nextLine();
		System.out.print("분류> ");
		String catogory = scan.nextLine();
		System.out.print("내용> ");
		String contents = scan.nextLine();
		System.out.print("금액> ");
		int amount = scan.nextInt();
		System.out.print("년 월 일> ");
		int year = scan.nextInt();
		int month = scan.nextInt();
		int day = scan.nextInt();
		Item item = new Item(inAndOut, catogory, contents, amount, year, month, day);
		
		list.add(item);
	}

	private static void printMenu() {
		System.out.println("메뉴");
		System.out.println("1. 내역 추가");
		System.out.println("2. 내역 확인");
		System.out.println("3. 내역 수정");
		System.out.println("4. 내역 삭제");
		System.out.println("5. 종료");
		System.out.print("메뉴 선택> ");
	}

}
class Date{
	int year;
	int month;
	int date;
	public Date(int year, int month, int date){
		this.year = year;
		this.month = month;
		this.date = date;
	}
	@Override
	public String toString() {
		return year+"-"+month+"-"+date;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + date;
		result = prime * result + month;
		result = prime * result + year;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Date other = (Date) obj;
		if (date != other.date)
			return false;
		if (month != other.month)
			return false;
		if (year != other.year)
			return false;
		return true;
	}
	
}
class Item{
	String inAndOut;
	String category;
	String contents;
	int amount;
	private int year;
	private int month;
	private int day;
	Date date = new Date(year,month,day);
	
	public Item(String inAndOut, String category, String contents, int amount, int year, int month,int day) {
		this.inAndOut = inAndOut;
		this.category = category;
		this.contents = contents;
		this.amount = amount;
		this.date = new Date(year, month,day);
	}
	
	@Override
	public String toString() {
		return inAndOut+" | "+ year+"-"+month+"-"+date+" | "+category+" | "+contents+
				" | "+amount;
	}
	
}