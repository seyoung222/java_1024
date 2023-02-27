package kr.kh.spring.service;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.kh.spring.dao.BoardDAO;
import kr.kh.spring.utils.UploadFileUtils;
import kr.kh.spring.vo.BoardTypeVO;
import kr.kh.spring.vo.BoardVO;
import kr.kh.spring.vo.FileVO;
import kr.kh.spring.vo.MemberVO;

@Service
public class BoardServiceImp implements BoardService{

	@Autowired
	BoardDAO boardDao;
	
	String uploadPath = "D:\\uploadfiles";

	private boolean checkBoard(BoardVO board) {
		//게시글이 없거나, 게시글 제목이 비었거나, 내용이 비어있으면
		if(board==null || 
			board.getBo_title()==null || board.getBo_title().trim().length()==0 ||
			board.getBo_content()==null || board.getBo_content().trim().length()==0)
			return false;
		return true;
	}
	private void uploadFiles(MultipartFile[] files, int bo_num) {
		if(files==null || files.length==0)
			return;
		//반복문(파일 여러개니까)
		for(MultipartFile file : files) {
			if(file==null || file.getOriginalFilename().length()==0)//객체가 있으면 이름 길이가 0일 수 없음
				continue;
			String fileName = "";
			//첨부파일 서버에 업로드
			try {
				fileName = UploadFileUtils.uploadFile(uploadPath,//업로드 경로
						file.getOriginalFilename(), //파일명
						file.getBytes());//실제 파일 데이터
			} catch (Exception e) {
				e.printStackTrace();
			} 
			System.out.println(fileName);
			//첨부파일 객체를 생성
			FileVO fileVo = new FileVO(file.getOriginalFilename(), fileName, bo_num);
			//다오에게서 첨부파일 정보를 주면서 추가하라고 요청
			boardDao.insertFile(fileVo);
		}
	}
	
	@Override
	public ArrayList<BoardTypeVO> getBoardType(int authority) {
		return boardDao.selectAllBoardType(authority);
	}
	
	@Override
	public boolean insertBoard(BoardVO board, MemberVO user, MultipartFile[] files) {
		//회원정보가 없으면(=로그인안한 상태)
		if(user==null)
			return false;
		//게시글에 빠진 항목이 있으면 false 리턴
		if(!checkBoard(board))
			return false;
		board.setBo_me_id(user.getMe_id());
		//게시글 등록
		boardDao.insertBoard(board);//mapper의 usegeneratedKeys="true"덕에 기본키를 바로 가져와서 쓸 수 있음
		//첨부파일 등록
		uploadFiles(files, board.getBo_num());
		
		return true;
	}
	
	@Override
	public ArrayList<BoardVO> getBoardList() {
		return boardDao.selectBoardList();
	}
	@Override
	public BoardVO getBoard(int bo_num) {
		return boardDao.selectBoard(bo_num);
	}
	@Override
	public ArrayList<FileVO> getFileList(int bo_num) {
		return boardDao.selectFileList(bo_num);
	}
}
