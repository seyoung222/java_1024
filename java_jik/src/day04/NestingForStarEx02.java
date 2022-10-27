package day04;

public class NestingForStarEx02 {

	public static void main(String[] args) {
		/* *    	row=1	*=1
		 * **   	row=2	*=2
		 * ***  	row=3	*=3
		 * **** 	row=4	*=4
		 * *****	row=5	*=5
		 * 					*=row
		 */
		
		int row, col;
		int line = 5;
		
		for(row=1; row<=line; row++) {
			for(col=1; col<=row; col++) {
				System.out.print("*");
			}
			System.out.println();
		}
		
		
		System.out.println("=================");
		/*     *	row=1	공백=4		*=1
		 *    ** 	row=2	공백=3		*=2
		 *   ***	row=3	공백=2		*=3
		 *  **** 	row=4	공백=1		*=4
		 * *****	row=5	공백=0		*=5
		 * 				    공백=5-row	*=row
		 */
		
		for(row=1; row<=line; row++) {
			//공백 출력 (line-row)개
			for(int i=1; i<=(line-row); i++) {
				System.out.print(" ");				
			}
			//*출력 row개
			for(col=1; col<=row; col++) {
				System.out.print("*");
			}
			//엔터
			System.out.println();
		}
		
		System.out.println("=================");
		/*     *		row=1	공백=4		*=1
		 *    *** 		row=2	공백=3		*=3
		 *   *****		row=3	공백=2		*=5
		 *  ******* 	row=4	공백=1		*=7
		 * *********	row=5	공백=0		*=9
		 * 				   		공백=5-row	*=2*row-1
		 */
		
		for(row=1; row<=line; row++) {
			for(int i=1; i<=(line-row); i++) {
				System.out.print(" ");
			}
			for(col=1; col<=(2*row)-1; col++) {
				System.out.print("*");
			}
			System.out.println();
		}
		
		
		// 역정삼각형이랑 다이아몬드도 해보기
		
		
	}
}
