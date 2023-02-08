package kr.kh.spring;

public class InfoVO {
	private String name;
	private int num;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return "InfoVO [name=" + name + ", num=" + num + "]";
	} 
	/*
	public InfoVO(String name, int num) {
		this.name = name;
		this.num = num;
	} //이거 때문에 기본 생성자 없어져서 500번 에러발생.*/
}
