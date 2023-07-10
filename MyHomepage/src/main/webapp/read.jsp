<%@page import="util.Dto"%>
<%@page import="util.Dao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>게시판 - 글읽기</title>
<link rel="stylesheet" href="common.css">
</head>

<body>
<hr>

<%
String no = request.getParameter("no");
Dao dao = new Dao();
Dto d = dao.read(no);
%>

No : <%=d.no %>
<hr>
Title : <%=d.title %>
<hr>
Id : <%=d.id %>
<hr>
Text : <%=d.text %>
<hr>

<a href="del.jsp?no=<%=d.no%>">삭제</a>
<a href="edit.jsp?no=<%=d.no%>">수정</a>
<a href="list.jsp">리스트로</a>


</body>

</html>