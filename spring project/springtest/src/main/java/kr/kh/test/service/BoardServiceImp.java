package kr.kh.test.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.kh.test.dao.BoardDAO;
import kr.kh.test.utils.UploadFileUtils;
import kr.kh.test.vo.BoardTypeVO;
import kr.kh.test.vo.BoardVO;
import kr.kh.test.vo.FileVO;
import kr.kh.test.vo.MemberVO;

@Service
public class BoardServiceImp implements BoardService{

	@Autowired
	BoardDAO boardDao;
	
	String uploadPath = "D://uploadfiles";
	
	@Override
	public ArrayList<BoardTypeVO> getBoardTypeList(MemberVO user) {
		if(user==null || user.getMe_authority()==0)
			return null;
		return boardDao.selectBoardTypeList(user.getMe_authority());
	}

	@Override
	public boolean insertBoard(BoardVO board, MemberVO user, MultipartFile[] files) {
		if(user==null || user.getMe_authority()==0)
			return false;
		if(board==null ||
				board.getBo_title().trim().length()==0 ||
				board.getBo_content().trim().length()==0||
				board.getBo_bt_num()==0)
			return false;
		board.setBo_me_id(user.getMe_id());
		int isOk = boardDao.insertBoard(board);
		System.out.println(board);
		if(isOk==0)
			return false;
		
		if(files==null || files.length==0)
			return false;
		for(MultipartFile file: files) {
			if(file==null || file.getOriginalFilename().length()==0)
				continue;
			try {
				String path = UploadFileUtils.uploadFile(uploadPath,// 업로드 경로 
						file.getOriginalFilename(),//파일명
						file.getBytes());//실제파일 데이터
				System.out.println(path);
				FileVO fileVo = new FileVO(board.getBo_num(), path, file.getOriginalFilename());
				System.out.println(fileVo);
				boardDao.insertFile(fileVo);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}		
		return true;
	}

}
