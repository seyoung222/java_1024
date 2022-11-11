package practice;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import lombok.Data;

public class WordListMain {

	public static void main(String[] args) {
		/* 영어 단어장 프로그램을 구현하세요.
		 * 메뉴
		 * 1. 단어 추가
		 * 2. 단어 확인
		 * 3. 단어 수정
		 * 4. 단어 삭제
		 * 5. 종료
		 * 메뉴 선택 : 1
		 * 단어: apple [한 단어만 입력, 공백 입력 안함]
		 * 뜻 : 사가 [한 문자을 입력 받음]
		 * 메뉴
		 * 1. 단어 추가
		 * 2. 단어 확인
		 * 3. 단어 수정
		 * 4. 단어 삭제
		 * 5. 종료
		 * 메뉴 선택 : 2
		 * 단어 검색 : apple
		 * 뜻 : 사가 
		 * 메뉴
		 * 1. 단어 추가
		 * 2. 단어 확인
		 * 3. 단어 수정
		 * 4. 단어 삭제
		 * 5. 종료
		 * 메뉴 선택 : 3
		 * 단어 입력 : apple
		 * 1. apple : 사가 [입력한 단어의 뜻을 모두 나열]
		 * 수정할 번호 : 1
		 * 뜻 : 사과
		 * 수정이 완료됐습니다.
		 * 메뉴
		 * 1. 단어 추가
		 * 2. 단어 확인
		 * 3. 단어 수정
		 * 4. 단어 삭제
		 * 5. 종료
		 * 메뉴 선택 : 4
		 * 단어 입력 : apple
		 * 1. apple : 사과 
		 * 삭제할 번호 선택 : 1
		 * 삭제가 완료됐습니다.
		 * */
		
		ArrayList<Word> list = new ArrayList<Word>();
		int menu=-1;
		Scanner scan = new Scanner(System.in);
		do {
			printMenu();
			try {
				menu=scan.nextInt();
				runMenu(list, menu);
			}catch(InputMismatchException e) {
				System.out.println("예외 발생: 정수를 입력하세요.");
			}catch(Exception e) {
				System.out.println("!!" + e.getMessage());
			}
			
		}while(menu != 5);
		
	}

	private static void runMenu(ArrayList<Word> list, int menu) throws Exception {
		Scanner scan = new Scanner(System.in);
		int index=-1;
		switch(menu) {
		case 1:
			System.out.print("단어 입력> ");
			String word = scan.next();
			scan.nextLine();
			System.out.print("뜻 입력> ");
			String mean = scan.nextLine();
			list.add(new Word(word, mean));
			break;
		case 2:
			System.out.print("단어 입력> ");
			Word w1 =new Word(scan.next());
			//해당 단어를 가지고 있는 객체들의 뜻을 나열
			for(Word tmp : list) {
				if(tmp.getWord().equals(w1.getWord())) {
					System.out.println("뜻 : "+tmp.getMean());
				}
			}
			break;
		case 3:
			System.out.print("단어 입력> ");
			String word2 =scan.next();
			printWord(list,new Word(word2));
			
			System.out.print("수정할 번호 입력> ");
			index = scan.nextInt() -1;
			scan.nextLine();
			System.out.print("뜻 입력> ");
			String mean2=scan.nextLine();
			
			//index 자리를 새로운 객체로 교체
			list.set(index,new Word(word2, mean2));
			break;
		case 4:
			System.out.print("단어 입력> ");
			String word3 =scan.next();
			printWord(list,new Word(word3));
			
			System.out.print("삭제할 번호 > ");
			index= scan.nextInt() -1;
			list.remove(index);
			System.out.println("삭제가 완료되었습니다.");
			break;
		case 5:
			System.out.println("프로그램을 종료합니다.");
			break;
		default:
			throw new Exception("예외 발생: 잘못된 메뉴입니다.");
		}
	}
	
	private static void printWord(ArrayList<Word>list, Word word) {
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).equals(word)) {
				System.out.println((i+1)+". "+list.get(i));
			}
		}
	}
	private static void printMenu() {
		System.out.println("메뉴");
		System.out.println("1. 단어 추가");
		System.out.println("2. 단어 확인");
		System.out.println("3. 단어 수정");
		System.out.println("4. 단어 삭제");
		System.out.println("5. 종료");
		System.out.print("메뉴 선택 : ");
	}

}
@Data
class Word{
	private String word;
	private String mean;
	
	public Word(String word, String mean) {
		this.word=word;
		this.mean=mean;
	}
	public Word(String word) {
		this.word=word;
		mean="";
	}
	public String toString() {
		return word+" : "+mean;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((word == null) ? 0 : word.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Word other = (Word) obj;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		return true;
	}
	
}