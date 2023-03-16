package kr.kh.test.vo;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentVO {
	int co_num;
	String co_content;
	Date co_register_date;
	Date co_update_date;
	int co_ori_num;
	String co_me_id;
	int co_bo_num;
	
}
