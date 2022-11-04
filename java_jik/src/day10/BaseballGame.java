package day10;

import java.util.Arrays;

public class BaseballGame {
	private int []com;
	private int []user;
	private int strike;
	private int ball;
	
	public BaseballGame(int[]com) { //깊은 복사
		this.com = Arrays.copyOf(com,com.length);
	}
	public void setUser(int []user) {
		this.user = Arrays.copyOf(user, user.length);
		calculateResult(); //유저가 입력될때마다 com과 user비교해서 결과 계산
	}
	private void calculateResult() {
		calculateStrike();
		calculateBall();
	}
	private void calculateStrike() {
		int count = 0;
		int size = com.length<user.length ? com.length : user.length;
		for(int i=0; i<size; i++) {
			if(com[i]==user[i]) {
				count++;
			}
		}
		strike = count; //필드인 strike에 저장하면 됨. 리턴할 필요x
	}
	private void calculateBall() {
		int count = 0;
		for(int i=0; i<com.length; i++) {
			for(int j=0; j<user.length; j++) {
				if(com[i]==user[j] && i!=j) {
					count++;
				}
			}
		}
		ball = count; //필드인 ball에 저장하면 됨. 리턴할 필요x
		
	}
	public void printResult() {
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
	public int getStrike() {
		return strike;
	}
}