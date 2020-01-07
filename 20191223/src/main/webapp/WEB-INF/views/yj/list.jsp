<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link  href="/resources/css/jquery-ui.min.css" rel='stylesheet' type='text/css' >
<script src="/resources/js/jquery-3.4.1.min.js"></script>
<script src="/resources/js/jquery-ui.min.js"></script>

<script>

$(function () {
	
	
	$("#searchType2").change(function() {

		$.ajax({
			url:'./listajax',	
			type:'post',
			data:$('#searchFrm').serialize(),
		
		success:function(data){
				$('#tablelist').html(data);
			},
			error:function(){
			}
			 
		})		
	
	}) 
	
	

	$("#proxyBtn").click(function() {
		
		window.open("/proxy","","width=250,height=150").focus();
		
	})	
	
	
	$("#logoutBtn").click(function() {
		
		$("#listFrm").attr("action","logout").attr("method","post").submit();
		
	})


	$("#searchBtn").click(function() {
		
		$("#searchFrm").attr("action","list").attr("method","post").submit();
		
	})		
	
	
	$("#startDay").datepicker(
			{
				dateFormat : 'yy-mm-dd',
				showOtherMonths : true,
				showMonthAfterYear : true,
				changeYear : true,
				changeMonth : true,
				yearSuffix : "년" ,
				monthNamesShort : [ '1', '2', '3', '4', '5', '6', '7', '8',
						'9', '10', '11', '12' ],
				monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월',
						'8월', '9월', '10월', '11월', '12월' ],
				dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ],
				dayNames : [ '일요일', '월요일', '화요일', '수요일', '목요일', '금요일',
						'토요일' ]            
			})

	$("#endDay").datepicker(
			{
				dateFormat : 'yy-mm-dd' ,
				showOtherMonths : true ,
				showMonthAfterYear : true ,
				changeYear : true ,
				changeMonth : true ,
				yearSuffix : "년" ,
				monthNamesShort : [ '1', '2', '3', '4', '5', '6', '7', '8',
						'9', '10', '11', '12' ] ,
				monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월',
						'8월', '9월', '10월', '11월', '12월' ] ,
				dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ],
				dayNames : [ '일요일', '월요일', '화요일', '수요일', '목요일', '금요일',
						'토요일' ]         
	})		
	
	
	
	
})

</script>
</head>
<body>
	
	<p>${login.name}(${login.rank}) 
	<c:if test="${!empty proxyone}"> 
		- ${proxyone.proxyName }(${proxyone.proxyRank })
	</c:if> 님 환영합니다
		<input type="button" id="logoutBtn" name="logoutBtn" value="로그아웃" onclick="location.href='logout'"><br>
	<c:if test="${!empty proxyone}">
		대리결재 기간: ${proxyone.receiveStartDay } ~ ${proxyone.receiveEndDate }
	</c:if>
	</p>

	<input type="button" id="saveBtn" name="saveBtn" value="등록" onclick="location.href='history'">
	<c:if test="${login.rank=='과장' or login.rank=='부장'}">
		<input type="button" id="proxyBtn" name="proxyBtn" value="대리결제">
	</c:if><br>
	<form id="searchFrm" name="searchFrm">
		<table border="1">
			<tr>
				<td>
					<select id="searchType" name="searchType">
						<option >선택</option>
						<option <c:if test="${type eq'name' }" >selected="selected"</c:if>value="name">작성자</option>
						<option <c:if test="${type eq'subject' }" >selected="selected"</c:if>value="subject">제목</option>
						<option <c:if test="${type eq'payment' }" >selected="selected"</c:if>value="payment">결재자</option>
					</select>
					<input type="text" id="search" name="search" placeholder="검색어를 입력해주세요"  value="${txt }">
				</td>
				<td>
					<select id="searchType2" name="searchType2">
						<option >결재상태</option>
						<option <c:if test="${type2 eq'temporarySave' }" >selected="selected"</c:if> value="temporarySave">임시저장</option>
						<option <c:if test="${type2 eq'payWaiting' }" >selected="selected"</c:if> value="payWaiting">결재대기</option>
						<option <c:if test="${type2 eq'payIng' }" >selected="selected"</c:if> value="payIng">결재중</option>
						<option <c:if test="${type2 eq'payComplete' }" >selected="selected"</c:if> value="payComplete">결재완료</option>
						<option <c:if test="${type2 eq'back' }" >selected="selected"</c:if> value="back">반려</option>
					</select>			
				</td>
			</tr>
			<tr>
				<td>
					<input type="text" id="startDay" name="startDay" placeholder="시작 날자를 입력해주세요" value="${strDay }">~
					<input type="text" id="endDay" name="endDay" placeholder="마지막 날자를 입력해주세요" value="${endDay }">
				</td>
				<td>
					<input type="button" id="searchBtn" name="searchBtn" value="검색">
				</td>
			</tr>
		</table>
	</form>
	<table border="1" id="tablelist">
		<tr>
			<td>글번호</td>
			<td>작성자</td>
			<td>제목</td>
			<td>작성일</td>
			<td>결재일</td>
			<td>결재자</td>
			<td>결재상태</td>
		</tr>
		<c:forEach items="${list }" var="list">
			<tr onclick="location.href='detail?seq=${list.listSeq}'">
				<td>${list.listSeq }</td>
				<td>${list.listName }</td>
				<td>${list.listSubject }</td>
				<td>${list.listRegDate }</td>
				<td>${list.listPaymentDate }</td>				
				<td>${list.listPaymentId }</td>
				<td>${list.listStatus }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>