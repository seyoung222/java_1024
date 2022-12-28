package example.db.mainsy;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;



public class Main {

	public static void main(String[] args) {
		String resource = "example/db/mainsy/mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			//리소스들은 한번 열면 닫아야하므로 원래 session.close();해야하는데 
			//try(){} 중 ()안에 쓰면 try문 끝날때 자동으로 닫아주는 기능을해서 따로 close()안해도됨
			try (SqlSession session = sqlSessionFactory.openSession()) {
				MainController mc = new MainController(session);
				mc.run();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
