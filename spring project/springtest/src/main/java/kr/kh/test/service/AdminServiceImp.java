package kr.kh.test.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kh.test.dao.BoardDAO;
import kr.kh.test.vo.BoardTypeVO;

@Service
public class AdminServiceImp implements AdminService{
	
	@Autowired
	BoardDAO boardDao;

	@Override
	public ArrayList<BoardTypeVO> getBoardTypeListAll() {
		final int adminAuthority = 9;//관리자 권한
		return boardDao.selectBoardTypeList(adminAuthority);
	}

	@Override
	public boolean insertBoardType(BoardTypeVO bt) {
		//매개변수 체크
		if(!checkBoardType(bt))
			return false;
		return boardDao.insertBoardType(bt) != 0;
	}

	@Override
	public boolean updateBoardType(BoardTypeVO bt) {
		//매개변수 체크
		if(!checkBoardType(bt))
			return false;
		if(bt.getBt_num()<1) //기본키 체크
			return false;
		//다오에게 게시판 정보 주면서 수정하라고 요청한 후 수정여부에 따라 true/false리턴
		return boardDao.updateBoardType(bt) != 0;
	}
	//BoardTypeVO 매개변수 체크하는 메소드(bt_num제외)
	private boolean checkBoardType(BoardTypeVO bt) {
		//매개변수 체크
		if(bt==null)
			return false;
		if(!bt.getBt_type().equals("일반") && !bt.getBt_type().equals("이미지"))
			return false;
		if(bt.getBt_r_authority()!=0 && bt.getBt_r_authority()!=1 && bt.getBt_r_authority()!=9)
			return false;
		if(bt.getBt_w_authority()!=1 && bt.getBt_w_authority()!=9)
			return false;
		//게시판명 체크(null체크 또는 게시판 명이 빈공백으로 되어있는 경우
		// " abc \t\n".trim() =>"abc"
		if(bt.getBt_name()==null || bt.getBt_name().trim().length() == 0)
			return false;
		
		//중복된 이름의 다른 타입명이 존재하지는 않는지 확인(같은 이름의 게시판 있는지 불러옴)
			//가져온 게시판이 존재할 경우
			//1. 서로 다른 게시판인데 이름이 중복되는 경우(중복이므로 false)
			//2. 같은 게시판인 경우(자기자신이므로 true)
		BoardTypeVO dbBt = boardDao.selectBoardTypeByName(bt.getBt_name());
		if(dbBt!=null && bt.getBt_num() != dbBt.getBt_num())
			return false;
		return true;
	}

	@Override
	public boolean deleteBoardType(Integer bt_num) {
		if(bt_num==null || bt_num<1)
			return false;
		return boardDao.deleteBoardType(bt_num) != 0;
	}
}
