package day04;

public class NestingForStarEx01 {

	public static void main(String[] args) {
		/* *****
		 * *****
		 * *****
		 * *****
		 * *****
		 */
		
		//내가한거
		int line = 5;
		int star = 7;
		for(int i=0; i<line; i++) {
			for(int j=0; j<star; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		
		
		System.out.println("==============");
		
		
		//강사님 풀이
		int col, row;
		for(row=1; row<=5; row++) {
			System.out.println("*****");
		}
		System.out.println("------------");
		for(row=1; row<=5; row++) {
			for(col=1; col<=20; col++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

}
