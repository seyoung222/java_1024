package day09;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class BaseballManagerSeyoung {

	public static void main(String[] args) {
		/* 숫자 야구게임을 플레이한 후, 기록을 저장하는 프로그램을 작성하세요.
		 * 메뉴
		 * 1. 플레이
		 * 2. 기록확인
		 * 3. 종료 
		 * 메뉴 선택 : 2
		 * 1. 홍길동 2회
		 * 2. 임꺽정 3회
		 * 3. 이몽룡 3회
		 * 4. 춘향이 4회
		 * */
		int menu=0;
		int count=0;
		int min=1, max=9, size=3;
		Player [] list = new Player[30];
		do {
		
		//메뉴 출력
		printMenu();
		//메뉴 선택
		menu = selectMenu();
		//선택한 메뉴에 따른 기능 실행
		runMenu(menu,min,max,size,list,count);
			//1. 플레이
				//컴퓨터가 랜덤으로 숫자 생성
				//사용자가 숫자 입력
				//판별
				//3S 종료
				//[new] 횟수를 기록 (이전 기록보다 좋을 때 기록, 최대 5등)
				//5등 기준으로 회수가 동일한 경우 먼저 플레이한 사용자 기록을 유지
			//2. 기록확인
				//등록된 5위까지의 기록을 확인
			//3. 종료
		
		
			
		}while(menu!=3);
		
	}
	public static void runMenu(int menu,int min,int max,int size, Player[]list,int count) {
		switch(menu) {
		case 1:
			System.out.println("~~ 게임 플레이! ~~");
			playGame(min,max,size,list,count);
			break;
		case 2:
			//기록 확인
			break;
		case 3:
			System.out.println("프로그램을 종료합니다.");
			break;
		default: 
			System.out.println("잘못된 메뉴를 입력하셨습니다.");
		}
	}
	public static int selectMenu() {
		Scanner scan = new Scanner(System.in);
		return scan.nextInt();
	}
	public static void printMenu() {
		System.out.println("-----메뉴-----");
		System.out.println("1. 플레이");
		System.out.println("2. 기록확인");
		System.out.println("3. 종료");
	}
	public static void playGame(int min, int max, int size, Player[]list, int count) {
		Scanner scan = new Scanner(System.in);
		
		
		int []com = createRandomArray(min, max, size);
		printArray(com);
		
		int strike=0, ball=0;
		int playTime=0;
		do {
			System.out.println("입력 : ");
			int []user = scanArray(scan, size);
			
			strike=getStrike(com, user);
			ball=getBall(com, user);
			printGame(strike, ball);
			playTime++;
			
		}while(strike<3);
		
		setRanking(playTime, list, count);
		
		System.out.println("게임 종료");
		scan.close();
	}
	
	//5등과 성적을 비교하여 더 높을 때 성적을 등록하는 메소드
	public static int setRanking(int playTime, Player[]list, int count) {
		Scanner scan = new Scanner(System.in);
		
		
		if(count<=5) {
			System.out.println("이름을 입력하세요> ");
			list[count].name = scan.next();
			list[count].playTime = playTime;
			count++;
			return count;
		} 
		else {
			int worst = getRankingInteger[4];
			
						
			if(playTime<=worst) {
				return count;
			} else {
				System.out.println("이름을 입력하세요> ");
				list[count].name = scan.next();
				list[count].playTime = playTime;
				count++;
				return count;
			}
		}
	}
	public static Integer[] getRankingArray(int playTime, Player[]list, int count) {
		Integer score[] = new Integer[30];
		for(int i=0; i<30; i++) {
			score[i]=list[i].playTime;
		}
		Arrays.sort(score, Collections.reverseOrder());
		
		return score;
	}
	public static void printGame(int strike, int ball) {
		if(strike!=0) {
			System.out.print(strike+"S");
		}
		if(ball!=0) {
			System.out.print(ball+"B");
		}
		if(strike==0 && ball==0) {
			System.out.print("OUT");
		}
		System.out.println();
	}
	public static int getBall(int[]arr1, int[]arr2) {
		if(arr1==null || arr2==null) {
			return 0;
		}
		int ball=0;
		int strike = getStrike(arr1, arr2);
		for(int num : arr1) {
			if(contains(arr2,num)) {
				ball++;
			}
		}
		return ball-strike;
	}
	public static int getStrike(int[]arr1, int[]arr2) {
		if(arr1==null || arr2==null) {
			return 0;
		}
		int size = arr1.length < arr2.length ? arr1.length : arr2.length;
		int strike=0;
		for(int i=0; i<size; i++) {
			if(arr1[i]==arr2[i]) {
				strike++;
			}
		}
		return strike;
	}
	
	public static int[] scanArray(Scanner scan, int size) {
		if(size<=0) {
			return null;
		}
		int [] arr = new int[size];
		for(int i=0; i<size; i++) {
			arr[i]=scan.nextInt();
		}
		return arr;
	}
	
	public static int countArray(int[] arr1, int[] arr2) {
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

class Player{
	int playTime;
	String name;
	public Player(String name){
		this.name=name;
	}
	public void setPlayTime(int num) {
		this.playTime = num;
	}
}