package db.day1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import lombok.Data;

public class DBTest1_1 {
// DBTest1의 내용을 사용해 메소드를 이용해서 DB연결하는 코드
	static ResultSet rs = null; //워크벤치에서 select했을때 출력되는 결과표를 ResultSet이라고 함!
	static Statement stmt = null;
	static PreparedStatement pstmt = null;
	//Statement와 PreparedStatement는 sql문 수행에 사용하는 클래스인데 차이점은 아래 메소드에 설명 적혀있음
	
	public static void main(String[] args) {
		Connection con = null;
        
        String url = "jdbc:mysql://localhost/university";
        String id = "root";
        String pw = "root";
        ArrayList<Student1> list = null;
        con = connect(url, id, pw); //DB연결하고 커넥션 객체를 리턴함
        
        try {
			stmt = con.createStatement(); //메소드의 오류처리 try-catch문 사용
			//insertStudent(con, "2022160004", "라길동",1,"재학","2022160002");
			//updateStudent(con, "2022160001", "가나다");
			//deleteStudent(con, "2022160005");
			
			//학생정보 조회
			list = selectAllStudent(con);
			System.out.println(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        connectClose(con);
	}
	//DB연결하고 커넥션 객체를 리턴하는 메소드
	public static Connection connect(String url,String id,String pw) {
		Connection con = null;
		try{
            Class.forName("com.mysql.cj.jdbc.Driver");//이 클래스가 있는지 찾아주고 연결도 해줌            
            con = DriverManager.getConnection(url, id, pw);
            System.out.println("[연결 성공]");
	        }
        catch(ClassNotFoundException e){
            System.out.println("[드라이버 로딩 실패]");
        }
        catch(SQLException e){
            System.out.println("[에러: " + e + "]");
        }
		return con;
	}
	public static void connectClose(Connection con) {
		try{
            if( con != null && !con.isClosed()){
                con.close();
                System.out.println("[연결 해제]");
            }
        }
        catch( SQLException e){
            e.printStackTrace();
        }
	}
	//저장된 Student 테이블을 list에 저장해서 list를 반환하는 메소드
	//select * from Student해서 resultSet에 나온 결과(=모든 데이터)의 한줄 한줄을 list에 하나씩 넣는 작업
	public static ArrayList<Student1> selectAllStudent(Connection con) throws SQLException{
		String sql = "select st_num, st_name, st_semester, st_state, st_pr_num from student"; //쿼리를 저장
		rs = stmt.executeQuery(sql); //쿼리 실행하고 출력표를 반환
		ArrayList<Student1> list = new ArrayList<Student1>();
		while(rs.next()) { //표에 있는 걸 한줄씩 불러와야함. 내용이 없을 때까지 반복실행.
			String st_num = rs.getString(1); //1번째 행(속성)의 값을 st_num에 저장
			String st_name = rs.getString(2); //2번째 행에 있는 값을 st_name에 저장 
			int st_semester = rs.getInt(3); //이하 동문
			String st_state = rs.getString(4);
			String st_pr_num = rs.getString(5);
			Student1 std = new Student1(st_num, st_name, st_semester, st_state, st_pr_num);
			//위에서 저장한 값들을 이용해 속성과 동일한 필드를 가진 Student1 클래스의 객체를 생성하고
			list.add(std);//리스트에 저장
		}
		return list;
		/* select문에서도 조건을 매번 바꿔서 사용하고 싶을때 ?와 prepareState 사용가능
		 * String sql = " ~~~ ? ";
		 * pstmt = con.prepareStatement(sql);
		 * pstmt.setString(1, "넣을내용");
		 * rs = pstmt.executeQuery(); <- executeUpdate()대신 executeQuery()사용
		 * 이후 stmt와 똑같이 사용하면 됨.
		 * */
	}
		
	public static void insertStudent(Connection con, String st_num, String st_name, int st_semester,
			String st_state, String st_pr_num) throws SQLException {
		String sql = "insert into student(st_num, st_name,st_semester, st_state, st_pr_num)"+
				"values(?, ?, ?, ?, ?)";
		//위 sql문처럼 고정되지 않은 값이 있으면 stmt가 아니라 pstmt를 사용.
		//즉 select * from ~ 처럼 내용이 고정된 sql은 stmt를, 사용자에게 입력받아 변화하는 값을 넣어야하는 경우 pstmt
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, st_num); //매개변수로 받은 st_num문자열을 1번째 ?에 집어넣음
		pstmt.setString(2, st_name); 
		pstmt.setInt(3, st_semester);
		pstmt.setString(4, st_state);
		pstmt.setString(5, st_pr_num);
		int count = pstmt.executeUpdate(); // ?에 값을 모두 대입하고 완성된 sql문을 실행
		// 쿼리 실행 후 무사히 실행된 명령어의 개수를 count에 리턴
		if(count == 0) { //제대로 실행된 명령어가 하나도 없다 = 제대로 작동하지 않았다.
			System.out.println("[추가 실패]");
		}else {
			System.out.println("[추가 성공]");
		}
	}
	private static void updateStudent(Connection con,String st_num,String st_name) throws SQLException {
		String sql = "update student set st_name =? where st_num = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, st_name);
		pstmt.setString(2, st_num);
		int count = pstmt.executeUpdate();
		if(count == 0) {
			System.out.println("[수정 실패]");
		}else {
			System.out.println("[수정 성공]");
		}
	}
	private static void deleteStudent(Connection con, String st_num) throws SQLException {
		String sql = "delete from student where st_num = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, st_num);
		int count = pstmt.executeUpdate();
		if(count == 0) {
			System.out.println("[삭제 실패]");
		}else {
			System.out.println("[삭제 성공]");
		}
	}
}
@Data
class Student1{
	private String st_num;
	private String st_name;
	private int st_semester;
	private String st_state;
	private String st_pr_num;
	public Student1(String st_num, String st_name, int st_semester, String st_state, String st_pr_num) {
		this.st_num = st_num;
		this.st_name = st_name;
		this.st_semester = st_semester;
		this.st_state = st_state;
		this.st_pr_num = st_pr_num;
	}
}