package example.accountBook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class AccountServiceImp implements AccountService {
	public Item inputItem() {
		Scanner scan = new Scanner(System.in);
		System.out.print("구분> ");
		String inAndOut = scan.next();
		System.out.print("일시(년 월 일)> ");
		int year = scan.nextInt();
		int month = scan.nextInt();
		int day = scan.nextInt();
		scan.nextLine();
		System.out.print("분류> ");
		String catogory = scan.nextLine();
		System.out.print("내용> ");
		String contents = scan.nextLine();
		System.out.print("금액> ");
		int amount = scan.nextInt();
		return new Item(inAndOut, catogory, contents, amount, year, month, day);
	}
	@Override
	public void insertItem(ArrayList<Item> list, Item item) {
		item = inputItem();
		list.add(item);
		System.out.println("내역을 추가하였습니다.");
	}

	@Override
	public void printItem(ArrayList<Item> list) {
		//년월일 순 정렬 후 출력
		Collections.sort(list,new Comparator<Item>() {

			@Override
			public int compare(Item o1, Item o2) {
				if(o1.getYear() != o2.getYear()) {
					return o1.getYear() - o2.getYear();
				}
				if(o1.getMonth() != o2.getMonth()) {
					return o1.getMonth() - o2.getMonth();
				}
				return o1.getDay() - o2.getDay();
			}
		});
		for(int i=0; i<list.size(); i++) {
			System.out.println((i+1)+". "+list.get(i));
		}
	}

	@Override
	public boolean updateItem(ArrayList<Item> list, int index, Item item) {
		Scanner scan = new Scanner(System.in);
		//년, 월, 일로 검색 후 내역 출력
		System.out.print("수정할 내역의 날짜(년 월 일)> ");
		int year = scan.nextInt();
		int month = scan.nextInt();
		int day = scan.nextInt();
		Item t1 = new Item(year,month,day);
		
		ArrayList<Integer> indexList = new ArrayList<Integer>();
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).equals(t1)) {
				indexList.add(i);
			}
		}
		for(int i=0; i<indexList.size(); i++) {
			System.out.println((i+1)+". "+list.get(indexList.get(i)));
		}
		//번호를 입력
		if(indexList==null || indexList.size()==0) {
			System.out.println("해당 날짜의 내역이 없습니다.");
			return false;
		}
		System.out.print("수정할 내역 선택> ");
		index = scan.nextInt() -1;
		if(index<0 || index>=indexList.size()) {
			System.out.println("잘못된 숫자를 입력하였습니다.");
			return false;
		}
		//입력받은 번호에 맞는 내역을 수정 - 구분/일시/분류/내용/금액 순으로 입력받아 수정
		item = inputItem();
		list.set(indexList.get(index), item);
		return true;
	}

	@Override
	public boolean deleteItem(ArrayList<Item> list, int index) {
		Scanner scan = new Scanner(System.in);
		//년, 월, 일로 검색 후 내역 출력
		System.out.print("삭제할 내역의 날짜(년 월 일)> ");
		int year = scan.nextInt();
		int month = scan.nextInt();
		int day = scan.nextInt();
		Item t1 = new Item(year,month,day);
		
		ArrayList<Integer> indexList = new ArrayList<Integer>();
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).equals(t1)) {
				indexList.add(i);
			}
		}
		for(int i=0; i<indexList.size(); i++) {
			System.out.println((i+1)+". "+list.get(indexList.get(i)));
		}
		//번호를 입력
		if(indexList==null || indexList.size()==0) {
			System.out.println("해당 날짜의 내역이 없습니다.");
			return false;
		}
		System.out.print("삭제할 내역 선택> ");
		index = scan.nextInt() -1;
		if(index<0 || index>=indexList.size()) {
			System.out.println("잘못된 숫자를 입력하였습니다.");
			return false;
		}
		//입력받은 번호에 맞는 내역을 삭제
		list.remove((int)indexList.get(index));
		return true;
		
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
		Item item=null;
		int index=0;
		switch(menu) {
		case 1:
			insertItem(list, item);
			break;
		case 2:
			printItem(list);
			break;
		case 3:
			if(updateItem(list,index,item)) {
				System.out.println("내역을 수정하였습니다.");
			}else{
				System.out.println("내역 수정에 실패하였습니다.");
			};
			break;
		case 4:
			if(deleteItem(list,index)) {
				System.out.println("내역을 삭제했습니다.");
			}else {
				System.out.println("내역 삭제에 실패하였습니다.");
			}
			break;
		case 5:
			System.out.println("프로그램 종료");
			break;
		default:
			System.out.println("잘못된 메뉴입니다.");
		}		
	}

}
