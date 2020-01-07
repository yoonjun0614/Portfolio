<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/resources/js/jquery-3.4.1.min.js">
</script>
<script>
$(function () {

	var idk='${idkk}';
	if(idk==2){
		alert("중복된 아이디는 가입 할 수 없습니다");
	}
	
	var id='${idk}';
	
	if(id==1){
		alert("아이디를 사용할 수 있습니다");
	}else if (id==2){
		alert("중복된 아이디 입니다");
	}	
	
	$("#idchk").click(function() {

		if($("#id").val()==''){
			alert("아이디를 입력해주세요");
		}else{
			
			$("#memberFrm").attr("action","idchk").attr("method","post").submit();
			
		}	
	
	}) 
			
	$("#saveBtn").click(function(){
		
		if($("#id").val()==''){
			alert("아이디를 입력해주세요");
		}else if($("#pass").val()==''){
			alert("비밀번호를 입력해주세요");
		}else if($("#pass").val()!=$("#password").val()){
			alert("비밀번호가 다릅니다");
		}else if($("#rank").val()==''){
			alert("직급을 선택해주세요");
		}else if($("#name").val()==''){
			alert("이름을 적어주세요");			
		}else{
			
			$("#memberFrm").attr("action","signup").attr("method","post").submit();
			
		}
		
	})

	
})
</script>
</head>
<body>
	<form id="memberFrm" name="memberFrm">
		아이디:<input type="text" id="id" name="id" placeholder="아이디를 입력해주세요" value="${id }">
		<input type="button" id="idchk" name="idchk" value="중복확인"><br>
		비밀번호:<input type="password" id="pass" name="pass" placeholder="비밀번호를 입력해주세요" value="${pass }"><br>
		비밀번호 확인: <input type="password" id="password" name="password" placeholder="비밀번호를 입력해주세요" value="${password }"><br>
		직급:
		<select id="rank" name="rank">
			<option value="">선택</option>
			<option <c:if test="${rank =='사원'}">selected="selected" </c:if> value="사원">사원</option>
			<option <c:if test="${rank =='대리'}">selected="selected" </c:if>value="대리">대리</option>
			<option <c:if test="${rank =='과장'}">selected="selected" </c:if>value="과장">과장</option>
			<option <c:if test="${rank =='부장'}">selected="selected" </c:if>value="부장">부장</option>
		</select><br>
		이름:<input type="text" id="name" name="name" placeholder="이름를 입력해주세요" value="${name }"><br>
		<input type="button" id="canBtn" name="canBtn" value="취소">
		<input type="button" id="saveBtn" name="saveBtn"value="가입">
	</form>
</body>
</html>