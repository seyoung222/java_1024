package practice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import lombok.Data;

public class BoardManager {
//day17 게시판 관리 프로그램 복습
	static Scanner scan = new Scanner(System.in);
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
		int menu=-1;
		ArrayList<Board> list = new ArrayList<Board>();
		do {
			printMenu();
			menu=scan.nextInt();
			runMenu(menu,list);
			
		}while(menu!=5);
	}
	private static void runMenu(int menu, ArrayList<Board> list) {
		switch(menu) {
		case 1:
			insertBoard(list);
			break;
		case 2: //조회 (전체조회, 상세조회)
			
			System.out.println("1. 전체 조회");
			System.out.println("2. 상세 조회");
			System.out.print("조회 메뉴 선택> ");
			int subMenu = scan.nextInt();
			switch(subMenu) {
			case 1:
				for(Board tmp : list) {
					System.out.println(tmp);
				}
				break;
			case 2:
				for(Board tmp : list) {
					System.out.println(tmp);
				}
				System.out.print("게시글 상세보기 선택> ");
				int index = list.indexOf(new Board(scan.nextInt()));
				//입력한 게시글 번호와 같은 글의 index를 찾아줌
				list.get(index).updateViews();
				list.get(index).print();
				break;
			default:
				System.out.println("잘못된 메뉴 선택입니다.");
			}
			break;
		case 3: //수정
			for(Board tmp : list) {
				System.out.println(tmp);
			}
			System.out.print("삭제할 게시글 선택> ");
			int updateIndex = list.indexOf(new Board(scan.nextInt()));
			System.out.print("제목 수정> ");
			String title = scan.nextLine();
			System.out.print("내용 수정> ");
			String content = scan.nextLine();
			list.get(updateIndex).update(title, content);
			break;
		case 4: //삭제
			for(Board tmp : list) {
				System.out.println(tmp);
			}
			System.out.print("삭제할 게시글 선택> ");
			int deleteIndex = list.indexOf(new Board(scan.nextInt()));
			list.remove(deleteIndex);
			break;
		case 5:
			System.out.println("프로그램 종료");
			break;
		default:
			System.out.println("잘못된 메뉴입니다.");
		}
	}
	private static void insertBoard(ArrayList<Board> list) {
		scan.nextLine();
		System.out.print("제목> ");
		String title = scan.nextLine();
		System.out.print("작성자> ");
		String writer = scan.nextLine();
		System.out.print("내용> ");
		String content = scan.nextLine();
		list.add(0, new Board(title, writer, content));
	}
	private static void printMenu() {
		System.out.println("=========메뉴========");
		System.out.println("1. 게시글 작성");
		System.out.println("2. 게시글 조회");
		System.out.println("3. 게시글 수정");
		System.out.println("4. 게시글 삭제");
		System.out.println("5. 종료");
		System.out.print("메뉴 선택> ");
		
	}

}
@Data
class Board{
	private static int count=1;
	private String title, writer, content;
	private int num, views;
	private Date date;
	public Board(String title, String writer, String content) {
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.num = count++;
		this.date = new Date();
	}
	public void update(String title, String content) {
		this.title=title;
		this.content=content;
	}
	public void updateViews() {
		views++;
	}
	private String getDateStr() {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		return f.format(date);
	}
	@Override
	public String toString() {
		return "["+num+"] 제목: "+title+" | 작성자: "+writer+" | 일시: "+getDateStr()+
				" | 조회수: "+views;
	}
	public void print() {
		System.out.println("["+num+"] 제목: "+title);
		System.out.println("작성자: "+writer+"  |  일시: "+getDateStr()+
			"  |  조회수: "+views);
		System.out.println("내용: "+content);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		if (num != other.num)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + num;
		return result;
	}
	public Board(int num) {
		this.num = num;
	}
	
}