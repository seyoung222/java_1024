package example.accountBook;

import java.util.ArrayList;
import java.util.Scanner;

public class AccountServiceImp implements AccountService {
	public Item inputItem() {
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
		return new Item(inAndOut, catogory, contents, amount, year, month, day);
	}
	@Override
	public void insertItem(ArrayList<Item> list, Item item) {
		list.add(item);
	}

	@Override
	public void printItem(ArrayList<Item> list) {
		for(Item tmp:list) {
			System.out.println(tmp);
		}
	}

	@Override
	public boolean updateItem(ArrayList<Item> list, int index, Item item) {
		Scanner scan = new Scanner(System.in);
		//년, 월, 일로 검색 후 내역 출력
		System.out.print("년 월 일> ");
		int year = scan.nextInt();
		int month = scan.nextInt();
		int day = scan.nextInt();
		Item t1 = new Item(year,month,day);
		
		ArrayList<Integer> indexList = new ArrayList<Integer>();
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).equals(t1)) {
				
			}
		}
		//번호를 입력
		//입력받은 번호에 맞는 내역을 수정 - 구분/일시/분류/내용/금액 순으로 입력받아 수정
		return false;
	}

	@Override
	public boolean deleteItem(ArrayList<Item> list, int index) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void printMenu() {
		System.out.println("메뉴");
		System.out.println("1. 내역 추가");
		System.out.println("2. 내역 확인");
		System.out.println("3. 내역 수정");
		System.out.println("4. 내역 삭제");
		System.out.println("5. 종료");
		System.out.print("메뉴 선택> ");
	}

	@Override
	public void runMenu(ArrayList<Item> list, int menu, Scanner scan) {
		switch(menu) {
		case 1:
			Item item=inputItem();
			insertItem(list, item);
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

}
