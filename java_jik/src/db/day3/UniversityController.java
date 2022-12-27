package db.day3;

import java.util.Scanner;

import db.day3.service.*;

public class UniversityController {
	/*
	 * 컨트롤러	-> 학생서비스	-> 학생 다오/매퍼
	 * 			-> 교수서비스	-> 교수 다오/매퍼
	 * 			-> 수강서비스	-> 수강 다오/매퍼
	 * 			-> 성적서비스	-> 성적 다오/매퍼
	 * !! 리스트 안만드는 이유: 조회할때마다 DB에 접근해서 가져오려고
	 * 		=> 코드가 간결해지고 최종적으로 웹에서 이렇게 동작함
	*/
	
	private Scanner scan = new Scanner(System.in);
	private DBConnector dbConnector;
	private StudentServiceImp studentService;
	private ProfessorServiceImp professorService;
	private CourseServiceImp courseService;
	
	{
		String url = "jdbc:mysql://localhost/university";
		String id = "root", pw = "root";
		dbConnector = new DBConnector(url,id,pw);
		studentService = new StudentServiceImp(dbConnector);
		professorService = new ProfessorServiceImp(dbConnector);
		courseService = new CourseServiceImp(dbConnector);
	}
	
	public void run() {
		int menu = -1;
		int exit = 5;
		do {
			printMenu();
			menu = scan.nextInt();
			scan.nextLine();
			runMenu(menu);
		}while(menu != exit);
		dbConnector.close();
	}
	
	private void runMenu(int menu) {
		int submenu = -1;
		switch(menu) {
		case 1:
			printStudentMenu();
			submenu = scan.nextInt();
			scan.nextLine();
			runStudentMenu(submenu);
			break;
		case 2:
			printProfessorMenu();
			submenu = scan.nextInt();
			scan.nextLine();
			runProfessorMenu(submenu);
			break;
		case 3:
			printCourseMenu();
			submenu = scan.nextInt();
			scan.nextLine();
			runCourseMenu(submenu);
			break;
		case 5: 
			System.out.println("프로그램 종료");
			break;
		default:
			System.out.println("잘못된 메뉴");
		}
	}
	private void runCourseMenu(int submenu) {
		switch(submenu) {
		case 1:
			courseService.insertLecture();
			break;
		case 2:
			courseService.updateLecture();
			break;
		case 3:
			courseService.deleteLecture();
			break;
		case 4:
			courseService.insertCourse();
			break;
		case 5:
			courseService.deleteCourse();
			break;
		case 6:
			System.out.println("취소합니다.");
			break;
		default:
			System.out.println("잘못된 메뉴입니다.");
		}
		
	}
	private void runProfessorMenu(int submenu) {
		switch(submenu) {
		case 1:
			professorService.insertProfessor();
			break;
		case 2:
			professorService.updateProfessor();
			break;
		case 3:
			professorService.updateAdvisor();
			break;
		case 4:
			System.out.println("취소합니다.");
			break;
		default:
			System.out.println("잘못된 메뉴입니다.");
		}
		
	}

	private void runStudentMenu(int submenu) {
		switch(submenu) {
		case 1:
			studentService.insertStudent();
			break;
		case 2:
			studentService.updateStudent();
			break;
		case 3:
			System.out.println("취소합니다.");
			break;
		default:
			System.out.println("잘못된 메뉴입니다.");
		}
	}
	private void printCourseMenu() {
		System.out.println("---수강 관리 메뉴---");
		System.out.println("1. 강좌 등록");
		System.out.println("2. 강좌 수정");
		System.out.println("3. 강좌 삭제");
		System.out.println("4. 수강 신청");
		System.out.println("5. 수강 취소");
		System.out.println("6. 취소");
		System.out.print("메뉴 선택> ");
		
	}
	private void printProfessorMenu() {
		System.out.println("---교수 관리 메뉴---");
		System.out.println("1. 교수 추가");
		System.out.println("2. 교수 수정");
		System.out.println("3. 지도학생 등록");
		System.out.println("4. 취소");
		System.out.print("메뉴 선택> ");
	}
	private void printStudentMenu() {
		System.out.println("---학생 관리 메뉴---");
		System.out.println("1. 학생 추가");
		System.out.println("2. 학생 수정");
		System.out.println("3. 취소");
		System.out.print("메뉴 선택> ");
	}
	private void printMenu() {
		System.out.println("-----전체 메뉴-----");
		System.out.println("1. 학생 관리");
		System.out.println("2. 교수 관리");
		System.out.println("3. 수강 관리");
		System.out.println("5. 프로그램 종료");
		System.out.print("메뉴 선택> ");
	}

}
