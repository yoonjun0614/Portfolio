<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/resources/js/jquery-3.4.1.min.js"></script>
<script src="/resources/js/jquery-ui.min.js"></script>
<script src="/resources/css/jquery-ui.min.css"></script>
<script>
	
	$(function(){
		$("#proxy").change(function() {
			
			 $.ajax({
				url:'/proxyajax',	
				type:'post',
				data:{"proxyId":$("#proxy").val()},//단일값 넘기기
				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
				success:function(data){
				
					
					$("#rank").val(data);
					
				},
				error:function(){
				}
				  
			 }) 
			 
		})
		
		$("#canBtn").click(function() {
		
			window.close();
		})
		
		$("#proxyBtn").click(function() {
			
			$("#proxyFrm").attr("action","proxysave").attr("method","post").submit();
		
			
		})
		
		
	})	
		
		
		
</script>
</head>
<body>
	<form id="proxyFrm" name="proxyFrm">
		대리결제자:
		<select id="proxy" name="proxy">
			<option>선택</option>
			<c:forEach items="${proxy}" var="proxy">
				<option value="${proxy.id}">${proxy.name}(${proxy.rank})</option>
			</c:forEach>
		</select>
		<br>
		직급: <input type="text" id="rank" name="rank" disabled="disabled" ><br>
		대리자: <input type="text" id="loingName" value="${login.name}"  disabled="disabled"><br>
	</form>
	<input type="button" id="canBtn" name="canBtn" value="취소" onclick="window.close">
	<input type="button" id="proxyBtn" name="proxyBtn" value="승인" onclick="StartClock();">
</body>
</html>