package kr.kh.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.kh.spring.service.MemberService;


@Controller
//controller는 이름이 컨트롤러가 아니라 어노테이션이 붙어야 역할을 함
public class HomeController {
	
	@Autowired 
	//원래 = new MemberService로 객체만들었던걸 자동으로 객체 만들어주는 어노테이션
	//단 ServiceImp 파일의 위에 꼭 @Service 가 붙어있어야함
	MemberService memberService;
	
	@RequestMapping(value = "/") 
	public ModelAndView home(ModelAndView mv) {
		mv.setViewName("/main/home");
		return mv;
	}
	
	@RequestMapping(value = "/ex1") 
	public ModelAndView ex1(ModelAndView mv, String name, Integer age) {
		//매개변수 int형으로 하면 기본 /ex1들어갈때 int에 null을 넣을 수 없어서 에러남.=>Integer쓸것
		System.out.println("예제1 - 화면에서 전달한 이름 :" + name);
		System.out.println("예제1 - 화면에서 전달한 나이 :" + age);
		mv.setViewName("/main/ex1");
		return mv;
	}
	@RequestMapping(value = "/ex2") 
	public ModelAndView ex2(ModelAndView mv, String name, Integer age) {
		System.out.println("예제2 - 화면에서 전달한 이름 :" + name);
		System.out.println("예제2 - 화면에서 전달한 나이 :" + age);
		mv.setViewName("/main/ex2");
		return mv;
	}
	@RequestMapping(value = "/ex3") 
	public ModelAndView ex3(ModelAndView mv) {
		mv.setViewName("/main/ex3");
		return mv;
	}
	@RequestMapping(value = "/ex3/{name}/{age}") 
	//url에 데이터를 보내는 방법
	public ModelAndView exNameAge3(ModelAndView mv,
			@PathVariable("name")String name, @PathVariable("age")int age) {
		System.out.println("예제3 - 화면에서 전달한 이름 :" + name);
		System.out.println("예제3 - 화면에서 전달한 나이 :" + age);
		mv.setViewName("/main/ex3");
		return mv;
	}
	@RequestMapping(value = "/ex4") 
	//서버에서 화면으로 이름과 나이를 전송
	//- 화면에서 호출할 이름(변수명)과 값을 지정 => addObject메소드 이용
	public ModelAndView ex4(ModelAndView mv) {
		mv.addObject("name", "둘리");
		mv.addObject("age", 10000);
		mv.setViewName("/main/ex4");
		return mv;
	}
	@RequestMapping(value = "/ex5") 
	public ModelAndView ex5(ModelAndView mv,String num) {
		String name = memberService.getNameByNum(num);
		mv.addObject("name", name);
		mv.addObject("num", num);
		mv.setViewName("/main/ex5");
		return mv;
	}
	
/*
//	@RequestMapping(value = "/", method = RequestMethod.GET)
	@RequestMapping(value = "/")
	//RequestMethod.GET는 get방식만 처리가능. .POST로 바꿔야 post방식 처리 가능
	//직접쳐서 들어가는 웹사이트는 모두 get방식이지만 둘다 처리하고 싶게하려면 method란을 걍 지우면 됨
	//tip) post방식은 다르게 처리하고 싶으면 메소드 하나 더 만들고 같은 처리방식이면 지우면 됨
	public ModelAndView home(ModelAndView mv, String name, Integer age) {
		//ModelAndView : 컨트롤러 처리 결과 후 응답할 view와 view에 전달할 값을 저장 및 전달하는 클래스 
		
		//get방식은 주소창에 /?name=어쩌구&age=30 과 같이 데이터를 url에 넣어서 보내는건데,
		//매개변수에 넣을 수 있음. 매개변수에 들어가는 데이터들은 url에 들어갈 이름과 똑같게..
		//단 매개변수가 선택적일때 null이 들어갈수있으므로 null을 수용할 수 있는 래퍼클래스 쓸 것=>int대신 Integer
			//안하면(데이터 입력하지 않고 그냥 홈페이지 들어갈때 등..) 오류남

//		String name = "홍길동"; 
//		int age = 20;
		mv.addObject("name1", name); //화면(jsp파일)에서 불릴 이름, 보낼 데이터
		mv.addObject("age", age);
		mv.setViewName("home");
		//화면이름을 home이라고 출력하게 함 = home.jsp파일을 만들게 함
		return mv;
	}
	@RequestMapping(value = "/board/{num}")
	//경로 상에 데이터의 값을 실은 변수를 넣고 싶을 때 => {} 사용하고 매개변수로 @PathVariable("경로속 변수명")
	public ModelAndView board(ModelAndView mv, @PathVariable("num")Integer num1) {
		System.out.println("게시글 번호 : " + num1);
		mv.setViewName("home");
		return mv;
	}
	@RequestMapping(value = "/test")
	public ModelAndView test(ModelAndView mv, InfoVO_ info) {
		//여러 데이터 세트를 한번에 보내줄 경우 객체(클래스)에 담아서 VO를 하나만 넘겨줌
		//url에 들어가는 변수명과 필드명이 일치하는 클래스 필드에 값을 넣어줌. getter, setter필요
		//기본생성자 역할을 함. => 기본생성자 없이 생성자만 있을 경우 500번 에러 발생
		System.out.println(info);
		mv.addObject("info1", info);
		mv.setViewName("home2");
		return mv;
		//여기서 http://localhost:8080/spring/test/?name=홍길동&num=24 입력하면
		//객체에 잘 입력되어 콘솔에 InfoVO [name=홍길동, num=24]가 출력
	}
	/* 예제1
	url : /login
	화면 : login.jsp
	- 아이디, 비번을 입력해서 서버로 전송하도록 화면 구성
	- 화면에서 전송한 아이디 비번을 콘솔에 출력
	 * *
	@RequestMapping(value = "/login", method=RequestMethod.GET) //get방식
	public ModelAndView login(ModelAndView mv) {
		mv.setViewName("login");
		return mv;
	}
	@RequestMapping(value = "/login", method=RequestMethod.POST) //post방식
	public ModelAndView loginPost(ModelAndView mv, String id, String pw) {
		System.out.println("id : " + id);
		System.out.println("pw : " + pw);
		//jsp의 form태그에서 action을 입력해야 입력한 값을 받아와서 출력가능
		mv.setViewName("login");
		return mv;
	}

*/
}


/*
 spring은 MVC 모델 
 -> 기능이 나눠있어서 데이터/화면/작업별로 나눠짐
	- M : model - 데이터를 주고 받을 때 만들어지는 클래스
	- V : view - JSP들이 화면을 보여줌
	- C : controller - 요청한 작업을 처리

디스패처 서블릭의 기능처리
1. 클라이언트가 URL로 접근하여 정보를 요청
2. 디스패처서블릿이 핸들러매핑을 통해 해당요청을 매핑한 컨트롤러가 있는지 검색
3. Controller에 처리요청
4. 클라이언트의 요청을 처리하고 결과를 출력할 view의 이름을 디스패처서블릿에 리턴
5. 컨트롤러에서 보내온 view이름을 토대로 처리 view를 검색(ViewResolver)
6. 처리결과를 view에 송신
7. 처리결과가 포함된 view를 DispatcherServlet에 송신
8. 최종결과 출력 

get과 post방식의 차이
- get
	- url에 데이터의 값이 들어가있음. 주소/?변수1=값1&변수2=값2 과 같은 방식
- post
	- url의 /뒤에 ?가 없음. 데이터 값이 뜨지 않기 때문에 게시글 등에 사용됨
	- 무조건 form 태그를 사용해야함

 * */
 