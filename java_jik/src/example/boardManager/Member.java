package example.boardManager;

import lombok.Data;

@Data
public class Member { 
	private String id,pw;

	public Member(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}
	
}
