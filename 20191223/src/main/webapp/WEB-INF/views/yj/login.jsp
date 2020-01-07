<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/resources/js/jquery-3.4.1.min.js">
</script>
<script>
	
	var idk='${idkk}';
	
	if(idk==1){
		alert("가입이 완료 되었습니다");
	}	
	
	var id='${id}';
	var pass='${pass}';
	
	if(id==1){
		alert("아이디를 확인해주세요");
	}else if (pass==1){
		alert("비밀번호를를 확인해주세요");
	}
	
	$(function () {
		
		
		$("#loginBtn").click(function(){
			
			if($("#id").val()==''){
				alert("아이디를 입력해주세요");
			}else if($("#pass").val()==''){
				alert("비밀번호를를 입력해주세요");
			}else{
				
				$("#loginFrm").attr("action","login").attr("method","post").submit();
				
			}
			
		})
	
		
	})
	
	
</script>
</head>
<body>

	<form id="loginFrm" name="loginFrm">
	<table>
		<tr>
		<td>
		아이디:<input type="text" id="id" name="id" ><br>
		비   번:<input type="password" id="pass" name="pass" ><br>
			<input type="button" id="loginBtn" name="loginBtn" value="로그인">
			<input type="button" id="memberBtn" name="memberBtn" value="회원가입" onclick="location.href='member'">
		</td>
		</tr>
	</table>
	</form>
</body>
</html>