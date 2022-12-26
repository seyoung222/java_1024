package example.university;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class UniversityMain {
	
	static Scanner scan = new Scanner(System.in);
	static DB db;
	static ArrayList<Student> stdList = new ArrayList<Student>();
	static ArrayList<Department> dptList = new ArrayList<Department>();
	static ArrayList<Professor> pfList = new ArrayList<Professor>();
	static ArrayList<Score> scoreList = new ArrayList<Score>();
	static ArrayList<Lecture> lecList = new ArrayList<Lecture>();
	static ArrayList<Course> coList = new ArrayList<Course>();
	

	public static void main(String[] args) {
		/*
		- 학생 등록/수정/삭제/조회
		- 교수 등록/수정/삭제/조회
		- 강좌 등록/수정/삭제/조회
		- 학부 등록/수정/삭제/조회
		- 성적 등록/수정/조회
		- 수강 신청/취소
		 * */
		String url = "jdbc:mysql://localhost/university";
        String id = "root";
        String pw = "root";
        
        try {
			db = new DB(url, id, pw);
		} catch (Exception e) {
			System.out.println("DB 연결 실패");
			System.out.println("프로그램 종료");
		}
        
        int menu=-1;
        load();
        do {
        	
        	try {
        		printMenu();
        		menu = scan.nextInt();
        		scan.nextLine();
				runMenu(menu);
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }while(menu!=7);
        
	}

	private static void runMenu(int menu)throws SQLException {
		int subMenu=-1;
		switch(menu) {
		case 1: //학생
			do {
				printStudentMenu();
				subMenu = scan.nextInt();
				scan.nextLine();
				runStudentMenu(subMenu);
			}while(subMenu!=5);
			break;
		case 2: break;//교수 
		case 3: break;//강좌
		case 4: break;//학부
		case 5: break;//성적
		case 6: //수강신청
			printCourseMenu();
			int courseMenu = scan.nextInt();
			switch(courseMenu) {
			case 1:
				insertCourse();
				break;
			case 2: 
				deleteCourse();
				break;
			case 3:
				System.out.println("취소합니다.");
			default: 
				System.out.println("잘못된 메뉴입니다.");
			}
			break;
		case 7:
			System.out.println("프로그램을 종료합니다.");
			break;
		default:
			System.out.println("잘못된 메뉴입니다.");
		}
		
	}

	private static void deleteCourse() {
		System.out.print("학생 학번> ");
		String co_st_num = scan.nextLine();
		System.out.print("강의번호> ");
		int co_le_num = scan.nextInt();
		scan.nextLine();
		db.deleteCourse(co_st_num, co_le_num);
		
	}

	private static boolean insertCourse() {
		System.out.print("학생 학번> ");
		String co_st_num = scan.nextLine();
		System.out.print("강의번호> ");
		int co_le_num = scan.nextInt();
		scan.nextLine();
		System.out.print("성적유형> ");
		String co_type = scan.nextLine();
		try {
			db.insertCourse(co_st_num, co_le_num, co_type);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	private static void printCourseMenu() {
		System.out.println("수강신청 메뉴");
		System.out.println("1. 수강신청");
		System.out.println("2. 수강신청 취소");
		System.out.println("3. 종료");
		System.out.print("메뉴 선택 > ");
	}

	private static void runStudentMenu(int subMenu) throws SQLException{
		switch(subMenu) {
		case 1:
			if(insertStudent())
				System.out.println("추가 성공");
			else
				System.out.println("추가 실패");
			break;
		case 2:
			if(updateStudent())
				System.out.println("수정 성공");
			else
				System.out.println("수정 실패");
			break;
		case 3:
			if(deleteStudent())
				System.out.println("삭제 성공");
			else
				System.out.println("삭제 실패");
			break;
		case 4:
			printStudent();
			break;
		case 5:
			System.out.println("이전 메뉴로 돌아갑니다.");
			break;
		default: 
		}
	}
	private static void printStudent() {
		ArrayList<Student> list;
		try {
			list = db.selectAllStudent();
			for(Student tmp : list) {
				System.out.println(tmp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	private static boolean deleteStudent() {
		System.out.print("삭제할 학생의 학번을 입력> ");
		String st_num = scan.nextLine();
		try {
			db.deleteStudent(st_num);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	private static boolean updateStudent() {
		System.out.print("수정할 학생의 학번을 입력> ");
		String st_num = scan.nextLine();
		System.out.print("이름> ");
		String st_name = scan.nextLine();
		System.out.print("학기> ");
		int st_semester = scan.nextInt();
		scan.nextLine();
		System.out.print("학적상태> ");
		String st_state = scan.nextLine();
		System.out.print("지도교수번호> ");
		String st_pr_num = scan.nextLine();
		try {
			db.updateStudent(st_num, st_name, st_semester, st_state, st_pr_num);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	private static boolean insertStudent() {
		String st_num = scan.nextLine();
		String st_name = scan.nextLine();
		int st_semester = scan.nextInt();
		scan.nextLine();
		String st_state = scan.nextLine();
		String st_pr_num = scan.nextLine();
		try {
			db.insertStudent(st_num, st_name, st_semester, st_state, st_pr_num);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	private static void printStudentMenu() {
		System.out.println("------메뉴------");
		System.out.println("1. 학생 등록");
		System.out.println("2. 학생 수정");
		System.out.println("3. 학생 삭제");
		System.out.println("4. 학생 조회");
		System.out.println("5. 이전");
		System.out.println("---------------");
		System.out.print("메뉴 선택> ");
	}
	private static void printMenu() {
		System.out.println("---------메뉴---------");
		System.out.println("1. 학생 등록/수정/삭제/조회");
		System.out.println("2. 교수 등록/수정/삭제/조회");
		System.out.println("3. 강좌 등록/수정/삭제/조회");
		System.out.println("4. 학부 등록/수정/삭제/조회");
		System.out.println("5. 성적 등록/수정/삭제/조회");
		System.out.println("6. 수강 신청/취소");
		System.out.println("7. 프로그램 종료");
		System.out.println("---------------------");
		System.out.print("메뉴 선택> ");
	}
	private static void load() {
		try {
			stdList = db.selectAllStudent();
			pfList = db.selectAllProfessor();
			lecList = db.selectAllLecture();
			dptList = db.selectAllDepartment();
			scoreList = db.selectAllScore();
			coList = db.selectAllCourse();
			System.out.println("[로딩 완료]");
		} catch (SQLException e) {
			System.out.println("[로딩 실패]");
		}
	}

}
