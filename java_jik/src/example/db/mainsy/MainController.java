package example.db.mainsy;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import example.db.daosy.StudentDAO;
import example.db.vosy.StudentVO;

public class MainController {
	
	private SqlSession session;
	private StudentDAO studentDao;
	private Scanner scan = new Scanner(System.in);
	
	public MainController(SqlSession session) {
		this.session = session;
		studentDao = session.getMapper(StudentDAO.class);
	}

	public void run() {
		/* 학생 추가
		 * 학생 수정
		 * 학생 조회
		 * 프로그램 종료
		 * */
		int menu = -1;
		do {
			printMenu();
			menu = scan.nextInt();
			scan.nextLine();
			runMenu(menu);
		}while(menu != 4);
	}

	private void runMenu(int menu) {
		switch(menu) {
		case 1:
			insertStudent();
			break;
		case 2:
			updateStudent();
			break;
		case 3:
			printStudent();
			break;
		case 4:
			System.out.println("프로그램 종료");
			break;
		default: 
			System.out.println("잘못된 메뉴입니다.");
		}
	}
	private void printStudent() {
		ArrayList<StudentVO> list = studentDao.selectAllStudent();
		for(StudentVO tmp : list) {
			System.out.println(tmp);
		}
	}
	private void updateStudent() {
		System.out.println("수정할 학생 정보를 입력하세요.");
		System.out.print("학번> ");
		String st_num = scan.nextLine();
		System.out.print("이름> ");
		String st_name = scan.nextLine();
		System.out.print("학기> ");
		int st_semester = scan.nextInt();
		scan.nextLine();
		System.out.print("상태> ");
		String st_state = scan.nextLine();
		System.out.print("지도교수번호> ");
		String st_pr_num = scan.nextLine();
		StudentVO std = new StudentVO(st_num, st_name, st_semester, st_state, st_pr_num);
		studentDao.updateStudent(std);
		System.out.println("수정 성공");
	}
	private void insertStudent() {
		System.out.println("추가할 학생 정보를 입력하세요.");
		System.out.print("학번> ");
		String st_num = scan.nextLine();
		System.out.print("이름> ");
		String st_name = scan.nextLine();
		System.out.print("학기> ");
		int st_semester = scan.nextInt();
		scan.nextLine();
		System.out.print("상태> ");
		String st_state = scan.nextLine();
		System.out.print("지도교수번호> ");
		String st_pr_num = scan.nextLine();
		StudentVO std = new StudentVO(st_num, st_name, st_semester, st_state, st_pr_num);
		studentDao.insertStudent(std);
		System.out.println("추가 성공");
	}
	private void printMenu() {
		System.out.println("메뉴");
		System.out.println("1. 학생 추가");
		System.out.println("2. 학생 수정");
		System.out.println("3. 학생 조회");
		System.out.println("4. 프로그램 종료");
		System.out.print("메뉴 선택> ");
	}
}
