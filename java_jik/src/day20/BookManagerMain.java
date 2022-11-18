package day20;

import java.util.ArrayList;
import java.util.Scanner;

public class BookManagerMain {

	public static void main(String[] args) {
		/* 도서 관리 프로그램을 작성하세요.
		 * 도서 정보
		 *  - 도서명, 저자(여러명, 엮은이, 옮긴이 다 포함), 가격, 출판사, 분류, ISBN
		 * 기능
		 *  - 도서 추가
		 *   - ISBN으로 정렬
		 *  - 도서 조회 (람다식 써보기)
		 *   - 도서명으로 조회
		 *   - 저자로 조회
		 *   - 출판사로 조회
		 *   - 분류로 조회
		 * */
		int menu =-1;
		Scanner scan = new Scanner(System.in);
		ArrayList<Book> list = new ArrayList<Book>();
		do {
			//메뉴 출력
			System.out.println("========메뉴========");
			System.out.println("1. 도서 추가");
			System.out.println("2. 도서 조회");
			System.out.println("3. 종료");
			System.out.print("메뉴 선택> ");
			//메뉴를 선택
			menu = scan.nextInt();
//tip: 얘를 char menu = scan.next().charAt(0)으로 받으면 입력에러 안나고 default에서 처리가능
			String title, author, publisher, genre, isbn;
			int price;
			//선택한 메뉴에 따른 기능 실행
			switch(menu) {
			//선택한 메뉴가 1번이면 도서 추가
			case 1:
				//추가할 도서 정보를 입력
				//도서명, 저자(여러명, 엮은이, 옮긴이 다 포함), 가격, 출판사, 분류, ISBN
				scan.nextLine();
				System.out.print("도서명 : ");
				title = scan.nextLine();
				System.out.print("저자 : ");
				author = scan.nextLine();
				System.out.print("가격 : ");
				price = scan.nextInt();
				scan.nextLine();
				System.out.print("출판사 : ");
				publisher = scan.nextLine();
				System.out.print("장르 : ");
				genre = scan.nextLine();
				System.out.print("ISBN : ");
				isbn = scan.next();
				
				//도서목록에 새 도서를 추가
				//위에서 입력받은 도서 정보를 이용하여 도서 객체를 생성
				Book book = new Book(title, author, publisher, genre, isbn, price);
				
				//생성된 도서 객체를 리스트에 추가
				//isbn 중복체크해서 중복되면 건너뜀(여기서 중복=리스트에 book객체가 포함되었다.)
				if(list.contains(book)) {
					System.out.println("이미 등록된 isbn 번호 입니다.");
					continue;
				}
				//중복되지 않으면 추가
				list.add(book);
				System.out.println("도서 추가가 완료되었습니다.");
				System.out.println("====================");
				break;
			//선택한 메뉴가 2번이면 도서 조회
			case 2:
				/* 도서조회: 도서명, 저자, 출판사, 분류*/
				//서브 메뉴 출력
				System.out.println("=======메뉴 조회=======");
				System.out.println("1. 도서명");
				System.out.println("2. 저자");
				System.out.println("3. 출판사");
				System.out.println("4. 장르");
				System.out.println("5. 취소");
				System.out.print("조회 방법 선택 : ");
				//서브 메뉴 선택
				int subMenu = scan.nextInt();
				System.out.println("====================");
				
				//선택한 서브 메뉴 실행
				int count = 9;
				switch(subMenu) {
				case 1: //도서명으로 조회
					System.out.print("도서명");
					scan.nextLine();
					title= scan.nextLine();
					System.out.println();
					for(Book tmp : list) {
						if(tmp.getTitle().contains(title.trim())) {//공백없애고 문장 비교
							System.out.println(tmp);
							count++;
						}
					}
					if(count==0) {
						System.out.println("검색 결과가 없습니다.");
					}
					break;
				case 2: //저자로 조회
					System.out.print("저자");
					author = scan.nextLine();
					for(Book tmp : list) {
						if(tmp.getAuthor().contains(author.trim())) {
							System.out.println(tmp);
							count++;
						}
					}
					if(count==0) {
						System.out.println("검색 결과가 없습니다.");
					}
					break;
				case 3: //출판사로 조회
					System.out.print("출판사");
					publisher = scan.nextLine();
					for(Book tmp : list) {
						if(tmp.getPublisher().contains(publisher.trim())) {
							System.out.println(tmp);
							count++;
						}
					}
					if(count==0) {
						System.out.println("검색 결과가 없습니다.");
					}
					break;
				case 4: //분류로 조회
					System.out.print("장르");
					genre = scan.nextLine();
					for(Book tmp : list) {
						if(tmp.getGenre().contains(genre.trim())) {
							System.out.println(tmp);
							count++;
						}
					}
					if(count==0) {
						System.out.println("검색 결과가 없습니다.");
					}
					break;
				case 5: //취소
					System.out.println("조회를 취소했습니다.");
					break;
				default: System.out.println("메뉴가 잘못되었습니다.");
				}
				break;
			//선택한 메뉴가 3번이면 프로그램 종료 출력
			case 3:
				System.out.println("프로그램 종료");
				break;
			//선택한 메뉴가 1,2,3이 아니면 잘못된 메뉴라고 출력
			default:
				System.out.println("잘못된 메뉴입니다.");
			}
		}while(menu!=3);
		scan.close();
	}

}
