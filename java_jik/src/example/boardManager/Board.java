package example.boardManager;

import java.util.ArrayList;

import lombok.Data;
@Data
public class Board {
	private static ArrayList<String> categories=new ArrayList<String>();
	private String category;
	private Post post;
	
	public Board(String category, Post post) {
		if(categories==null || categories.size()==0) {
			categories.add("공지");
			categories.add("자유");
		}
		if(categories.contains(category))
			this.category=category;
		else {
			System.out.println("존재하지 않는 카테고리입니다. 자유게시판에 등록됩니다.");
			this.category="자유";
			return;
		}
		this.post=post;
	}
	public void makeNewCategory(String category) {
		if(categories.contains(category)) {
			System.out.println("이미 등록된 카테고리입니다.");
		}else {
			categories.add(category);
		}
	}
	
	public void printCategories() {
		System.out.println(categories);
	}
	@Override
	public String toString() {
		return category+"   " + post;
	}
}
