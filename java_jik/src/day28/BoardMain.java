package day28;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class BoardMain {
	private static Scanner scan = new Scanner(System.in);
	private static List<Member> memberList=new ArrayList<Member>();
	private static List<Object> boardList=new ArrayList<Object>();
	private static List<String> categoryList=new ArrayList<String>();
	         //Arrays.asList()는 사이즈 고정된 리스트를 만듦
	private static Member user = null;
	/* 게시글 관리 프로그램
	 * - 회원가입
	 *  - 처음 가입한 회원이 관리자, 그 외 회원은 일반 회원
	 * - 로그인
	 * - 게시글 등록/수정/삭제 => 회원만 가능
	 * - 카테고리 관리(공지, 자유)
	 * - 카테고리 추가/수정/삭제(관리자만 가능)
	 * 
	 * - 기능
	 *  - 회원관리
	 *   - 로그인
	 *   - 회원가입
	 *  - 게시글 관리
	 *   - 게시글 작성 (회원)
	 *   - 게시글 수정 (회원+작성자 본인)
	 *   - 게시글 삭제 (회원+작성자 본인)
	 *   - 게시글 목록
	 *    - 게시글 검색(검색 번호는 게시글 번호로)
	 *    - 게시글 확인
	 *  - 카테고리 관리(회원+관리자)
	 *   - 카테고리 추가
	 *   - 카테고리 수정
	 *   - 카테고리 삭제
	 * */
	public static void main(String[] args) {
		int menu = -1;
		loadMember("member.txt");
		loadCategory("category.txt");
		do {
			try {
				printMenu();
				menu = scan.nextInt();
				scan.nextLine(); //엔터 흡수용
				runMenu(menu);
			}catch(InputMismatchException e) {
				scan.nextLine(); //잘못된 문자열을 비우기 위해 들어감
				System.out.println("숫자를 입력하세요.");
			}catch(Exception e) {
//				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}while(menu != 4);
		saveMember("member.txt");
		saveCategory("category.txt");
	}
	//양식이 같아서 제네릭함수로 만듦list)
	private static <T> save(String fileName,List<T> {
		try(ObjectOutputStream oos = 
				new ObjectOutputStream(new FileOutputStream(fileName))){
			for(String tmp : categoryList) {
				oos.writeObject(tmp);
			}
			System.out.println("저장 완료");
		}catch(IOException e) {
			System.out.println("저장 실패");
		}
	}
	private static void saveCategory(String fileName) {
		try(ObjectOutputStream oos = 
				new ObjectOutputStream(new FileOutputStream(fileName))){
			for(String tmp : categoryList) {
				oos.writeObject(tmp);
			}
			System.out.println("저장 완료");
		}catch(IOException e) {
			System.out.println("저장 실패");
		}
	}
	private static void loadCategory(String fileName) {
		try(ObjectInputStream ois = 
				new ObjectInputStream(new FileInputStream(fileName))){
			while(true) {
				String category = (String)ois.readObject();
				categoryList.add(category);
			}
		}catch(ClassNotFoundException e) {
			System.out.println("불러오기 실패");
		}catch(EOFException e) {
			if(categoryList.size()==0)
				categoryList=new ArrayList<String>(Arrays.asList("공지","자유"));
			System.out.println("불러오기 성공");
		}catch(IOException e) {
			System.out.println("불러오기 실패");
		}
	}
	
	private static void saveMember(String fileName) {
		if(memberList.size()==0)
			return;
		try(ObjectOutputStream oos = 
				new ObjectOutputStream(new FileOutputStream(fileName))){
			for(Member tmp : memberList) {
				oos.writeObject(tmp);
			}
			System.out.println("저장 완료");
		}catch(IOException e) {
			System.out.println("저장 실패");
		}
	}
	private static void loadMember(String fileName) {
		try(ObjectInputStream ois = 
				new ObjectInputStream(new FileInputStream(fileName))){
			while(true) {
				Member member = (Member)ois.readObject();
				memberList.add(member);
			}
		}catch(ClassNotFoundException e) {
			System.out.println("불러오기 실패");
		}catch(EOFException e) {
			System.out.println("불러오기 성공");
		}catch(IOException e) {
			System.out.println("불러오기 실패");
		}
	}
	private static void runMenu(int menu) {
		switch(menu) {
		case 1:
			memberMenu();
			break;
		case 2:
			boardMenu();
			break;
		case 3:
			categoryMenu();
			break;
		case 4:
			System.out.println("프로그램 종료");
			break;
		default:
			throw new RuntimeException("잘못된 메뉴입니다.");
		}
		
	}
	private static void categoryMenu() {
		//관리자 체크=> 관리자가 아니면 메인메뉴로
		if(!isAdmin())
			return;
		int subMenu=-1;
		do {
			//서브메뉴를 출력
			printSubMenu(3);
			//서브메뉴 선택 및 선택한 서브메뉴에 맞는 기능 실행
			subMenu = scan.nextInt();
			scan.nextLine();
			runCategoryMenu(subMenu);
			
		}while(subMenu!=5);
	}
	private static void runCategoryMenu(int subMenu) {
		switch(subMenu) {
		case 1://1. 카테고리 추가
			insertCategory();
			break;
		case 2://2. 카테고리 수정
			updateCategory();
			break;
		case 3://3. 카테고리 삭제
			deleteCategory();
			break;
		case 4://4. 카테고리 확인
			printCategory();
			break;
		case 5://5. 이전
			subMenu=5;
			System.out.println("이전 메뉴로 돌아갑니다.");
			break;
		default:
			System.out.println("잘못된 메뉴입니다.");
		}
	}
	private static void printCategory() {
		// 모든 카테고리 출력
		if(categoryList.size()==0) {
			System.out.println("등록된 카테고리가 없습니다.");
			return;
		}
		for(int i=0;i<categoryList.size();i++) {
			System.out.println(i+1+". "+categoryList.get(i));
		}
	}
	private static void deleteCategory() {
		//삭제할 카테고리명 입력
		System.out.print("카테고리명 : ");
		String category = scan.nextLine();
		if(categoryList.remove(category)) {
			System.out.println("카테고리를 삭제했습니다.");
			return;
		}
		System.out.println("등록되지 않은 카테고리입니다.");
	}
	private static void updateCategory() {
		//수정할 카테고리명 입력
		System.out.print("카테고리명 : ");
		String category = scan.nextLine();
		//새 카테고리명 입력
		System.out.print("카테고리명 : ");
		String newCategory = scan.nextLine();
		//기존 카테고리에 있는지 학인하여 없으면 수정		
		if(categoryList.contains(category)) {
			categoryList.remove(category);
			categoryList.add(newCategory);
			System.out.println("카테고리를 수정했습니다.");
			return;
		}
		System.out.println("이미 등록된 카테고리입니다.");
	}
	private static void insertCategory() {
		//새 카테고리명 입력
		System.out.print("카테고리명 : ");
		String category = scan.nextLine();
		//기존 카테고리에 있는지 확인하여 없으면 추가
		if(categoryList.indexOf(category)==-1) {
			categoryList.add(category);
			System.out.println("새 카테고리를 추가했습니다.");
			return;
		}
		System.out.println("이미 있는 카테고리입니다.");
	}
	private static boolean isAdmin() {
		if(user==null || user.getAuthority()!=Authority.ADMIN) {
			System.out.println("관리자가 아닙니다. 해당 기능을 이용할 수 없습니다.");
			return false;
		}
		return true;
	}
	private static void boardMenu() {
		//서브메뉴를 출력
		//서브메뉴 선택 및 선택한 서브메뉴에 맞는 기능 실행
			//1.게시글 등록
				//회원 체크=> 회원(로그인한 사용자)가 아니면 게시글 등록 못함
				//게시글 정보(제목, 내용) 입력
				//게시글 등록
			//2.게시글 수정
				//수정할 게시글 번호 입력
				//해당 게시글이 존재하고, 작성자가 회원과 같지않으면 수정 못함
				//작성자 체크=> 회원(로그인한 사용자)가 아니면 게시글 등록 못함
				//게시글 정보(제목, 내용) 입력
				//게시글 수정
			//3.게시글 삭제
				//삭제할 게시글 번호 입력
				//해당 게시글이 존재하고, 작성자가 회원과 같지않으면 삭제 못함
				//작성자 체크=> 회원(로그인한 사용자)가 아니면 게시글 등록 못함
				//해당 게시글 삭제
			//4.게시글 목록
				//서브메뉴 출력
				//서브메뉴 선택 및 기능 실행
					//1. 게시글 목록 확인 : 모든 게시글 확인
					//2. 게시글 검색 : 검색어 입력 후 게시글 확인
					//3. 게시글 확인 : 게시글 번호를 입력 후 입력한 게시글이 있으면 확인
					//4. 이전
			//5. 이전
		
	}
	private static void memberMenu() {
		//로그인 체크=> 로그인한 사람은 로그인/회원가입 시도를 할수 없게함
		if(checkLogin(true))
			return;
		int subMenu=-1;
		do {
			//서브메뉴를 출력
			printSubMenu(1);
			subMenu=scan.nextInt();
			scan.nextLine();
			//서브메뉴 선택 및 선택한 서브메뉴에 맞는 기능 실행 => 반복
			subMenu = runMemberMenu(subMenu);
		}while(subMenu!=3);
		
	}
	private static int runMemberMenu(int subMenu) {
		switch(subMenu) {
		case 1://1. 회원가입
			signUp();
			break;
		case 2://2. 로그인
			login();
			//로그인 성공하면 서브 메뉴를 3으로 수정(반복문 탈출)
			if(user != null)
				return 3; //subMenu는 지역변수이므로 안에서 바꿔도 바깥으로 영향X
			break;
		case 3://3. 이전
			System.out.println("이전 메뉴로 돌아갑니다.");
			break;
		default:
			System.out.println("잘못된 메뉴입니다.");
		}
		return subMenu;
	}
	private static void login() {
		//회원 정보 입력(아이디. 비번)
		System.out.println("로그인 정보 입력");
		Member member = inputMember();
		//일치하는 회원이 있으면 회원정보를 가져옴(로그인 성공)
		int index = memberList.indexOf(member);
		if(index ==-1) {
			System.out.println("로그인 실패");
			return;
		}
		user = memberList.get(index);
		System.out.println("로그인 성공");
		
	}
	private static void signUp() {
		//회원 정보 입력
		System.out.println("회원 정보 입력");
		Member member = inputMember();
		//가입된 아이디인지 체크
		if(isMember(member)) {
			System.out.println("이미 가입된 아이디입니다.");
			return;
		}
		//가입된 아이디가 아니면 회원가입 진행
		memberList.add(member);
		System.out.println("회원가입이 완료되었습니다.");
	}
	private static boolean isMember(Member member) {
		if(member == null)
			return false;
		if(memberList==null)
			memberList = new ArrayList<Member>();
		if(memberList.size()==0)
			return false;
		for(Member tmp : memberList) {
			if(tmp.getId().equals(member.getId()))
				return true;
		}
		return false;
	}
	private static Member inputMember() {
		System.out.print("아이디 : ");
		String id = scan.nextLine();
		System.out.print("비밀번호 : ");
		String pw = scan.nextLine();
		Authority autority = memberList.size()==0 ? Authority.ADMIN : Authority.MEMBER;
		return new Member(id, pw, autority);
	}
	private static void printSubMenu(int menu) {
		switch(menu) {
		case 1:
			System.out.println("=======회원관리 메뉴=======");
			System.out.println("1. 회원 가입");
			System.out.println("2. 로그인");
			System.out.println("3. 이전");
			break;
		case 2:
			System.out.println("=======게시글관리 메뉴=======");
			System.out.println("1. 게시글 등록");
			System.out.println("2. 게시글 수정");
			System.out.println("3. 게시글 삭제");
			System.out.println("4. 게시글 목록");
			System.out.println("5. 이전");
			break;
		case 3:
			System.out.println("=======카테고리관리 메뉴=======");
			System.out.println("1. 카테고리 추가");
			System.out.println("2. 카테고리 수정");
			System.out.println("3. 카테고리 삭제");
			System.out.println("4. 카테고리 확인");
			System.out.println("5. 이전");
			break;
		}
		System.out.println("=================");
		System.out.print("메뉴 선택> ");
		
	}
	private static boolean checkLogin(boolean res) {
		if(user != null && res) {
			System.out.println("이미 로그인 되어있어 해당 기능을 사용할 수 없습니다.");
			return true;
		}
		if(user==null && !res) {
			System.out.println("로그인하지 않은 사용자는 해당 기능을 이용할 수 없습니다.");
			return true;
		}
		return false;
	}
	private static void printMenu() {
		System.out.println("=======메뉴=======");
		System.out.println("1. 회원 관리");
		System.out.println("2. 게시글 관리");
		System.out.println("3. 카테고리 관리");
		System.out.println("4. 종료");
		System.out.println("=================");
		System.out.print("메뉴 선택> ");
	}
	
}
