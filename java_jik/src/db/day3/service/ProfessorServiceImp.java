package db.day3.service;

import java.util.Scanner;

import db.day3.DBConnector;
import db.day3.dao.*;
import db.day3.vo.*;

public class ProfessorServiceImp implements ProfessorService{
	
	private ProfessorMapper professorDao;
	private StudentMapper studentDao;
	private Scanner scan = new Scanner(System.in);
	
	public ProfessorServiceImp(DBConnector dbConnector) {
		professorDao = new ProfessorMapper(dbConnector); 
		studentDao = new StudentMapper(dbConnector);
	}

	public void insertProfessor() {
		System.out.println("추가할 교수 정보를 입력하세요.");
		System.out.print("교번> ");
		String pr_num = scan.nextLine();
		System.out.print("이름> ");
		String pr_name = scan.nextLine();
		System.out.print("직위> ");
		String pr_state = scan.nextLine();
		System.out.print("학부번호> ");
		String pr_de_num = scan.nextLine();
		System.out.print("연락처> ");
		String pr_tel= scan.nextLine();
		ProfessorVO pf = new ProfessorVO(pr_num, pr_name, pr_state, pr_de_num, pr_tel);
		professorDao.insertProfessor(pf);
		
	}

	public void updateProfessor() {
		System.out.println("수정할 교수 정보를 입력하세요.");
		System.out.print("교번> ");
		String pr_num = scan.nextLine();
		System.out.print("이름> ");
		String pr_name = scan.nextLine();
		System.out.print("직위> ");
		String pr_state = scan.nextLine();
		System.out.print("학부번호> ");
		String pr_de_num = scan.nextLine();
		System.out.print("연락처> ");
		String pr_tel= scan.nextLine();
		ProfessorVO pf = new ProfessorVO(pr_num, pr_name, pr_state, pr_de_num, pr_tel);
		professorDao.updateProfessor(pf);
		
	}

	public void updateAdvisor() {
		System.out.print("교번> ");
		String st_pr_num = scan.nextLine();
		System.out.print("학번> ");
		String st_num = scan.nextLine();
		StudentVO std = studentDao.selectStudent(st_num);
		if(std==null) {
			System.out.println("존재하지 않는 학번입니다.");
			return;
		}
		std.setSt_pr_num(st_pr_num);
		studentDao.updateStudent(std);
	}
}
