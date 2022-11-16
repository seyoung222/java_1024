package example.accountBookAnswer;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AccountBook {
	ArrayList<Item> list = new ArrayList<Item>();
	AccountService accountService = new AccountServiceImp();//인터페이스도 객체참조 가능..
	Scanner scan;
	
	public void run() {
		System.out.println("가계부 프로그램 실행합니다.");
		int menu=-1;
		do {
			try {
				accountService.printMenu();
				menu=scan.nextInt();
				accountService.runMenu(list, menu,scan);
			}catch(ParseException e) {
				System.out.println("날짜 형태가 잘못되었습니다.");
			}catch(InputMismatchException e) {
				System.out.println("잘못된 타입을 입력했습니다.");
			}catch(RuntimeException e) {
				System.out.println(e.getMessage());
			}
			
		}while(menu != 5);
		System.out.println("가계부 프로그램을 종료합니다.");
	}
	public AccountBook(Scanner scan) {
		if(scan==null) {
			this.scan = new Scanner(System.in);
		}else {
			this.scan = scan;
		}
	}
}
