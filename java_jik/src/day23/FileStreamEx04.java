package day23;

import java.io.*; //java.io.의 모든 클래스를 임포트해서 쓰겠다는 의미

public class FileStreamEx04 {
	public static void main(String[] args) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		DataInputStream dis = null; //보조스트림(기본 데이터타입 출력)
		DataOutputStream dos = null;
		
		try {
			fis = new FileInputStream("test.txt");
			fos = new FileOutputStream("test.txt");
			dis = new DataInputStream(fis);
			dos = new DataOutputStream(fos);
//			int age = 10;
//			String name = "홍길동";
//			double pi = 3.14;
			dos.writeUTF("홍길동");
			dos.writeUTF("2000-01-01");
			dos.writeUTF("임꺽정");
			dos.writeUTF("2000-02-02");
			dos.writeUTF("고길동");
			dos.writeUTF("2000-03-03");
			
			while(true) {
				//입력받을 때 문자열/int/double 등의 숫자를 맞춰서 읽고받아줘야함
				String name = dis.readUTF();
				String birth = dis.readUTF();
				System.out.println(name+" : "+birth);
			}
//			age = dis.readInt();
//			name = dis.readUTF();
//			pi = dis.readDouble();
//			System.out.println(age+", "+name+", "+pi);
		}catch(EOFException e) { //EOFException은 파일의 끝을 나타내주는 에러
			System.out.println("파일 읽기가 완료되었습니다.");
		}catch(Exception e) { //여러 종류의 에러를 한번에 처리하려고 그냥 Exception으로 씀
			e.printStackTrace();
		}finally {
			try {
				fis.close();
				fos.close();
				dis.close();
				dos.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
				
		}
		
	}
}
