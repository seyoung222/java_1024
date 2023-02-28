package kr.kh.spring.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.kh.spring.service.BoardService;
import kr.kh.spring.utils.MessageUtils;
import kr.kh.spring.vo.BoardTypeVO;
import kr.kh.spring.vo.BoardVO;
import kr.kh.spring.vo.FileVO;
import kr.kh.spring.vo.MemberVO;

@Controller
//@RequestMapping(value="/board")//이걸 추가하면 여기포함된 건 모두 /board로 시작하는셈
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@RequestMapping(value = "/board/list", method=RequestMethod.GET) 
	public ModelAndView boardList(ModelAndView mv) {
		//우선 천제 게시글을 가져오는 코드로 작성하고 추후에 페이지네이션 및 검색 기능을 적용
		ArrayList<BoardVO> list = boardService.getBoardList();
		mv.addObject("list",list);
		mv.setViewName("/board/list");
		return mv;
	}
	@RequestMapping(value = "/board/insert", method=RequestMethod.GET) 
	public ModelAndView boardinsert(ModelAndView mv,HttpServletRequest request) {
		MemberVO user = (MemberVO)request.getSession().getAttribute("user");
		ArrayList<BoardTypeVO> btList = boardService.getBoardType(user.getMe_authority());
		mv.addObject("btList", btList);
		//작성가능한 타입(게시판)이 없으면 작성페이지로 갈 필요없으니 게시글 리스트로 이동시킴
		if(btList.size()==0)
			mv.setViewName("redirect:/board/list");
		else
			mv.setViewName("/board/insert");
		return mv;
	}
	@RequestMapping(value = "/board/insert", method=RequestMethod.POST) 
	public ModelAndView boardinsertPost(ModelAndView mv, BoardVO board,
			HttpSession session, MultipartFile []files) { //파일올리기용 매개변수 이름은 jsp의 요소 name명과 맟주기
		// 작성자에 넣기 위해 세션에 있는 회원정보 가져옴
		MemberVO user = (MemberVO)session.getAttribute("user");
		boardService.insertBoard(board, user, files);
		mv.setViewName("redirect:/board/list");
		return mv;
	}
	@RequestMapping(value = "/board/detail/{bo_num}", method=RequestMethod.GET) 
	public ModelAndView boardDetail(ModelAndView mv,
			@PathVariable("bo_num")int bo_num,//경로에 매개변수가 있는 경우 PathVariable로 가져올 수 있음
			HttpSession session, HttpServletResponse response) {
		MemberVO user = (MemberVO)session.getAttribute("user"); //권한 있는 게시글만 볼 수 있게 회원정보 가져옴. null일수도 있음
		BoardVO board = boardService.getBoard(bo_num, user);
		ArrayList<FileVO> files = boardService.getFileList(bo_num);
		mv.addObject("board", board);
		mv.addObject("files", files);
		if(board==null) {
			MessageUtils.alertAndMovePage(response,
					"삭제되거나 조회 권한이 없는 게시글입니다.",
					"/spring","/board/list");
			mv.setViewName("redirect:/board/list");
		}else {
			mv.setViewName("/board/detail");			
		}
		return mv;
	}
}