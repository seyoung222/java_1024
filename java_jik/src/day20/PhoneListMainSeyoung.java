package day20;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import lombok.Data;

public class PhoneListMainSeyoung {
	public static Scanner scan = new Scanner(System.in);
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
		/* 가이드 주석
			 * 1. 번호 추가
			 *   성명, 직장 입력
			 *   반복
			 *     전화번호를 입력(이름: 번호) => PhonNumber
			 *     전화번호를 더 입력할건지 물어봄
			 *   전화번호부에 추가 => PhoneBook 개체를 생성
			 * 2. 번호 수정
			 *   이름 입력
			 *   이름이 포함된 전화번호부를 검색하여 출력(번호와 함께)
			 *   수정할 전화번호부 선택
			 *   서브 메뉴 출력, 선택
			 *   서브메뉴 실행
			 *   1. 이름, 직장 수정
			 *     이름, 직장 입력
			 *     이름, 직장 수정
			 *   2. 기존 전화번호 수정
			 *     기존 전화번호들을 출력
			 *     수정할 전화번호 선택
			 *     이름과 번호 입력, 수정
			 *   3. 새 전화번호 추가
			 *     이름, 번호를 입력
			 *     새 전화번호를 추가
			 * 3. 번호 삭제
			 *   이름을 입력
			 *   이름이 포함된 전화번호부를 검색하여 출력(번호와 함께)
			 *   삭제할 전화번호부를 선택
			 *   전화번호 삭제
			 * 4. 번호 조회
			 *   이름 입력
			 *   이름이 포함된 전화번호부를 검색하여 출력(번호와 함께)
			 *   조회할 전화번호부를 선택
			 *   전화번호 조회
			 * */
		
