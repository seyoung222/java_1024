package day11;

public class StringEx03 {

	public static void main(String[] args) {
		/* 주어진 파일명들 중에서 이미지 파일들을 찾아 파일명을 출력하세요.
		 * 이미지 파일 확장자는 jpg, png*/
		String [] list = {
				"이미지1.jpg", "음악1.bmp", "이미지2.mp4", "이미지3.png","예제.txt"};
		//반복문
		
		for(String tmp : list) {
			//확장자 추출: lastIndexOf(파일이름 안에 "."이 들어갈수 있으므로), substring
			int index = tmp.lastIndexOf(".");
			String extension = tmp.substring(index);
			//확장자가 이미지 확장자인지 확인하여 맞으면 파일명 출력
			if(extension.equals(".jpg") || extension.equals(".png")) {
				System.out.println(tmp);
			}
		}
		
		
		/* 내거
		System.out.println("이미지 파일> ");
		for(int i=0; i<list.length; i++) {
			String tmp = list[i].substring(list[i].lastIndexOf(".")+1);
			if(tmp.equals("jpg") || tmp.equals("png")) {
				System.out.println(list[i]);
			}
		}
		*/
		
	}

}
