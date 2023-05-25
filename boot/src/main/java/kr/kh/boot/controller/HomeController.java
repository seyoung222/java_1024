package kr.kh.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.kh.boot.service.MemberService;
import kr.kh.boot.vo.MemberVO;

@Controller
public class HomeController {
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/")
	@ResponseBody
	public String home(Model model) {
		MemberVO member = memberService.getMember("asdasd");
		System.out.println(member);
		//왜 thymeleaf가 좋으냐
		model.addAttribute("id", "abc");
		return "contents/home";
	}
}
