package kr.kh.spring.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.kh.spring.service.BoardService;
import kr.kh.spring.vo.CommentVO;
import kr.kh.spring.vo.MemberVO;
import kr.kh.sprint.pagination.Criteria;
import kr.kh.sprint.pagination.PageMaker;

@RestController //이 안에 있는 건 전부 @ResponseBoby를 안붙여도 ajax사용가능
public class CommentController {
	@Autowired
	BoardService boardService;

	@RequestMapping(value = "/comment/insert", method=RequestMethod.POST) 
	public Map<String, Object> commentInsert(@RequestBody CommentVO comment,
			HttpSession session) { 
		Map<String, Object> map = new HashMap<String, Object>();
		MemberVO user = (MemberVO) session.getAttribute("user");
		boolean res = boardService.insertComment(comment, user); //서비스에서 null체크시키려고 전부넘김
		map.put("result", res);
		return map;
	}
	@RequestMapping(value = "/comment/list/{co_bo_num}", method=RequestMethod.POST) 
	public Map<String, Object> commentList(@RequestBody Criteria cri,
			@PathVariable("co_bo_num") int co_bo_num) { 
		Map<String, Object> map = new HashMap<String, Object>();
		//댓글을 가져와서 개수별로 보여줌(mapper에서 limit로 cri 설정)
		cri.setPerPageNum(2);
		ArrayList<CommentVO> list = boardService.getCommentList(cri, co_bo_num);
		//PageMaker 만들어줘야함
		int totalCount = boardService.getTotalCountCommentList(co_bo_num);
		PageMaker pm = new PageMaker(totalCount, 5, cri);
		map.put("list", list);
		map.put("pm", pm);
		return map;
	}
	@RequestMapping(value = "/comment/delete", method=RequestMethod.POST) 
	public Map<String, Object> commentDelete(@RequestBody CommentVO comment,
			HttpSession session) { 
		Map<String, Object> map = new HashMap<String, Object>();
		MemberVO user = (MemberVO) session.getAttribute("user");
		boolean res = boardService.deleteComment(comment, user);
		map.put("result", res);
		return map;
	}
}
