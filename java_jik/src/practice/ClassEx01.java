package practice;

public class ClassEx01 {
	public static void main(String[] args) {
		Point pt = new Point();
		pt.print();
		
		
		Point [] pts = new Point[3];
		for(int i=0; i<pts.length; i++) {
			pts[i] = new Point(i,i);
			pts[i].print(); //x,y 출력됨
		}
		for(Point p1 : pts) {
			System.out.println(p1); //배열에서 Point의 값이 저장된 주소값이 출력
			p1.print(); //Point라는 객체 속 x,y의 값을 출력함
		}
	}
}

class Point{
	private int x, y;
	public Point() {}
	public Point(int x1, int y1) {
		x=x1;
		y=y1;
	}
	public void move(int x1, int y1) {
		x=x1; y=y1;
	}
	public void print() {
		System.out.println(x+", "+y);
	}
}