		int menu = -1;
		ArrayList<Phone> list = new ArrayList<Phone>();
		do {
			printMenu();
			menu = scan.nextInt();
			runMenu(menu,list);
		}while(menu!=5);
	}
	public static void printMenu() {
		System.out.println("=======메뉴========");
		System.out.println("1. 전화번호 추가");
		System.out.println("2. 전화번호 수정");
		System.out.println("3. 전화번호 삭제");
		System.out.println("4. 전화번호 조회");
		System.out.println("5. 종료");
		System.out.println("==================");
		System.out.print("메뉴 선택> ");
	}
	private static void runMenu(int menu, ArrayList<Phone> list) {
		switch(menu) {
		case 1://전화번호 추가
			Phone phone = createPhone();
			list.add(phone);
			break;
		case 2://전화번호 수정
			updatePhone(list,searchPhone(list));
			break;
		case 3://전화번호 삭제
			if(deletePhone(list,searchPhone(list)))
				System.out.println("전화번호부가 삭제되었습니다.");
			break;
		case 4://전화번호 조회
			LookUpPhone(list,searchPhone(list));
			break;
		case 5:
			System.out.println("프로그램 종료");
			break;
		default:
			System.out.println("잘못된 메뉴입니다.");
		}
	}
	//검색한 이름을 포함한 리스트를 만들어 검색결과 출력하고 만들어진 리스트를 리턴하는 메소드
	private static ArrayList<Integer> searchPhone(ArrayList<Phone> list) {
		System.out.print("검색할 이름 입력> ");
		String searchName=scan.next();
		ArrayList<Integer> indexList = new ArrayList<Integer>();
		for(int i=0; i<list.size(); i++) {
			String fullName = list.get(i).getLastName()+list.get(i).getFirstName();
			if(fullName.contains(searchName)) {
				indexList.add(i);
			}
		}
		if(indexList==null || indexList.size()==0) {
			System.out.println("검색 결과가 없습니다.");
			return null;
		}
		for(int i=0; i<indexList.size(); i++) {
			System.out.println((i+1)+". "+list.get(indexList.get(i)));
		}
		return indexList;
	}
	public static void updatePhone(ArrayList<Phone>list, ArrayList<Integer>indexList) {
		if(indexList==null || indexList.size()==0) {
			return;
		}
		System.out.print("수정할 전화번호부 선택> ");
		int index = scan.nextInt() -1;
		if(index<0 || index>=indexList.size()) {
			System.out.println("전화번호부 선택이 잘못되었습니다.");
			return;
		}
		Phone tmp =list.get(indexList.get(index));//내가 수정하려는 지정된 전화번호부를 tmp로 명명
		
		printSunMenu();
		int subMenu = scan.nextInt();
		switch(subMenu) {
		case 1: //이름, 직장 수정
			System.out.println("수정 정보를 입력하세요.");
			System.out.print("성> ");
			tmp.setLastName(scan.next());
			System.out.print("이름> ");
			tmp.setFirstName(scan.next());
			System.out.print("직장> ");
			tmp.setWork(scan.next());
			break;
		case 2: //기존 전화번호 수정
			System.out.println(" < 전화번호들 목록 >");
			for(int i=0; i<tmp.getNumList().size(); i++) {
				System.out.println("("+(i+1)+")"+tmp.getNumList().get(i));
			}
			System.out.print("수정할 전화번호 선택> ");
			int index2 = scan.nextInt() -1;
			if(index2<0 || index2>=tmp.getNumList().size()) {
				System.out.println("전화번호 선택이 잘못되었습니다.");
				return;
			}
			System.out.print("수정할 항목 이름> ");
			tmp.getNumList().get(index2).setName(scan.next());
			System.out.print("수정할 전화번호> ");
			tmp.getNumList().get(index2).setNumber(scan.next());
			break;
		case 3: //새 전화번호 추가
			System.out.print("추가할 항목 이름> ");
			String name = scan.next();
			System.out.print("추가할 전화번호> ");
			String number = scan.next();
			tmp.getNumList().add(new PhoneNumber(name,number));
			break;
		case 4: //취소
			System.out.println("조회를 취소합니다.");
			break;
		default:
			System.out.println("잘못된 메뉴입니다.");
		}
	}
	private static void printSunMenu() {
		System.out.println("======서브 메뉴======");
		System.out.println("1. 이름, 직장 수정");
		System.out.println("2. 기존 전화번호 수정");
		System.out.println("3. 새 전화번호 추가");
		System.out.println("4. 취소");
		System.out.println("===================");
		System.out.print("메뉴 선택> ");
		
	}
	public static boolean deletePhone(ArrayList<Phone>list,ArrayList<Integer>indexList) {
		if(indexList==null || indexList.size()==0) {
			return false;
		}
		System.out.print("삭제할 전화번호부 선택> ");
		int index = scan.nextInt() -1;
		if(index<0 || index>=indexList.size()) {
			System.out.println("전화번호부 선택이 잘못되었습니다.");
			return false;
		}
		list.remove((int)indexList.get(index));
		return true;
	}
	public static void LookUpPhone(ArrayList<Phone>list, ArrayList<Integer>indexList) {
		System.out.print("조회할 전화번호부 선택> ");
		int index = scan.nextInt() -1;
		if(index<0 || index>=indexList.size()) {
			System.out.println("전화번호부 선택이 잘못되었습니다.");
			return;
		}
		Phone tmp =list.get(indexList.get(index));
		System.out.println(" < 전화번호들 목록 >");
		for(int i=0; i<tmp.getNumList().size(); i++) {
			System.out.println("("+(i+1)+")"+tmp.getNumList().get(i));
		}
		
	}
	public static Phone createPhone(){
		System.out.print("성 입력> ");
		String lastName = scan.next();
		System.out.print("이름 입력> ");
		String firstName = scan.next();
		System.out.print("직장 입력> ");
		String work = scan.next();
		Phone phone = new Phone(lastName, firstName, work);
		char answer;
		do {
			System.out.print("전화번호 종류(집/휴대폰/직장 등)> ");
			String name = scan.next();
			System.out.print("전화번호 입력(010-1234-5678)> ");
			String number = scan.next();
			phone.insertNumList(name, number);
			
			System.out.print("더 입력하시겠습니까?(y/n)> ");
			answer=scan.next().charAt(0);
		}while(answer=='y');
		
		return phone;
	}
}
@Data
class Phone{
	private String lastName, firstName, work;
	ArrayList<PhoneNumber> numList=new ArrayList<PhoneNumber>();
	
	public Phone(String lastName, String firstName, String work) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.work = work;
		
	}
	public void insertNumList(String name, String number) {
		numList.add(new PhoneNumber(name, number));
	}
	@Override
	public String toString() {
		return "이름: "+lastName+" "+firstName+" | 직장: "+work+" | "+numList;
	}
}
@Data
class PhoneNumber{
	private String name, number;

	public PhoneNumber(String name, String number) {
		this.name = name;
		this.number = number;
	}

	@Override
	public String toString() {
		return "(" + name + " : " + number + ")";
	}
}