package db.day3.dao;

import java.sql.SQLException;

import db.day3.DBConnector;
import db.day3.vo.ProfessorVO;

public class ProfessorMapper implements ProfessorDAO{
	
	private DBConnector dbConnector;
	
	public ProfessorMapper(DBConnector dbConnector) {
		this.dbConnector = dbConnector;
	}

	public void insertProfessor(ProfessorVO pf) {
		String sql = "insert into professor(pr_num, pr_name, pr_state, pr_de_num, pr_tel) "
				+"values(?, ?, ?, ?, ?)";
		try {
			dbConnector.pstmt = dbConnector.con.prepareStatement(sql);
			dbConnector.pstmt.setString(1, pf.getPr_num());
			dbConnector.pstmt.setString(2, pf.getPr_name());
			dbConnector.pstmt.setString(3, pf.getPr_state());
			dbConnector.pstmt.setString(4, pf.getPr_de_num());
			dbConnector.pstmt.setString(5, pf.getPr_tel());
			int count = dbConnector.pstmt.executeUpdate();
			if(count == 0)
				System.out.println("교수 정보 추가 실패");
			else
				System.out.println("교수 정보 추가 성공");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateProfessor(ProfessorVO pf) {
		String sql = "update professor set pr_name = ?, pr_state = ?, pr_de_num = ?, "
				+ "pr_tel = ? where pr_num = ?";
		try {
			dbConnector.pstmt = dbConnector.con.prepareStatement(sql);
			dbConnector.pstmt.setString(5, pf.getPr_num());
			dbConnector.pstmt.setString(1, pf.getPr_name());
			dbConnector.pstmt.setString(2, pf.getPr_state());
			dbConnector.pstmt.setString(3, pf.getPr_de_num());
			dbConnector.pstmt.setString(4, pf.getPr_tel());
			int count = dbConnector.pstmt.executeUpdate();
			if(count == 0)
				System.out.println("교수 정보 수정 실패");
			else
				System.out.println("교수 정보 수정 성공");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
