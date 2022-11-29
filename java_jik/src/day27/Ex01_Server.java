package day27;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Ex01_Server {

	private static ArrayList<Client> clientList = new ArrayList<Client>();
	
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		
		try {
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress(5001));
			System.out.println("[서버 시작]");
			while(true) {
				Socket socket = serverSocket.accept();
				Client client = new Client(socket);
				client.recieve();
//				while(!clienList.remove(null));
				clientList.add(client);
				System.out.println("[연결 완료] - 현재 접속 인원 : "+clientList.size());
			}
		} catch (IOException e) {
			System.out.println("예외 발생: 서버 종료");
		}
	}
	//내부 클래스: 클래스 안에 선언, 서버에서만 사용 가능
	static class Client {
		private Socket socket;
		public Client(Socket socket) {
			this.socket=socket;
		}
		public void recieve() {
			Thread t = new Thread( ()->{
				InputStream is = null;
				try {
					is=socket.getInputStream();
					byte[] bytes = new byte[1024];
					//연결되어있는 동안 계속해서 데이터를 읽어와야하기 때문에 반복문
					while(true) {
						/* read(): 1바이트 읽어와서 정수로 알려줌
						 * read(byte[] bytes): bytes 크기만큼 한번에 읽어와서 
						 * 					  bytes배열에 저장하고, 읽어온 갯수를 정수로 알려줌*/
						int readCount = is.read(bytes);
						if(readCount == -1) //=읽어온 데이터가 없다면
							break;
						//byte[]에 있는 byte들들 문자열로 변환, 0번지부터
						//readCount 개수만큼 변환하는데 인코딩은 UTF-8로 한다는 의미
						String str=new String(bytes,0,readCount,"UTF-8");
						//다른 클라이언트들에게 str을 전송
						System.out.println(str);
						if(clientList.size()==0) {
							continue;
						}
						for(Client tmp : clientList) { //리스트에 있는 애들한테 다 뿌려줌
							tmp.send(str);
						}
					}
					is.close();
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					//읽기 위해서 대기하다 클라이언트가 접속을 종료해 예외가 발생하면 socket을 닫아줌
					try {
						if(socket!=null && !socket.isClosed())
							socket.close();
						clientList.remove(this);//클라이언트 리스트에서 접속 종료한 클라이언트를 제거
						System.out.println("[클라이언트 종료] - 현재 접속 인원 : "+clientList.size());
					} catch (IOException e1) {
						System.out.println("클라이언트 소켓 닫기 실패");
					}
				}
			});
			t.start();
		}
		public void send(String data) {
			Thread t = new Thread( ()->{
				OutputStream os = null;
				try {
					os = socket.getOutputStream();
					byte[] bytes = data.getBytes("UTF-8"); //매개로 주어진 문자열을 변환
					os.write(bytes);
					os.flush();
				}catch(Exception e) {
					System.out.println("서버 예외 발생!");
				}
			});
			t.start();
		}
	}
	
}
