package day21;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Predicate;

public class PhoneListMain {
	
	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		/* 전화번호를 관리하는 프로그램을 작성하세요.
		 * 1. 전화번호 추가(성, 이름, 직장, 전화번호들(이름: 번호))
		 * 2. 전화번호 수정
		 *   - 이름(성+이름)으로 검색, 출력
		 *   - 검색된 사람들 중에서 선택
		 *   - 성, 이름을 수정할건지
		 *     기존 등록된 전화번호를 수정할건지
		 *     새 전화번호를 등록할건지를 선택하여 동작
		 * 3. 전화번호 삭제
		 *   - 이름(성+이름)으로 검색
		 *   - 검색된 사람들 중에서 선택
		 *   - 선택된 사람의 연락처를 삭제
		 * 4. 전화번호 조회
		 *   - 이름으로 검색
		 *   - 검색된 사람들 중에서 선택
		 *   - 선택된 사람의 연락처를 출력
		 * */
			//반복
		 int menu=-1;
		 ArrayList<PhoneBook> list = new ArrayList<PhoneBook>();
		 do {
			 printMenu();
			try {
				menu = scan.nextInt();
				runMenu(menu,list);
			}catch(RuntimeException e) {
				System.out.println(e.getMessage());
			}
		 }while(menu!=5);
		 
			 

	}

	private static void runMenu(int menu, ArrayList<PhoneBook> list) {
		switch(menu) {
		case 1:// 1. 번호 추가
			if(insertPhoneBook(list)) {
				System.out.println("전화번호를 추가했습니다");
			}else {
				System.out.println("전화번호를 추가하지 못했습니다.");
			}
			break;
		case 2://2. 번호 수정
			if(updatePhoneBook(list)) {
				System.out.println("전화번호를 수정했습니다.");
			}else {
				System.out.println(list);
				System.out.println("수정하지 못했습니다.");
			}
	
			break;
		case 3: //3. 번호 삭제
			if(deletePhoneBook(list)) {
				System.out.println("전화번호를 삭제했습니다.");
			}else{
				System.out.println("전화번호를 삭제하지 못했습니다");
			}
			break;
		case 4: //4. 번호 조회
			printSearchNumber(list);
			break;
		case 5:
			System.out.println("프로그램을 종료합니다.");
			break;
		default: System.out.println("잘못된 메뉴입니다.");
		}
	}
	private static boolean updatePhoneBook(ArrayList<PhoneBook> list) {
		scan.nextLine();
		//이름을 입력
		System.out.print("이름 : ");
		String name = scan.nextLine();
		//이름이 포함된 전화번호부를 검색하여 번지를 확인
		ArrayList<Integer> indexs 
			= searchPhoneBook(list,p->p.getName().contains(name));
		//확인된 번지에 있는 번호들을 출력(번호와 함께)
		printIndexsNumber(list, indexs);
		//수정할 전화번호부 선택
		System.out.print("수정할 번호 선택 : ");
		int selelteIndex = scan.nextInt() -1;
		if(selelteIndex<0 || selelteIndex>=indexs.size())
			throw new RuntimeException("예외 발생: 잘못 선택하였습니다.");
		//서브 메뉴 출력, 선택
		printSubMenu();
		int subMenu = scan.nextInt();
		//서브 메뉴 실행
		int index = indexs.get(selelteIndex);
		return runSubMenu(subMenu, list.get(index));
	}

	private static boolean runSubMenu(int subMenu, PhoneBook pb) {
		if(pb==null)
			return false;
		switch(subMenu) {
		case 1: //1. 이름, 직장 수정
			//이름, 직장 입력
			scan.nextLine();
			System.out.print("성명 : ");
			String name = scan.nextLine();
			System.out.print("직장 : ");
			String company = scan.nextLine();
			//이름, 직장 수정
			pb.update(name, company);
			break;
		case 2: //2. 기존 전화번호 수정
			//기존 전화번호들을 출력
			pb.printPhoneNumbers();
			//수정할 전화번호 선택
			System.out.print("번호 입력 : ");
			int index = scan.nextInt()-1;
			//이름과 번호 입력, 수정
			scan.nextLine();
			System.out.print("이름 : ");
			String pName = scan.nextLine();
			System.out.print("번호 : ");
			String pNum = scan.nextLine();
			pb.getPnList().get(index).update(pName,pNum);
			break;
		case 3: //3. 새 전화번호 추가
			scan.nextLine();
			//이름, 번호를 입력
			ArrayList<PhoneNumber> pnList = inputPhoneNumbers();
			//새 전화번호를 추가
			pb.getPnList().addAll(pnList);
			//addAll? 리스트에 리트스를 추가할 경우 사용(콜렉션을 추가하는거라 set도 가능)
			break;
		default:
			System.out.println("잘못된 메뉴입니다.");
			return false;
		}
		return true;
	}

	private static void printSubMenu() {
		System.out.println("=======메뉴========");
		System.out.println("1. 이름, 직장 수정");
		System.out.println("2. 기존 전화번호 수정");
		System.out.println("3. 새 전화번호 추가");
		System.out.println("==================");
		System.out.print("메뉴 선택> ");
	}

	private static void printIndexsNumber(ArrayList<PhoneBook> list, 
			ArrayList<Integer> indexs) {
		// 확인된 번지에 있는 번호들을 출력(번호와 함께)
		if(indexs==null ||indexs.size()==0 || 
				list==null || list.size()==0) {
			throw new RuntimeException("예외 발생: 검색 결과가 없습니다.");
		}
		for(int i=0; i<indexs.size(); i++) {
			int index = indexs.get(i);
			System.out.println(i+1+". "+list.get(index));
		}
	}
	private static void printSearchNumber(ArrayList<PhoneBook> list) {
		scan.nextLine();
		//이름을 입력
		System.out.print("이름 : ");
		String name = scan.nextLine();
		//이름이 포함된 전화번호부를 검색하여 번지를 확인
		ArrayList<Integer> indexs 
			= searchPhoneBook(list,p->p.getName().contains(name)
				/* || p.getCompany().contains(name)*/ ); //람다식쓰면 조건 다양하게 가능
		//확인된 번지에 있는 번호들을 출력(번호와 함께)
		printIndexsNumber(list, indexs);
		
		//조회할 전화번호부를 선택
		System.out.print("조회할 번호 선택 : ");
		int selelteIndex = scan.nextInt() -1;
		if(selelteIndex<0 || selelteIndex>=indexs.size())
			throw new RuntimeException("예외 발생: 잘못 선택하였습니다.");
		list.get(selelteIndex).print();//예쁘게 출력하려고 만든 메소드
	}

	private static boolean deletePhoneBook(ArrayList<PhoneBook> list) {
		scan.nextLine();
		// 이름을 입력
		System.out.print("이름 : ");
		String name = scan.nextLine();
		// 이름이 포함된 전화번호부를 검색하여 번지를 확인
		ArrayList<Integer> indexs 
			= searchPhoneBook(list,p->p.getName().contains(name)
				/* || p.getCompany().contains(name)*/ ); //람다식쓰면 조건 다양하게 가능
		// 확인된 번지에 있는 번호들을 출력(번호와 함께)
		printIndexsNumber(list, indexs);
		
		// 삭제할 전화번호부를 선택
		System.out.print("삭제할 번호 선택 : ");
		int selelteIndex = scan.nextInt() -1;
		if(selelteIndex<0 || selelteIndex>=indexs.size())
			return false;
		// 전화번호 삭제
		int deleteIndex = indexs.get(selelteIndex);
		return list.remove(deleteIndex) != null; 
		//리스트.remove() 의 리턴값?
		//번지로 삭제하는 경우 삭제에 성공했다면 삭제한 객체 정보를 알려줌
		//객체값을 삭제하는 경우 삭제여부를 true/false로 알려줌
	}

	private static ArrayList<Integer> searchPhoneBook(ArrayList<PhoneBook> list, 
			Predicate<PhoneBook> p) {
		ArrayList<Integer> indexs = new ArrayList<Integer>();
		for(int i=0; i<list.size(); i++) {
			if(p.test(list.get(i)))
				indexs.add(i);
		}
		return indexs;
	}

	private static ArrayList<PhoneNumber> inputPhoneNumbers(){
		ArrayList<PhoneNumber> pnList = new ArrayList<PhoneNumber>();
		do {
			//입력받은 이름, 번호를 이용하여 PhoneNumber 객체를 만들고,			
			//만들어진 PhoneNumber 객체를 PhoneNumber 리스트에 추가
			pnList.add(inputPhoneNumber());
			//전화번호를 더 입력할건지 물어봄
			System.out.print("더 입력하겠습니까?(y/n) : ");
		}while(scan.nextLine().charAt(0) !='n');
		return pnList;
	}
	private static PhoneNumber inputPhoneNumber() {
		// 전화번호를 입력(이름: 번호) => PhoneNumber
		System.out.print("이름(집, 직장 등) : ");
		String pName = scan.nextLine();
		System.out.print("번호(예:010-1234-5678) : ");
		String pNum = scan.nextLine();
		return new PhoneNumber(pName, pNum);
	}
	private static boolean insertPhoneBook(ArrayList<PhoneBook> list) {
		//성명, 직장 입력
		scan.nextLine();
		System.out.print("성명 : ");
		String name = scan.nextLine();
		System.out.print("직장 : ");
		String company = scan.nextLine();
		
		ArrayList<PhoneNumber> pnList;
		try{
			//전화번호들을 입력(예외발생가능)
			pnList = inputPhoneNumbers();
		}catch(RuntimeException e) {
			return false;
		}
		
		//성명,직장,전화번호들(PhoneNumber리스트)를 이용하여 PhoneBook 개체를 생성
		PhoneBook pb = new PhoneBook(name, company, pnList);
		//list에 PhoneBook 객체를 추가
		list.add(pb);
		return true;
	}

	private static void printMenu() {
		System.out.println("=======메뉴========");
		System.out.println("1. 전화번호 추가");
		System.out.println("2. 전화번호 수정");
		System.out.println("3. 전화번호 삭제");
		System.out.println("4. 전화번호 조회");
		System.out.println("5. 종료");
		System.out.println("==================");
		System.out.print("메뉴 선택> ");
	}

}
