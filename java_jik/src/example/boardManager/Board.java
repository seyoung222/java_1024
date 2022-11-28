package example.boardManager;

import java.util.ArrayList;

public class Board {
	private String category;
	private ArrayList<Post> posts;
	public Board(String category, Post post) {
		if(post.isCategoryExist(category))
			post.categories.add(category);
		else {
			System.out.println("존재하지 않는 카테고리입니다. 카테고리를 먼저 생성하세요.");
			return;
		}
		posts.add(post);
	}
	
	
}
