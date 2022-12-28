package example.db.daosy;

import java.util.ArrayList;

import example.db.vosy.StudentVO;

public interface StudentDAO {

	void insertStudent(StudentVO std);

	ArrayList<StudentVO> selectAllStudent();

	void updateStudent(StudentVO std);

}
