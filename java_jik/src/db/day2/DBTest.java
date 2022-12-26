package db.day2;

import java.sql.*;
import java.util.ArrayList;

import lombok.Data;

public class DBTest {
	
	Connection con;
	ResultSet rs ;
	Statement stmt;
	PreparedStatement pstmt;
	
	public static void main(String[] args) {
        DBTest dbTest = new DBTest();
        ArrayList<StudentVO1> list = new ArrayList<StudentVO1>();
        
        StudentDAOImp studentDao = null;
        
        try{
//      	Class.forName("com.mysql.cj.jdbc.Driver");
        	//이 문자열과 일치하는 클래스가 있는지 체크하고 있으면 불러옴
        	//cj로 바뀌면서 이제는 이 명령어 자체가 없어도 실행됨
        	
            //자바와 mysql이 동일 컴에 있을 때 -> localhost
        	//자바와 mysql이 다른 컴에 있을 때 -> 아이피 주소 입력
        	String url = "jdbc:mysql://localhost/university";
        	String id = "root", pw = "root";
        	
        	dbTest.connect(url,id,pw);
        	System.out.println("연결 성공");
            
        	// DB 연결 완료
        	studentDao = new StudentDAOImp(dbTest.con);
            
            list = studentDao.selectAllStudent(); //각 테이블의 dao를 통해서만 정보 가져오고 넣도록 함
            
            System.out.println(list);
            System.out.println(studentDao.selectStudentBySt_num("2022160001"));
            /*
            StudentVO1 std = new StudentVO1("2022135001","고둘리",1,"재학","2022160001");
            if(studentDao.insertStudent(std)) {
            	System.out.println("추가 성공");
            }else {
            	System.out.println("추가 실패");
            }
            
            if(studentDao.deleteStudent("2022135001"))
            	System.out.println("삭제 성공");
            else
            	System.out.println("삭제 실패");
            
            if(studentDao.updateStudent("2022160001", "가길동"))
            	System.out.println("수정 성공");
            else
            	System.out.println("수정 실패");
            */

        }
//        catch(ClassNotFoundException e){
//            System.out.println("드라이버 로딩 실패");
//        } Class.forname을 주석처리하면서 이 에러가 일어날 일이 아예 없어짐
        catch(SQLException e){
            System.out.println("연결 실패");
        }
        finally{
           dbTest.closeConnect();
        }
	}
	public void connect(String url, String id, String pw) throws SQLException {
		 con = DriverManager.getConnection(url, id, pw);
         stmt = con.createStatement();
	}
	public void closeConnect() {
		try{
            if( con != null && !con.isClosed()){
                con.close();
            }
            if( stmt != null && !stmt.isClosed()){
                stmt.close();
            }
            if( rs != null && !rs.isClosed()){
                rs.close();
            }
            if( pstmt != null && !pstmt.isClosed()){
                pstmt.close();
            }
        }
        catch( SQLException e){
            e.printStackTrace();
        }
	}
	
}
@Data
class StudentVO1{
	String st_num;
	String st_name;
	int st_semester;
	String st_state;
	String st_pr_num;
	public StudentVO1(String st_num, String st_name, int st_semester, String st_state, String st_pr_num) {
		this.st_num = st_num;
		this.st_name = st_name;
		this.st_semester = st_semester;
		this.st_state = st_state;
		this.st_pr_num = st_pr_num;
	}
	@Override
	public String toString() {
		return "[학번:" + st_num + ", 이름:"+ st_name + ", 학기:" + st_semester + ", 재학상태:"
				+ st_state + ", 지도교수번호:" + st_pr_num + "]";
	}
	
	
}