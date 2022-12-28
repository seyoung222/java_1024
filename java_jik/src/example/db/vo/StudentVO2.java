package example.db.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//mapper에서 resultNum을 적용하여 사용한 예시 : DB의 속성이름과 VO 필드 이름이 다를때
@Data
@AllArgsConstructor // 모든 요소 들어가는 생성자
@NoArgsConstructor // 기본 생성자
public class StudentVO2 {
	private String num;
	private String name;
	private int semester;
	private String state;
	private String professorNum;
	
}
