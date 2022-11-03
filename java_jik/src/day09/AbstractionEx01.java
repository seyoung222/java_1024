package day09;

public class AbstractionEx01 {

	public static void main(String[] args) {
		Student std1 = new Student(1,1,1,"홍길동");
		std1.print();
		std1.updateScore(50,30,45);
		std1.print();
		
		
		
		/* 내가 한거
		Student st1 = new Student();
		st1.setInfor("Kim", 2, 5, 18);
		st1.setScore(70, 100, 80);
		st1.printInfo();
		*/
	}

}
/* 학생 성적 관리 프로그램에서 필요한 학생 클래스를 만들어 보세요. 
 * 학생은 학년, 반, 번호, 이름, 성적을 관리
 * 성적은 국어, 영어, 수학 성적만 관리. 각 과목은 100점 만점을 기준으로 관리
 * 학생 정보 출력 기능
 * 성적 수정하는 기능
 *  - 국어
 *  - 영어
 *  - 수학
 *  - 국어, 영어, 수학 (한번에 수정)
 * 학생 정보의 초기값
 *  - 학년:1, 반:1, 번호:1, 이름:빈문자열, 성적:0
 * */
class Student{
	private int grade;
	private int classNum;
	private int num;
	private String name;
	private int kor, eng, math;
	
	public void print() {
		System.out.println(grade+"학년 "+classNum+"반 "+num+"번 "+name);
		System.out.println("국어: "+kor+", 영어: "+eng+", 수학: "+math);
	}
	public void updateKor(int kor1) {
		kor = kor1;
	}
	public void updateEng(int eng1) {
		eng = eng1;
	}
	public void updateMath(int math1) {
		math = math1;
	}
	public void updateScore(int kor1, int eng1, int math1) {
		updateKor(kor1);
		updateEng(eng1);
		updateMath(math1);
	}
	public Student() {
		grade=1;
		classNum=1;
		num=1;
		name= "";
	}
	public Student(int grade1, int classNum1, int num1, String name1) {
		grade = grade1;
		classNum = classNum1;
		num = num1;
		name = name1;
	}
	
}


/* 내가 한거
class Student{
	private int grade, group, studentNumber, korScore, engScore, mathScore;
	private String studentName;
	public Student() { //빈문자열 초기화 어케함?????? => ""로 하면 됨!
		grade=1;
		group=1;
		studentNumber=1;
	}
	public void printInfo() {
		System.out.println(studentName+" 학생은 "+grade+"학년 "+group+"반 "+studentNumber+
				"번이고, 국어성적은 "+korScore+"점, 영어성적은 "+engScore+"점, 수학성적은 "
				+mathScore+"점 입니다.");
	}
	public void setInfor(String name, int n1, int n2, int n3) {
		studentName = name;
		grade = n1;
		group = n2;
		studentNumber = n3;
	}
	public void setKorScore(int newScore) {
		korScore = newScore;
	}
	public void setEngScore(int newScore) {
		engScore = newScore;
	}
	public void setMathScore(int newScore) {
		mathScore = newScore;
	}
	public void setScore(int kor, int eng, int math) {
		korScore = kor;
		engScore = eng;
		mathScore = math;
	}
	
}
*/