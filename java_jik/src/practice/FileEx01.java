package practice;

import java.io.File;
//import java.io.IOException;

public class FileEx01 {

	public static void main(String[] args) {
		File f = new File("D:/test2/test");
		//mkdir()
		if(f.mkdir())
			System.out.println("폴더가 생성됐습니다.");
		else {
			System.out.println("폴더 생성에 실패했습니다.");
			System.out.println("이미 폴더가 존재하거나 상위폴더가 없습니다.");
		}
		if(f.mkdirs())
			System.out.println("폴더가 생성됐습니다.");
		else {
			System.out.println("폴더 생성에 실패했습니다");
			System.out.println("이미 존재하는 폴더입니다.");
		}
		
		if(f.isDirectory())
			System.out.println("폴더명: "+f.getName());
		if(f.isFile())
			System.out.println("파일명: "+f.getName());
		
		
//		try{
//			if(f.createNewFile())
//				System.out.println("파일이 생셩됐습니다.");
//			else
//				System.out.println("파일 생성에 실패했습니다.");
//		}catch(IOException e) {
//			System.out.println("IOException이 발생했습니다.");
//		}
		
	}

}
