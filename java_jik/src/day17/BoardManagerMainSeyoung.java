package day17;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;

import lombok.Data;

public class BoardManagerMainSeyoung {
	
	public static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		/* 게시글 관리 프로그램을 작성하세요.
		 * 1. 게시글 작성
		 * 2. 게시글 조회
		 * 3. 게시글 수정
		 * 4. 게시글 삭제
		 * 5. 프로그램 종료
		 * - 게시글은 번호, 제목, 내용, 작성자, 작성일, 조회수로 구성
		 * - 게시글 전체 조회는 나중에 작성된 게시글이 상단에 조회
		 * - 게시글 상세 조회는 게시글 번호를 입력하여 게시글 내용을 확인
		 * - 게시글 상세 조회를 하면 조회수가 1 증가
		 * - 게시글 수정은 게시글 번호를 선택하면 해당 게시글의 제목, 내용을 수정
		 * - 게시글 삭제는 게시글 번호를 선택하면 해당 게시글을 삭제
		 * */
		ArrayList<Post> board = new ArrayList<Post>();
		int menu=0;
		do {
			printMenu();
			menu = scan.nextInt();
			runMenu(board,menu);
			
		}while(menu!=5);
	}
	
	private static void runMenu(ArrayList<Post> board, int menu) {
		switch(menu) {
		case 1://게시글 작성
			writePost(board);
			break;
		case 2: //게시글 조회 전체조회->게시글번호로 상세조회(조회수 1증가)
			viewPost(board);
			break;
		case 3://게시글 수정->게시글 번호로 제목, 내용 수정
			if(updatePost(board)) {
				System.out.println("게시글이 수정되었습니다.");
			};
			break;
		case 4://게시글 삭제->게시글 번호로 게시글 삭제
			deletePost(board);
			break;
		case 5:
			System.out.println("프로그램 종료");
			break;
		default:
			System.out.println("잘못된 메뉴 선택입니다.");
		}
	}
	
	private static boolean deletePost(ArrayList<Post> board) {
		sortPrintPost(board);
		System.out.print("수정할 게시글 번호 입력> ");
		int deleteNum = board.size()-scan.nextInt();;
		if(deleteNum<0 || deleteNum>=board.size()) {
			System.out.println("게시글 번호가 올바르지 않습니다.");
			return false;
		}
		board.remove(deleteNum);
		return true;
	}

	private static boolean updatePost(ArrayList<Post> board) {
		sortPrintPost(board);
		System.out.print("수정할 게시글 번호 입력> ");
		int updateNum = board.size()-scan.nextInt();;
		if(updateNum<0 || updateNum>=board.size()) {
			System.out.println("게시글 번호가 올바르지 않습니다.");
			return false;
		}
		System.out.print("수정할 제목> ");
		board.get(updateNum).setSubject(scan.nextLine());
		System.out.print("수정할 내용> ");
		board.get(updateNum).setContents(scan.nextLine());		
		return true;
	}

	private static void sortPrintPost(ArrayList<Post>board) {
		ArrayList<Post> sort = board;
		if(sort.size()>1) { 
			Collections.sort(board, new Comparator<Post>() {
				@Override
				public int compare(Post o1, Post o2) {
					return o2.getPostNum() - o1.getPostNum();
				}
			});
		}
		for(Post tmp : sort) {
			System.out.println("["+tmp.getPostNum()+"] 제목: "+tmp.getSubject());
		}
	}
	private static void viewPost(ArrayList<Post> board) {
		sortPrintPost(board);
		
		System.out.print("조회할 게시글 번호 입력> ");
		int viewNum = board.size()-scan.nextInt();
		
		board.get(viewNum).setViews(board.get(viewNum).getViews()+1);
		System.out.println(board.get(viewNum));
	}

	private static void writePost(ArrayList<Post> board) {
		scan.nextLine();
		System.out.print("제목> ");
		String subject=scan.nextLine();
		System.out.print("작성자> ");
		String writter=scan.nextLine();
		System.out.print("내용> ");
		String contents=scan.nextLine();
		Post post = new Post(subject, contents, writter);
		
		board.add(post);		
	}

	public static void printMenu() {
		System.out.println("1. 게시글 작성");
		System.out.println("2. 게시글 조회");
		System.out.println("3. 게시글 수정");
		System.out.println("4. 게시글 삭제");
		System.out.println("5. 종료");
		System.out.print("메뉴 선택> ");
	}
}
@Data
class Post{
	private static int postCount=1;
	private String subject, contents, writter, dateStr;
	private int postNum, views;
//	private String year, month, day;
	
	
	public Post(String subject, String contents, String writter) {
		this.postNum= postCount++;
		this.subject = subject;
		this.contents = contents;
		this.writter = writter;
		Date date=new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		this.dateStr=format.format(date);
		this.views = 0;
//		String[] date1 = dateStr.split("-");
//		this.year=date1[0];
//		this.month=date1[1];
//		this.day=date1[2];
		
	}
	@Override
	public String toString() {
		return "["+postNum+"] 제목: "+subject+"\n(작성자:"+writter+") ("+dateStr+
				") (조회수:"+views+")\n내용: "+contents;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		if (postNum != other.postNum)
			return false;
		return true;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + postNum;
		return result;
	}
	
}