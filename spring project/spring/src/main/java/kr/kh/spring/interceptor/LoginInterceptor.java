package kr.kh.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.kh.spring.vo.MemberVO;

public class LoginInterceptor extends HandlerInterceptorAdapter{
//HandlerInterceptorAdapter에서 사용하는 두가지 대표 메소드 
//	: freeHandle(들어가기 전에 가로챔), postHandle(나간 직후 가로챔)
//- 로그인은 컨트롤러 메소드에 있는 멤버정보를 가로채는 거라서 postHandle(들어가기 전엔 멤버정보 없음)
	
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
	    }
	}
}
