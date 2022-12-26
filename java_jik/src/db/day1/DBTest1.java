package db.day1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBTest1 {
/* DB 연결하기
 * 프로젝트 우클릭>properties>Java build Path>Libraries>ModulePath클릭>Add External JARs>
 * (C드라이브\Program Files(X86)\MySQL\Connector J 8.0 선택 후 Apply
 * */
	public static void main(String[] args) {
		Connection conn = null;
		//java와 DB를 연결하기 위한 Connection클래스의 객체를 생성(선언은 예외처리해야해서 초기화만 한 상태)
		//꼭 다시 close해야하고 DB와 데이터를 주고 받는 동안에는 꼭 연결되어있어야함(=close되지 않은 상태)

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");//드라이버 로딩: 이 클래스가 있는지 찾아주고 연결도 해줌
            //드라이버 인터페이스를 구현한 클래스를 로딩하는건데 버전이 높아지면서 이젠 이걸 굳이 안써도 실행O
            //-> 단, 이 명령어로 인해 ClassNotFoundException이 생긴 것이므로 이거 지우면 해당 catch도 삭제
            
            
            String url = "jdbc:mysql://localhost/university"; // "jdbc:DBMS명://서버IP/DB명"
            String id = "root";
            String pw = "root";
            conn = DriverManager.getConnection(url, id, pw);
            /* url
             * mysql : DBMS
             * localhost : 내부 로컬 주소-> 프로그램이 실행되는 서버와 DB가 같은 PC에 있는 경우
             * 		서버와 DB프로그램이 다른 PC에 있는 경우 다른 PC의 아이피 주소를 적을 것.
             * university : 데이터베이스 명
             * */
            
            System.out.println("연결 성공");

        }
        catch(ClassNotFoundException e){
            System.out.println("드라이버 로딩 실패");
        }
        catch(SQLException e){
            System.out.println("에러: " + e);
        }
        finally{
        	// -> 수업에서는 finally이하내용을 closeConnect() 메소드로 생성함.
            try{
                if( conn != null && !conn.isClosed()){
                    conn.close();
                }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
	}
}