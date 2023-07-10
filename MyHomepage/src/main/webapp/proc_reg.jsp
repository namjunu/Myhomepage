<%@page import="java.sql.PreparedStatement"%>
<%@page import="util.Cw"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="util.Db"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException"%>
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
	PreparedStatement pstmt = null;
	Connection con = null;
	try {
		Class.forName(Db.DB_JDBC_DRIVER_PACKAGE_PATH);
		con = DriverManager.getConnection(Db.DB_URL, Db.DB_ID, Db.DB_PW);
		String sql = "INSERT INTO PS_MEMBER (ps_id, ps_pw) VALUES (?, ?)";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, pw);
		Cw.wn("sql :" + pstmt.toString());
		int rowsAffected = pstmt.executeUpdate();
		if (rowsAffected > 0) {
			c = "1";
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	if (c.equals("1")) {   // 로그인 성공
		out.println("회원가입 성공");
		response.sendRedirect("index.html");
	} else {
		out.println("회원가입 실패");
	}
%>
</body>
</html>
