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
		// Connection 객체를 자동완성으로 import할 때는 com.mysql.connection이 아닌
        // java 표준인 java.sql.Connection 클래스를 import해야 한다.
		Connection conn = null;

        try{
            // 1. 드라이버 로딩
            // 드라이버 인터페이스를 구현한 클래스를 로딩
            // mysql, oracle 등 각 벤더사 마다 클래스 이름이 다르다.
            // mysql은 "com.mysql.jdbc.Driver"이며, 이는 외우는 것이 아니라 구글링하면 된다.
            // 참고로 이전에 연동했던 jar 파일을 보면 com.mysql.jdbc 패키지에 Driver 라는 클래스가 있다.
            Class.forName("com.mysql.cj.jdbc.Driver");//이 클래스가 있는지 찾아주고 연결도 해줌
            /* mysql : DBMS
             * localhost : 내부 로컬 주소-> 프로그램이 실행되는 서버와 DB가 같은 PC에 있는 경우
             * university : 데이터베이스 명
             * */
            
            // 2. 연결하기
            // 드라이버 매니저에게 Connection 객체를 달라고 요청한다.
            // Connection을 얻기 위해 필요한 url 역시, 벤더사마다 다르다.
            // mysql은 "jdbc:mysql://localhost/사용할db이름" 이다.
            String url = "jdbc:mysql://localhost/university";
            
            String id = "root";
            String pw = "root";
            conn = DriverManager.getConnection(url, id, pw);
            System.out.println("연결 성공");

        }
        catch(ClassNotFoundException e){
            System.out.println("드라이버 로딩 실패");
        }
        catch(SQLException e){
            System.out.println("에러: " + e);
        }
        finally{
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