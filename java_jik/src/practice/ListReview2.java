package practice;

import java.util.ArrayList;

public class ListReview2 {
//새로운 class를 만들어 이를 리스트로 응용하기 
//리스트 생성, add, get, contains(toString관리까지), 출력
	public static void main(String[] args) {
		ArrayList<Book> books = new ArrayList<Book>();
		books.add(new Book("양들의 침묵", 1));
		books.add(new Book("가나다라마바사",2));
		System.out.println(books);
		System.out.println(books.get(1));
		System.out.println("이 책이 있나요? "+books.contains(new Book("양들의 침묵")));
		//책 이름으로만 검색
		System.out.println("번호는? "+(books.indexOf(new Book("양들의 침묵"))+1));
	}

}
class Book{
	private String name;
	private int num;
	
	public Book(String name) {
		this.name = name;
		num = 0;
	}
	public Book(String name, int num) {
		this.name = name;
		this.num = num;
	}
	public String toString() {
		return num+"번. "+name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}