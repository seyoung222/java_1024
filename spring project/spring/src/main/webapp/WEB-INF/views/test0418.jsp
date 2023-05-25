<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>이벤트 항상 발생하게 하기</title>
</head>
<body>
<!-- 
<script src="jquery연결링크"></script>
<button class="btn">클릭</button>
<script>
$('.btn').click(function(){
console.log(1)
})
</script>
 html은 위에서부터 차례대로 읽기 때문에 이 jquery연결링크가 script보다 아래에 배치되어있으면 스크립트 코드를 읽을때 등록되어있지 않기 때문에 동작하지 않음. 
1. 위로 이동
2. $(function(){이벤트!}) 해서 화면로딩 끝나고 동작하도록
3. $(document).ready(function(){이벤트})
4. $(document).on('click','.btn', function(){이벤트}) 
-->
</body>
</html>