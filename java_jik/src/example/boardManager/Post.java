package example.boardManager;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;

@Data
public class Post {

	private static int count=1;
	private int postNum;
	private String title, content, writer;
	private Date date;
	public Post(String title, String writer, String content) {
		this.postNum = count;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.date=new Date(0);
		count++;
	}
	public String getDateStr() {
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
		return d.format(date);
	}
	@Override
	public String toString() {
		return "["+postNum+"]  |  제목:"+title+"  |  작성자:"+writer+"  |  ("+getDateStr()+")\n내용 : "+content;
	}
	public void printBriefly() {
		System.out.println("["+postNum+"]  |  제목:"+title+"  |  작성자:"+writer+"  |  {"+getDateStr()+")");
	}
}
