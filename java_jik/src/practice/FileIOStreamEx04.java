package practice;

import java.io.*;


public class FileIOStreamEx04 {

	public static void main(String[] args) {
		FileOutputStream fos = null;
		FileInputStream fis = null;
		DataOutputStream dos = null;
		DataInputStream dis = null;
		
		try {
			fis = new FileInputStream("test2.txt");
			fos = new FileOutputStream("test2.txt");
			dis = new DataInputStream(fis);
			dos = new DataOutputStream(fos);
			
			dos.writeInt(10);
			dos.writeUTF("홍길동");
			dos.writeUTF("2000-01-01");
			dos.writeInt(18);
			dos.writeUTF("임꺽정");
			dos.writeUTF("2002-03-01");
			dos.writeInt(17);
			dos.writeUTF("고길동");
			dos.writeUTF("2001-04-11");
			
			while(true) {
				int age = dis.readInt();
				String name = dis.readUTF();
				String birth = dis.readUTF();
				System.out.println(age+"살 "+name+"의 생일: "+birth);
			}
		}catch(EOFException e) {
			System.out.println("파일 읽기가 완료되었습니다.");
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				fos.close();
				fis.close();
				dis.close();
				dos.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}

	}
}
