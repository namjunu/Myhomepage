<%@page import="util.Db"%>
<%@page import="util.Cw"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	String c = "";
	Statement st = null;
	Connection con = null;
	try{
		Class.forName(Db.DB_JDBC_DRIVER_PACKAGE_PATH);
		con = DriverManager.getConnection(Db.DB_URL, Db.DB_ID, Db.DB_PW);
		st = con.createStatement();
		String sql = "select count(*) from PS_MEMBER where PS_ID='"+id+"' and PS_PW='"+pw+"'";
		Cw.wn("sql :" + sql);
		ResultSet rs=st.executeQuery(sql);
		rs.next();
		c = rs.getString("count(*)");
	} catch (Exception e){
		e.printStackTrace();
	} finally{
		if(st!=null){
			st.close();
		}
		if(con!=null){
			con.close();
		}
	}
	if(c.equals("1")){   //로그인성공
		out.println("로그인 성공");
		response.sendRedirect("list.jsp");
	} else{
		out.println("로그인 실패");
	}
%>
</body>
</html>