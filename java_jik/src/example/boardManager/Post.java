package example.boardManager;

import java.util.ArrayList;

public class Post {

	private ArrayList<String> categories;
	private static int count=1;
	private int postNum;
	private String title, content;
	public Post(int postNum, String title, String content) {
		this.postNum = count;
		this.title = title;
		this.content = content;
		count++;
	}
	public boolean isCategoryExist(String category) {
		if(categories.contains(category))
			return true;
		else
			return false;
	}
	public void makeNewCategory(String category) {
		if(categories.contains(category)) {
			System.out.println("이미 등록된 카테고리입니다.");
		}else {
			categories.add(category);
		}
	}
}
