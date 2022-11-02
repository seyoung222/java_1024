package day08;


public class ClassEx01 {

	public static void main(String[] args) {
		//기본 생성자 호출
		Point pt = new Point();
		pt.print();
		pt.move(5, 3);
		pt.print();
		
		//생성자 오버로딩 호출
		Point pt1 = new Point(3,1);
		pt1.print();
		//복사 생성자 호출(생성자 오버로딩)
		Point pt2 = new Point(pt1);
		pt2.print();
	}
}

/* 2차원 좌표평면의 점을 나타내는 클래스
 * - 필드
 * 	x좌표, y좌표
 * - 메소드(기능)
 *  좌표 출력 기능
 */
class Point{
	private int x,y;
	public void print() {
		System.out.println("좌표 : "+x+","+y);
	}
	public void move(int x1, int y1) { //일반적으로 필드의 값을 바꾸는 경우는 void
		x=x1;
		y=y1;
	}
	public Point() {
		x=3;
		y=3;
	}
	public Point(int x1, int y1) {
		x=x1;
		y=y1;
	}
	public Point(Point pt) {//복사생성자
		x=pt.x; //깊은 복사해야되므로 하나씩 값을 복사해줘야함
		y=pt.y;
	}
}