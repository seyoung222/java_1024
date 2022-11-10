package day14;

import java.util.ArrayList;

import lombok.Data;

@Data
public class Student {
	//필드: 학년, 반, 번호, 이름, 성적들
	private int grade;
	private int classNum;
	private int num;
	private String name;
	private ArrayList<Score> scoreList = new ArrayList<Score>();
	
	public void printScore() {
//원래는 전체 배열 크기랑 채워진 크기가 달라서 향상된 for문 안썼는데 이제 됨
//		for(int i=0; i<count; i++) {
//			System.out.println(scoreList[i]); //toString있으므로 출력만하면됨
//		}
		for(Score tmp: scoreList) {
			System.out.println(tmp);
		}
	}
	
	/**
	 * 학생 정보에 성적을 추가하는 메소드
	 * @param 추가할 성적
	 * @return 추가 여부 
	 * */
	public boolean addScore(Score score) {
		if(score==null) {
//			return false;
			throw new RuntimeException("성적 정보가 없어서 추가할 수 없습니다.");
		}
		//배열이 꽉 찼을 때의 예외 처리> 지움
		
		//학생의 성적 정보들 중에서 과목, 학기가 같은 성적 정보가 없으면 추가
		boolean res = scoreList.contains(score);
		if(res) {
			return false;
		}
//		for(int i=0; i<count; i++) {
//			if(!score.equals(scoreList[i])) {
//				return false;
//			}
//		}
//		scoreList[count]=new Score(score); //복사 생성자를 만들어둬서 가능
//		count++;
		scoreList.add(new Score(score));
		return true;
	}
	
	
	//메소드: getter/setter, equals(): 같은 객체인지 비교하기 위해
	//      toString(): 객체를 쉽게 문자열로 만들기 위해
	
	@Override
	public String toString() {
		return "["+grade+"학년 "+classNum+"반 "+num+"번 "+name+"]";
	}
	
	//생성자: 초기화
	//학년, 반, 번호, 이름이 필요한 생성자
	public Student(int grade, int classNum, int num, String name) {
		this.grade = grade;
		this.classNum = classNum;
		this.num = num;
		this.name = name;
	}
	//학년, 반, 번호가 필요한 생성자(성적 추가시 학생이 있는지 없는지 판별할 때 활용)
	public Student(int grade, int classNum, int num) {
		this.grade = grade;
		this.classNum = classNum;
		this.num = num;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (classNum != other.classNum)
			return false;
		if (grade != other.grade)
			return false;
		if (num != other.num)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + classNum;
		result = prime * result + grade;
		result = prime * result + num;
		return result;
	}
}
