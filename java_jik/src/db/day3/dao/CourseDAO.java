package db.day3.dao;

import db.day3.vo.CourseVO;
import db.day3.vo.LectureVO;

public interface CourseDAO {
	void insertLecture(LectureVO lec);
	void updateLecture(LectureVO lec);
	void deleteLecture(int le_num);
	LectureVO selectLectureByName(String le_name, int le_class, int le_year, String le_term);
	void insertCourse(CourseVO courseVO);
	void deleteCourse(int le_num, String co_st_num);
}
