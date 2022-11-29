package day27;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Ex01_Client {

	public static void main(String[] args) {
		Socket socket = null;
		try {
			socket = new Socket();
			System.out.println("[연결 시도 중]");
			socket.connect(new InetSocketAddress("192.168.10.6",5001));
			System.out.println("[연결 성공]");
			send(socket, "홍길동");
			recieve(socket);
		} catch (IOException e) {
			System.out.println("[연결 실패]");
		}
	}
	//클라이언트는 본인거 하나만 관리하면 되기 때문에 굳이 클래스를 만들 필요X
	public static void recieve(Socket socket) {
		Thread t = new Thread( ()->{
			InputStream is = null;
			try {
				is=socket.getInputStream();
				byte[] bytes = new byte[1024];
				while(true) {
					int readCount = is.read(bytes);
					if(readCount == -1) //=읽어온 데이터가 없다면
						break;
					String str=new String(bytes,0,readCount,"UTF-8");
					int index = str.indexOf(",");
					if(index==-1)
						continue;
					String name=str.substring(0,index);
					String message=str.substring(index+1);
					System.out.println(name+">"+message);
				}
				is.close();
			}catch(Exception e) {
				
			}finally {
				try {
					if(socket!=null && !socket.isClosed())
						socket.close();
					System.out.println("[클라이언트 종료]");
				} catch (IOException e1) {
					System.out.println("클라이언트 소켓 닫기 실패");
				}
			}
		});
		t.start();
	}
	public static void send(Socket socket, String name) {
		Scanner scan = new Scanner(System.in);
		Thread t = new Thread( ()->{
			try {
				OutputStream os = socket.getOutputStream();
				while(true) {
					String str=scan.nextLine();
					if(str.equals("exit"))
						break;
					str = name+","+str;
					byte[] bytes = str.getBytes("UTF-8");
					os.write(bytes);
					os.flush();
				}
				os.close();
			}catch(Exception e) {
				
			}finally {
				System.out.println("[종료]");
				try {
					if(socket!=null && !socket.isClosed())
						socket.close();
				}catch(Exception e) {
					System.out.println("클라이언트 소켓 종료 실패");
				}
			}
		});
		t.start();
	}
}
