package kr.kh.spring.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MemberVO {
	String me_id;
	String me_pw;
	String me_email;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date me_birthday;//화면에서 받아온 문자열을 날짜로 자동변환 시키기 위해 세터 어노테이션
	int me_authority;
	Date me_join_time;
	String me_session_id;
	Date me_session_limit;
	boolean autoLogin;
	
	
}
