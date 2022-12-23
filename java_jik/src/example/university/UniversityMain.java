package example.university;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class UniversityMain {
	
	static Scanner scan = new Scanner(System.in);
	static DB db;
	static ArrayList<Student> stdList = new ArrayList<Student>();
	static ArrayList<Department> dptList = new ArrayList<Department>();
	static ArrayList<Professor> prfList = new ArrayList<Professor>();
	static ArrayList<Score> scoreList = new ArrayList<Score>();
	static ArrayList<Lecture> lecList = new ArrayList<Lecture>();
	

	public static void main(String[] args) {
		/*
		- 학부 등록/수정/삭제/조회
		- 강좌 등록/수정/삭제/조회
		- 학생 등록/수정/삭제/조회
		- 교수 등록/수정/삭제/조회
		- 수강 신청/취소
		- 성적 등록/수정/조회
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
        	printMenu();
        	menu = scan.nextInt();
        	scan.nextLine();
        	runMenu(menu);
        }while(menu!=7);
        
	}


	private static void runMenu(int menu) {
		// TODO Auto-generated method stub
		
	}


	private static void printMenu() {
		// TODO Auto-generated method stub
		
	}


	private static void load() {
		try {
			stdList = db.selectAllStudent();
			dptList = db.selectAllDepartment();
			prfList = db.selectAllProfessor();
			scoreList = db.selectAllScore();
			lecList = db.selectAllLecture();
		} catch (SQLException e) {
			System.out.println("로딩 실패");
		}
	}

}
