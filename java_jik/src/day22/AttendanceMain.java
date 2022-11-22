package day22;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class AttendanceMain {
	
	static Scanner scan = new Scanner(System.in);
	
	//학생(Student) 클래스 : 이름, 생년월일
	//학생 일지(StudentLog) 클래스: 이름, 생년월일, 출결
	//일지(Log) 클래스: 날짜, 학생일지들(이름, 생년월일, 출결)
	//출석부(Attendance) 클래스: 학생들, 일지들
	/* 출석부 관리 프로그램
		 * 1. 학생 등록
		 *  - 이름과 생년월일
		 * 2. 학생 수정
		 *  - 이름과 생년월일로 검색하여 이름과 생년월일을 수정
		 * 3. 학생 삭제
		 *  - 이름과 생년월일로 검색하여 삭제
		 * 4. 학생 출결 확인
		 *  - 이름과 생년월일로 검색해서 등록된 해당 학생의 모든 출결을 확인
		 *  - 2022-11-22 : O
		 * 5. 출석 확인
		 *  - 날짜 입력 : 2022-11-22
		 *  - 출석 : 0, 지각 : /, 조퇴 : \, 결석 : X
		 *  - 날짜입력 후, 등록된 이름 기준으로 이름 자동으로 뜨고 출석여부만 적음
		 *  홍길동 출석현황 : X
		 *  고길동 출석현황 : O
		 *  임꺽정 출석현황 : /
		 * 6. 출석 수정
		 *  - 날짜 입력
		 *  - 수정할 학생의 이름과 생년월일 입력
		 * 7. 출석 삭제
		 *  - 날짜 입력하여 해당 날짜 출석 삭제
		 * 8. 날짜별 출석 확인
		 *  - 날짜 입력하여 해당날짜의 모든 학생의 출석 여부를 확인
		 * 9. 프로그램 종료
		 * */
	public static void main(String[] args) {
		
		int menu=-1;
		Attendance attendance = new Attendance();
		do {
			printMenu();
			menu = scan.nextInt();
			scan.nextLine();
			runMenu(menu,attendance);
		}while(menu!=3);
	}
	
	private static void runMenu(int menu, Attendance attendance) {
		ArrayList<Student> stds=attendance.getStds();
		ArrayList<Log> logs = attendance.getLogs();
		switch(menu) {
		case 1:
			ManagementStudent(stds);
			break;
		case 2:
//			ManagementLogs(logs, stds);
			break;
		case 3:
			System.out.println("프로그램 종료");
			break;
		default:
			System.out.println("잘못된 메뉴입니다.");
		}
	}
	
	private static void ManagementStudent(ArrayList<Student> stds) {
		printSubMenu(1);
		int subMenu = scan.nextInt();
		scan.nextLine();
		switch(subMenu) {
		case 1: 	addStudent(stds);		break;
		case 2:  	updateStudent(stds);	break;
		case 3:		deleteStudent(stds); 	break;
		case 4: //학생 출결 확인 - 이름과 생년월일로 검색해서 등록된 해당 학생의 모든 출결을 확인
			break;
		case 5:		System.out.println("취소합니다.");		break;
		default:	System.out.println("잘못된 메뉴입니다.");
		}
	}

	private static void addStudent(ArrayList<Student> stds) {
		if(stds==null)
			throw new RuntimeException("예외발생: 학생 정보를 관리하는 리스트가 없습니다.");
		System.out.print("이름 입력> ");
		String name = scan.nextLine();
		System.out.print("생일 입력> ");
		String birth = scan.nextLine();
		Student std = new Student(name, birth);
		if(stds.indexOf(std) != -1) {
			System.out.println("학생 정보를 추가하지 못했습니다.");
			return;
		}
		stds.add(std);
		System.out.println("학생 정보를 추가했습니다.");
	}
	private static void updateStudent(ArrayList<Student> stds) {
		if(stds==null)
			throw new RuntimeException("예외발생: 학생 정보를 관리하는 리스트가 없습니다.");
		System.out.print("이름 입력> ");
		String name = scan.nextLine();
		System.out.print("생일 입력> ");
		String birth = scan.nextLine();
		Student std = new Student(name, birth);
		int index = stds.indexOf(std);
		if( index == -1) {
			System.out.println("입력 정보와 일치하는 학생이 없습니다.");
			return;
		}
		System.out.print("수정할 이름 입력> ");
		name = scan.nextLine();
		System.out.print("수정할 생일 입력> ");
		birth = scan.nextLine();
		std = new Student(name, birth);
		stds.set(index, std);
		System.out.println("학생 정보를 수정했습니다.");
	}
	private static void deleteStudent(ArrayList<Student> stds) {
		if(stds==null)
			throw new RuntimeException("예외발생: 학생 정보를 관리하는 리스트가 없습니다.");
		System.out.print("이름 입력> ");
		String name = scan.nextLine();
		System.out.print("생일 입력> ");
		String birth = scan.nextLine();
		Student std = new Student(name, birth);
		int index = stds.indexOf(std);
		if( index == -1) {
			System.out.println("입력 정보와 일치하는 학생이 없습니다.");
			return;
		}
		stds.remove(index);
		System.out.println("학생 정보를 삭제했습니다.");
	}
	private static void printMenu() {
		System.out.println("=======메뉴========");
		System.out.println("1. 학생 관리");
		System.out.println("2. 출석 관리");
		System.out.println("3. 종료");
		System.out.println("==================");
		System.out.print("메뉴 선택> ");
	}
	private static void printSubMenu(int num) {
		switch(num) {
		case 1: 
			System.out.println("=======메뉴========");
			System.out.println("1. 학생 등록");
			System.out.println("2. 학생 수정");
			System.out.println("3. 학생 삭제");
			System.out.println("4. 학생 출결 확인");
			System.out.println("5. 취소");
			System.out.println("==================");
			System.out.print("메뉴 선택> ");
			break;
		case 2:
			System.out.println("=======메뉴========");
			System.out.println("1. 출석 체크 - 전체");
			System.out.println("2. 출석 확인 - 개별");
			System.out.println("3. 출석 확인 - 닐찌별");
			System.out.println("4. 출석 수정");
			System.out.println("5. 출석 삭제");
			System.out.println("6. 취소");
			System.out.println("==================");
			System.out.print("메뉴 선택> ");
			break;
		case 3: 
			System.out.println("프로그램 종료");
			break;
		default: 
			System.out.println("잘못된 메뉴입니다.");
		}
	}
	
}

