package practice;
//객체 배열을 복사하려고 할때 객체도 깊은 복사해야하는지 테스트하는 용도
// => 객체를 여러군데 복사했을 경우 값들이 한번에 바뀜. 당연히 깊은 복사를 해아함.
// 하지만 옆으로 옮기기 위해서 복사하고 원래자리를 새로운 객체로 덮어쓰면 둘은 다른 객체기 때문에 별개로 취급.
public class ObjectArrayCopyTest {

	public static void main(String[] args) {
		Car [] arr = new Car[5];
		arr[0] = new Car("씽씽이", 1);
		arr[1] = new Car("돌돌이", 2);
		arr[2] = new Car("해피", 3);
		arr[3] = arr[2]; 
		
		for(Car tmp : arr) {
			if(tmp!=null) {
				tmp.print();
			}
		}
//		arr[2].num=5; //객체도 깊은 복사를 해야함. 복사하고 하나바꾸면 다른것도 같이 바뀜
		arr[2] = new Car("뉴멤바",7); //하지만 객체를 아예 새로만들어서 덮어씌우면 별개가 됨
		for(Car tmp : arr) {
			if(tmp!=null) {
				tmp.print();
			}
		}
	}

}
class Car{
	public String name;
	public int num;
	public Car() {}
	public Car(String name, int num) {
		this.name=name;
		this.num=num;
	}
	public void setNum(int num) {
		this.num=num;
	}
	public void print(){
		System.out.println(name+" : "+num);
	}
}