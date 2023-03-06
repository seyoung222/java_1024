package kr.kh.sprint.pagination;

import lombok.Data;

@Data
public class Criteria {
	
	private int page; //현재 페이지
	private int perPageNum;//한 페이지 당 컨텐츠 갯수
	private String search; //검색어
	private int type; //게시판 번호
	//Criteria 디폴트 생성자 : 현재 페이지를 1페이지로, 한 페이지에 10개의 컨텐츠
	public Criteria() {
		this.page = 1;
		this.perPageNum = 10;
		this.search = "";
		this.type = 0;
	}
	/* 쿼리문에서 limit에 사용되는 인덱스를 계산하는 getter */
	public int getPageStart() {
		return (this.page -1) * perPageNum;
	}
}
