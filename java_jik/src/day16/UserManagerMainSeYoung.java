package day16;
//리스트를 이용해서 할거임
import java.util.ArrayList;
import java.util.Scanner;

import lombok.Data;

public class UserManagerMainSeYoung {

	public static void main(String[] args) {
		/* 회원정보를 관리하는 프로그램을 작성하세요.
		 * - 회원정보는 아이디, 비번, 이름, 나이, 주민번호
		 * - 기능1 : 회원 추가
		 * - 기능2 : 회원 검색
		 * - 기능3 : 회원 정보 수정
		 * - 기능4 : 회원 삭제
		 * */
		ArrayList<User2> userList = new ArrayList<User2>();
		Scanner scan = new Scanner(System.in);
		int menu=-1;
		do {
			printMenu();
			menu=scan.nextInt();
			runMenu(userList, menu);
			
		}while(menu != 5);
	}

	private static void runMenu(ArrayList<User2> userList, int menu) {
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
	private static void deleteUser(ArrayList<User2> userList) {
		System.out.print("삭제할 회원의 아이디를 입력하세요> ");
		Scanner scan = new Scanner(System.in);
		User2 user = new User2(scan.nextLine());
		for(int i=0; i<userList.size(); i++) {
			if(userList.get(i).equals(user)) {
				System.out.println(userList.get(i));
				System.out.print("정말 삭제하시겠습니까?(y/n)> ");
				if(scan.next().charAt(0)=='y') {
					userList.remove(i);
					System.out.println("삭제되었습니다.");
				} else {
					System.out.println("취소되었습니다.");
				}
			} else {
				System.out.println("일치하는 회원의 정보가 없습니다.");
			}
		}
	}

	private static boolean editUser(ArrayList<User2> userList) {
		User2 user = findUser(userList);
		Scanner scan = new Scanner(System.in);
		
		for(User2 tmp : userList) {
			if(tmp.equals(user)) {
				System.out.print("비밀번호 수정> ");
				tmp.setPw(scan.nextLine());
				System.out.print("이름 수정> ");
				tmp.setName(scan.nextLine());
				System.out.print("주민번호 수정> ");
				tmp.setPNum(scan.nextLine());
				System.out.print("나이 수정> ");
				tmp.setAge(scan.nextInt());
				return true;
			}else {
				System.out.println("일치하는 회원 정보가 없습니다.");
				return false;
			}
		}
		return false;
	}

	public static User2 findUser(ArrayList<User2> userList) {
		Scanner scan = new Scanner(System.in);
		System.out.print("아이디 입력> ");
		String id = scan.nextLine();
		return new User2(id);
	}
	public static boolean searchUser(ArrayList<User2> userList) {
		User2 user = findUser(userList);
		
		for(User2 tmp: userList) {
			if(tmp.equals(user)) {
				System.out.println(tmp);
				return true;
			} 
		}
		return false;
	}
	public static boolean insertUser(ArrayList<User2> userList) {
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
		User2 user = new User2(id,pw,name,pNum,age);
		
		//userList.contains(user)로도 판별가능...
		for(int i=0; i<userList.size(); i++) {
			if(userList.get(i).equals(user)) {
				System.out.println("이미 사용중인 아이디입니다.");
				return false;
			}else if(userList.get(i).getName().equals(name) &&
					userList.get(i).getPNum().equals(pNum)) {
				System.out.println("이미 가입한 회원입니다.");
				return false;
			}
		}
		userList.add(user);
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
class User2{
	String id, pw, name, pNum;
	int age;
	public User2(String id, String pw, String name, String pNum, int age) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.pNum = pNum;
		this.age = age;
	}
	
	public User2(String id) {
		super();
		this.id = id;
	}

	@Override
	public String toString() {
		return name+" 님의 id: "+id+", 비밀번호:" + pw + "\n주민번호: "+
				pNum+", 나이: "+age;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User2 other = (User2) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

}