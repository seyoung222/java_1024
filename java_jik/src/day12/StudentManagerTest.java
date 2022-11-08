package day12;

import java.util.Scanner;

public class StudentManagerTest {

	public static void main(String[] args) {
		/* 학생 성적 관리 프로그램을 작성하세요.
		 * - 작성하기 위해 필요한 작업들을 주석으로 정리해보세요.
		 * 1. 프로그램에 필요한 기능을 정리
		 *  메뉴
		 *  1. 학생 정보 추가
		 *  2. 학생 성적 추가
		 *  3. 학생 정보 출력
		 *  4. 프로그램 종료
		 *  
		 * 2. 프로그램 실행 과정은 어떻게 할 건지?
		 *  기능
		 *  1. 학생정보 추가
		 *   - 학년, 반, 번호, 이름을 이용하여 학생 정보 추가
		 *   - 학년, 반, 번호가 같은 학생이 이미 있으면 추가하지 않음
		 *  2. 학생 성적 추가
		 *   - 학년, 반, 번호로 학생을 검색한 후 있으면 성적을 추가
		 *   - 성적 추가시 과목명, 학기, 중간, 기말, 수행평가를 입력하여 추가
		 *   - 이미 추가된 과목(학기랑 과목명이 같으면)이면 추가하지 않음
		 *  3. 학생 정보 출력
		 *    
		 * 3. 정보를 효율적으로 관리하기 위해 클래스를 만들것인가? 만든다면 어떤 클래스를 만들건지?
		 *  - 학생클래스 HighStudent
		 *  - 성적클래스 Score
		 * 
		 * 4. 프로그램 실행 과정을 주석으로 작성
		 * */
		Scanner scan = new Scanner(System.in);
		for( ; ; ) {
		//메뉴 출력
			System.out.println("------메뉴------");
			System.out.println("1. 학생 정보 추가");
			System.out.println("2. 학생 성적 추가");
			System.out.println("3. 학생 정보 출력");
			System.out.println("4. 프로그램 종료");
			System.out.println("메뉴 선택> ");
		//메뉴 선택
			int menu = scan.nextInt();
			//선택한 메뉴에 따른 기능 실행
			if(menu==1) {
				System.out.println("1. 학생 정보 추가");
				//1. 학생 정보 추가
			//학생 정보를 입력(학년, 반, 번호, 이름)
			//입력한 학생 정보가 없으면 추가
			} else if(menu==2) {
				System.out.println("2. 학생 성적 추가");
				//2. 학생 성적 추가
			//학생 정보를 입력(학년, 반, 번호)
			//입력한 학생 정보가 없으면 끝
				
			//성적 정보를 입력(과목, 학기, 중간, 기말, 수행평가)
			//입력한 성적 정보가 없으면 추가	
			} else if(menu==3) {
				System.out.println("3. 학생 정보 출력");
				//3. 학생 정보 출력
			//저장된 학생 정보를 출력
			} else if(menu==4) {
				System.out.println("4. 프로그램 종료");
				break;
			} else {
				System.out.println("잘못된 메뉴입니다.");
			}
		
		
		
		
			
		
		}
		HighStudent [] students = new HighStudent[30];
		
		
	}
	//학년, 반, 번호가 같은 사람이 없으면 새 학생을 추가하고 있으면 추가하지 않는 메소드
	public static void addStudent(int grade, int classNum, int num, String name,HighStudent [] students) {
		HighStudent s1 = new HighStudent(grade, classNum, num, name);
		int count=0;
		for(int i=0; i<30; i++) {
			if(students[i]==null) {
				break;
			}
			if(s1.equals(students[i])) {
				return;
			}
			count++;
		}
		students[count]=s1;
	}
	
	public static void setScore(HighStudent[]students) {
		//학생 검색
		Scanner sc = new Scanner(System.in);
		System.out.print("성적을 등룍할 학생의 학년, 반, 번호> ");
		int grade=sc.nextInt();
		int classNum=sc.nextInt();
		int num=sc.nextInt();
		//학생 없으면 끝
		if(findStudent(grade,classNum,num,students)==-1) {
			return;
		}
		
		//학생 있고, 성적 정보가 없으면 등록 (있으면 끝)
		String sub=sc.nextLine();
		System.out.print("성적 입력(과목(kor/eng/math), 학기, 중간, 기말, 수행평가)> ");
		sub=sc.nextLine();
		int semester=sc.nextInt();
		int mid=sc.nextInt();
		int fin=sc.nextInt();
		int per=sc.nextInt();
		
		
	}
	//학생 검색해서 있으면 몇번째(index)학생인지, 없으면 -1 리턴하는 메소드
	public static int findStudent(int grade,int classNum,int num,HighStudent [] students) {
		HighStudent s1 = new HighStudent(grade, classNum, num, "");
		for(int i=0; i<30; i++) {
			if(students[i]==null) {
				return -1;
			}
			if(s1.equals(students[i])) {
				return i;
			}
		}
		return -1;
	}
}

class HighStudent{
	Score []kor = new Score[2]; 
	Score []eng = new Score[2]; 
	Score []math = new Score[2]; 
	int grade, classNum, num;
	String name;
	
	
	public HighStudent() {
		grade=1; classNum=1; num=1; name="";
	}
	public HighStudent(int grade, int classNum, int num, String name) {
		this.grade=grade;
		this.classNum=classNum;
		this.num=num;
		this.name=name;
	}
	public void setScore(Score[] sub,int semester,int mid,int fin,int per) {
		Score s1 = new Score(semester,mid,fin,per);
		if(s1.equals(sub[0]) || s1.equals(sub[1])){
			return;
		}
		//성적이 없으면 성적 추가 하면 됨
		
	}
	
	public void print() {
		this.toString();
	}
	@Override
	public String toString() {
		return "HighStudent [grade=" + grade + ", classNum=" + classNum + ", num=" + num + ", name=" + name + "]";
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HighStudent other = (HighStudent) obj;
		if (classNum != other.classNum)
			return false;
		if (grade != other.grade)
			return false;
		if (num != other.num)
			return false;
		return true;
	}
	
}

class Score{
	int semester, mid, fin, per;
	public Score() {}
	public Score(int semester,int mid,int fin,int per) {
		this.semester=semester;
		this.mid=mid;
		this.fin=fin;
		this.per=per;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Score other = (Score) obj;
		if (semester != other.semester)
			return false;
		return true;
	}
}