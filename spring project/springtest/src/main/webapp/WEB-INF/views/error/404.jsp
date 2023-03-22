<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>404에러</h1>


에러처리 페이지를 만들어서 에러나도 그 페이지로 가게 만들 수 있지만 404 에러가 날 경우(없는 페이지로 이동할 경우) 페이지가 없기 때문에 만들어둔 페이지로 가지 않음 -> web.xml에서 <servlet-name>appServlet</servlet-name> 아래에서 아래 코드를 넣어서 404에러를 에러로 처리하게끔 만듦
    <xmp>
    <init-param>
      <param-name>throwExceptionIfNoHandlerFound</param-name>
      <param-value>true</param-value>
    </init-param></xmp>
<br>
* 이때 500에러와 404에러를 다른 페이지로 처리하게 하고 싶다면 ErrorAdvise 클래스에서 404에 해당하는 NoHandlerFoundExceotion 예외를 다루는 메소드만 분리하면 됨
<br>
** 에러처리 페이지는 마지막에 하는 게 좋음