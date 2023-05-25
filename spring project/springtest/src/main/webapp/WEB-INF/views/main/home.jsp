<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

유저정보 : ${user} <br>
<button id="btn">조회</button>
<div id="result"></div>

<script type="text/javascript">
	$('#btn').click(()=>{
		var $table = $('<table border="1"></table>');//생성할 테이블 태그를 선언
		var header = '<tr>'
			+ '<td>일련번호</td>'
			+ '<td>시도명</td>'
			+ '<td>시군구명</td>'
			+ '<td>대피지구명</td>'
			+ '<td>대피장소명</td>'
			+ '<td>주소</td>'
			+ '<td>경도</td>'
			+ '<td>위도</td>'
			+ '<td>수용가능인원수</td>'
			+ '<td>해변으로부터거리</td>'
			+ '<td>대피소 분류명</td>'
			+ '<td>내진적용여부</td>'
			+ '<td>해발높이</td>'
			+ '</tr>';
		$.ajax({
			url: 'https://apis.data.go.kr/1741000/TsunamiShelter3/getTsunamiShelter1List?serviceKey=BW%2B8DHMlphblKKdkiPIfH7vS1s6zs3MLdx3xosYjqWGhvDKUb2z7Bv%2BVm%2BXiixyAkjR6%2BOj5Q9CrMtDamL9%2F9w%3D%3D&pageNo=1&numOfRows=10&type=xml',
			success: data=>{ //function(data){ 랑 같은 표현임 $(this)를 안쓸때 화살표함수 써도 됨
				var body = '';
				console.log(data.firstElementChild);
				data.firstElementChild.querySelectorAll('row').forEach(item=>{
					//console.log(item) 각 <row>들이 가져와지는지 확인
					var id = item.querySelector('id').innerHTML; //각 태그의 값들을 가져옴
					var sido_name = item.querySelector('sido_name').innerHTML;
					var sigungu_name = item.querySelector('sigungu_name').innerHTML;
					var remarks = item.querySelector('remarks').innerHTML;
					var shel_nm = item.querySelector('shel_nm').innerHTML;
					var address = item.querySelector('address').innerHTML;
					var lon = item.querySelector('lon').innerHTML;
					var lat = item.querySelector('lat').innerHTML;
					var shel_av = item.querySelector('shel_av').innerHTML;
					var lenth = item.querySelector('lenth').innerHTML;
					var shel_div_type = item.querySelector('shel_div_type').innerHTML;
					var seismic = item.querySelector('seismic').innerHTML;
					var height = item.querySelector('height').innerHTML;
					body += '<tr>'
						+'<td>'+id+'</td>'
						+'<td>'+sido_name+'</td>'
						+'<td>'+sigungu_name+'</td>'
						+'<td>'+remarks+'</td>'
						+'<td>'+shel_nm+'</td>'
						+'<td>'+address+'</td>'
						+'<td>'+lon+'</td>'
						+'<td>'+lat+'</td>'
						+'<td>'+shel_av+'</td>'
						+'<td>'+lenth+'</td>'
						+'<td>'+shel_div_type+'</td>'
						+'<td>'+seismic+'</td>'
						+'<td>'+height+'</td>'
						+'</tr>';
				})
			$table.html(header)
			.append(body)
			.appendTo('#result');
			}
		})
	})
</script>