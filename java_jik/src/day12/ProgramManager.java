package day12;

public interface ProgramManager {
	void printMenu();
	int selectMenu();
	void run();
	// 이걸 전에 만들어둔 day10.StudentManager 클래스에 implements 시켜도 문제없이 돌아감
	// 왜냐면 거기에는 이미 동일이름/동일리턴타입의 메소드가 구현되어있기 때문
}
