package day26;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Ex01_ServerMain {
//서버 소켓 실행시 주의! 
// 0) 무조건 서버를 먼저 실행하고 클라이언트 실행
// 1) 콘솔에서 중지시키지 않고 중복 실행할시 포트가 이미 백그라운드에서 실행중이므로 오류남
// -> 1.포트 번호 바꿔서 실행하거나 2.작업관리자에서 작업끝냄(cmd에서 netstat -ano 입력 후 PID 찾아서 강종)
// 2) 서버와 클라이언트는 1:1만 가능 -> 이클립스를 새로 켜서 연결요청하더라도 이미 서버가 연결대기중이면 응답X
// -> 연결될때마다 스레드를 생성하여 다중 클라이언트의 연결이 가능하도록 해야함
	
	public static void main(String[] args) {
		ServerSocket serverSocket = null; //자체적으로 오류나므로 try-catch문 안에 생성
		Scanner scan = new Scanner(System.in);
		final String encode = "UTF-8"; //모든 인코딩을 UTF-8로 하기 위해 지정해둠
		
		try {
			serverSocket = new ServerSocket();
			//바인드라는 작업을 통해 연결을 해줘야함
			serverSocket.bind(new InetSocketAddress(5001)); //5001번 포트와 연결
			//반복문으로 응답대기
			while(true) {
				System.out.println("[연결 대기 중]");
				Socket socket = serverSocket.accept(); //허용하면 소켓 객체를 하나 생성
				//연결되었다는걸 확인하기 위해서 소켓주소를 가져와서 확인함
				InetSocketAddress isa = (InetSocketAddress)socket.getRemoteSocketAddress();
				System.out.println("[연결 수락함]"+isa.getAddress()); //연결된 주소 확인
				
				
				//소켓을 이용해 데이터를 전달받고 읽어오는 과정: inputStream 이용
				InputStream is = socket.getInputStream();
				byte []bytes = new byte[1024]; //최대 몇개씩 받아올건지 결정(1024)
				int readCount = is.read(bytes); //읽어온 데이터를 bytes배열에 저장
		//1024는 최대 사이즈지 실제 받아온 개수가 아니기때문에 실제로 받아온 개수확인을 위해 readCount 변수 생성
				String str = new String(bytes, 0, readCount, encode);//배열을 문자열로 바꿈
				//bytes배열에서 readCount개수만큼 UTF-8 양식으로 읽어옴(Output에서 이렇게 보냈으므로)
				System.out.println(str);
				
				
				//콘솔에서 문자열을 입력받아 클라이언트로 전송하기 : OutputStream 이용
				OutputStream os = socket.getOutputStream();
				str = scan.nextLine();
				bytes = str.getBytes(encode);
				os.write(bytes);
				os.flush();
				System.out.println("[전송 완료]");
				
				is.close(); //스트림은 묶어서 닫아야함
				os.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
