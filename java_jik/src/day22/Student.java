package day22;

import lombok.Data;

@Data
public class Student{
	private String name, birth;

	public Student(String name, String birth) {
		this.name = name;
		this.birth = birth;
	}
	public Student(Student std) {
		this.name = std.name;
		this.birth = std.birth;
	}
	
}
