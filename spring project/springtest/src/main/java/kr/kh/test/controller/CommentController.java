package kr.kh.test.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.kh.test.service.CommentService;
import kr.kh.test.vo.CommentVO;

@RestController
@Controller
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	@RequestMapping(value = "/comment/insert", method = RequestMethod.POST)
	public Map<String, Object> commentInsert(CommentVO comment) {
		Map<String,Object> map = new HashMap<String,Object>();
		System.out.println(comment); //화면에서는 제대로 보냈는데 여기서는 출력이 잘안됨.. 다시보ㅏ야해
		return map;
	}
}
