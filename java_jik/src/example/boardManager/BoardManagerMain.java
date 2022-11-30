package example.boardManager;

import java.util.ArrayList;
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
		/* 게시글 관리 프로그램
		 * - 회원가입
		 *  - 처음 가입한 회원이 관리자, 그 외 회원은 일반 회원
		 * - 로그인
		 * - 게시글 등록/수정/삭제 => 회원만 가능
		 * - 카테고리 관리(공지, 자유)
		 * - 카테고리 추가/수정/삭제(관리자만 가능)
		 * */
		BoardManager boardManager = new BoardManager();
		int menu=-1;
		do {
			printMenu();
			menu=scan.nextInt();
			runMenu(menu, boardManager);
		}while(menu!=4);
	}
	private static void runMenu(int menu, BoardManager boardManager) {
		ArrayList<Board> bList=boardManager.getBoards();
		ArrayList<Member> mList=boardManager.getMembers();
		switch(menu) {
		case 1:
			signUp(mList);
			break;
		case 2:
			System.out.println("로그인하세요. 비회원은 일부 활동이 제한됩니다.");
			int memberClass = logIn(mList);
			switch(memberClass) {
			case 1: 
				System.out.println("관리자로 로그인 합니다.");
				int managerMenu=0;
				while(managerMenu!=3) {
					printSubMenu(1);
					managerMenu=scan.nextInt();
					switch(managerMenu) {
					case 1:
						printSubMenu(11);
						int boardMenu=scan.nextInt();
						runBoardManagement(boardMenu,bList);
						break;
					case 2:
						printSubMenu(12);
						int categoryMenu=scan.nextInt();
						runCategoryManagement(categoryMenu);
						break;
					case 3:
						System.out.println("로그아웃합니다.");
						break;
					default:
						System.out.println("잘못된 메뉴입니다.");
					}
				}
				break;
			case 2: 
				System.out.println("일반 회원으로 로그인 합니다.");
				int memberMenu=0;
				while(memberMenu!=3) {
					printSubMenu(2);
					memberMenu=scan.nextInt();
//					runBoardManagement(memberMenu);
				}
				break;
			case 3:
				System.out.println("로그인에 실패했습니다. 비회원은 조회만 가능합니다.");
				printSubMenu(3);
				int guestMenu=scan.nextInt();
//				runGuestMenu(GuestMenu);
				break;
			}
			break;
		case 3:
			System.out.println("프로그램 종료");
			break;
		default:
			System.out.println("잘못된 메뉴입니다.");
		}
	}
	private static void signUp(ArrayList<Member>mList) {
		System.out.print("가입할 아이디> ");
		String id = scan.next();
		System.out.print("비밀번호 입력> ");
		String pw = scan.next();
		Member m = new Member(id,pw);
		if(mList.contains(m))
			System.out.println("이미 존재하는 아이디입니다.");
		else {
			mList.add(m);
			System.out.println("회원가입이 완료되었습니다.");
		}
	}
	private static int logIn(ArrayList<Member>mList) {
		System.out.print("아이디 입력> ");
		String id = scan.next();
		System.out.print("비밀번호 입력> ");
		String pw = scan.next();
		Member m = new Member(id,pw);
		if(!mList.contains(m)) {
			System.out.println("가입하지 않은 회원입니다.");
			return 3;
		}
		int index = mList.indexOf(m);
		if(mList.get(index).getPw().equals(pw)) {
			if(index==0)
				return 1;
			else
				return 2;
		}
		System.out.println("비밀번호가 틀렸습니다.");
		return 3;
	}
	private static void runBoardManagement(int menu,ArrayList<Board>bList) {
		switch(menu) {
		case 1://게시글 작성
			writeNewBoard(bList);
			//아래는 확인용
			bList.get(0).printCategories();
			for(Board tmp : bList)
				System.out.println(tmp);
			break;
		case 2://수정
			printSubMenu(4);
			
			break;
		case 3://삭제
			break;
		case 4: //목록
			break;
		case 5: 
			break;
		default:
			System.out.println("잘못된 메뉴입니다.");
		}
	}
	private static void writeNewBoard(ArrayList<Board>bList) {
		scan.nextLine();
		System.out.print("제목> ");
		String title=scan.nextLine();
		System.out.print("작성자> ");
		String writer=scan.nextLine();
		System.out.print("내용> ");
		String content=scan.nextLine();
		System.out.print("카테고리> ");
		String category=scan.nextLine();
		Board b = new Board(category,new Post(title,writer,content));
		bList.add(0,b);
	}
	private static void runCategoryManagement(int menu) {
		switch(menu) {
		case 1://카테고리 추가
			break;
		case 2://수정
			break;
		case 3://삭제
			break;
		default:
		}
	}
	private static void printSubMenu(int subMenu) {
		switch(subMenu) {
		case 1:
			System.out.println("=======관리자 메뉴=======");
			System.out.println("1. 게시글 관리");
			System.out.println("2. 카테고리 관리");
			System.out.println("3. 로그아웃");
			System.out.print("메뉴 선택> ");
			break;
		case 11:
			System.out.println("=======관리자-게시글 관리=======");
			System.out.println("1. 게시글 작성");
			System.out.println("2. 게시글 수정");
			System.out.println("3. 게시글 삭제");
			System.out.println("4. 게시글 목록");
			System.out.println("5. 뒤로 가기");
			System.out.print("메뉴 선택> ");
			break;
		case 12:
			System.out.println("=======관리자-카테고리 관리=======");
			System.out.println("1. 카테고리 추가");
			System.out.println("2. 카테고리 수정");
			System.out.println("3. 카테고리 삭제");
			System.out.println("4. 뒤로 가기");
			System.out.print("메뉴 선택> ");
			break;
		case 2:
			System.out.println("=======회원 메뉴=======");
			System.out.println("1. 게시글 작성");
			System.out.println("2. 게시글 수정");
			System.out.println("3. 게시글 삭제");
			System.out.println("4. 로그아웃");
			System.out.print("메뉴 선택> ");
			break;
		
		case 3:
			System.out.println("=======비회원 메뉴=======");
			System.out.println("1. 게시글 조회");
			System.out.println("2. 뒤로 가기");
			System.out.print("메뉴 선택> ");
			break;
		case 4:
			System.out.println("=======조회 메뉴=======");
			System.out.println("1. 전체 조회");
			System.out.println("2. 카테고리별 조회");
			System.out.println("3. 뒤로 가기");
		}
	}
	private static void printMenu() {
		System.out.println("=======메뉴=======");
		System.out.println("1. 회원 가입");
		System.out.println("2. 게시판 입장");
		System.out.println("3. 종료");
		System.out.print("메뉴 선택> ");
	}

}
