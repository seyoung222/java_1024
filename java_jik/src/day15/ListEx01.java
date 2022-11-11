package day15;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ListEx01 {

	public static void main(String[] args) {
		/* 리스트를 이용하여 다음 기능을 갖는 오늘의 할일 프로그램을 작성하세요.
		 * 메뉴
		 * 1. 할일 추가
		 * 2. 할일 확인
		 * 3. 할일 삭제
		 * 4. 종료
		 * 메뉴 선택: 1
		 * ---------------
		 * 할일 입력 : 오후 수업 참여
		 * ---------------
		 * 메뉴 
		 * 1. 할일 추가
		 * 2. 할일 확인
		 * 3. 할일 삭제
		 * 4. 종료
		 * 메뉴 선택: 1
		 * ---------------
		 * 할일 입력 : 저녁
		 * ---------------
		 * 메뉴 
		 * 1. 할일 추가
		 * 2. 할일 확인
		 * 3. 할일 삭제
		 * 4. 종료
		 * 메뉴 선택: 2
		 * --------------
		 * 1. 오후 수업 참여
		 * 2. 저녁
		 * --------------
		 * 메뉴 
		 * 1. 할일 추가
		 * 2. 할일 확인
		 * 3. 할일 삭제
		 * 4. 종료
		 * 메뉴 선택: 3
		 * 1. 오후 수업 참여
		 * 2. 저녁
		 * 삭제할 할일 번호 선택 : 1
		 * 1번 할일이 삭제되었습니다.
		 *  --------------
		 * 메뉴 
		 * 1. 할일 추가
		 * 2. 할일 확인
		 * 3. 할일 삭제
		 * 4. 종료
		 * 메뉴 선택: 2
		 * --------------
		 * 1. 저녁
		 * */
		
		ArrayList<String> todoList = new ArrayList<String>();
		int menu=-1;
		Scanner scan = new Scanner(System.in);
		do {
			//메뉴 출력
			printMenu();
			try {
				//메뉴 선택
				menu = scan.nextInt();
				//선택한 메뉴 실행
				runMenu(todoList, menu);
			}catch(InputMismatchException e) {
				System.out.println("--------------");
				System.out.println("예외 발생: 정수를 입력하세요.");
				System.out.println("--------------");
				scan.nextLine(); //입력버퍼 비움
				
			}catch(Exception e) {
				System.out.println("--------------");
				System.out.println(e.getMessage()); //오류 메세지 출력
				System.out.println("--------------");
			}
			
		}while(menu != 4);
		
		/*
		ArrayList<String> todolist = new ArrayList<String>();
		int menu=0;
		Scanner scan = new Scanner(System.in);
		
		do {
			printMenu();
			try {
				menu = scan.nextInt();
			}catch (Exception e) {
				System.out.println("정수를 입력하세요");
				continue;
			}
			runMenu(menu, todolist);
		}while(menu!=3);
		System.out.println("프로그램을 종료합니다.");
		*/
	}

	private static void runMenu(ArrayList<String> todoList, int menu) throws Exception {
		Scanner scan = new Scanner(System.in);
		switch(menu) {
		case 1:
			System.out.print("할일 입력 : ");
			//할일을 입력(공백 포함해서 입력)
			String todo = scan.nextLine();
			//리스트에 입력된 할일을 추가
			todoList.add(todo);
			break;
		case 2:
			printTodoList(todoList);
			break;
		case 3:
			printTodoList(todoList);
			System.out.print("삭제할 할일 번호 선택: ");
			int index = scan.nextInt() -1;
			//잘못된 번호를 입력한 경우
			if(index<0 || index>=todoList.size()) {
				throw new Exception("예외 발생: 삭제할 번호를 잘못 입력했습니다.");
			}
			todoList.remove(index);
			System.out.println((index+1)+"번 할일이 삭제되었습니다.");
			break;
		case 4:
			System.out.println("프로그램 종료");
			break;
		default:
		throw new Exception("예외 발생 : 잘못된 메뉴입니다.");
		//runtimeException이 아니므로 throws도 해야하고, main부분도 예외처리해야함
		}
	}
	
	private static void printTodoList(ArrayList<String> todoList) {
		if(todoList==null || todoList.size()==0) {
			System.out.println("저장된 할 일이 없습니다.");
			return;
		}
		for(int i=0; i<todoList.size(); i++) {
			System.out.println((i+1)+". "+todoList.get(i));
		}
	}

	private static void printMenu() {
		System.out.println("메뉴");
		System.out.println("1. 할일 추가");
		System.out.println("2. 할일 확인");
		System.out.println("3. 할일 삭제");
		System.out.println("4. 종료");
		System.out.print("메뉴 선택 : ");
	}
	
	/*
	private static void runMenu(int menu, ArrayList<String> list) {
		switch(menu) {
		case 1:
			System.out.println("---------------");
			Scanner scan = new Scanner(System.in);
			System.out.print("할일 입력> ");
			list.add(scan.nextLine());
			break;
		case 2:
			System.out.println("---------------");
			for(int i=0; i<list.size(); i++) {
				System.out.println((i+1)+". "+list.get(i));
			}
			break;
		case 3:
			break;
		default:
			System.out.println("메뉴를 다시 입력하세요.");
		}
	}
	public static void printMenu() {
		System.out.println("---------------");
		System.out.println("메뉴");
		System.out.println("1. 할일 추가");
		System.out.println("2. 할일 확인");
		System.out.println("3. 종료");
		System.out.print("메뉴 선택 > ");
	}
	*/
}
