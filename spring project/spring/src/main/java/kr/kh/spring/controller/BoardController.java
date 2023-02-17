package kr.kh.spring.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.kh.spring.service.BoardService;
import kr.kh.spring.vo.BoardTypeVO;
import kr.kh.spring.vo.MemberVO;

@Controller
//@RequestMapping(value="/board")//이걸 추가하면 여기포함된 건 모두 /board로 시작하는셈
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@RequestMapping(value = "/board/list", method=RequestMethod.GET) 
	public ModelAndView boardList(ModelAndView mv) {
		mv.setViewName("/board/list");
		return mv;
	}
	@RequestMapping(value = "/board/insert", method=RequestMethod.GET) 
	public ModelAndView boardinsert(ModelAndView mv,HttpServletRequest request) {
		MemberVO user = (MemberVO)request.getSession().getAttribute("user");
		ArrayList<BoardTypeVO> btList = boardService.getBoardType(user.getMe_authority());
		//작성가능한 타입(게시판)이 없으면 작성페이지로 갈 필요없으니 게시글 리스트로 이동시킴
		if(btList.size()==0)
			mv.setViewName("redirect:/board/list");
		else
			mv.setViewName("board/insert");
		mv.addObject("btList", btList);
		mv.setViewName("/board/insert");
		return mv;
	}
}
