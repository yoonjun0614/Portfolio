<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/resources/js/jquery-3.4.1.min.js"></script>
<script>

	
	function fncback(a){
		
		$("#saveFrm").attr("action","back").attr("method","post").submit();
		
	} 	
	
	
	$(function () {

		
		$("#temporarySaveBtn").click(function(){
			
			if($("#subject").val()==''){
				alert("제목을 입력해 주세요");
			}else if($("#content").val()==''){
				alert("내용을 입력해 주세요");	
			}else{
				
				$("#saveFrm").attr("action","save").attr("method","post").submit();
				
			}
			
		})			
		
		$("#paymentBtn").click(function(){
			
			if($("#subject").val()==''){
				alert("제목을 입력해 주세요");
			}else if($("#content").val()==''){
				alert("내용을 입력해 주세요");	
			}else{
				
				$("#saveFrm").attr("action","payment").attr("method","post").submit();
				
			}
			
		})		
		
		
		var stu='${stu}';
		
		var seq='${detail.DSeq}';
		var name='${detail.DName}';
		
		var newseq='${seq}';
		var newname='${login.name}';
		var status ='${detail.DStatus}';
		
		var id='${detail.listId}';
		var loginId='${login.id}';
		var rank='${login.rank}';
		var proxyRank = '${proxyone.proxyRank}';
		
		
		if(stu=='stu'){
			$("#seq").val(seq);
			$("#name").val(name);
		
			if(status=='payWaiting'){
				$("#payWaiting").prop("checked",true);
			}else if(status=='payIng'){
				$("#payWaiting").prop("checked",true);
				$("#payIng").prop("checked",true);
			}else if(status=='payComplete'){
				$("#payWaiting").prop("checked",true);
				$("#payIng").prop("checked",true);
				$("#payComplete").prop("checked",true);
			}	
			
			if(rank =='부장'){
				
				$("#temporarySaveBtn").hide();
				
				if((id == loginId) && (status=='temporarySave')){
					$("#backBtn").hide();
				}						
				
						if(status =='payComplete' || status=='back'){
						
							$("#backBtn").hide();
							$("#paymentBtn").hide();
						}					
			}		
			if(rank =='과장'){
				
				
				if((id == loginId) && (status=='temporarySave' || status=='back')){
					$("#backBtn").hide();
				}				
				if((id != loginId) && (status =='payWaiting')){
					$("#temporarySaveBtn").hide();
				}					
				
				if( status =='payIng' || status =='payComplete' || status=='back'){
					$("#temporarySaveBtn").hide();
					$("#backBtn").hide();
					$("#paymentBtn").hide();
				}
			}	
			if(rank =='대리' || rank =='사원'){

				if((id == loginId) && (status=='temporarySave'|| status=='back')){
					$("#backBtn").hide();
					
					if(status =='payWaiting' || status =='payIng' || status =='payComplete'){
						$("#temporarySaveBtn").hide();
						$("#backBtn").hide();
						$("#paymentBtn").hide();
					}		
					
				}					
				if((id == loginId) && (status =='payWaiting' || status =='payIng' || status =='payComplete')){
					$("#backBtn").hide();
					$("#temporarySaveBtn").hide();
					$("#paymentBtn").hide();		
					
				}						
				if(proxyRank !=null){
					if(proxyRank =='과장'){
						
						$("#temporarySaveBtn").hide();
						
								if((id == loginId) && (status=='temporarySave' || status=='back')){
							$("#backBtn").hide();
						}				
						
							if(status =='payIng' || status =='payComplete' || status=='back'){
								
								$("#backBtn").hide();
								$("#paymentBtn").hide();
							}
					}						
					if(proxyRank=='부장'){
							
							$("#temporarySaveBtn").hide();
							
							if((id == loginId) && (status=='temporarySave')){
								$("#backBtn").hide();
							}						
									if(status =='payComplete' || status=='back'){
									
										$("#backBtn").hide();
										$("#paymentBtn").hide();
									}					
					}
				}
			}	
		}else{
			$("#seq").val(newseq);
			$("#name").val(newname);			
			$("#subject").removeAttr("readonly");
			$("#content").removeAttr("readonly");
			$("#backBtn").hide();
		}

		if((id == loginId) && (status=='temporarySave' || status=='back')){
		
			$("#subject").removeAttr("readonly");
			$("#content").removeAttr("readonly");
		
		}
	
	})
	
</script>
</head>
<body>
	<table border="1">
		<tr>
			<td>결재 요정</td>
			<td>과장 승인</td>
			<td>부장 승인</td>
		</tr>
		<tr>
			<td><input type="checkbox" id="payWaiting" name="payWaiting" disabled="disabled" ></td>
			<td><input type="checkbox" id="payIng" name="payIng" disabled="disabled"></td>
			<td><input type="checkbox" id="payComplete" name="payComplete" disabled="disabled"></td>
		</tr>
	</table>



	<form id="saveFrm" name="saveFrm">
		글번호:<input type="text" id="seq" name="seq" readonly="readonly"><br>
		작성자:<input type="text" id="name" name="name" readonly="readonly"><br>
		제  목:<input type="text" id="subject" name="subject" value="${detail.DSubject}" readonly="readonly"><br>
		내  용:<br>
		<textarea rows="5" cols="30" id="content" name="content" readonly="readonly">${detail.DContent}</textarea>  
	</form>
	<input type="button" id="temporarySaveBtn" name="temporarySaveBtn" value="임시저장" >
	<input type="button" id="paymentBtn" name="paymentBtn" value="결재">
	<input type="button" id="backBtn" name="backBtn" value="반려" >
	<table border="1">
		<tr>
			<td>번호</td>
			<td>결재일</td>
			<td>결재자</td>
			<td>결재상태</td>
		</tr>
		<c:forEach items="${historydetail}" var="historydetail">
			<tr>
				<td>${historydetail.HistoryDSubject }</td>
				<td>${historydetail.HistoryPayDate }</td>
				<td>${historydetail.HDName }</td>
				<td>${historydetail.HistoryStatus }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>