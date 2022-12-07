package practice;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import lombok.Data;

public class FileIOStreamEx03 {

	public static void main(String[] args) {
		FileOutputStream fos = null;
		FileInputStream fis = null;
		ObjectOutputStream oos =null;
		ObjectInputStream ois = null;
		
		try {
			fos = new FileOutputStream("test2.txt");
			oos = new ObjectOutputStream(fos);
			
			oos.writeObject(new Student("홍길동", "2022-01-02"));
			oos.writeObject(new Student("임꺽정", "2020-01-02"));
			oos.writeObject(new Student("고길동", "2021-03-02"));
		}catch(EOFException e) {
			System.out.println("파일 읽기가 완료되었습니다.");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				fos.close();
				oos.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		try {
			fis = new FileInputStream("test2.txt");
			ois = new ObjectInputStream(fis);
			
			while(true) {
				Student std = (Student)ois.readObject();
				System.out.println(std);
			}
		}catch(EOFException e) {
			System.out.println("파일 읽기가 완료되었습니다.");
		}catch(Exception e) {
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
@Data //데이터 어노테이션 안하면 그냥 참조변수 주소값만 뜸.... 해야..학생정보떠요
class Student implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5978748846270223494L;
	//보조스트림인 ObjectOutputStream을 사용해서 Serializable을 implement.
	//serialVersionUID를 add 해야함
	private String name, birth;
	public Student(String name, String birth) {
		this.name = name;
		this.birth = birth;
	}
	
}