package day16;
//이거는 맵 이용해서 해볼거임
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import lombok.Data;

public class UserManagerMainSeYoung2 {

	public static void main(String[] args) {
		/* 회원정보를 관리하는 프로그램을 작성하세요.
		 * - 회원정보는 아이디, 비번, 이름, 나이, 주민번호
		 * - 기능1 : 회원 추가
		 * - 기능2 : 회원 검색
		 * - 기능3 : 회원 정보 수정
		 * - 기능4 : 회원 삭제
		 * */
//		ArrayList<User2> userList = new ArrayList<User2>();
		HashMap<String, UserInfo> userList = new HashMap<String, UserInfo>();
		Scanner scan = new Scanner(System.in);
		int menu=-1;
		do {
			printMenu();
			try{
				menu=scan.nextInt();
				runMenu(userList, menu);
			}catch(InputMismatchException e) {
				System.out.println("정수를 입력하세요.");
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}while(menu != 5);
	}

	private static void runMenu(HashMap<String, UserInfo> userList, int menu) {
		switch(menu) {
		case 1 :
			if(insertUser(userList)) {
				System.out.println("성공적으로 등록되었습니다.");
			};
			break;
		case 2 :
			if(!searchUser(userList)) {
				System.out.println("일치하는 회원이 없습니다.");
			};
			break;
		case 3 :
			if(editUser(userList)) {
				System.out.println("회원정보를 수정하였습니다.");
			} else {
				System.out.println("회원정보 수정에 실패하였습니다.");
			};
			break;
		case 4 :
			deleteUser(userList);
			break;
		case 5 :
			System.out.println("프로그램 종료");
			break;
		default:
			System.out.println("메뉴를 다시 입력해주세요.");
		}
	}
	private static void deleteUser(HashMap<String, UserInfo> userList) {
		Scanner scan = new Scanner(System.in);
		String id =inputId();
		Set<Entry<String,UserInfo>> entrySet =userList.entrySet();
		
		for(Entry<String,UserInfo> tmp : entrySet) {
			if(tmp.getKey().equals(id)) {
				System.out.println(tmp.getKey()+" 님의 "+tmp.getValue());
				System.out.print("정말 삭제하시겠습니까?(y/n)> ");
				if(scan.next().charAt(0)=='y') {
					userList.remove(tmp.getKey());
					System.out.println("삭제되었습니다.");
				} else {
					System.out.println("취소되었습니다.");
				}
			} else {
				System.out.println("일치하는 회원의 정보가 없습니다.");
			}
		}

	}

	private static boolean editUser(HashMap<String, UserInfo> userList) {
		Scanner scan = new Scanner(System.in);
		String id =inputId();
		Set<Entry<String,UserInfo>> entrySet =userList.entrySet();
		
		for(Entry<String,UserInfo> tmp : entrySet) {
			if(tmp.getKey().equals(id)) {
				System.out.print("비밀번호 수정> ");
				tmp.getValue().setPw(scan.nextLine());
				System.out.print("이름 수정> ");
				tmp.getValue().setName(scan.nextLine());
				System.out.print("주민번호 수정> ");
				tmp.getValue().setPNum(scan.nextLine());
				System.out.print("나이 수정> ");
				tmp.getValue().setAge(scan.nextInt());
				return true;
			}
		}
		return false;
		
	}

	public static String inputId() {
		Scanner scan = new Scanner(System.in);
		System.out.print("아이디 입력> ");
		return scan.nextLine();
	}
	public static boolean searchUser(HashMap<String, UserInfo> userList) {
		Set<Entry<String,UserInfo>> entrySet = userList.entrySet();
		for(Entry<String,UserInfo> tmp: entrySet) {
			if(tmp.getKey().equals(inputId())) {
				System.out.println(tmp.getKey()+" 님의 "+tmp.getValue());
				return true;
			}
		}
		
		return false;
	}
	public static boolean insertUser(HashMap<String, UserInfo> userList) {
		Scanner scan = new Scanner(System.in);
		System.out.print("이름 입력> ");
		String name = scan.nextLine();
		System.out.print("주민번호 입력> ");
		String pNum = scan.nextLine();
		System.out.print("나이 입력> ");
		int age = scan.nextInt();
		scan.nextLine();
		System.out.print("아이디 입력> ");
		String id = scan.nextLine();
		System.out.print("비밀번호 입력> ");
		String pw = scan.nextLine();
		UserInfo user = new UserInfo(pw,name,pNum,age);
		
		if(userList.containsKey(id)) {
			System.out.println("이미 사용중인 아이디입니다.");
			return false;
		}
//		Set<String> keySet = (Set<String>)userList.keySet();
//		for(String tmp : keySet) {
//			if(tmp.equals(id)) {
//				System.out.println("이미 사용중인 아이디입니다.");
//				return false;
//			}
//		}
		//UserInfo가 같으면(이름,주민번호가 같으면) 이미 등록된 회원입니다.(iterator사용)
		userList.put(id, user);
		return true;
	}
	private static void printMenu() {
		System.out.println("1. 회원 추가");
		System.out.println("2. 회원 검색");
		System.out.println("3. 회원 정보 수정");
		System.out.println("4. 회원 삭제");
		System.out.println("5. 종료");
		System.out.print("선택할 메뉴> ");
	}
	
}
@Data
class UserInfo{
	String pw, name, pNum;
	int age;
	public UserInfo(String pw, String name, String pNum, int age) {
		this.pw = pw;
		this.name = name;
		this.pNum = pNum;
		this.age = age;
	}
	
	
	@Override
	public String toString() {
		return "비밀번호: "+pw + "\n이름: "+name+", 나이: "+age+", 주민번호: "+	pNum;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserInfo other = (UserInfo) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pNum == null) {
			if (other.pNum != null)
				return false;
		} else if (!pNum.equals(other.pNum))
			return false;
		return true;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pNum == null) ? 0 : pNum.hashCode());
		return result;
	}

	
}