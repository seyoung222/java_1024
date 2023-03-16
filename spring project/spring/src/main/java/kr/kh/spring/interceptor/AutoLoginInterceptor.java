package kr.kh.spring.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import kr.kh.spring.service.MemberService;
import kr.kh.spring.vo.MemberVO;

public class AutoLoginInterceptor extends HandlerInterceptorAdapter{

	@Autowired
	MemberService memberService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user==null) {
			Cookie cookie = WebUtils.getCookie(request, "springCookie");//이 이름의 쿠키가 있는지 확인함
			if(cookie != null) {
				String me_session_id = cookie.getValue(); //value가 세션 아이디값을 저장하므로 가져옴
				user = memberService.getMemberBySession(me_session_id);
				if(user != null) {
					session.setAttribute("user", user);//세션에 회원정보를 저장
				}
			}
		}
		return true;
	
	}
}
