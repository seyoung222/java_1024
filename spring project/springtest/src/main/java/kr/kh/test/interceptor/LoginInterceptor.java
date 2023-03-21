package kr.kh.test.interceptor;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.kh.test.service.MemberService;
import kr.kh.test.vo.MemberVO;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	@Autowired
	MemberService memberService;
	
	@Override
	public void postHandle(
	    HttpServletRequest request, 
	    HttpServletResponse response, 
	    Object handler, 
	    ModelAndView modelAndView)
	    throws Exception {
		//컨트롤러가 ModelAndView에 담아서 보낸 user 정보를 가져오는 작업
	    ModelMap modelMap = modelAndView.getModelMap();
	    MemberVO user = (MemberVO)modelMap.get("user");
	    //MemberVO user = (MemberVO)medelAndView.getModelMap().get("user");
	    //회원정보가 있으면
	    if(user != null) {
	    	//세션에 회원정보 추가
	        HttpSession session = request.getSession();
	        session.setAttribute("user", user);
	        if(user.isAutoLogin()) {
	        	int time = 60 * 60 * 24 * 7;
	        	Cookie cookie = new Cookie("testCookie", session.getId());
	        	cookie.setPath("/");
	        	cookie.setMaxAge(time);
	        	response.addCookie(cookie);
	        	
	        	Date date = new Date(System.currentTimeMillis() + (time)*1000L);
	        	user.setMe_session_limit(date);
	        	user.setMe_session_id(session.getId());
	        	memberService.updateSession(user);
	        }
	    }
	}
	
}
