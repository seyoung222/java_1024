package day23;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileStreamEx01 {
	public static void main(String[] args) {
		FileOutputStream fos = null;
		//객체가 생성될 때 파일과 직접 연결됨
		//파일이 존재하지 않으면 자동으로 생성하지만 이미 파일이 존재하는 경우 파일을 덮어씀!!!!
		//만일 기존 파일에 이어 작성하고 싶다면 =new FileOutputStream("파일경로", true);처럼 객체 생성시 가능
		FileInputStream fis = null;
		try {
			fos = new FileOutputStream("test.txt");
			char ch = '\u0000'; //유니코드로 정수 0
			for(int i=0; i<200; i++) {
//				System.out.print(ch);
				fos.write(ch);//1바이트 단위로 읽기때문에 넘어가면 앞부분이 잘려서 데이터손실 => 콘솔 출력값과 다르게 저장
				++ch;
			}
			fis = new FileInputStream("test.txt");
			int num;
			for(int i=0; i<200; i++) {
				num=fis.read();
				System.out.print((char)num); //바이트 단위로 읽어서 정수인지 문자인지 분간이 힘들다...
				//test.txt에 저장은 이상하게 되어있는데 또 읽어오면 제대로 읽힘.. 뭐지
				++ch;
			}
		}catch(FileNotFoundException e) {
			System.out.println("파일을 찾지 못했습니다."); //파일이 없으면 만드는데 폴더는 아님. 폴더없을때 에러 발생
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				fos.close();
				fis.close();
				//중간에 메인메소드가 종료되더라도 반드시 실행되어야함->finally에 넣음
				//이 메소드 자체에서 예외가 발생할 수 있게 설계되어있어서 예외처리 또 해야함
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}
