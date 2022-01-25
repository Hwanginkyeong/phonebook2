<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %> 
<%@ page import="com.javaex.vo.PersonVo" %>
<%@ page import="com.javaex.dao.PhoneDao" %>


<%
	List<PersonVo> personList = (List<PersonVo>)request.getAttribute("pList");   
	//기존처럼 폰다오로 꺼내오면 돌아가더라도 따로 뺀 의미가 없음 --> 따라서  list.jsp 는 열리지 않음
	//WEB-INF 안에 넣으면 페이지가 외부에서 접근 할 수 없음 //WebContent 안에 넣으면 외부에서 접근이 가능함 
	//(List<PersonVo>) 를 붙여서 형변환 해야함 
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
		<h1>[Phonebook2]</h1>
		
		<h2>전화번호 리스트</h2>
		
		<p>입력한 정보 내역입니다.</p> 
		
		
		<% 
		for (int i=0; i<personList.size(); i++){
		%>
			
			<table border="1">
					<tr>
							<td>이름(name)</td>
							<td><%=personList.get(i).getName()%></td>
					</tr>
					<tr>
							<td>핸드폰(hp)</td>
							<td><%=personList.get(i).getHp()%></td>
					</tr>
					<tr>
							<td>회사(company)</td>
							<td><%=personList.get(i).getCompany()%></td>
					</tr>
					<tr>
							<td><a href="/phonebook2/pbc?action=updateForm&id=<%=personList.get(i).getPersonId()%>">수정</a></td>
							<td><a href="/phonebook2/pbc?action=delete&id=<%=personList.get(i).getPersonId()%>">삭제</a></td>
				</tr>
			
					
			</table>
			<br>
		<%
		}
		%>
	
	
				<a href="/phonebook2/pbc?action=writeForm">추가번호 등록</a>



</body>
</html>