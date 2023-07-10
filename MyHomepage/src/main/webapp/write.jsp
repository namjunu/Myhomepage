<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 - 글쓰기</title>
<link rel="stylesheet" href="common.css">
</head>
<body>
<hr>

<form action="write_proc.jsp" method="get">
	글제목:<input name="title"><br>
	글작성자id:<input name="id"><br>
	글내용:<input name = "text">
	<input type="submit" value="글쓰기">
</form>


</body>
</html>