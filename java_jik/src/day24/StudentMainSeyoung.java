package day24;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import lombok.Data;

public class StudentMainSeyoung {
	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		/* 학생 정보 관리 프로그램을 작성하세요.
		 * 1. 학생 추가( 이름, 학년, 반, 번호)
		 * 2. 학생 출력
		 * 3. 종료
		 * - 프로그램 시작 전 학생 정보를 읽어오는 기능 추가(load)
		 * - 프로그램 종료 전 학생 정보를 저장하는 기능 추가(save)
		 * */
		FileInputStream fis = null; ;
		FileOutputStream fos = null; 
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		
		ArrayList<Student>list = new ArrayList<Student>();
		load(fis,ois,list);
		System.out.println("프로그램을 시작합니다.");
		
		int menu =-1;
		do {
//			System.out.println("확인용: "+list);//확인용
			printMenu();
			menu = scan.nextInt();
			runMenu(menu,list,fos,oos);
		}while(menu!=4);
		save(fos,oos,list);
		System.out.println("프로그램을 종료합니다.");
	}
	private static void load(FileInputStream fis, ObjectInputStream ois, ArrayList<Student>list) {
		try {
			fis = new FileInputStream("studentMain.txt");
			ois = new ObjectInputStream(fis);
			
			while(true) {
				Student std = (Student)ois.readObject();
				list.add(std);
			}
		}catch(FileNotFoundException e) {
			try{
				File f = new File("studentMain.txt");
				f.createNewFile();
			}catch(IOException ee) {
				ee.printStackTrace();
			}
		}catch(EOFException e) {
			System.out.println("파일 로딩이 완료되었습니다...");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				fis.close();
				ois.close();
			}catch(NullPointerException ee) {
				System.out.println("저장된 정보가 없습니다.");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	private static void printMenu() {
		System.out.println("=========메뉴=========");
		System.out.println("1. 학생 추가");
		System.out.println("2. 학생 출력");
		System.out.println("3. 학생 삭제");
		System.out.println("4. 종료");
		System.out.print("메뉴 선택> ");
	}
	private static void runMenu(int menu, ArrayList<Student>list,
			FileOutputStream fos, ObjectOutputStream oos) {
		switch(menu) {
		case 1:
			insertStudent(list);
			break;
		case 2:
			printStudent(list);
			break;
		case 3:
			removeStudent(list);
			break;
		case 4:
			break;
		default:
			System.out.println("잘못된 메뉴입니다.");
		}
	}
	private static void insertStudent(ArrayList<Student>list) {
		System.out.print("이름> ");
		String name = scan.next();
		System.out.print("학년> ");
		int grade = scan.nextInt();
		System.out.print("반> ");
		int classNum = scan.nextInt();
		System.out.print("번호> ");
		int num = scan.nextInt();
		list.add(new Student(name, grade, classNum, num));
		Collections.sort(list,new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				if(o1.getGrade()!=o2.getGrade())
					return o1.getGrade()-o2.getGrade();
				if(o1.getClassNum()!=o2.getClassNum())
					return o1.getClassNum()-o2.getClassNum();
				return o1.getNum()-o2.getNum();
			}
		});
		return;
	}
	private static void printStudent(ArrayList<Student>list) {
		for(Student std : list) {
			System.out.println(std);
		}
	}
	private static void removeStudent(ArrayList<Student>list) {
		System.out.print("이름> ");
		String name = scan.next();
		System.out.print("삭제할 학생의 학년 반 번호>");
		int grade = scan.nextInt();
		int classNum = scan.nextInt();
		int num = scan.nextInt();
		Student std = new Student(name, grade, classNum, num);
		int index = list.indexOf(std);
		list.remove(index);
		System.out.println(std+" 정보가 삭제되었습니다.");
	}
	private static void save(FileOutputStream fos, ObjectOutputStream oos, ArrayList<Student> list) {
		try {
			fos = new FileOutputStream("studentMain.txt");
			oos = new ObjectOutputStream(fos);
			
			for(Student std : list) {
				oos.writeObject(std);
			}
		}catch(Exception e) {
			System.out.println("백업 과정에서 오류가 발생하였습니다.");
			e.printStackTrace();
		}finally {
			try {
				fos.close();
				oos.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("리스트 저장이 완료되었습니다...");
	}

}