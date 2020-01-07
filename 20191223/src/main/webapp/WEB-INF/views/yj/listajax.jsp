<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
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