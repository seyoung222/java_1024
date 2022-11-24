package day24;

import java.io.Serializable;

import lombok.Data;

@Data
public class Student implements Serializable{
	private static final long serialVersionUID = -5072470046372390335L;
	private String name;
	private int grade, classNum, num;
	public Student(String name, int grade, int classNum, int num) {
		this.name = name;
		this.grade = grade;
		this.classNum = classNum;
		this.num = num;
	}
	@Override
	public String toString() {
		return grade+"학년 "+classNum+"반 "+num +"번 "+name+" 학생";
	}
}
