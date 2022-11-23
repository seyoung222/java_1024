package day23;

//java.io.의 모든 클래스를 임포트해서 쓰겠다는 의미
import java.io.*;

import lombok.Data;

public class FileStreamEx03 {
	public static void main(String[] args) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;
		
		
		//입력과 출력을 동시에 하려니까 에러나서 출력/입력 부분을 나눔
		//출력하는 부분
		try {
			fos = new FileOutputStream("test.txt");
			oos = new ObjectOutputStream(fos);
			
			oos.writeObject(new Student("홍길동","2022-01-01"));
			oos.writeObject(new Student("임꺽정","2022-02-02"));
			oos.writeObject(new Student("고길동","2022-03-03"));
			
		}catch(EOFException e) { //EOFException은 파일의 끝을 나타내주는 에러
			System.out.println("파일 읽기가 완료되었습니다.");
		}catch(Exception e) { //여러 종류의 에러를 한번에 처리하려고 그냥 Exception으로 씀
			e.printStackTrace();
		}finally {
			try {
				fos.close();
				oos.close();
			}catch(IOException e) {
				e.printStackTrace();
			}	
		}
		
		//입력하는 부분
		try {
			fis = new FileInputStream("test.txt");
			ois = new ObjectInputStream(fis);
			
			while(true) {
				Student std = (Student)ois.readObject();
				System.out.println(std);
			}
		}catch(EOFException e) { //EOFException은 파일의 끝을 나타내주는 에러
			System.out.println("파일 읽기가 완료되었습니다.");
		}catch(Exception e) { //여러 종류의 에러를 한번에 처리하려고 그냥 Exception으로 씀
			e.printStackTrace();
		}finally {
			try {
				fis.close();
				ois.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
				
		}
	}
}
@Data
class Student implements Serializable{
	
	private static final long serialVersionUID = 1700858336115503227L;
	//serialVersionUID 필드 (Serializable 안에 있음)
	//Serializable을 implements할때 JVM(자바버추얼머신)에서 자동으로 추가해줘서 에러가 나진 않지만 
	//역직렬화과정에서 예기치못한 에러가 발생할 수 있으므로 추가해주는게 좋다. (직접적는게 아니고 마우스 호버해서 add머시기..)
	private String name;
	private String birthday;
	public Student(String name, String birthday) {
		this.name=name;
		this.birthday=birthday;
	}
}