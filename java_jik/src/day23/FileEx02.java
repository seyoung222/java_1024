package day23;

import java.io.File;
import java.io.IOException;

public class FileEx02 {

	public static void main(String[] args) {
		File f = new File("D:/test2/test"); 
		//폴더 생성 메소드 mkdir()이 false인 경우
		// 1. 이미 있는 폴더인 겨웅
		// 2. 상위 폴더가 없는 경우
		if(f.mkdir()) {
			System.out.println("폴더가 생성되었습니다.");
		}else {
			System.out.println("이미 폴더가 있거나 상위폴더가 없습니다.");
		}
		//mkdirs()는 경로상에 없는 폴더까지 모두 생성, 이미 있는 경우 실패
		if(f.mkdirs()) {
			System.out.println("폴더들이 생성되었습니다.");
		}else {
			System.out.println("폴더들의 생성에 실패했습니다.");
		}
		
		if(f.isDirectory())
			System.out.println("폴더명: "+f.getName());
		if(f.isFile())
			System.out.println("파일명: "+f.getName());
		
		if(f.delete()) {
			System.out.println("폴더가 삭제되었습니다");
		}else {
			System.out.println("없는 폴더입니다.");
		}
	}

}
