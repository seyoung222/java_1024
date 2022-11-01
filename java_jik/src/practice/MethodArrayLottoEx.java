package practice;

public class MethodArrayLottoEx {

	public static void main(String[] args) {
		/* 숫자 6개를 입력하면 당첨번호와 함께 몇등인지 알려(출력해)주는 코드를 작성. 메소드를 이용할 것
		 * */
		
		printLottoResult(1,2,3,4,5,6);
		//출력 결과 예시)
		//당첨 번호: 5 2 1 4 3 7 보너스: 6
		//2등입니다.
	}
	public static void printLottoResult(int n1,int n2, int n3, int n4, int n5, int n6) {
		
//		당첨 번호 6개 생성
		int min=1, max=10; //원래 로또면 max=45인데 그럼 등수안에 들기 힘들어서 10으로 줄여서 확인했어요
		int count=0;
		int [] lotto = new int[6];
		for(; count<lotto.length; ) {
			int r = (int)(Math.random()*(max-min+1)+min);
			boolean isDuplicated = false;
			for(int i=0; i<count; i++) {
				if(r == lotto[i]) {
					isDuplicated = true;
				}
			}
			if(isDuplicated) {
				continue;
			} else {
				lotto[count]=r;
				count++;
			}
		}
		
//		보너스 번호 생성
		int bonus;
		while(true) {
			int r = (int)(Math.random()*(max-min+1)+min);
			boolean isDuplicated = false;
			for(int i=0; i<lotto.length; i++) {
				if(r == lotto[i]) {
					isDuplicated = true;
				}
			}
			if(isDuplicated) {
				continue;
			} else {
				bonus=r;
				break;
			}
		}
		
//		당첨번호+ 보너스번호 출력
		System.out.print("당첨 번호: ");
		for(int tmp : lotto) {
			System.out.print(tmp+" ");
		}
		System.out.println("보너스: "+bonus);
		
//		몇 개의 숫자가 일치하는지 확인
		int user[] = {n1, n2, n3, n4, n5, n6};
		int sameCount =0;
		for(int i=0; i<user.length; i++) {
			for(int j=0; j<lotto.length; j++) {
				if(user[i] == lotto[j]) {
					sameCount++;
				}
			}
		}
		
//		보너스번호를 맞췄는지 아닌지 확인
		boolean isBonusSame=false;
		for(int i=0; i<user.length; i++) {
			if(bonus == user[i]) {
				isBonusSame = true;
			}
		}
		
//		일치한 숫자 개수에 따라 등수 결과 출력
		switch(sameCount) {
		case 6:
			System.out.println("1등입니다.");
			break;
		case 5:
			if(isBonusSame) {
				System.out.println("2등입니다.");
			} else {
				System.out.println("3등입니다.");
			}
			break;
		case 4:
			System.out.println("4등입니다.");
			break;
		case 3:
			System.out.println("5등입니다.");
			break;
		default: 
			System.out.println("꽝~");
		}
	}
}
