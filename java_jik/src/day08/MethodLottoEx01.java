package day08;
// 메소드를 왜 굳이 사용하는가?
// 1. 메소드의 재사용을 통해 main의 길이를 줄임
// 2. 기능의 유지보수가 쉬워짐 (해당 부분의 코드만 검토하면 됨)
// 3. 구조적으로 파악하기가 쉬움 (코드가 줄어들고, 메소드명을 통해 손쉽게 파악가능)
import java.util.Arrays;
import java.util.Scanner;

public class MethodLottoEx01 {

	public static void main(String[] args) {
		int min=1, max=45;
		int size=6;
		int [] lotto = createRandomArray(min,max,size);
		Arrays.sort(lotto);
		System.out.print("로또 번호: ");
		printArray(lotto);
		
		int bonus;
		do {
			bonus = random(1,45);
		}while(contains(lotto, bonus));
		System.out.println("보너스 번호 : "+bonus);
		
		int []user = new int [size];
		System.out.print("입력 번호 : ");
		Scanner scan = new Scanner(System.in);
		for(int i=0; i<user.length; i++) {
			user[i] = scan.nextInt();
		}
		scan.close();
		
		int rank = getRank(lotto,bonus,user);
		printRank(rank);
		
	}
//메소드는 원래 하나의 기능을 하는게 이상적~ 그래서 등수알려주는 메소드를 두개로 나눔
	/* 기능 : 로또번호(보너스번호 포함)와 입력번호가 주어지면 등수를 알려주는 메소드
	 * 매개변수 : 로또번소, 입력번호=>int[]lotto,int bonus,int[]user
	 * 리턴타입 : 등수=>정수=>int
	 * 메소드명 : getRank
	 * */
	public static int getRank(int[]lotto,int bonus,int[]user) {
		int count=countLotto(lotto,user);
		switch(count) {
		case 6: return 1;
		case 5: return contains(user,bonus)? 2: 3;
		case 4: return 4;
		case 3: return 5;
		default: return -1;
		}
	}
	/* 기능: 등수가 주어지면 등수를 콘솔에 출력하는 메소드
	 * 매개변수 : 등수=>정수=>int
	 * 리턴타입 : 출력 => void
	 * 메소드명 : printRank
	 * */
	public static void printRank(int rank) {
		switch(rank) {
		case 1: System.out.println("1등!"); break;
		case 2: System.out.println("2등!"); break;
		case 3: System.out.println("3등!"); break;
		case 4: System.out.println("4등!"); break;
		case 5: System.out.println("5등!"); break;
		case -1: System.out.println("꽝!"); break;
		}
	}
	/* 기능: 당첨 등수를 알려주는 메소드
	 * 매개변수 : 일치하는 개수, 입력번호, 보너스번호=>int count, int[]arr, int bonus
	 * 리턴타입 : 출력 => 없음 => void
	 * 메소드명 : printResult
	 * */
	public static void printResult(int count,int[]user,int bonus) {
		switch(count) {
		case 6:
			System.out.println("1등");
			break;
		case 5:
			if(contains(user,bonus)) {
				System.out.println("2등");
			} else{ 
				System.out.println("3등");
			}
			break;
		case 4:
			System.out.println("4등");
			break;
		case 3:
			System.out.println("5등");
			break;
		default:
			System.out.println("꽝");
		}
	}
	
	/* 기능 : 주어진 두 배열에서 일치하는 숫자의 개수를 알려주는 메소드
	 * 매개변수 : 두 배열=>int[] arr1, int[] arr2
	 * 리턴타입 : 일치하는 숫자의 개수=>정수=>int
	 * 메소드명 : countLotto
	 * */
	public static int countLotto(int[] arr1, int[] arr2) {
		if(arr1==null || arr2==null) {
			return 0;
		}
		int sameCount=0;
		for(int tmp : arr1) {
			if(contains(arr2,tmp)) {
				sameCount++;
			}
		}
		return sameCount;
	}
	
	public static void printArray(int arr[]) {
		if(arr==null) {
			return;
		}
		for(int i=0; i<arr.length; i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}
	
	public static int [] createRandomArray(int min, int max, int size) {
		if(max-min+1 <= size) {
			return null;
		}
		int arr[] = new int[size];
		//i는 1부터 i가 배열의 크기보다 작을 때까지 1씩 증가
		//=> i는 0부터 i가 배열의 크기보다 작을 때까지
		for(int i=0; i<arr.length; ) {
			//랜덤한 수를 배열 i번지에 저장
			//=>랜덤한 수를 r에 저장
			int r = random(min,max);
			//배열에 r이 없으면 [배열 i번지에 r을 저장한 후 i를 1증가]
			if(!contains(arr, r)) {
				arr[i++] = r;
			} 
		}
		return arr;
	}
	
	public static int random(int min, int max) {
		if(min>max) {
			int tmp = min;
			min = max;
			max = tmp;
		}
		return (int)(Math.random()*(max-min+1)+min);
	}
	
	public static boolean contains(int []arr, int num) {
		if(arr==null || arr.length==0) {
			return false;
		}
		for(int tmp : arr) {
			if(num==tmp) {
				return true;
			}
		}
		return false;
	}
}
