package db.day1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import lombok.Data;


public class DBTest1_2 {
	
	static Scanner scan = new Scanner(System.in);
	static Statement stmt = null;
	static ResultSet rs = null;
	static PreparedStatement pstmt = null;
	
	public static void main(String[] args) {
	//DB를 이용하여 학생 정보를 추가하고 조회하는 프로그램을 작성하세요. - 내가 한거
		
		Connection con = null;
		String url = "jdbc:mysql://localhost/university";
		String id = "root";
		String pw = "root";
		ArrayList<Student2> list = null;
		
		//연결
		con = connect(url,id,pw);
		
		int menu=-1;
		do {
			
			try {
				printMenu();
				menu = scan.nextInt();
				run(menu,con);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch(InputMismatchException e) {
				System.out.println("정수를 입력하세요.");
			}
		}while(menu!=3);
		
		//연결해제
		connectClose(con);
	}
	public static Connection connect(String url, String id, String pw) {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,id,pw);
			System.out.println("연결 성공");
		}catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		}catch(SQLException e) {
			System.out.println("에러: "+ e);
		}
		return con;
	}
	public static void connectClose(Connection con) {
		try {
			if(con!=null && !con.isClosed()) {
				con.close();
				System.out.println("연결 해제");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void run(int menu, Connection con) throws SQLException {
		scan.nextLine();
		stmt = con.createStatement();
		switch(menu) {
		case 1: // 학생 추가
			insertStudent(con);
			break;
		case 2: // 학생 조회
			selectAllStudent();
			break;
		case 3:
			System.out.println("프로그램을 종료합니다.");
			break;
		default:
			System.out.println("잘못된 메뉴입니다.");	
		}
		
	}

	private static void insertStudent(Connection con) throws SQLException {
		System.out.print("학번> ");
		String st_num = scan.next();
		System.out.print("이름> ");
		String st_name = scan.next();
		System.out.print("학기> ");
		int st_semester = scan.nextInt();
		scan.nextLine();
		System.out.print("학적상태> ");
		String st_state = scan.next();
		System.out.print("지도교수번호> ");
		String st_pr_num = scan.next();
		
		String sql = "insert into student(st_num,st_name,st_semester,st_state,st_pr_num)"+
				"values(?, ?, ?, ?, ?)";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, st_num);
		pstmt = con.prepareStatement(sql);
		pstmt.setString(2, st_name);
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(3, st_semester);
		pstmt = con.prepareStatement(sql);
		pstmt.setString(4, st_state);
		pstmt = con.prepareStatement(sql);
		pstmt.setString(5, st_pr_num);
		int count = pstmt.executeUpdate();
		if(count == 0) 
			System.out.println("추가 성공");
		else
			System.out.println("추가 실패");
		
	}
	private static void selectAllStudent() throws SQLException {
		String sql = "select * from student";
		rs = stmt.executeQuery(sql);
		ArrayList<Student2> list = new ArrayList<Student2>();
		while(rs.next()) {
			String st_num = rs.getString(1);
			String st_name = rs.getString(2);
			int st_semester = rs.getInt(3);
			String st_state = rs.getString(4);
			String st_pr_num = rs.getString(5);
			Student2 std = new Student2(st_num, st_name, st_semester, st_state, st_pr_num);
			list.add(std);
		}
		for(int i=0; i<list.size(); i++) {
			System.out.print((i+1)+". ");
			System.out.println(list.get(i));
		}
	}
	private static void printMenu() {
		System.out.println("----메뉴----");
		System.out.println("1. 학생 추가");
		System.out.println("2. 학생 조회");
		System.out.println("3. 프로그램 종료");
		System.out.print("메뉴> ");
	}

}
@Data
class Student2{
	private String st_num;
	private String st_name;
	private int st_semester;
	private String st_state;
	private String st_pr_num;
	public Student2(String st_num, String st_name, int st_semester, String st_state, String st_pr_num) {
		this.st_num = st_num;
		this.st_name = st_name;
		this.st_semester = st_semester;
		this.st_state = st_state;
		this.st_pr_num = st_pr_num;
	}
	@Override
	public String toString() {
		return st_num + " " + st_name + " " + st_semester + " "+st_state+" "+st_pr_num;
	}
	
}