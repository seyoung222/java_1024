package day16;

import java.util.ArrayList;
import java.util.Scanner;

public class UserManagerMain {

	public static void main(String[] args) {
		/* 회원정보를 관리하는 프로그램을 작성하세요.
		 * - 회원정보는 아이디, 비번, 이름, 나이, 주민번호
		 * - 기능1 : 회원 추가
		 * - 기능2 : 회원 검색
		 * - 기능3 : 회원 정보 수정
		 * - 기능4 : 회원 삭제
		 * */
		int menu=-1;
		Scanner scan = new Scanner(System.in);
		ArrayList<Member1> list = new ArrayList<Member1>();//회원리스트
		do {
			//메뉴출력
			printMenu();
			//메뉴선택(입력)
			menu=scan.nextInt();
			//선택된 메뉴에 따른 기능 실행
			switch(menu){
				case 1: 
					if(addMember(list)) {
						printStr("입력한 회원정보를 추가했습니다.");
					}else{
						printStr("입력한 아이디가 이미 존재합니다.");
					};
					break;
				case 2: 
					Member1 searchTmp = searchMember(list);
					if(searchTmp==null) {
						printStr("아이디 또는 비밀번호가 잘못되었습니다.");
					}else{
						System.out.println(searchTmp);
					};
					break;
				case 3: 
					//수정할 회원 아이디와 비번을 입력하여 일치하는 회원정보를 가져옴
					Member1 updateTmp = searchMember(list);
					if(updateMember(list,updateTmp)) {
						printStr("회원정보 수정이 완료되었습니다.");
					}else {
						printStr("아이디 또는 비밀번호가 잘못되었습니다.");
					}
					break;
				case 4: 
					//삭제할 회원 아이디와 비번을 입력하여 일치하는 회원정보를 가져옴
					Member1 deleteTmp = searchMember(list);
					//일치하는 회원정보가 있으면 삭제(메소드 만들필요가 없음. . . . )
					
//					if(deleteTmp != null) {list.remove(deleteTmp);} //도 되지만
					if(list.remove(deleteTmp)) {//객체넣었을때 함수자체가 삭제성공하면 true값 리턴함
						printStr("삭제가 완료되었습니다.");
					}else {
						printStr("아이디 또는 비밀번호가 잘못되었습니다");
					}
					break;
				case 5: 
					System.out.println("프로그램 종료"); 
					break;
				default:
					System.out.println("잘못된 메뉴");
			}
		}while(menu!=5);
	}
	public static boolean updateMember(ArrayList<Member1>list, Member1 updateTmp) {
		//일치하지 않으면 건너뜀
		if(updateTmp==null) {
			return false;
		}
		
		Scanner scan = new Scanner(System.in);
		//일치하는 회원정보가 있으면 비번, 이름, 주민번호, 나이를 입력받음
		System.out.println("수정할 회원정보를 입력합니다.");
		System.out.print("비번: ");
		String pw = scan.next();
		System.out.print("이름: ");
		String name = scan.next();
		System.out.print("주민번호: ");
		String residentNumber = scan.next();
		System.out.print("나이: ");
		int age = scan.nextInt();
		//입력받은 회원정보를 이용하여 updateTmp를 수정
		updateTmp.update(pw,name,residentNumber,age);
		//Member클래스에 새로운 메소드를 생성했음
		
		return true;
	}
	
	private static Member1 searchMember(ArrayList<Member1> list) {
		Scanner scan = new Scanner(System.in);
		//아이디입력
		System.out.println("검색할 회원정보를 입력하세요.");
		System.out.print("아이디: ");
		String id = scan.next();
		//비번입력
		System.out.print("비번: ");
		String pw = scan.next();
		//아이디,비번을 이용하여 객체를 만듦
		Member1 member = new Member1(id,pw);
		//회원리스트에서 아이디가 같은 회원정보를 가져옴
		int index=list.indexOf(member);
		if(index==-1) {
			System.out.println("해당 회원 정보가 없습니다.");
			return null;
		}
		Member1 tmp=list.get(index);
		//가져온 회원정보의 비번과 입력한 비번을 비교하여 다르면 종료
		if(!tmp.getPw().equals(member.getPw())) {
			System.out.println("비밀번호가 다릅니다.");
			return null;
		}
		//같으면 회원정보를 보여줌
		return tmp;
	}
	public static void printMenu() {
		System.out.println("1. 회원 추가");
		System.out.println("2. 회원 검색");
		System.out.println("3. 회원 정보 수정");
		System.out.println("4. 회원 삭제");
		System.out.println("5. 종료");
		System.out.print("선택할 메뉴> ");
	}
	public static boolean addMember(ArrayList<Member1>list) {
		Scanner scan = new Scanner(System.in);
		//회원정보 입력
		//아이디, 비번, 이름, 주민번호, 나이순으로
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
		//입력한 정보를 이용한 객체를 생성(회원리스트의 비교 및 추가)
		Member1 member = new Member1(id, pw, name, residentNumber, age);
		//회원리스트에 이미 가입된 아이디면 안내문구 출력후 종료
		// = 회원아이디가 같은 회원이 이미 있으면
		if(list.contains(member)) {
			return false;
		}
		//아니면 회원리스트에 member객체를 추가
		list.add(member);
		return true;
	}
	public static void printStr(String str) {
		System.out.println("---------------");
		System.out.println(str);
		System.out.println("---------------");
		
	}
}
