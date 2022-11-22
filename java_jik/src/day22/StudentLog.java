package day22;

import lombok.Data;

@Data
public class StudentLog {
	private Student std;
	private String state;
	public StudentLog(String name, String birth, String state) {
		std = new Student(name, birth);
		this.state = state;
	}
	public StudentLog(Student std, String state) {
		this.std =new Student(std); //깊은 복사 위해
		this.state = state;//state도 참조변수인데 왜 깊은복사 안함? String의 특징=값이 바뀔 수 없음
		//리터럴 문자열을 할당받고, 바뀔경우 새로운 문자열을 생성해 배당하기 때문이지 일반적으로 참조번수는 깊은복사해야함
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentLog other = (StudentLog) obj;
		if (std == null) {
			if (other.std != null)
				return false;
		} else if (!std.equals(other.std))
			return false;
		return true;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((std == null) ? 0 : std.hashCode());
		return result;
	}
	
	
}
