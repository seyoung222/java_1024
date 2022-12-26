package example.university;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DB {
	Scanner scan = new Scanner(System.in);
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public DB(String url,String id,String pw) throws Exception {
		connect(url,id,pw);
		stmt = con.createStatement();
	}
	private void connect(String url,String id,String pw) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, id, pw);
        System.out.println("[DB 연결 성공]");
	}
	public ArrayList<Student> selectAllStudent() throws SQLException{
		String sql = "select st_num, st_name, st_semester, st_state, st_pr_num from student";
		rs = stmt.executeQuery(sql); 
		ArrayList<Student> list = new ArrayList<Student>();
		while(rs.next()) {
			String st_num = rs.getString(1);
			String st_name = rs.getString(2);
			int st_semester = rs.getInt(3);
			String st_state = rs.getString(4);
			String st_pr_num = rs.getString(5);
			Student std = new Student(st_num, st_name, st_semester, st_state, st_pr_num);
			list.add(std);
		}
		return list;
	}
	public void insertStudent(String st_num,String st_name,int st_semester,String st_state,
			String st_pr_num) throws SQLException {
		String sql = "insert into student(st_num, st_name,st_semester, st_state, st_pr_num)"+
				"values(?, ?, ?, ?, ?)";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, st_num);
		pstmt.setString(2, st_name);
		pstmt.setInt(3, st_semester);
		pstmt.setString(4, st_state);
		pstmt.setString(5, st_pr_num);
		int count = pstmt.executeUpdate();
		if(count == 0) {
			throw new RuntimeException("추가 실패");
		}
	}
	public void updateStudent(String st_num,String st_name,int st_semester,String st_state,
			String st_pr_num) throws SQLException{
		String sql = "update student set st_name = ?, st_semester = ?, st_state = ?"
				+ ", st_pr_num = ? where st_num = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, st_name);
		pstmt.setInt(2, st_semester);
		pstmt.setString(3, st_state);
		pstmt.setString(4, st_pr_num);
		pstmt.setString(5, st_num);
		int count = pstmt.executeUpdate();
		if(count == 0) {
			throw new RuntimeException("수정 실패");
		}
	}
	public void deleteStudent(String st_num) throws SQLException{
		String sql = "delete from student where st_num = ?";		
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, st_num);
		int count = pstmt.executeUpdate();
		if(count == 0) {
			throw new RuntimeException("삭제 실패");
		}
	}
	
	
	
	public ArrayList<Professor> selectAllProfessor() throws SQLException{
		String sql = "select pr_num, pr_name, pr_state, pr_de_num, pr_tel from professor";
		rs = stmt.executeQuery(sql); 
		ArrayList<Professor> list = new ArrayList<Professor>();
		while(rs.next()) {
			String pr_num = rs.getString(1);
			String pr_name = rs.getString(2);
			String pr_state = rs.getString(3);
			String pr_de_num = rs.getString(4);
			String pr_tel = rs.getString(5);
			Professor pf = new Professor(pr_num, pr_name, pr_state, pr_de_num, pr_tel);
			list.add(pf);
		}
		return list;
	}
	
	
	
	public ArrayList<Lecture> selectAllLecture() throws SQLException{
		String sql = "select le_num, le_name, le_schedule, le_point, le_class, le_year, "
				+ "le_term, le_pr_num from lecture";
		rs = stmt.executeQuery(sql); 
		ArrayList<Lecture> list = new ArrayList<Lecture>();
		while(rs.next()) {
			int le_num = rs.getInt(1);
			String le_name = rs.getString(2);
			String le_schedule = rs.getString(3);
			int le_point = rs.getInt(4);
			int le_class = rs.getInt(5);
			int le_year = rs.getInt(6);
			String le_term = rs.getString(7);
			String le_pr_num = rs.getString(8);
			Lecture lec = new Lecture(le_num,le_name,le_schedule,le_point, le_class, 
					le_year,le_term, le_pr_num);
			list.add(lec);
		}
		return list;
	}
	
	public ArrayList<Department> selectAllDepartment() throws SQLException{
		String sql = "select de_num, de_name, de_address, de_tel, de_pr_num from department";
		rs = stmt.executeQuery(sql);
		ArrayList<Department> list = new ArrayList<Department>();
		while(rs.next()) {
			String de_num = rs.getString(1);
			String de_name = rs.getString(2);
			String de_address = rs.getString(3);
			String de_tel = rs.getString(4);
			String de_pr_num = rs.getString(5);
			Department dpt = new Department(de_num, de_name, de_address, de_tel, de_pr_num);
			list.add(dpt);
		}
		return list;
	}
	
	
	
	public ArrayList<Score> selectAllScore() throws SQLException{
		String sql = "select sc_num, sc_mid, sc_final, sc_homework, sc_attendance, sc_total,"
				+ " sc_co_num from score";
		rs = stmt.executeQuery(sql);
		ArrayList<Score> list = new ArrayList<Score>();
		while(rs.next()) {
			int sc_num = rs.getInt(1);
			int sc_mid = rs.getInt(2);
			int sc_final = rs.getInt(3);
			int sc_homework = rs.getInt(4);
			int sc_attendance = rs.getInt(5);
			int sc_total = rs.getInt(6);
			int sc_co_num = rs.getInt(7);
			Score score = new Score(sc_num, sc_mid, sc_final, sc_homework, sc_attendance, sc_total, sc_co_num);
			list.add(score);
		}
		return list;
	}
	
	public ArrayList<Course> selectAllCourse() throws SQLException{
		String sql = "select co_num, co_st_num, co_le_num, co_type, co_grade from course";
		rs = stmt.executeQuery(sql);
		ArrayList<Course> list = new ArrayList<Course>();
		while(rs.next()) {
			int co_num = rs.getInt(1);
			String co_st_num = rs.getString(2);
			int co_le_num = rs.getInt(3);
			String co_type = rs.getString(4);
			String co_grade = rs.getString(5);
			Course course = new Course(co_num, co_st_num, co_le_num, co_type, co_grade);
			list.add(course);
		}
		return list;
	}
	public void insertCourse(String co_st_num,int co_le_num,String co_type) 
			throws SQLException {
		String sql = "insert into student(co_st_num, co_le_num, co_type, co_grade)"+
				"values(?, ?, ?, ?)";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, co_st_num);
		pstmt.setInt(2, co_le_num);
		pstmt.setString(3, co_type);
		pstmt.setString(4, null);
		int count = pstmt.executeUpdate();
		if(count == 0) {
			throw new RuntimeException("추가 실패");
		}
	}
	public void deleteCourse(String co_st_num,int co_le_num) throws SQLException {
		String sql = "delete from student where co_st_num = ? and co_le_num = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, co_st_num);
		pstmt.setInt(2, co_le_num);
		int count = pstmt.executeUpdate();
		if(count == 0) {
			throw new RuntimeException("삭제 실패");
		}
	}
}
