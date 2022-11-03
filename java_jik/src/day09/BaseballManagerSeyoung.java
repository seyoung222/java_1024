package day09;

import java.util.Scanner;

class Player{
	int playTime;
	String name;
	public Player(String name, int playTime){
		this.name=name;
		this.playTime=playTime;
	}
	public void setPlayTime(int num) {
		this.playTime = num;
	}
	public void print() {
		System.out.println(this.name+" "+this.playTime+"회");
	}
}

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
		int min=1, max=9, size=3;
		Player [] list = new Player[5];
		
		do {
		
		//메뉴 출력
		printMenu();
		//메뉴 선택
		menu = selectMenu();
		//선택한 메뉴에 따른 기능 실행
		runMenu(menu,min,max,size,list);
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
	public static void runMenu(int menu,int min,int max,int size, Player[]list) {
		switch(menu) {
		case 1:
			System.out.println("~~ 게임 플레이! ~~");
			playGame(min,max,size,list);
			break;
		case 2:
			printRank(list);
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
		System.out.println("-------------");
		System.out.print("메뉴 선택> ");
	}
	public static void playGame(int min, int max, int size, Player[]list) {
		Scanner scan = new Scanner(System.in);
		
		int []com = createRandomArray(min, max, size);
		printArray(com);
		
		int strike=0, ball=0;
		int playTime=0;
		do {
			playTime++;
			System.out.print((playTime)+"번째 입력 : ");
			int []user = scanArray(scan, size);
			
			strike=getStrike(com, user);
			ball=getBall(com, user);
			printGame(strike, ball);
			
		}while(strike<3);
		
		registerPlayer(list,playTime);
		
		System.out.println("게임 종료");
		
	}
	public static void printRank(Player[]list) {
		if(list==null) {
			return;
		}
		System.out.println(" *** 등수 *** ");
		for(int i=0; i<list.length; i++) {
			if(list[i]==null) {
				return;
			}
			System.out.print((i+1)+". ");
			list[i].print();
		}
	}
	// 입력받은 index등 부터의 배열을 뒤로 한칸씩 미루는 메소드
	public static Player[] pushRank(Player[]list, int index) {
		int lastNum=4;
		for(int i=4; i>0; i--) {
			if(list[i]==null) {
				lastNum--;
			}
		}
		if(lastNum==4) {
			for(int i=lastNum; i>index; i--) {
				list[i].playTime = list[i-1].playTime;
				list[i].name = list[i-1].name;
			}
		} else {
			list[lastNum+1]=new Player("",0);
			for(int i=lastNum; i>index-1; i--) {
				list[i+1].playTime = list[i].playTime;
				list[i+1].name = list[i].name;
			}
		}
		return list;
	}
	
	//점수를 비교해서 횟수가 더 적으면 등수에 등록하는 메소드
	public static void registerPlayer(Player[]list, int playTime) {
		if(list==null) {
			return;
		}
		Scanner scan = new Scanner(System.in);
		for(int i=0; i<5; i++) {
			if(list[i]==null) {
				System.out.print("이름을 입력하세요> ");
				String name=scan.next();
				list[i]=new Player(name, playTime);
				return;
			} else if(playTime<list[i].playTime) {
				pushRank(list,i);
				System.out.print("이름을 입력하세요> ");
				String name=scan.next();
				list[i]=new Player(name, playTime);
				return;
			}
		}
		
	}
//이하 숫자야구에서 했던 메소드 그대로(수정사항X)====================================
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

