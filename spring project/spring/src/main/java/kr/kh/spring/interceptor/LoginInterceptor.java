package kr.kh.spring.interceptor;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.kh.spring.service.MemberService;
import kr.kh.spring.vo.MemberVO;

public class LoginInterceptor extends HandlerInterceptorAdapter{
//HandlerInterceptorAdapter에서 사용하는 두가지 대표 메소드 
//	: freeHandle(들어가기 전에 가로챔), postHandle(나간 직후 가로챔)
//- 로그인은 컨트롤러 메소드에 있는 멤버정보를 가로채는 거라서 postHandle(들어가기 전엔 멤버정보 없음)
	
	@Autowired
	MemberService memberService;
	
	@Override
	public void postHandle(
	    HttpServletRequest request, 
	    HttpServletResponse response, 
	    Object handler, 
	    ModelAndView modelAndView)
	    throws Exception {
	    ModelMap modelMap = modelAndView.getModelMap();
	    MemberVO user = (MemberVO)modelMap.get("user");

	    if(user != null) {
	        HttpSession session = request.getSession();
	        session.setAttribute("user", user);
	        //로그인된 계정을 세션에 저장해서 로그인 상태를 유지하게 하려고 request에 있는 세션을 가져옴
	        
	        //자동로그인 확인 -> 로그인 되어있으면 쿠키 생성
	        if(user.isAutoLogin()) {
	        	Cookie cookie = new Cookie("springCookie", session.getId());
	        	//만료시간 1주일
	        	int time = 60*60*24*7;
	        	cookie.setPath("/");
	        	cookie.setMaxAge(time);
	        	response.addCookie(cookie);
	        	//DB에 me_session_id와 me_session_limit를 수정
	        	user.setMe_session_id(session.getId());
	        	Date date = new Date(System.currentTimeMillis()+time*1000); //현재시간에 1주 더한 날짜
	        	user.setMe_session_limit(date);
	        	System.out.println(user);
	        	memberService.updateMemberBySession(user);
	        }
	    }
	}
}
