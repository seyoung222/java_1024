package db.day4.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor // 모든 요소 들어가는 생성자
@NoArgsConstructor // 기본 생성자
public class StudentVO {
	private String st_num;
	private String st_name;
	private int st_semester;
	private String st_state;
	private String st_pr_num;
	
}
