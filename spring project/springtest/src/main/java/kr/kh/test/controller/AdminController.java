package kr.kh.test.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.kh.test.service.AdminService;
import kr.kh.test.utils.MessageUtils;
import kr.kh.test.vo.BoardTypeVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@RequestMapping(value = "/admin/board/type/list")
	public ModelAndView boardTypeList(ModelAndView mv) {
		ArrayList<BoardTypeVO> btList = adminService.getBoardTypeListAll();
		mv.addObject("btList", btList);//화면에 전송
		mv.setViewName("/admin/boardTypeList");
		return mv;
	}
	@RequestMapping(value = "/admin/board/type/insert", method = RequestMethod.POST)
	public ModelAndView boardTypeListInsert(ModelAndView mv, BoardTypeVO bt,
			HttpServletResponse response, HttpServletRequest request) {
		//서비스에게 게시판 정보를 주면서 등록하라고 요청한 후 등록여부를 알려줌
		if(adminService.insertBoardType(bt))
			MessageUtils.alertAndMovePage(response, "게시판 등록에 성공했습니다.", request.getContextPath(), "/admin/board/type/list");
		else
			MessageUtils.alertAndMovePage(response, "게시판 등록에 실패했습니다.", request.getContextPath(), "/admin/board/type/list");
		mv.setViewName("redirect:/admin/board/type/list");
		return mv;
	}
	@RequestMapping(value = "/admin/board/type/update", method = RequestMethod.POST)
	public ModelAndView boardTypeListUpdate(ModelAndView mv, BoardTypeVO bt,
			HttpServletResponse response, HttpServletRequest request) {
		System.out.println(bt);
		if(adminService.updateBoardType(bt))
			MessageUtils.alertAndMovePage(response, "게시판을 수정했습니다.", request.getContextPath(), "/admin/board/type/list");
		else
			MessageUtils.alertAndMovePage(response, "게시판을 수정하지 못했습니다.", request.getContextPath(), "/admin/board/type/list");
		
		mv.setViewName("redirect:/admin/board/type/list");
		return mv;
	}
	
}
