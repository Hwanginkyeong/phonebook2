<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="com.javaex.vo.PersonVo" %>
<%@ page import="com.javaex.dao.PhoneDao" %>



<%
	//PhoneDao를 메모리에 올린다. 
	//PhoneDao phoneDao = new PhoneDao();

	//파라미터에서 수정할 id값을 가져온다
	int personId = Integer.parseInt(request.getParameter("id"));
	
	PersonVo personVo = new PhoneDao().getPerson(personId);
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  			<h1>[Phonebook2]</h1>
		
			<h2>전화번호 수정폼</h2>
		
			<p>
				수정화면입니다.<br>
			   	아래의 값을 수정하고 "수정" 버튼을 클릭하세요
			</p> 
			
			<form action="/phonebook2/pbc" method="get">
			<input type="hidden" name="id" value="<%= personVo.getPersonId() %>"/><br>
		     	<table border="1">
	     			<tr >
	     				<th align="left">이름(name)</th>
	     				<th ><input type="text" style="text-align:center;" name="name" value="<%= personVo.getName() %>"/></th>
	     			</tr>
		    		<tr>
		     			<th align="left">핸드폰(hp)</th>
		     			<th><input type="text" style="text-align:center;" name="hp" value="<%= personVo.getHp() %>"/></th>
		     		</tr>
					<tr>
		     			<th align="left">회사(company)</th>
		     			<th><input type="text" style="text-align:center;" name="company" value="<%= personVo.getCompany() %>"/></th>
		     		</tr>
		     		<tr>
		     			<th align="left">코드(id)</th>
		     			<th><%= personVo.getPersonId() %></th>
		     		</tr>
		     	</table>
	     			<button type="submit" style="margin: 5px 0px 0px 0px;"onclick="location.href='/phonebook2/pbc?action=list' "> 뒤로 </button>
	     			<button type="submit" style="margin: 5px 0px 0px 215px;"> 수정 </button>
	     			<input type="hidden" name="action" value="update">
	     	</form>
</body>
</html>