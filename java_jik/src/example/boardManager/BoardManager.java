package example.boardManager;

import java.util.ArrayList;

import lombok.Data;

@Data
public class BoardManager {
	private ArrayList<Board>boards=new ArrayList<Board>();
	private ArrayList<Member>members=new ArrayList<Member>();
	
	
}
