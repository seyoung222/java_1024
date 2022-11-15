package day17;

import java.util.HashMap;
import java.util.Scanner;

public class UserManagerMapMain {
	
	private static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		int menu = 0;
		HashMap<String, Member> map = new HashMap<String,Member>();
		do {
			System.out.println("1. 회원 추가");
			System.out.println("2. 회원 검색");
			System.out.println("3. 회원 정보 수정");
			System.out.println("4. 회원 삭제");
			System.out.println("5. 종료");
			System.out.print("선택할 메뉴> ");
			
			menu= scan.nextInt();
			switch(menu) {
			case 1:
				if(addMember(map)) {
					System.out.println("회원정보가 추가되었습니다.");
				}else{
					System.out.println("이미 가입된 아이디입니다.");
				}
				break;
			case 2:
				if(!searchMember(map)) {
					System.out.println("아이디 또는 비밀번호가 일치하지 않습니다.");
				}
				break;
			case 3:
				if(updateMember(map)) {
					System.out.println("회원정보를 수정하였습니다.");
				}else {
					System.out.println("아이디 또는 비밀번호가 일치하지 않습니다.");
				}
				break;
			case 4:
				if(deleteMember(map)) {
					System.out.println("회원 정보를 삭제했습니다.");
				}else {
					System.out.println("아이디 또는 비밀번호가 일치하지 않습니다.");
				}
				break;
			case 5:
				System.out.println("프로그램 종료");
				break;
			default:
				System.out.println("잘못된 메뉴");
			}
		}while(menu != 5);
	}
	private static boolean deleteMember(HashMap<String, Member> map) {
		System.out.println("삭제할 회원정보를 입력하세요.");
		System.out.print("아이디: ");
		String id = scan.next();
		System.out.print("비번: ");
		String pw = scan.next();
		
		Member deleteTmp = map.get(id);
		if(deleteTmp==null || !deleteTmp.getPw().equals(pw)) {
			return false;
		}
		map.remove(id);
		return true;
	}
	public static boolean updateMember(HashMap<String,Member>map) {
		System.out.println("검색할 회원정보를 입력하세요.");
		System.out.print("아이디: ");
		String id = scan.next();
		System.out.print("비번: ");
		String pw = scan.next();
		
		Member updateTmp = map.get(id);
		if(updateTmp == null) {
			return false;
		}
		if(!updateTmp.getPw().equals(pw)) {
			return false;
		}
		
		System.out.println("수정할 회원정보를 입력합니다.");
		System.out.print("비번: ");
		String newPw = scan.next();
		System.out.print("이름: ");
		String name = scan.next();
		System.out.print("주민번호: ");
		String residentNumber = scan.next();
		System.out.print("나이: ");
		int age = scan.nextInt();
		updateTmp.update(newPw, name, residentNumber, age);
		return true;
	}
	public static boolean searchMember(HashMap<String,Member>map) {
		System.out.println("검색할 회원정보를 입력하세요.");
		System.out.print("아이디: ");
		String id = scan.next();
		System.out.print("비번: ");
		String pw = scan.next();
		//아이디를 이용하여 회원정보를 가져옴
		Member searchTmp = map.get(id);
		//일치하는 회원정보가 없으면 건너
		if(searchTmp==null) {
			return false;
		}
		//가져온 회원정보에서 비번이 같은지 확인, 다르면 건너뜀
		if(!searchTmp.getPw().equals(pw)) {
			return false;
		}
		//같으면 회원정보 출력
		System.out.println(id+" : "+searchTmp);
		return true;
	}
	public static boolean addMember(HashMap<String,Member>map) {
		if(map==null) {
			throw new RuntimeException("예외발생: Map이 null입니다.");
		}
		System.out.println("회원정보 입력");
		System.out.print("아이디: ");
		String id = scan.next();
		System.out.print("비번: ");
		String pw = scan.next();
		System.out.print("이름: ");
		String name = scan.next();
		System.out.print("주민번호: ");
		String residentNumber = scan.next();
		System.out.print("나이: ");
		int age = scan.nextInt();
		
		//map에 있는지 확인
		Member addTmp = map.get(id);
		//있으면 건너뜀
		if(addTmp!=null) { //if(map.get(id)!=null) ~로 써도 됨~
			return false;
		}
		//없으면 추가
		map.put(id, new Member(pw,name,residentNumber,age));
		return true;
	}
}
