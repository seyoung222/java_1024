package day02;

import java.util.Scanner;

public class ScannerEx03 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		//콘솔에서 한 단어이상을 입력하고, next(), nextLine() 순으로 동작을 하면
		//nextLine()에서는 콘솔에서 따로 입력하지 않아도 앞에서 입력한 값이 들어간다.
		System.out.println("한 단어를 입력하세요 : ");
		String str1 = scan.next();
		System.out.println(str1);
		
		scan.nextLine(); //입력버퍼에 남아있는 문자열과 공백을 제거
		
		System.out.println("한 문장을 입력하세요 : ");
		String str2 = scan.nextLine();
		System.out.println(str2);
		
// 중간에 걸러내지 않을 경우 두번째값을 따로 입력받지않고 처음에서 남은 문자열을 두번째에서 출력함
// why?? ★입력버퍼★ 때문! 우리가 입력한 문자열의 공백 이후가 입력버퍼에 남아있어서...
// 홍길동 엔터<를 입력하면 엔터전(홍길동)을 next()가, 엔터이후(엔터)를 nextLine()이 가져감
		
		
		scan.close();
	}

}
