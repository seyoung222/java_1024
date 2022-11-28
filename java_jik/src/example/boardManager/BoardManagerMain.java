package example.boardManager;

import java.util.Scanner;

public class BoardManagerMain {
	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		/*게시글 관리 프로그램을 작성하는 코드를 작성하세요.
		- 게시글 종류는 자유, 공지는 기본
		- 게시글 종류는 추가 가능
		- 게시글을 회원만 작성 가능
		- 회원 가입, 로그인 기능 필요
		- 비회원은 작성, 수정 불가능, 조회 가능
		- 회원 정보와 게시글을 파일에 저장하여 관리
		회원정보, 게시글 로딩
		1. 회원 가입
		2. 로그인(아이디 조회 후 비밀번호 일치하면 로그인 완료, 다음의 메뉴 출력 및 사용 가능)
			//반복
			1. 게시글 작성 (
			2. 게시글 조회
			 - 전체 조회 (최신 게시글이 위로 오도록 정렬)
			 - 분류별 조회 (카테고리 출력 후 고르면 그 카테고리 글들만 보이게 함)
			3. 게시글 수정
			4. 로그아웃
		3. 비회원 활동(조회만 가능)
			1. 게시글 조회
			 - 전체 조회
			 - 분류별 조회 (카테고리 출력 후 고르면 그 카테고리 글들만 보이게 함)
			2. 뒤로 가기
		4. 종료
		*/
		BoardManager boardManager = new BoardManager();
		int menu=-1;
		do {
			printMenu();
			menu=scan.nextInt();
			runMenu(menu, boardManager);
		}while(menu!=4);
	}
	private static void runMenu(int menu, BoardManager boardManager) {
		
	}
	private static void printSubMenu(int subMenu) {
		switch(subMenu) {
		case 1:
			System.out.println("=======회원 메뉴=======");
			System.out.println("1. 게시글 작성");
			System.out.println("2. 게시글 조회");
			System.out.println("3. 게시글 수정");
			System.out.println("4. 로그아웃");
			System.out.print("메뉴 선택> ");
			break;
		case 2:
			System.out.println("=======비회원 메뉴=======");
			System.out.println("1. 게시글 조회");
			System.out.println("2. 뒤로 가기");
			System.out.print("메뉴 선택> ");
			break;
		case 3:
			System.out.println("=======조회 메뉴=======");
			System.out.println("1. 전체 조회");
			System.out.println("2. 카테고리별 조회");
		}
	}
	private static void printMenu() {
		System.out.println("=======메뉴=======");
		System.out.println("1. 회원 가입");
		System.out.println("2. 로그인");
		System.out.println("3. 비회원 활동(조회만 가능)");
		System.out.println("4. 종료");
		System.out.print("메뉴 선택> ");
	}

}
