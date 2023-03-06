package kr.kh.spring.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.kh.spring.service.BoardService;
import kr.kh.spring.utils.MessageUtils;
import kr.kh.spring.vo.BoardTypeVO;
import kr.kh.spring.vo.BoardVO;
import kr.kh.spring.vo.FileVO;
import kr.kh.spring.vo.LikesVO;
import kr.kh.spring.vo.MemberVO;
import kr.kh.sprint.pagination.Criteria;
import kr.kh.sprint.pagination.PageMaker;

@Controller
//@RequestMapping(value="/board")//이걸 추가하면 여기포함된 건 모두 /board로 시작하는셈
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@RequestMapping(value = "/board/list", method=RequestMethod.GET) 
	public ModelAndView boardList(ModelAndView mv, Criteria cri) {
		//우선 천제 게시글을 가져오는 코드로 작성하고 추후에 페이지네이션 및 검색 기능을 적용
		cri.setPerPageNum(2);//한페이지당 보이는 게시글 수
		ArrayList<BoardVO> list = boardService.getBoardList(cri);
		int totalCount = boardService.getBoardTotalCount(cri);
		PageMaker pm = new PageMaker(totalCount,3,cri);//시작페이지, 한번에 보이는 페이지네이션 수
		ArrayList<BoardTypeVO> typeList = boardService.getBoardType(9);
		mv.addObject("list",list);
		mv.addObject("pm", pm);
		mv.addObject("typeList", typeList);
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
		LikesVO likesVo = boardService.getLikes(bo_num, user);
		
		mv.addObject("board", board);
		mv.addObject("files", files);
		mv.addObject("likes", likesVo);
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
	@ResponseBody //ajax쓸때 꼭 써야함. return값을 직접 화면에 불러주는 어노테이션 -> ModelAndView 필요없음, 데이터 표현에 젤 흔하게 쓰는 Map으로 변경
	@RequestMapping(value = "/board/like/{li_state}/{bo_num}", method=RequestMethod.GET) 
	public Map<String, Object> boardLike(HttpSession session, 
			@PathVariable("li_state")int li_state, @PathVariable("bo_num")int bo_num) { 
		HashMap<String, Object> map = new HashMap<String, Object>();
		//res==1 : 추천, res==-1 : 비추천, res==0 : 취소
		MemberVO user = (MemberVO)session.getAttribute("user");
		int res = boardService.updateLikes(user, bo_num, li_state);
		boardService.updateBoardByLikes(bo_num);
		map.put("res", res);
		return map;
	}
	@RequestMapping(value = "/board/delete/{bo_num}", method=RequestMethod.GET) 
	public ModelAndView boardDelete(ModelAndView mv, 
			HttpSession session, @PathVariable("bo_num")int bo_num,
			HttpServletResponse response) {
		//세션에 있는 회원정보 가져옴. 작성자와 아이디가 같은지 확인하려고
		MemberVO user = (MemberVO)session.getAttribute("user");
		boolean res = boardService.deleteBoard(bo_num, user);
		if(res) {
			MessageUtils.alertAndMovePage(response, "게시글을 삭제했습니다.", "/spring", "/board/list");
		}else {
			MessageUtils.alertAndMovePage(response, "작성자가 아니거나 이미 삭제된 게시글입니다.",
					"/spring", "/board/detail/"+bo_num);
		}
		
		mv.setViewName("redirect:/board/list");
		return mv;
	}
	@RequestMapping(value = "/board/update/{bo_num}", method=RequestMethod.GET) 
	public ModelAndView boardUpdate(ModelAndView mv, HttpSession session, 
			@PathVariable("bo_num")int bo_num, HttpServletResponse response) {
		//세션에 있는 회원정보 가져옴. 작성자와 아이디가 같은지 확인하려고
		MemberVO user = (MemberVO)session.getAttribute("user");
		BoardVO board = boardService.getBoardByWriteAuthority(bo_num, user);
		ArrayList<FileVO> files = boardService.getFileList(bo_num);
		if(board==null) {
			MessageUtils.alertAndMovePage(response, "작성자가 아니거나 존재하지 않는 게시글입니다.",
					"/spring", "/board/list");
		}else {
			mv.addObject("board", board);
			ArrayList<BoardTypeVO> btList = boardService.getBoardType(user.getMe_authority());
			mv.addObject("btList", btList);
			System.out.println(files);
			mv.addObject("files", files);
			//작성가능한 타입(게시판)이 없으면 작성페이지로 갈 필요없으니 게시글 리스트로 이동시킴
			if(btList.size()==0)
				MessageUtils.alertAndMovePage(response, "권한이 없어서 작성할 수 있는 게시판이 없습니다.",
						"/spring", "/board/list");
			else
				mv.setViewName("/board/update");	
		}
		return mv;
	}
	@RequestMapping(value = "/board/update/{bo_num}", method=RequestMethod.POST) 
	public ModelAndView boardUpdatePost(ModelAndView mv, HttpSession session, 
			@PathVariable("bo_num")int bo_num, HttpServletResponse response,
			BoardVO board, //수정할 게시글 정보
			MultipartFile[] files, //추가된 첨부파일
			int [] fileNums) { //삭제될 첨부파일
		//세션에 있는 회원정보 가져옴. 작성자와 아이디가 같은지 확인하려고
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(boardService.updateBoard(board,files,fileNums,user)) {
			MessageUtils.alertAndMovePage(response, "게시글을 수정했습니다.",
					"/spring", "/board/detail/"+bo_num);
		}else {
			MessageUtils.alertAndMovePage(response, "게시글을 수정하지 못했습니다.",
					"/spring", "/board/list");
		}
		
//		if(files!=null) 
//			for(MultipartFile file: files) {
//				if(file == null || file.getOriginalFilename().length()==0)
//					continue;
//				System.out.println(file.getOriginalFilename());
//			}
//		if(fileNums!=null)
//			for(int fileNum : fileNums) {
//				System.out.println(fileNum);
//			}	
		return mv;
	}
}
