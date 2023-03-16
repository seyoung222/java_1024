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

public class LoginRedirectInterceptor extends HandlerInterceptorAdapter{
//로그인 성공한 후에 redirect된 경우를 인터셉트함...
	@Autowired
	MemberService memberService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user!=null) {
			String url = (String)session.getAttribute("prevURL");
			if(url != null) {//이전에 있던 페이지가 있으면
				response.sendRedirect(url);
				session.removeAttribute("prevURL");
				return false;
			}
		}
		return true;
		/* 인터셉터 - postHandle은 리턴값없음, preHandle은 리턴값이 boolean
		 * preHandle 인터셉터에서 
		 * - return값이 true면 가려던 URL로 이동
		 * - return값이 false면 가려던 URL을 가지않고, response에 리다이렉트할 URL이 있으면 해당 URL로 이동
		 * 	없으면 빈 화면(아무것도 없는)이 나옴
		 * */
	}
}
