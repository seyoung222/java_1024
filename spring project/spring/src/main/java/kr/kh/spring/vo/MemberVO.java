package kr.kh.spring.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;

@Data
public class MemberVO {
	String me_id;
	String me_pw;
	String me_email;
	Date me_birthday;
	int me_authority;
	Date me_join_time;
	
	//화면에서 받아온 문자열을 날짜로 자동변환 시키기 위한 새로운 세터를 생성
	public void setMe_birthday(String str) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			me_birthday = format.parse(str);
		} catch (ParseException e) {
			me_birthday = null;
		}
	}
}
