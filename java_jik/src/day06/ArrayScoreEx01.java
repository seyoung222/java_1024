package day06;

import java.util.Scanner;

public class ArrayScoreEx01 {

	public static void main(String[] args) {
		/* 5명 학생의 국어, 영어, 수학 성적을 저장하기 위한 배열을 만들고
		 * Scanner를 이용하여 입력받아, 국어평균, 영어평균, 수학평균을 구하는
		 * 코드를 작성하세요.
		 * */
		
		/* 내가 한거
		int student =5;
		int kor[] = new int[student];
		int eng[] = new int[student];
		int math[] = new int[student];
		
		Scanner scan = new Scanner(System.in);
		for(int i=0; i<student; i++) {
			System.out.print((i+1)+"번째 학생의 국어 성적을 입력하세요> ");
			kor[i]= scan.nextInt();
			System.out.print((i+1)+"번째 학생의 영어 성적을 입력하세요> ");
			eng[i]= scan.nextInt();
			System.out.print((i+1)+"번째 학생의 수학 성적을 입력하세요> ");
			math[i]= scan.nextInt();
		}
		
		int korSum=0;
		int engSum=0;
		int mathSum=0;
		for(int i=0; i<student; i++) {
			korSum += kor[i];
			engSum += eng[i];
			mathSum += math[i];
		}
		
		System.out.println("국어성적 평균: "+(double)korSum/student);
		System.out.println("영어성적 평균: "+(double)engSum/student);
		System.out.println("수학성적 평균: "+(double)mathSum/student);
		
		scan.close();
		*/
		
		int stdCount = 5;
		int kor[] = new int[stdCount];
		int eng[] = new int[stdCount];
		int math[] = new int[stdCount];
		
		//학생별 국어, 영어, 수학 성적을 입력
		Scanner scan = new Scanner(System.in);
		
		for(int i=0; i<stdCount; i++) {
			System.out.print("학생"+(i+1)+"의 국어, 영어, 수학 성적을 입력> ");
			kor[i] = scan.nextInt();
			eng[i] = scan.nextInt();
			math[i] = scan.nextInt();
		}
		
		//과목별 합(총점)
		double korSum=0, engSum=0, mathSum=0; //각각 초기화해야함!
		for(int i=0; i<stdCount; i++) {
			korSum += kor[i];
			engSum += eng[i];
			mathSum += math[i];
		}
		
		//과목별 평균
		System.out.println("국어 성적의 평균 : "+ korSum/stdCount);
		System.out.println("영어 성적의 평균 : "+ engSum/stdCount);
		System.out.println("수학 성적의 평균 : "+ mathSum/stdCount);

		//학생별 평균
		/* 내가 한거 (별루)
		double stdAvg[] = new double[stdCount];
		for(int i=0; i<stdCount; i++) {
			stdAvg[i] += kor[i];
			stdAvg[i] += eng[i];
			stdAvg[i] += math[i];
			stdAvg[i] /= 3;
			System.out.println((i+1)+"번째 학생의 평균: "+stdAvg[i]);
		}
		 */
		
		for(int i=0; i<stdCount; i++) {
			double sum = kor[i]+eng[i]+math[i];
			System.out.println("학생"+(i+1)+"의 평균: "+ sum/3);
		}
		
		
	}
}
