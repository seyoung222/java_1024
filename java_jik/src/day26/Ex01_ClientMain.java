package day26;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Ex01_ClientMain {

	public static void main(String[] args) {
		Socket socket = null;
		Scanner scan = new Scanner(System.in);
		
		try {
			socket = new Socket(); //기본 생성자로 생성하고 포트와 연결. 서버와 포트번호가 같아야함!
			socket.connect(new InetSocketAddress(5001));
			System.out.println("[연결 완료]"); //반복문 아니고 그냥 연결요청하고 끝냄
			//클라이언트는 한번 실행되고 바로 종료됨
			//서버 소켓은 계속 실행되며 클라이언트 요청이 들어오면 확인하고 다시 대기하기를 반복
			
			//소켓을 이용해 문자를 전송하는 과정 : OutputStream 이용
			OutputStream os = socket.getOutputStream(); //소켓을 통해 Output스트림 생성
//			String str = "안녕하세요.";
			String str = scan.nextLine(); //직접 입력한 문자열을 보낼수도 있음~
			byte [] bytes = str.getBytes("UTF-8"); 
			//문자열을 바이트 배열로 변환해줌. (" ") 안에서 원하는 양식을 정할 수도 있음
			os.write(bytes); 
			os.flush(); //outputStream을 사용해 전송 후 flush를 통해 밀어줌
			
			//서버에서 보낸 문자열을 입력받아 콘솔에 출력
			InputStream is = socket.getInputStream();
			bytes = new byte[1024]; 
			//배열 사이즈를 새로 지정해주지 않으면 위에서 보낸 글자수만큼만 받아서 출력할 수 있음
			int readCount = is.read(bytes);
			str = new String(bytes, 0, readCount, "UTF-8");
			System.out.println(str);
			System.out.println("[전송 완료]");

			os.close(); //스트림 객체 한번에 닫음
			is.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
