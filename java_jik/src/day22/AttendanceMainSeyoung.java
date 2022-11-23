package day22;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class AttendanceMainSeyoung {
	
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
		case 1: //학생 등록
			if(insertStudent(stds)) 
				System.out.println("학생 정보를 수정했습니다");
			else
				System.out.println("학생 정보 수정에 실패했습니다.");
			break;
		case 2: //학생 수정
			if(updateStudent(stds)) 
				System.out.println("학생 정보를 수정했습니다");
			else
				System.out.println("학생 정보 수정에 실패했습니다.");
			break;
		case 3: //학생 삭제
			if(deleteStudent(stds)) 
				System.out.println("학생 정보를 삭제했습니다");
			else
				System.out.println("학생 정보 삭제에 실패했습니다.");
			break;
		case 4: //학생 출결 확인 - 이름과 생년월일로 검색해서 등록된 해당 학생의 모든 출결을 확인
			if(logs==null) {
				System.out.println("출석부가 비었습니다.");
				break;
			}
			Student searchStd = inputStudent();
			//검색한 학생의 출결정보가 있는지 확인
			//해당학생과 일치하는 정보가 하나도 없으면 검색결과가 없다고 출력
			if(logs.contains(searchStd)==false) {
				System.out.println("일치하는 학생이 없습니다.");
				break;
			}
			//Log에서 날짜와 학생일지 중 해당학생의 출결정보만을 출력
			for(int i=0; i<logs.size(); i++) {
				for(int j=0; j<logs.get(i).getSlogs().size(); j++) {
					if(logs.get(i).getSlogs().get(j).equals(searchStd)) {
						System.out.print(logs.get(i).getDate()+" : "); 
						System.out.println(logs.get(i).getSlogs().get(j).getState()); 
					break;
					}
				}
			}
			break;
		case 5: //출석 확인
			insertAttendance(stds,logs);
			break;
		case 6: //출석 수정
			updateAttendance(logs);
			break;
		case 7: //출석 삭제
			deleteAttendance(logs);
			break;
		case 8: //날짜별 출석 확인
			
			break;
		case 9:
			System.out.println("프로그램 종료");
			break;
		default:
			System.out.println("잘못된 메뉴입니다.");
		}
		
	}
	private static void deleteAttendance(ArrayList<Log> logs) {
		System.out.print("출석을 수정할 날짜> ");
		String date = scan.nextLine();
		int dateIndex = logs.indexOf(date);
		if(dateIndex == -1) {
			System.out.println("해당 날짜 출석 정보는 아직 입력되지 않았습니다.");
			return;
		}
		System.out.print("삭제할 학생 이름> ");
		String name = scan.nextLine();
		System.out.print("삭제할 학생 생일> ");
		String birth = scan.nextLine();
		int stdIndex = logs.get(dateIndex).getSlogs().indexOf(new Student(name,birth));
		if(stdIndex == -1) {
			System.out.println("입력 정보와 일치하는 학생이 없습니다.");
			return;
		}
		logs.get(dateIndex).getSlogs().remove(stdIndex);
		
	}
	private static void updateAttendance(ArrayList<Log> logs) {
		System.out.print("출석을 수정할 날짜> ");
		String date = scan.nextLine();
		int dateIndex = logs.indexOf(date);
		if(dateIndex == -1) {
			System.out.println("해당 날짜 출석 정보는 아직 입력되지 않았습니다.");
			return;
		}
		System.out.print("수정할 학생 이름> ");
		String name = scan.nextLine();
		System.out.print("수정할 학생 생일> ");
		String birth = scan.nextLine();
		int stdIndex = logs.get(dateIndex).getSlogs().indexOf(new Student(name,birth));
		if(stdIndex == -1) {
			System.out.println("입력 정보와 일치하는 학생이 없습니다.");
			return;
		}
		System.out.print("수정할 출석 상태");
		String state = scan.nextLine();
		logs.get(dateIndex).getSlogs().get(stdIndex).setState(state);
		
	}

	private static void insertAttendance(ArrayList<Student> stds, ArrayList<Log> logs) {
		System.out.print("날짜 입력> ");
		String date = scan.nextLine();
		for(Log tmp : logs) {
			if(tmp.getDate().equals(date)==true) {
				System.out.println("출석 입력이 이미 완료된 날짜입니다.");
				return;
			}
		}
		Log log = new Log(stds,date);
		for(StudentLog slog : log.getSlogs()) {
			System.out.print(slog.getStd().getName()+" 출석현황 : ");
			slog.setState(scan.nextLine());
		}
		//한 날짜의 출석 정보를 모두 입력받아 일지 리스트에 넣음
		logs.add(log);
	}

	private static boolean deleteStudent(ArrayList<Student> list) {
		System.out.println("삭제할 학생 정보를 검색합니다.");
		Student searchStudent = inputStudent();
		if(searchStudent==null)
			return false;
		int index =list.indexOf(searchStudent);
		if(index==-1) {
			System.out.println("일치하는 학생이 없습니다");
			return false;
		}
		list.remove(index);
		return true;
	}
	private static boolean updateStudent(ArrayList<Student> list) {
		System.out.println("수정할 학생 정보를 검색합니다.");
		Student searchStudent = inputStudent();
		if(searchStudent==null)
			return false;
		int index =list.indexOf(searchStudent);
		if(index==-1) {
			System.out.println("일치하는 학생이 없습니다");
			return false;
		}
		Student updateStudent = inputStudent();
		if(updateStudent==null)
			return false;
		list.set(index, updateStudent);
		return true;
	}
	private static boolean insertStudent(ArrayList<Student> list) {
		Student s = inputStudent();
		if(s==null)
			return false;
		if(list.contains(s)) {
			System.out.println("이미 등록된 학생입니다.");
			return false;
		}
		list.add(s);
		return true;
	}
	private static Student inputStudent() {
		System.out.print("이름> ");
		String name = scan.next();
		System.out.print("생년월일(yyyy-mm-dd)> ");
		String birth = scan.next();
		if(!checkBirthRegex(birth)) {
			System.out.println("생년월일 양식이 틀렸습니다.");
			return null;
		}
		return new Student(name,birth);
	}
	private static void printMenu() {
		System.out.println("=======메뉴========");
		System.out.println("1. 학생 등록");
		System.out.println("2. 학생 수정");
		System.out.println("3. 학생 삭제");
		System.out.println("4. 학생 출결 확인");
		System.out.println("5. 출석 확인");
		System.out.println("6. 출석 수정");
		System.out.println("7. 출석 삭제");
		System.out.println("8. 날짜별 출결 확인");
		System.out.println("9. 종료");
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
	public static boolean checkBirthRegex(String birth){
		String regex = "\\d{4}-\\d{2}-\\d{2}";
		return Pattern.matches(regex, birth);
	}
}

