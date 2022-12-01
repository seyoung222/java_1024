package day30;

import java.util.ArrayList;
import java.util.Scanner;

public class BoardMain {
	private static ArrayList<Board> bList = new ArrayList<Board>();
	private static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		/* 기능
		 * - 게시글 등록
		 * - 게시글 수정
		 * - 게시글 삭제
		 * - 게시글 목록
		 * - 게시글 확인
		 *  - 댓글 등록
		 *  - 댓글 확인
		 *  - 이전
		 * - 프로그램 종료
		 * */
		int menu=-1;
		while(menu!=6) {
			printMenu();
			menu=scan.nextInt();
			scan.nextLine();
			runMenu(menu);
		}
	}
	/** 메뉴를 출력하는 메소드
	 * */
	private static void printMenu() {
		System.out.println("====메뉴====");
		System.out.println("1. 게시글 등록");
		System.out.println("2. 게시글 수정");
		System.out.println("3. 게시글 삭제");
		System.out.println("4. 게시글 목록");
		System.out.println("5. 게시글 확인");
		System.out.println("6. 종료");
		System.out.println("============");
		System.out.print("메뉴 선택> ");
	}
	private static void runMenu(int menu) {
		switch(menu) {
		case 1:
			insertBoard();
			break;
		case 2:
			updateBoard();
			break;
		case 3:
			deleteBoard();
			break;
		case 4:
			printBoard();
			break;
		case 5:
			viewBoard();
			break;
		case 6:
			System.out.println("프로그램을 종료합니다.");
			break;
		default:
			System.out.println("잘못된 메뉴입니다.");
		}
	}
	private static void viewBoard() {
		// TODO Auto-generated method stub
		
	}
	private static void printBoard() {
		for(Board tmp : bList) {
			
		}
		
	}
	private static void deleteBoard() {
		// TODO Auto-generated method stub
		
	}
	private static void updateBoard() {
		// TODO Auto-generated method stub
		
	}
	/** insertBoard
	 * 게시글 정보를 입력받아 게시글을 출력하는 메소드 
	 * */
	private static void insertBoard() {
		System.out.print("글 번호> ");
		int num = scan.nextInt();
		System.out.print("제목> ");
		String title = scan.nextLine();
		System.out.print("작성자> ");
		String writer = scan.nextLine();
		System.out.print("내용> ");
		String content = scan.nextLine();
		Board board = new Board(num, title, content, writer);
		if(bList.contains(board)) {
			System.out.println("이미 등록된 게시글 번호입니다.");
			return;
		}
		bList.add(board);
		System.out.println("게시글이 등록되었습니다.");
	}
}
